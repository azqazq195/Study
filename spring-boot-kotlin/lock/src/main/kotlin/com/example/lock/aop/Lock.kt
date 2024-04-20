package com.example.lock.aop

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Lock(
    /**
     * 락의 이름
     */
    val key: String = "",

    /**
     * 락 대기 여부
     * 동일한 key 에 대해 락이 걸려있는 경우 Exception 반환 혹은 대기
     */
    val wait: Boolean = true,

    /**
     * class 수준의 락 여부
     * 실행된 method 의 class 도 함께 Lock
     */
    val synchronizedClass: Boolean = true,
)