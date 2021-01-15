package chapter1.section3

fun main() {
    val a: Int = 128
    val b = a

    val c: Int? = a
    val d: Int? = a
    val e: Int? = c

    println(c == d)
    println(c === d)
    println(c === e)

    // true
    // false
    // true

    // e => c => a
    //      d => a
    // c 와 d 는 Int? 형 객체로 다른 공간
}