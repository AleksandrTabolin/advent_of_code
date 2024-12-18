package utils

fun <T> Iterable<T>.withoutItemAt(index: Int): List<T> = filterIndexed { i, _ -> i != index }

fun List<CharArray>.indexesOf(ch: Char): Sequence<Pair<Int, Int>> = sequence {
    for (i in indices) {
        for (j in get(i).indices) {
            if (get(i)[j] == ch) yield(i to j)
        }
    }
}


fun String.takeDigits() = filter { it.isDigit() }.toInt()
fun String.takeIntList(separator: Char = ',') =
    filter { it.isDigit() || it == separator }.split(separator).map { it.toInt() }
