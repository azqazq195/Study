fun main() {
    val str1: String = "Hello"
    val str2 = "World"
    val str3 = "Hello"

    println("str1 === str2 ${str1 === str2}")
    println("str1 === str3 ${str1 === str3}")
    println()

    // 표현식과 $ 기호 사용하여 문자열 출력하기
    val a: Int = 1
    val string = "a is $a"

    println(string)
}