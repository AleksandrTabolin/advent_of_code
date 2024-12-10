object Day10 {

    private val directions = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

    fun solvePart1(input: Sequence<String>): Int {
        return input.parseInput().run { findTrailheads().sumOf { count(0, it) } }
    }

    fun solvePart2(input: Sequence<String>): Int {
        return input.parseInput().run { findTrailheads().sumOf { count2(0, it) } }
    }

    private fun List<List<Int>>.count(num: Int, point: Pair<Int, Int>, visited: MutableSet<Pair<Int, Int>> = mutableSetOf()): Int {
        if (point in visited) return 0
        visited.add(point)

        if (num == 9) return 1
        var result = 0
        val next = num + 1
        directions.forEach { diff ->
            val nextPoint = point.first + diff.first to point.second + diff.second
            if (isInBound(nextPoint) && get(nextPoint.first)[nextPoint.second] == next) {
                result += count(next, nextPoint, visited)
            }
        }
        return result
    }

    private fun List<List<Int>>.count2(num: Int, point: Pair<Int, Int>): Int {
        if (num == 9) return 1
        var result = 0
        val next = num + 1
        directions.forEach { diff ->
            val nextPoint = point.first + diff.first to point.second + diff.second
            if (isInBound(nextPoint) && get(nextPoint.first)[nextPoint.second] == next) {
                result += count2(next, nextPoint)
            }
        }
        return result
    }

    private fun Sequence<String>.parseInput(): List<List<Int>> {
        return map { it.map { if (it == '.') -1 else it.digitToInt() } }.toList()
    }

    private fun List<List<Int>>.findTrailheads(): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()

        for (i in indices) {
            for (j in get(i).indices) {
                if (get(i)[j] == 0) result.add(i to j)
            }
        }

        return result
    }

    private fun List<List<Any>>.isInBound(p: Pair<Int, Int>) = p.first in indices && p.second in get(p.first).indices

}