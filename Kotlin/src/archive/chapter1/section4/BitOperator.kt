package archive.chapter1.section4

fun main() {
    val x = 4               // 4(10), 0000_0100
    val y = 0b0000_1010     // 5 (10)
    val z = 0x0f            // 0b0000_1111(2), 15(10)

    println("x shl 2 -> ${x.shl(2)}")   // 16(10), 0001_0000(2)
    println("x inv -> ${x.inv()}")              // -5(10), 111....1011(2)
}