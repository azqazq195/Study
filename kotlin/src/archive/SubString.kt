package archive

fun main(args: Array<String>) {
    val address1 = "asd asd asd asd 741"
    println(address1)
    val address2 = address1.substring(0, address1.lastIndexOf(" "))
    println(address2)
}