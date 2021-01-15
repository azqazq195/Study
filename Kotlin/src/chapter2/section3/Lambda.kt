package chapter2.section3

fun main() {
    val result: Int

    // val multi = { a: Int, b: Int -> a * b }
    val multi: (a: Int, b: Int) -> Int = { a, b -> a * b }

    result = multi(10, 20)
    println(result)
}