package chapter5.section1

fun main() {
    retFunc()
}

inline fun inlinelambda(a: Int, b: Int, out: (Int, Int) -> Unit){
    out(a,b)
}

fun retFunc() {
    println("start of retFunc")
    inlinelambda(1, 3) { a, b ->
        val result = a + b
        if(result > 10) return
        println("result: $result")
    }
    println("end of retFunc")
}