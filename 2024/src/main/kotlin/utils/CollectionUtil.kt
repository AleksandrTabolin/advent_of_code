package utils

fun <T> Iterable<T>.withoutItemAt(index: Int): List<T> = filterIndexed { i, _ -> i != index }
