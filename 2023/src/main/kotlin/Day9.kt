object Day9 {

    fun solvePart1(input: Sequence<String>): Int {
        return input
            .map { line -> parseInput(line).collectDiffs() }
            .map { diffs -> diffs.foldRight(0) { diff, acc -> acc + diff.last() } }
            .sum()
    }

    fun solvePart2(input: Sequence<String>): Int {
        return input
            .map { line -> parseInput(line).collectDiffs() }
            .map { diffs -> diffs.foldRight(0) { diff, acc -> (diff.first() - acc) } }
            .sum()
    }

    private fun parseInput(line: String): List<Int> = line.split(' ').map { it.toInt() }

    private fun List<Int>.collectDiffs(): List<List<Int>> {
        return mutableListOf(this).apply {
            while (last().any { it != 0 }) add(last().diff().toList())
        }
    }

    private fun List<Int>.diff(): Sequence<Int> = sequence {
        for (i in 0 until size - 1) {
            yield(get(i + 1) - get(i))
        }
    }
}