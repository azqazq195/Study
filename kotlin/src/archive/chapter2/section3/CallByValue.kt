package archive.chapter2.section3

fun main() {
    val result = archive.chapter2.section3.callByValue(archive.chapter2.section3.lambda())
    println(result)
}

fun callByValue(b: Boolean): Boolean {
    println("callByValue function")
    return b
}

val lambda: () -> Boolean = {
    println("lambda function")
    true
}