import utils.Direction
import utils.parseDirections

object Day15 {

    fun solvePart1(input: Sequence<String>): Int {
        val (field, moves) = input.parseInput()
        moves.scan(field.indexOfSubMarine()) { pos, m -> field.moveP1(pos, m) }
        return field.count('O')
    }

    fun solvePart2(input: Sequence<String>): Int {
        val (field, moves) = input.parseInput(p2 = true)
        moves.scan(field.indexOfSubMarine()) { pos, m -> field.moveP2(pos, m) }
        return field.count('[')
    }

    private fun List<CharArray>.moveP1(pos: Pair<Int, Int>, dir: Direction): Pair<Int, Int> {
        var spacePos = freeSpacePos(pos, dir) ?: return pos
        while (pos != spacePos) {
            val prev = (spacePos.first - dir.di) to (spacePos.second - dir.dj)
            swap(prev, spacePos)
            spacePos = prev
        }
        return pos.first + dir.di to pos.second + dir.dj
    }

    private fun List<CharArray>.moveP2(pos: Pair<Int, Int>, dir: Direction): Pair<Int, Int> = when {
        dir.isHorizontal -> moveP1(pos, dir)
        isVerticalMovePossible(pos, dir) -> {
            moveVertical(pos, dir)
            pos.first + dir.di to pos.second + dir.dj
        }

        else -> pos
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


    private fun List<CharArray>.freeSpacePos(pos: Pair<Int, Int>, dir: Direction): Pair<Int, Int>? {
        var nextI = pos.first + dir.di
        var nextJ = pos.second + dir.dj
        while (get(nextI)[nextJ] != '#') {
            if (get(nextI)[nextJ] == '.') return nextI to nextJ
            nextI += dir.di
            nextJ += dir.dj
        }
        return null
    }

    private fun List<CharArray>.swap(p1: Pair<Int, Int>, p2: Pair<Int, Int>) {
        val temp = get(p1.first)[p1.second]
        get(p1.first)[p1.second] = get(p2.first)[p2.second]
        get(p2.first)[p2.second] = temp
    }

    private fun List<CharArray>.indexOfSubMarine(): Pair<Int, Int> {
        for (i in indices) {
            for (j in get(i).indices) {
                if (get(i)[j] == '@') return i to j
            }
        }
        throw IllegalStateException("There is no submarine")
    }

    private fun List<CharArray>.countP1(): Int {
        var result = 0
        for (i in indices) {
            for (j in indices) {
                if (get(i)[j] == 'O') result += 100 * i + j
            }
        }
        return result
    }

    private fun List<CharArray>.count(ch: Char): Int {
        var result = 0
        for (i in indices) {
            for (j in get(i).indices) {
                val row = get(i)
                if (row[j] == ch) {
                    result += 100 * i + j
                }
            }
        }
        return result
    }

    private fun Sequence<String>.parseInput(p2: Boolean = false): Pair<List<CharArray>, List<Direction>> {
        val field = mutableListOf<CharArray>()
        val moves = mutableListOf<Direction>()
        var takeFiled = true
        forEach {
            when {
                it.isBlank() -> takeFiled = false
                takeFiled -> field.add(it.update(p2).toCharArray())
                else -> moves.addAll(it.parseDirections())
            }
        }
        return field to moves
    }

    private fun String.update(p2: Boolean = false): String {
        if (!p2) return this
        return replace("#", "##")
            .replace("O", "[]")
            .replace(".", "..")
            .replace("@", "@.")
    }
}
