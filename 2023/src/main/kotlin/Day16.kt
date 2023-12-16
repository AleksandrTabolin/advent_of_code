import Day16.Direction.*
import java.util.*
import kotlin.math.max

object Day16 {

    fun solvePart1(input: Sequence<String>): Int {
        return process(Position(-1, 0) to RIGHT, input.toList())
    }

    fun solvePart2(input: Sequence<String>): Int {
        val matrix = input.toList()

        val right = (0..matrix.first().lastIndex).maxOf {
            process(Position(-1, it) to RIGHT, matrix)
        }
        val left = (0..matrix.first().lastIndex).maxOf {
            process(Position(matrix.size, it) to LEFT, matrix)
        }
        val top = (0..matrix.lastIndex).maxOf {
            process(Position(it, matrix.first().length) to TOP, matrix)
        }
        val bottom = (0..matrix.lastIndex).maxOf {
            process(Position(it, -1) to BOTTOM, matrix)
        }
        return listOf(right, left, top, bottom).max()
    }

    private fun process(
        start: Pair<Position, Direction>,
        matrix: List<String>,
    ): Int {
        val visited = mutableSetOf<Pair<Position, Direction>>()
        val queue = LinkedList<Pair<Position, Direction>>()
        queue.add(start)

        while (queue.isNotEmpty()) {
            val (position, direction) = queue.pop()

            val nextPosition = matrix.nextPosition(position, direction) ?: continue

            val nextCell = matrix[nextPosition.y][nextPosition.x]

            when {
                nextCell == '.' || nextCell == '-' && direction.isHorizontal() || nextCell == '|' && direction.isVertical() -> {
                    queue.addIfUnvisited(nextPosition, direction, visited)
                }

                nextCell == '-' && direction.isVertical() -> {
                    queue.addIfUnvisited(nextPosition, LEFT, visited)
                    queue.addIfUnvisited(nextPosition, RIGHT, visited)
                }

                nextCell == '|' && direction.isHorizontal() -> {
                    queue.addIfUnvisited(nextPosition, TOP, visited)
                    queue.addIfUnvisited(nextPosition, BOTTOM, visited)
                }

                nextCell == '\\' -> when (direction) {
                    RIGHT -> queue.addIfUnvisited(nextPosition, BOTTOM, visited)
                    TOP -> queue.addIfUnvisited(nextPosition, LEFT, visited)
                    LEFT -> queue.addIfUnvisited(nextPosition, TOP, visited)
                    BOTTOM -> queue.addIfUnvisited(nextPosition, RIGHT, visited)
                }

                nextCell == '/' -> when (direction) {
                    RIGHT -> queue.addIfUnvisited(nextPosition, TOP, visited)
                    TOP -> queue.addIfUnvisited(nextPosition, RIGHT, visited)
                    LEFT -> queue.addIfUnvisited(nextPosition, BOTTOM, visited)
                    BOTTOM -> queue.addIfUnvisited(nextPosition, LEFT, visited)
                }
            }
        }

        return visited.map { it.first }.toSet().size
    }

    private fun LinkedList<Pair<Position, Direction>>.addIfUnvisited(
        position: Position,
        direction: Direction,
        visited: MutableSet<Pair<Position, Direction>>,
    ) {
        if (position to direction !in visited) {
            visited.add(position to direction)
            add(position to direction)
        }
    }

    private fun List<String>.nextPosition(position: Position, direction: Direction): Position? {
        return when {
            direction == RIGHT && position.x + 1 <= lastIndex -> position.copy(x = position.x + 1)
            direction == TOP && position.y - 1 >= 0 -> position.copy(y = position.y - 1)
            direction == LEFT && position.x - 1 >= 0 -> position.copy(x = position.x - 1)
            direction == BOTTOM && position.y + 1 <= first().lastIndex -> position.copy(y = position.y + 1)
            else -> null
        }
    }

    data class Position(
        val x: Int,
        val y: Int
    )

    enum class Direction {
        RIGHT, TOP, LEFT, BOTTOM;

        fun isVertical(): Boolean = this == TOP || this == BOTTOM

        fun isHorizontal(): Boolean = this == RIGHT || this == LEFT
    }
}