import utils.Direction
import utils.indexesOf
import utils.parseDirections

object Day15 {

    fun solvePart1(input: Sequence<String>): Int {
        val (field, moves) = input.parseInput()
        moves.fold(field.indexOfSubMarine()) { pos, m -> field.moveP1(pos, m) }
        return field.count('O')
    }

    fun solvePart2(input: Sequence<String>): Int {
        val (field, moves) = input.parseInput(p2 = true)
        moves.fold(field.indexOfSubMarine()) { pos, m -> field.moveP2(pos, m) }
        return field.count('[')
    }

    private fun List<CharArray>.moveP1(pos: Pair<Int, Int>, dir: Direction): Pair<Int, Int> {
        return if (simpleMove(pos, dir)) pos.first + dir.di to pos.second + dir.dj else pos
    }

    private fun List<CharArray>.moveP2(pos: Pair<Int, Int>, dir: Direction): Pair<Int, Int> = when {
        dir.isHorizontal -> if (simpleMove(pos, dir)) pos.first + dir.di to pos.second + dir.dj else pos
        isVerticalMovePossible(pos, dir) -> {
            moveVertical(pos, dir)
            pos.first + dir.di to pos.second + dir.dj
        }

        else -> pos
    }

    private fun List<CharArray>.simpleMove(pos: Pair<Int, Int>, dir: Direction): Boolean {
        val nextI = pos.first + dir.di
        val nextJ = pos.second + dir.dj
        return when (get(pos.first)[pos.second]) {
            '.' -> true
            '#' -> false
            else -> simpleMove(nextI to nextJ, dir).also {
                if (it) swap(pos, nextI to nextJ)
            }
        }
    }

    private fun List<CharArray>.isVerticalMovePossible(pos: Pair<Int, Int>, dir: Direction): Boolean {
        val nextI = pos.first + dir.di
        val nextJ = pos.second + dir.dj
        return when (val curr = get(pos.first)[pos.second]) {
            '.' -> true
            '#' -> false
            '@' -> isVerticalMovePossible(nextI to nextJ, dir)
            '[' -> isVerticalMovePossible(nextI to nextJ, dir) && isVerticalMovePossible(nextI to nextJ + 1, dir)
            ']' -> isVerticalMovePossible(nextI to nextJ, dir) && isVerticalMovePossible(nextI to nextJ - 1, dir)
            else -> throw IllegalStateException("Unknown $curr")
        }
    }

    private fun List<CharArray>.moveVertical(pos: Pair<Int, Int>, dir: Direction) {
        val nextI = pos.first + dir.di
        val nextJ = pos.second + dir.dj
        when (get(pos.first)[pos.second]) {
            '@' -> {
                moveVertical(nextI to nextJ, dir)
                swap(pos, nextI to nextJ)
            }

            '[' -> {
                moveVertical(nextI to nextJ, dir)
                moveVertical(nextI to nextJ + 1, dir)
                swap(pos, nextI to nextJ)
                swap(pos.first to pos.second + 1, nextI to nextJ + 1)
            }

            ']' -> {
                moveVertical(nextI to nextJ, dir)
                moveVertical(nextI to nextJ - 1, dir)
                swap(pos, nextI to nextJ)
                swap(pos.first to pos.second - 1, nextI to nextJ - 1)
            }
        }
    }

    private fun List<CharArray>.swap(p1: Pair<Int, Int>, p2: Pair<Int, Int>) {
        val temp = get(p1.first)[p1.second]
        get(p1.first)[p1.second] = get(p2.first)[p2.second]
        get(p2.first)[p2.second] = temp
    }

    private fun List<CharArray>.indexOfSubMarine() = indexesOf('@').first()

    private fun List<CharArray>.count(ch: Char): Int = indexesOf(ch).sumOf{ (i, j) -> 100 * i + j }

    private fun Sequence<String>.parseInput(p2: Boolean = false): Pair<List<CharArray>, List<Direction>> {
        var takeFiled = true
        return fold(mutableListOf<CharArray>() to mutableListOf<Direction>()) { acc, line ->
            when {
                line.isBlank() -> takeFiled = false
                takeFiled -> acc.first.add(line.update(p2).toCharArray())
                else -> acc.second.addAll(line.parseDirections())
            }
            acc
        }
    }

    private fun String.update(p2: Boolean = false): String {
        if (!p2) return this
        return replace("#", "##")
            .replace("O", "[]")
            .replace(".", "..")
            .replace("@", "@.")
    }
}
