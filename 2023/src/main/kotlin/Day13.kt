object Day13 {

    fun solvePart1(input: Sequence<String>): Int {
        return parseInput(input).sumOf { matrix ->
            checkVertical(matrix) ?: checkHorizontal(matrix) ?: 0
        }
    }

    fun solvePart2(input: Sequence<String>): Int {
        return parseInput(input).sumOf { matrix ->
            checkVertical2(matrix) ?: checkHorizontal2(matrix) ?: 0
        }
    }

    private fun parseInput(input: Sequence<String>): Sequence<List<String>> = sequence {
        val result = mutableListOf<String>()

        input.forEach {
            if (result.isNotEmpty() && it.isEmpty()) {
                yield(result)
                result.clear()
            } else {
                result.add(it)
            }
        }

        if (result.isNotEmpty()) {
            yield(result)
            result.clear()
        }
    }

    private fun checkVertical(matrix: List<String>): Int? {
        for (j in matrix.first().indices) {
            if (matrix.areColumnsEquals(j, j + 1)) {
                var left = j
                var right = j + 1
                val center = left to right
                while (matrix.areColumnsEquals(left - 1, right + 1)) {
                    left -= 1
                    right += 1
                }
                if (left == 0 || right == matrix.first().lastIndex) {
                    return center.first + 1
                }
            }
        }
        return null
    }

    private fun checkHorizontal(matrix: List<String>): Int? {
        for (j in matrix.indices) {
            if (matrix.areRowsEquals(j, j + 1)) {
                var left = j
                var right = j + 1
                val center = left to right
                while (matrix.areRowsEquals(left - 1, right + 1)) {
                    left -= 1
                    right += 1
                }

                if (left == 0 || right == matrix.lastIndex) {
                    return (center.first + 1) * 100
                }
            }
        }
        return null
    }

    private fun List<String>.areRowsEquals(j1: Int, j2: Int): Boolean {
        if (j1 !in indices || j2 !in indices) return false
        return get(j1) == get(j2)
    }

    private fun List<String>.areColumnsEquals(j1: Int, j2: Int): Boolean {
        if (j1 !in get(0).indices || j2 !in get(0).indices) return false

        for (i in indices) {
            if (get(i)[j1] != get(i)[j2]) return false
        }
        return true
    }

    private fun checkVertical2(matrix: List<String>): Int? {
        for (j in matrix.first().indices) {
            var diff = matrix.getColumnsDiffs(j, j + 1)

            if (diff <= 1) {
                var left = j
                var right = j + 1
                val center = left to right
                var nextDiff = matrix.getColumnsDiffs(left - 1, right + 1)

                while (nextDiff <= 1 && diff + nextDiff <= 1) {
                    diff += nextDiff
                    left -= 1
                    right += 1
                    nextDiff = matrix.getColumnsDiffs(left - 1, right + 1)
                }
                if (diff == 1 && (left == 0 || right == matrix.first().lastIndex)) {
                    return center.first + 1
                }
            }
        }
        return null
    }

    private fun checkHorizontal2(matrix: List<String>): Int? {
        for (j in matrix.indices) {
            var diff = matrix.getRowsDiffs(j, j + 1)

            if (diff <= 1) {
                var left = j
                var right = j + 1
                val center = left to right
                var nextDiff = matrix.getRowsDiffs(left - 1, right + 1)

                while (nextDiff <= 1 && diff + nextDiff <= 1) {
                    diff += nextDiff
                    left -= 1
                    right += 1
                    nextDiff = matrix.getRowsDiffs(left - 1, right + 1)
                }
                if (diff == 1 && (left == 0 || right == matrix.lastIndex)) {
                    return (center.first + 1) * 100
                }
            }
        }
        return null
    }

    private fun List<String>.getRowsDiffs(j1: Int, j2: Int): Int {
        if (j1 !in indices || j2 !in indices) return Int.MAX_VALUE
        var diff = 0
        for (i in first().indices) {
            if (get(j1)[i] != get(j2)[i])  {
                diff += 1
            }
        }
        return diff
    }

    private fun List<String>.getColumnsDiffs(j1: Int, j2: Int): Int {
        if (j1 !in get(0).indices || j2 !in get(0).indices) return Int.MAX_VALUE

        var diff = 0
        for (i in indices) {
            if (get(i)[j1] != get(i)[j2])  {
                diff += 1
            }
        }
        return diff
    }

}