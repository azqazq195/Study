package archive.chapter5.section2

fun main() {
    val a = 6
    val b = 0
    val c: Int

    try {
        c = a / b
        // 실행 되지 않음
        println("After")
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        println("finally")
    }
}