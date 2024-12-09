object Day9 {

    private const val EMPTY = -1L

    fun solvePart1(input: Sequence<String>): Long {
        val points = input.first().collectPoints()

        var l = 0
        var r = points.lastIndex
        while (r > l) {
            if (points[l] == EMPTY && points[r] != EMPTY) {
                points[l] = points[r]
                points[r] = EMPTY
                r -= 1
                l += 1
            } else if (points[l] == EMPTY) {
                r -= 1
            } else if (points[r] != EMPTY) {
                l += 1
            } else {
                r -= 1
                l += 1
            }
        }
        return points.asSequence().filter { it != -1L }.mapIndexed { i, v -> i * v }.sum()
    }

    fun solvePart2(input: Sequence<String>): Long {
        val intervals = input.first().collectIntervals()
        var lastId = intervals.last { it.first != EMPTY }.first

        while (lastId >= 0) {
            val r = intervals.indexOfLast { it.first == lastId }
            assert(r > -1)

            var l = 0
            while (r > l && (intervals[l].first != EMPTY || intervals[l].second < intervals[r].second)) {
                l += 1
            }

            if (r > l) {
                if (intervals[l].second == intervals[r].second) {
                    intervals[l] = intervals[r]
                    intervals[r] = intervals[r].copy(first = EMPTY)
                } else {
                    val remain = intervals[l].second - intervals[r].second
                    intervals[l] = intervals[r]
                    intervals[r] = intervals[r].copy(first = EMPTY)
                    intervals.add(l + 1, EMPTY to remain)
                }
            }
            lastId -= 1
        }

        var size = 0
        var result1 = 0L
        for (item in intervals) {
            val newSize = size + item.second
            if (item.first != EMPTY) {
                for (j in size until newSize) {
                    result1 += item.first * j
                }
            }
            size = newSize
        }

        return result1
    }

    private fun String.collectPoints(): MutableList<Long> {
        var id = 0L
        var isData = true
        return fold(mutableListOf()) { acc, ch ->
            acc.apply {
                repeat(ch.digitToInt()) { add(if (isData) id else EMPTY) }
                if (isData) id += 1
                isData = !isData
            }
        }
    }

    private fun String.collectIntervals(): MutableList<Pair<Long, Int>> {
        var id = 0L
        var isData = true
        return fold(mutableListOf()) { acc, ch ->
            val size = ch.digitToInt()
            acc.apply {
                add(if (isData) id to size else -1L to size)
                if (isData) id += 1
                isData = !isData
            }
        }
    }
}