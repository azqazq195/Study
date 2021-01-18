package chapter2.section4

inline fun shortFunc(a: Int, crossinline out: (Int) -> Unit) {
    println("Hello")
    out(a)
    println("Goodbye")
}

fun main() {
    shortFunc(3) {
        println("a: $it")
        // return
    }
}

