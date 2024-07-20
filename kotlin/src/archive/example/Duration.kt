package archive.example

fun main() {
    val timeString = "1:1:800"
    val seconds = timeString.toSecond()

    println(seconds)
}

fun String.toSecond(): Int {
    val factors = arrayOf(60000, 1000, 1)
    var value = 0
    this.split(":").forEachIndexed { i, s -> value += factors[i] * s.toInt() }
    return value
}