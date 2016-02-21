package sesma.eu.kotlinweather.domain.commands

interface Command<T> {
    fun execute(): T
}
