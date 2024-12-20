import utils.length
import utils.splitInHalf

object Day11 {

    fun solvePart1(input: Sequence<String>): Long = input.first().solve(25)

    fun solvePart2(input: Sequence<String>): Long = input.first().solve(75)

    private fun String.solve(iterationLimit: Int): Long {
        val cache: MutableMap<Pair<Long, Int>, Long> = mutableMapOf()
        return split(' ').map { it.toLong() }.sumOf { count(cache, it, 0, iterationLimit) }
    }

    private fun count(cache: MutableMap<Pair<Long, Int>, Long>, value: Long, iter: Int, iterLimit: Int): Long {
        if (iter == iterLimit) return 1
        val key = value to iter
        if (key in cache) return cache.getValue(key)
        return value.nextValue().let { (l, r) ->
            if (r != null) count(cache, l, iter + 1, iterLimit) + count(cache, r, iter + 1, iterLimit)
            else count(cache, l, iter + 1, iterLimit)
        }.also { cache[key] = it }
    }

    private fun Long.nextValue(): Pair<Long, Long?> = when {
        this == 0L -> 1L to null
        length() % 2 == 0 -> splitInHalf()
        else -> this * 2024 to null
    }
}