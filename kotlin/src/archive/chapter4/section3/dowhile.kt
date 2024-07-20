package archive.chapter4.section3

fun main() {
    do {
        print("Enter the number: ")
        val input = readLine()!!.toInt()

        // until -> input 전까지
        for (i in 0 until input) {
            for (j in 0..(input - 1)) {
                print((i + j) % input + 1)
            }
            println()
        }
    } while (input != 0)
}