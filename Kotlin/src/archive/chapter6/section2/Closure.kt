package archive.chapter6.section2

fun main() {
    val calc = Calc()
    // 외부 변수
    var result = 0
    // 클로저
    calc.addNum(2, 3) { x, y -> result = x + y }
    // 값을 유지하여 5가 출력
    println(result)

    filteredNames(3)
}

fun filteredNames(length: Int) {
    val names = arrayListOf("Kim", "Hong", "Go", "Hwang", "Jeon")
    val filterResult = names.filter {
        it.length == length // 바깥의 length 에 접근
    }
    println(filterResult)
}

class Calc {
    fun addNum(a: Int, b: Int, add: (Int, Int) -> Unit) {
        // 람다식 add 에는 반환값이 없음
        add(a, b)
    }
}