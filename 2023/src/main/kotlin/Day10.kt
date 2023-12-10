object Day10 {

    fun solvePart1(input: Sequence<String>): Int {
        val maze = input.toList().map { it.toMutableList() }
        val startPos = findStartTile(maze)
        val path = Direction.values().map { findPath(startPos, it, maze) }.first { it.isNotEmpty() }
        return if (path.size % 2 == 0) path.size / 2 else path.size / 2 + 1
    }


    fun solvePart2(input: Sequence<String>): Int {
        val maze = input.toList().map { it.toMutableList() }
        val startPos = findStartTile(maze)
        val path = Direction.values().map { findPath(startPos, it, maze) }.first { it.isNotEmpty() }

        maze[startPos.first][startPos.second] = getStartPointTile(startPos, maze, path)

        for (i in maze.indices) {
            for (j in maze[i].indices) {
                if (i to j !in path) {
                    maze[i][j] = '.'
                }
            }
        }


        val points: List<List<Pair<Int, Int>>> = path.groupBy { it.first }
            .entries
            .fold<Map.Entry<Int, List<Pair<Int, Int>>>, MutableList<List<Pair<Int, Int>>>>(mutableListOf()) { acc, entry ->
                acc.apply { add(entry.value.filter { maze[it.first][it.second] != '-' }.sortedBy { it.second }) }
            }
            .sortedBy { it.first().first }

        var result = 0
        for (i in points.indices) {
            val pairs = points[i].windowed(2)

            for (j in pairs.indices) {
                val left = pairs[j].first()
                val right = pairs[j].last()

                if (maze[left.first][left.second + 1] != '.') continue

                var visited = mutableSetOf(right.second)

                for (z in j + 1 until pairs.size) {
                    val f = pairs[z].first()
                    val l = pairs[z].last()
                    val fTile = maze[f.first][f.second]
                    val lTile = maze[l.first][l.second]

                    if (fTile == 'L' && lTile == 'J' || fTile == 'F' && lTile == '7') {
                        visited.add(f.second)
                        visited.add(l.second)
                    } else {
                        if (fTile.nextDirection(Direction.RIGHT) == null) {
                            visited.add(f.second)
                        }

                        if (lTile.nextDirection(Direction.RIGHT) == null) {
                            visited.add(l.second)
                        }
                    }
                }

                val size = visited.size
                val diff = (right.second - left.second - 1)

                if (size % 2 == 1) {
                    result += diff
                }
            }
        }

        return result
    }

    private fun getStartPointTile(
        startPos: Pair<Int, Int>,
        maze: List<List<Char>>,
        path: List<Pair<Int, Int>>
    ): Char {
        val top = (startPos.first - 1 to startPos.second).takeIf {
            it in path && maze[it.first][it.second].nextDirection(Direction.TOP) != null
        }
        val left = (startPos.first to startPos.second - 1).takeIf {
            it in path && maze[it.first][it.second].nextDirection(Direction.LEFT) != null
        }
        val bottom = (startPos.first + 1 to startPos.second).takeIf {
            it in path && maze[it.first][it.second].nextDirection(Direction.BOTTOM) != null
        }
        val right = (startPos.first to startPos.second + 1).takeIf {
            it in path && maze[it.first][it.second].nextDirection(Direction.RIGHT) != null
        }

        return when {
            top != null && bottom != null -> '|'
            top != null && left != null -> 'J'
            top != null && right != null -> 'L'
            left != null && right != null -> '-'
            bottom != null && left != null -> '7'
            bottom != null && right != null -> 'F'
            else -> throw IllegalStateException("start position error")
        }
    }


    private fun findPath(
        posToFind: Pair<Int, Int>,
        direction: Direction,
        maze: List<MutableList<Char>>,
    ): List<Pair<Int, Int>> {
        var nextPos: Pair<Int, Int> = posToFind
        var nextDirection: Direction? = direction
        val result = mutableListOf<Pair<Int, Int>>()

        while (true) {
            if (nextDirection == null) return emptyList()

            nextPos = getNextPos(nextPos, nextDirection)

            if (nextPos == posToFind) {
                result.add(nextPos)
                break
            }

            if (!maze.isExists(nextPos)) return emptyList()

            val tail = maze[nextPos.first][nextPos.second]

            nextDirection = tail.nextDirection(nextDirection)

            if (nextDirection != null) result.add(nextPos)
        }

        return result
    }

    private fun getNextPos(pos: Pair<Int, Int>, direction: Direction): Pair<Int, Int> {
        return when (direction) {
            Direction.LEFT -> pos.first to pos.second - 1
            Direction.TOP -> pos.first - 1 to pos.second
            Direction.RIGHT -> pos.first to pos.second + 1
            Direction.BOTTOM -> pos.first + 1 to pos.second
        }

    }

    private fun Char.nextDirection(from: Direction): Direction? {

        return when (this) {
            '|' -> when (from) {
                Direction.TOP -> Direction.TOP
                Direction.BOTTOM -> Direction.BOTTOM
                else -> null
            }

            '-' -> when (from) {
                Direction.RIGHT -> Direction.RIGHT
                Direction.LEFT -> Direction.LEFT
                else -> null
            }

            'L' -> when (from) {
                Direction.BOTTOM -> Direction.RIGHT
                Direction.LEFT -> Direction.TOP
                else -> null
            }

            'J' -> when (from) {
                Direction.BOTTOM -> Direction.LEFT
                Direction.RIGHT -> Direction.TOP
                else -> null
            }

            '7' -> when (from) {
                Direction.RIGHT -> Direction.BOTTOM
                Direction.TOP -> Direction.LEFT
                else -> null
            }

            'F' -> when (from) {
                Direction.TOP -> Direction.RIGHT
                Direction.LEFT -> Direction.BOTTOM
                else -> null
            }

            'S' -> from

            else -> null
        }
    }

    private fun findStartTile(maze: List<List<Char>>): Pair<Int, Int> {
        for (i in maze.indices) {
            val row = maze[i]
            for (j in row.indices) {
                if (row[j] == 'S') return i to j
            }
        }
        throw IllegalStateException("There is no start position")
    }

    private fun List<List<Char>>.isExists(pos: Pair<Int, Int>): Boolean {
        if (pos.first < 0 || pos.first >= size) return false
        val row = get(pos.first)
        return !(pos.second < 0 || pos.second >= row.size)
    }

    enum class Direction {
        LEFT, TOP, RIGHT, BOTTOM;
    }
}