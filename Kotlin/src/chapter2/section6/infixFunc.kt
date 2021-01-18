package chapter2.section6

fun main() {
    val num = 3
    // val str = num.strPlus("Kotlin")
    val str = num strPlus "kotlin" // 중위 표현 법

    println("str: $str")
}

infix fun Int.strPlus(x: String): String {
    return "$x version $this"
}