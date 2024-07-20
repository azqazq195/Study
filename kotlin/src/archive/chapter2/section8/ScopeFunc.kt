package archive.chapter2.section8

var global = 10

fun main() {

    fun localFunc1(){
        println("localFunc1")
    }

    global = 15
    val local = 15
    println("global $global")
    userFunc()
    println("final - global: $global, local: $local")
}

fun userFunc() {
    global = 20
    val local = 20
    println("userFunc - global: $global, local: $local")
}