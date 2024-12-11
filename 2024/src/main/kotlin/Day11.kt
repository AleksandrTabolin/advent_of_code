object Day11 {

    fun solvePart1(input: Sequence<String>): Long {
        return input.first().solve(25)
    }

    fun solvePart2(input: Sequence<String>): Long {
        return input.first().solve(75)
    }

    private fun String.solve(iterationLimit: Int): Long {
        val cache: MutableMap<Pair<Long, Int>, Long> = mutableMapOf()
        return split(' ').map { it.toLong() }.sumOf { count(cache, it, 0, iterationLimit) }
    }

    private fun count(cache: MutableMap<Pair<Long, Int>, Long>, value: Long, iter: Int, iterLimit: Int): Long {
        if (iter == iterLimit) return 1
        val key = value to iter
        if (key in cache) return cache.getValue(key)
        return value.nextValue().let { (l, r) ->
            if (r != null) {
                count(cache, l, iter + 1, iterLimit) + count(cache, r, iter + 1, iterLimit)
            } else {
                count(cache, l, iter + 1, iterLimit)
            }
        }.also { cache[key] = it }
    }

    private fun Long.nextValue(): Pair<Long, Long?> = when {
        this == 0L -> 1L to null
        length() % 2 == 0 -> split()
        else -> this * 2024 to null
    }

    private fun Long.split(): Pair<Long, Long> {
        val m = 10L.pow(length() / 2)
        return this / m to this % m
    }

    private fun Long.length(): Int {
        var value = this
        var size = 0
        while (value > 0) {
            value /= 10
            size += 1
        }
        return size
    }

    private fun Long.pow(x: Int): Long = (2..x).fold(this) { r, _ -> r * this }
}