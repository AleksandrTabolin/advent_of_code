object Day10 {

    private val directions = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

    fun solvePart1(input: Sequence<String>): Int {
        return input.parseInput().run {
            findTrailheads().sumOf {
                val visited = mutableSetOf<Pair<Int, Int>>()
                count(0, it) { point -> !visited.add(point)}
            }
        }
    }

    fun solvePart2(input: Sequence<String>): Int {
        return input.parseInput().run { findTrailheads().sumOf { count(0, it) { false } } }
    }

    private fun List<List<Int>>.count(
        num: Int, point: Pair<Int, Int>,
        isVisited: (Pair<Int, Int>) -> Boolean
    ): Int = when {
        isVisited.invoke(point) -> 0
        num == 9 -> 1
        else -> directions
            .asSequence()
            .map { point.first + it.first to point.second + it.second }
            .filter { isInBound(it) && get(it.first)[it.second] == num + 1 }
            .sumOf { count(num + 1, it, isVisited) }
    }

    private fun Sequence<String>.parseInput(): List<List<Int>> {
        return map { line -> line.map { it.digitToInt() } }.toList()
    }

    private fun List<List<Int>>.findTrailheads(): List<Pair<Int, Int>> {
        return foldIndexed(mutableListOf()) { i, acc, row ->
            row.indices.forEach { j -> if (get(i)[j] == 0) acc.add(i to j) }
            acc
        }
    }

    private fun List<List<Any>>.isInBound(p: Pair<Int, Int>) = p.first in indices && p.second in get(p.first).indices
}