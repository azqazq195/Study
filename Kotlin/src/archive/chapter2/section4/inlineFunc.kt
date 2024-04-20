package archive.chapter2.section4

inline fun shortFunc(a: Int, crossinline out: (Int) -> Unit) {
    println("Hello")
    out(a)
    println("Goodbye")
}

fun main() {
    archive.chapter2.section4.shortFunc(3) {
        println("a: $it")
        // return
    }
}

