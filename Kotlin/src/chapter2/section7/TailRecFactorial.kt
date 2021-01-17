package chapter2.section7

fun main() {
    val number = 5
    println("Factorial: $number -> ${factorial(number)}")
}

// 스택을 사용하지 않음
tailrec fun factorial(n: Int, run: Int = 1): Long {
    return if (n == 1) run.toLong() else factorial(n - 1, run * n)
}