object Day4 {
    private val directions = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1, 1 to 1, 1 to -1, -1 to 1, -1 to -1)
    private val diagonals = arrayOf(1 to 1, 1 to -1, -1 to 1, -1 to -1)
    const val word = "XMAS"

    fun solvePart1(input: Sequence<String>): Int {
        val board = input.toList()
        var count = 0
        for (i in board.indices) {
            for (j in board[i].indices) {
                count += board.isXmas(i, j)
            }
        }
        return count
    }

    fun solvePart2(input: Sequence<String>): Int {
        val board = input.toList()
        var count = 0
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board.isXmas2(i, j)) count += 1
            }
        }
        return count
    }

    private fun List<String>.isXmas(i: Int, j: Int): Int {
        if (get(i)[j] != word.first()) return 0

        var resultCount = 0
        for ((di, dj) in directions) {
            var count = 1
            var nextI = i + di
            var nextJ = j + dj

            while (isCorrectIndex(nextI, nextJ) && get(nextI)[nextJ] == word[count]) {
                count += 1
                nextI += di
                nextJ += dj
                if (count == 4) {
                    resultCount += 1
                    break
                }
            }
        }
        return resultCount
    }

    private fun List<String>.isXmas2(i: Int, j: Int): Boolean {
        if (get(i)[j] != 'A') return false
        if (diagonals.any { (di, dj) -> !isCorrectIndex(i + di, j + dj) }) return false
        val l =
            get(i - 1)[j + 1] == 'M' && get(i + 1)[j - 1] == 'S'
                    || get(i - 1)[j + 1] == 'S' && get(i + 1)[j - 1] == 'M'
        val r =
            get(i + 1)[j + 1] == 'M' && get(i - 1)[j - 1] == 'S'
                    || get(i + 1)[j + 1] == 'S' && get(i - 1)[j - 1] == 'M'
        return l && r
    }

    private fun List<String>.isCorrectIndex(i: Int, j: Int) = i in indices && j in get(i).indices
}