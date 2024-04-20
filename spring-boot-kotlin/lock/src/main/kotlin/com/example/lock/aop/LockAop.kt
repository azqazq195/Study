package com.example.lock.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Aspect
@Component
class LockAop {

    private val locks = ConcurrentHashMap<String, ReentrantLock>()
    private val parser = SpelExpressionParser()

    @Around("@annotation(lock)")
    fun lock(joinPoint: ProceedingJoinPoint, lock: Lock): Any? {
        val methodKey = constructMethodKey(joinPoint, lock)
        val methodLock = getLock(methodKey)

        return if (lock.synchronizedClass) {
            val classKey = getClassName(joinPoint)
            val classLock = getLock(classKey)
            classLock.withLock {
                proceedWithLock(joinPoint, methodLock, lock)
            }
        } else {
            proceedWithLock(joinPoint, methodLock, lock)
        }

    }

    private fun proceedWithLock(
        joinPoint: ProceedingJoinPoint,
        methodLock: ReentrantLock,
        lock: Lock
    ): Any? {
        if (!lock.wait && methodLock.isLocked) {
            throw RuntimeException("The lock has been locked.")
        } else {
            methodLock.withLock {
                return joinPoint.proceed()
            }
        }
    }

    private fun getLock(key: String): ReentrantLock =
        locks.computeIfAbsent(key) { ReentrantLock() }

    private fun getClassName(joinPoint: ProceedingJoinPoint): String {
        val className = joinPoint.target.javaClass.simpleName
        return className
    }

    private fun constructMethodKey(joinPoint: ProceedingJoinPoint, lock: Lock): String {
        val methodSignature = joinPoint.signature as MethodSignature
        val method = methodSignature.method
        val parameters = method.parameters
        val arguments = joinPoint.args

        val context = StandardEvaluationContext()
        for (i in arguments.indices) {
            context.setVariable(parameters[i].name, arguments[i])
        }

        val className = joinPoint.target.javaClass.simpleName
        val methodName = joinPoint.signature.name
        val key = parser.parseExpression(lock.key).getValue(context, String::class.java)
            ?: throw RuntimeException("Failed to parse expression")

        return "$className:$methodName:$key"
    }
}