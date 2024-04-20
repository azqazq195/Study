package archive.chapter3.section2

fun main() {
    print("Enter the score: ")
    // readLine() 은 문자열로 받아들인다.
    val score = readLine()!!.toDouble()
    var grade: Char = 'F'

    if(score >= 90.0){
        grade = 'A'
    } else if (score in 80.0..89.9){
        grade = 'B'
    } else if (score in 70.0..79.9){
        grade = 'C'
    }

    println("score: $score, grade: $grade")
}