fun main(args: Array<String>) {
    val day = mutableSetOf("Mon", "Tue", "Wed")
    println("day = $day")
    println("day subtract setOf('Tue') ${day subtract setOf("Tue")}")
    println()

    day.add("Thu")
    println("day.add('Thu')")
    println("day = $day")
    println()

    val day2 = day
    println("val day2 = day")
    println("day2 = $day2")
    println()
    
    day.remove("Thu")
    println("day.remove('Thu')")
    println("day = $day")
    println("day2 = $day2")
    println()

    day.add("Thu")
    println("day.add('Thu')")
    println("day = $day")
    println("day2 = $day2")
    println()

    val day3 = mutableSetOf<String>()
    day3.addAll(day)
    println("day = $day")
    println("day2 = $day2")
    println("day3 = $day3")
    println()

    day.remove("Thu")
    println("day.remove('Thu')")
    println("day = $day")
    println("day2 = $day2")
    println("day3 = $day3")
    println()

}