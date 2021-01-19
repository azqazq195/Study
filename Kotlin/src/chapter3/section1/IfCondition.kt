package chapter3.section1

fun main() {
    val a = 12
    val b = 7

    // 블록과 함께 사용
    val max = if (a > b) {
        println("a $a")
        a // 마지막 식인 a 가 반환되어 max 에 할당
    } else {
        println("b $b")
        b // 마지막 식인 b 가 반환되어 max 에 할당
    }

    println("max $max")
}