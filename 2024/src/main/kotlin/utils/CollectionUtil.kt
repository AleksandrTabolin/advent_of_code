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

fun String.takeDigitsToLong(): Long {
    var pos = 0L
    var result = 0L
    for (i in lastIndex downTo 0) {
        if (get(i).isDigit()) {
            result += get(i).digitToInt() * 10L.pow(pos)
            pos += 1L
        }
    }
    return result
}

fun String.takeDigitsToInt(): Int {
    var pos = 0
    var result = 0
    for (i in lastIndex downTo 0) {
        if (get(i).isDigit()) {
            result += get(i).digitToInt() * 10.pow(pos)
            pos += 1
        }
    }
    return result
}


fun String.takeIntList(separator: Char = ',') =
    filter { it.isDigit() || it == separator }.split(separator).map { it.toInt() }


inline fun <K, V> MutableMap<K, V>.withCache(input: K, block: (K) -> V): V {
    return if (input in this) getValue(input) else block.invoke(input).also { put(input, it) }
}

fun <T> combinations(left: Iterable<T>, right: Iterable<T>): Sequence<Pair<T, T>> = sequence {
    left.forEach { l -> right.forEach { r -> yield(l to r)  }  }
}
