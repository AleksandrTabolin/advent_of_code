object Day14 {

    fun solvePart1(input: Sequence<String>): Int {
        return input.toList().map { it.toMutableList() }.apply { tiltNorth() }.countRocks()
    }

    fun solvePart2(input: Sequence<String>): Int {
        val board = input.toList().map { it.toMutableList() }

        val acc = mutableListOf<Int>()

        val n = 1_000_000_000

        for (i in 0..n) {
            board.spin()
            acc.add(board.countRocks())

            val repeatable = acc.lastRepeatable() ?: continue

            val cycle = acc.subList(repeatable.first, repeatable.second)
            val firstIndex = acc.firstIndexOf(cycle)
            val pos = (n - firstIndex - 1) % cycle.size

            return cycle[pos]
        }

        return 0
    }

    private fun List<MutableList<Char>>.spin() {
        tiltNorth()
        tiltWest()
        tiltSouth()
        tiltEast()
    }

    private fun List<Int>.firstIndexOf(list: List<Int>): Int {
        var j = 0
        for (i in 0..size) {
            if (get(i) == list[j]) {
                j += 1
                if (j == list.size) return i - list.size + 1
            } else {
                j = 0
            }
        }
        return -1
    }

    private fun List<Int>.lastRepeatable(): Pair<Int, Int>? {
        var pos = 1
        for (x in (size / 2 + 1 until size - 1).reversed()) {
            if (subList(x, size) == subList(2 * x - size, x)) {
                pos = x
                break
            }
        }

        return if (pos > 1) pos to size else null
    }

    private fun List<MutableList<Char>>.tiltNorth() {
        val board = this
        for (j in board.first().indices) {
            var vacantPosition = 0
            for (i in board.indices) {
                when (board[i][j]) {
                    '#' -> vacantPosition = i + 1
                    'O' -> {
                        if (i != vacantPosition) board[i][j] = '.'
                        board[vacantPosition][j] = 'O'
                        vacantPosition += 1
                    }
                }
            }
        }
    }

    private fun List<MutableList<Char>>.tiltWest() {
        val board = this
        for (i in board.indices) {
            var vacantPosition = 0
            for (j in board.first().indices) {
                when (board[i][j]) {
                    '#' -> vacantPosition = j + 1
                    'O' -> {
                        if (j != vacantPosition) board[i][j] = '.'
                        board[i][vacantPosition] = 'O'
                        vacantPosition += 1
                    }
                }
            }
        }
    }

    private fun List<MutableList<Char>>.tiltSouth() {
        val board = this

        for (j in board.first().indices) {
            var vacantPosition = board.first().size - 1
            for (i in board.indices.reversed()) {
                when (board[i][j]) {
                    '#' -> vacantPosition = i - 1
                    'O' -> {
                        if (i != vacantPosition) board[i][j] = '.'
                        board[vacantPosition][j] = 'O'
                        vacantPosition -= 1
                    }
                }
            }
        }
    }

    private fun List<MutableList<Char>>.tiltEast() {
        val board = this
        for (i in board.indices) {
            var vacantPosition = size - 1
            for (j in board.first().indices.reversed()) {
                when (board[i][j]) {
                    '#' -> vacantPosition = j - 1
                    'O' -> {
                        if (j != vacantPosition) board[i][j] = '.'
                        board[i][vacantPosition] = 'O'
                        vacantPosition -= 1
                    }
                }
            }
        }
    }

    private fun List<List<Char>>.countRocks(): Int {
        val board = this

        var result = 0
        for (j in first().indices) {
            for (i in indices) {
                if (board[i][j] == 'O') {
                    result += (size - i)
                }
            }
        }

        return result
    }


}