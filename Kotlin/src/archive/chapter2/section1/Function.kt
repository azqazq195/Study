package archive.chapter2.section1

fun sum1(a: Int, b: Int): Int {
    return a + b
} // 최상위 함수

fun sum2(a: Int, b: Int) = a + b

fun sum3(a: Int = 2, b: Int = 5) = a + b
// default 값을 지정할 수 있다.
// b 에 값이 전달되지 않을 경우 5가 대입되게 된다.

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int) = if (a > b) a else b

fun outfunc(name: String): Unit { // Unit return 이 없는 함수
    println("Name: $name")
    return Unit // 생략 가능 java 의 void 함수
}

fun outfunc2(name: String) = println("Name: $name")


fun main() {    // 최상위 (Top-level) 함수
    val result1 = sum1(2, 3)
    println(result1)

    fun sum4(a: Int, b: Int): Int {
        return a + b
    } // 지역 함수

    val result2 = sum3(2, 3)
    println(result1)

    val a = 3
    val b = 5
    val result3 = max(a, b)
    println(result3)

    outfunc("moseoh")

    val result4 = sum3(3)
    println(result4)

    // 인자가 여러개일 경우 지정해서 넣을 수 있다.
    val result5 = sum3(b = 3)
    println(result5)
}

// 최상위 함수는 어디에 위치해도 실행이 된다.
// 지역함수는 함수 생성후 실행하여야 한다.