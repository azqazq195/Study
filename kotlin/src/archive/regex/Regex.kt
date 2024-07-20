package archive.regex

fun main() {
    val string = "azqazq195@gmail.com"
    val regexString = """^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$"""
    val regex = Regex(regexString)

    println(string.matches(regex))
}

