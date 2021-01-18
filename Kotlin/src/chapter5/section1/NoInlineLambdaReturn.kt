package chapter5.section1

fun main() {
    retFunc2()
}

inline fun inlinelambda2(a: Int, b: Int, out: (Int, Int) -> Unit){
    out(a,b)
}

fun retFunc2() {
    println("start of retFunc")
    inlinelambda2(13, 3) lit@{ a, b ->
        val result = a + b
        if(result > 10) return@lit // 라벨을 사용한 블록의 끝부분으로 반환
        println("result: $result")
    }
    println("end of retFunc")
}