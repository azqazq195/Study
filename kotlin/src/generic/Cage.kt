package generic

fun main() {
    val cage = Cage()
    cage.put(Carp("잉어"))
    val carp: Carp = cage.getFirst() as? Carp ?: throw IllegalArgumentException()

    val cage2 = Cage2<Fish>()
    cage2.put(Carp("잉어"))
    cage2.put(GoldFish("금붕어"))
}


class Cage(
    private val animals: MutableList<Animal> = mutableListOf()
) {
    fun getFirst(): Animal {
        return this.animals.first()
    }

    fun put(animal: Animal) {
        this.animals.add((animal))
    }

    fun putFrom(cage: Cage) {
        this.animals.addAll(cage.animals)
    }
}

class Cage2<T>(
    private val animals: MutableList<T> = mutableListOf()
) {
    fun getFirst(): T {
        return this.animals.first()
    }

    fun put(animal: T) {
        this.animals.add((animal))
    }

    fun putFrom(cage: Cage2<out T>) {
        cage.getFirst()
        this.animals.addAll(cage.animals)
    }
}