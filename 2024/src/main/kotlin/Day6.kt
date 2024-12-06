object Day6 {

    fun solvePart1(input: Sequence<String>): Int {
        return input.parseInput().collectPaths().size
    }

    fun solvePart2(input: Sequence<String>): Int {
        val board = input.parseInput()
        val start = board.findGuard()
        val visited = mutableSetOf<GuardPosition>()
        return board.collectPaths().count { (i, j) -> board.hasLoopIfWall(i, j, start, visited) }
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

    private fun List<CharArray>.hasLoopIfWall(
        i: Int,
        j: Int,
        start: GuardPosition,
        visited: MutableSet<GuardPosition>
    ): Boolean {
        var result = false
        if (get(i)[j] == '.') {
            get(i)[j] = '#'
            result = checkLoop(start, visited)
            get(i)[j] = '.'
        }
        return result
    }

    private fun List<CharArray>.checkLoop(start: GuardPosition, visited: MutableSet<GuardPosition>): Boolean {
        var guard = start
        visited.clear()
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
                val direction = when (get(i)[j]) {
                    '^' -> Direction.UP
                    '>' -> Direction.RIGHT
                    'v' -> Direction.DOWN
                    '<' -> Direction.LEFT
                    else -> null
                }
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

    private enum class Direction(val dx: Int, val dy: Int) {
        UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

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