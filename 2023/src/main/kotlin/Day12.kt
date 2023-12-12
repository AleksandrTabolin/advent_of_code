object Day12 {


    fun solvePart1(input: Sequence<String>): Long {
        return input.map(::parseLine).map(::countArrangement).sum()
    }

    fun solvePart2(input: Sequence<String>): Long {
        return input.map(::parseLine).map(::updateLine).map(::countArrangement).sum()
    }

    private fun parseLine(line: String): Pair<String, List<Int>> {
        val (row, right) = line.split(' ')
        val groups = right.split(',').map { it.toInt() }
        return row to groups
    }

    private fun updateLine(input: Pair<String, List<Int>>): Pair<String, List<Int>> {
        val (row, groups) = input

        var resultRow = ""
        val resultGroups = mutableListOf<Int>()

        repeat(5) {
            resultRow += row
            resultGroups += groups

            if (it != 4) {
                resultRow += "?"
            }
        }
        return resultRow to resultGroups
    }

    private var memo = mutableMapOf<Pair<String, List<Int>>, Long>()
    private fun countArrangement(input: Pair<String, List<Int>>): Long {
        val (row, groups) = input
        if (!row.contains('#') && groups.isEmpty()) return 1L
        if (row.isEmpty() || groups.isEmpty()) return 0L

        if (input in memo) return memo.getValue(input)

        return when (row.first()) {
            '.' -> handleOperational(row, groups)
            '#' -> handleDamaged(row, groups)
            else -> handleDamaged(row, groups) + handleOperational(row, groups)
        }.also { result ->
            memo[input] = result
        }
    }

    private fun handleOperational(row: String, groups: List<Int>): Long {
        return countArrangement(row.drop(1) to groups)
    }

    private fun handleDamaged(row: String, groups: List<Int>): Long {
        return if (check(row, groups.first())) {
            countArrangement(row.drop(groups.first() + 1) to groups.drop(1))
        } else {
            0
        }
    }

    private fun check(row: String, group: Int): Boolean {
        if (group > row.length) return false

        for (i in 0 until group) {
            if (row[i] == '.') return false
            if (i == group - 1 && i != row.lastIndex && row[i + 1] == '#') return false
        }
        return true
    }
}
