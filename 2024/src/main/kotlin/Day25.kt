import utils.combinations

object Day25 {

    fun solvePart1(input: Sequence<String>): Int {
        return input.parseInput().collect().let { (locks, keys) ->
            combinations(locks, keys).count { (l, k) -> l.isFit(k) }
        }
    }

    fun solvePart2(input: Sequence<String>): Int {
        return -1
    }

    private fun List<String>.isFit(key: List<String>): Boolean {
        for (i in indices) for (j in get(i).indices) if (get(i)[j] == '#' && key[i][j] == '#') return false
        return true
    }

    private fun List<List<String>>.collect(): Pair<List<List<String>>, List<List<String>>> {
        return groupBy { it.first().all { ch -> ch == '#' } }.let { it.getValue(true) to it.getValue(false) }
    }

    private fun Sequence<String>.parseInput(): List<List<String>> {
        var curr = mutableListOf<String>()
        val result = mutableListOf<List<String>>()
        forEach { line ->
            if (line.isBlank()) {
                result.add(curr)
                curr = mutableListOf()
            } else {
                curr.add(line)
            }
        }
        result.add(curr)
        return result
    }
}