package section2

fun main() {
//    var str1: String
//    str1 = null

    var str1: String?
    str1 = null
    println("str1: $str1")
    println()

    // println("length: ${str1.length}")
    // str1 은 String? 으로 선언되어 널일 가능성이 있기 떄문에 불가능

    println("length: ${str1?.length}")
    println()
    // ?. 세이프콜 => 혹시라도 널이면 뒷부분 length 를 실행하지 않는다. 결과는 null 이 나옴)

    str1 = "Hello"
    println("length: ${str1?.length}")
    println()

    //str1 = null
    println("length: ${str1!!.length}")
    // !!. 넌널(nonNull) 단정 기호 => 앞의 변수가 널일리 없다. 즉 널이더라도 실행이 된다(오류 발생).
    // 넌널 단정 기호는 쓰지 않는 것이 좋다.

    str1 = null
    // val len = if(str1 != null) str1.length else -1
    // elvis 연산자
    val len = str1?.length ?: -1
    // 널이 아니면 길이가, 널이면 -1 이 나오게 된다.

    println("str1: $str1, length: $len")
}