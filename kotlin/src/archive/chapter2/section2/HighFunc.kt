package archive.chapter2.section2

fun highFunc(a: Int, b: Int, sum: (Int, Int) -> Int): Int {
    return sum(a, b)
}

fun main() {
    // 뒤로 뺴게 되면 람다식을 밖에 쓸 수 있다.
    val result = highFunc(1, 3) { x, y -> x + y }
    println(result)
}