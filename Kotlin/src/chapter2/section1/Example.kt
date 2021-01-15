package chapter2.section1

fun main() {
    val result = avgFunc(5f, 100f, 90f, 80f)
    println("avg result: $result")
}

fun avgFunc(initial:Float, vararg numbers: Float): Double {
    var result = 0f
    for(num in numbers){
        result += num
    }
    println("result: $result, numbers.size: ${numbers.size}")
    val avg = result / numbers.size + initial
    return avg.toDouble()
}