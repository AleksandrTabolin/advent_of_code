object Day13 {

    fun solvePart1(input: Sequence<String>): Long {
        return input.parseInput().sumOf { it.countTokens() }
    }

    fun solvePart2(input: Sequence<String>): Long {
        return input.parseInput().sumOf { it.countTokens(10000000000000) }
    }

    // Fucking math
    // If we consider two lines a1x + b1y + c1 = 0 and a2x + b2y + c2 = 0 the point of intersection of these two lines is given by:
    // Point of Intersection (x, y) = ((b1×c2 − b2×c1)/(a1×b2 − a2×b1), (c1×a2 − c2×a1)/(a1×b2 − a2×b1))
    private fun Game.countTokens(addition: Long = 0): Long {
        val prize = prize.first + addition to prize.second + addition
        val x = -1 * (b.first * prize.second - b.second * prize.first) / (a.first * b.second - b.first * a.second)
        val y = -1 * (prize.first * a.second - prize.second * a.first) / (a.first * b.second - a.second * b.first)
        return if (x >= 0 && y >= 0 && x * a.first + y * b.first == prize.first) x * 3 + y else 0
    }

    private fun Sequence<String>.parseInput(): List<Game> {
        return filter { it.isNotBlank() }.chunked(3).map {
            Game(a = it[0].parse(), b = it[1].parse(), prize = it[2].parse())
        }.toList()
    }

    private fun String.parse(): Pair<Long, Long> {
        return filter { it.isDigit() || it == ',' }.split(',').let { it.first().toLong() to it.last().toLong() }
    }

    private data class Game(val a: Pair<Long, Long>, val b: Pair<Long, Long>, val prize: Pair<Long, Long>)
}