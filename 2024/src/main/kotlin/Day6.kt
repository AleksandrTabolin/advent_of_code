object Day6 {

    fun solvePart1(input: Sequence<String>): Int {
        return input.parseInput().collectPaths().size
    }

    fun solvePart2(input: Sequence<String>): Int {
        val board = input.parseInput()
        val start = board.findGuard()
        return board.collectPaths().sumOf { (i, j) -> board.hasLoopIfWall(i, j, start) }
    }

    private fun List<CharArray>.collectPaths(): Set<Pair<Int, Int>> {
        var guard = findGuard()
        val result = mutableSetOf<Pair<Int, Int>>()
        while (true) {
            result.add(guard.i to guard.j)
            val next = guard.step()
            if (!isIndexInBound(next.i, next.j)) break

            if (get(next.i)[next.j] == '#') {
                guard = guard.turnRight()
            }
            guard = guard.step()
        }
        return result
    }

    private fun List<CharArray>.hasLoopIfWall(i: Int, j: Int, start: GuardPosition): Int {
        var result = 0
        if (get(i)[j] == '.') {
            get(i)[j] = '#'
            if (checkLoop(start)) result = 1
            get(i)[j] = '.'
        }
        return result
    }

    private fun List<CharArray>.checkLoop(start: GuardPosition): Boolean {
        var guard = start
        val visited = mutableSetOf<GuardPosition>()
        while (isIndexInBound(guard.i, guard.j)) {
            if (guard in visited) return true
            guard = if (getNextIfExists(guard) == '#') {
                visited.add(guard)
                guard.turnRight()
            } else {
                guard.step()
            }
        }
        return false
    }

    private fun Sequence<String>.parseInput(): List<CharArray> {
        return map { it.toCharArray() }.toList()
    }

    private fun List<CharArray>.findGuard(): GuardPosition {
        for (i in indices) {
            for (j in get(i).indices) {
                val direction = Direction.entries.find { it.char1 == get(i)[j] }
                if (direction != null) return GuardPosition(i, j, direction)
            }
        }
        throw IllegalStateException("no guard")
    }

    private fun List<CharArray>.isIndexInBound(i: Int, j: Int) = i in indices && j in get(i).indices

    private fun List<CharArray>.getNextIfExists(guard: GuardPosition): Char? {
        val nextI = guard.i + guard.direction.dy
        val nextJ = guard.j + guard.direction.dx
        return if (isIndexInBound(nextI, nextJ)) get(nextI)[nextJ] else null
    }

    private data class GuardPosition(
        val i: Int,
        val j: Int,
        val direction: Direction
    )

    private fun GuardPosition.step() = GuardPosition(i + direction.dy, j + direction.dx, direction)

    private fun GuardPosition.turnRight() = GuardPosition(i, j, direction.turnRight())

    private enum class Direction(val char1: Char, val dx: Int, val dy: Int) {
        UP('^', 0, -1),
        RIGHT('>', 1, 0),
        DOWN('v', 0, 1),
        LEFT('<', -1, 0);

        fun turnRight(): Direction {
            return when (this) {
                UP -> RIGHT
                RIGHT -> DOWN
                DOWN -> LEFT
                LEFT -> UP
            }
        }
    }
}