object Day9 {

    fun solvePart1(input: Sequence<String>): Int {
        return input
            .map(::parseInput)
            .map(::collectDiffs)
            .map { diffs -> diffs.reversed().fold(0) { acc, diff -> acc + diff.last() } }
            .sum()
    }

    fun solvePart2(input: Sequence<String>): Int {
        return input
            .map(::parseInput)
            .map(::collectDiffs)
            .map { diffs -> diffs.reversed().fold(0) { acc, diff -> (diff.first() - acc) } }
            .sum()
    }

    private fun parseInput(line: String): List<Int> = line.split(' ').map { it.toInt() }

    private fun collectDiffs(numbers: List<Int>): List<List<Int>> {
        return mutableListOf(numbers).apply {
            while (true) {
                val diff = last()
                val newDiff = mutableListOf<Int>()
                var prev = diff.first()
                for (i in 1 until diff.size) {
                    newDiff.add(diff[i] - prev)
                    prev = diff[i]
                }
                add(newDiff)
                if (newDiff.all { it == 0 }) break
            }
        }
    }
}