import java.util.*
import kotlin.math.min

object Day17 {

    fun solvePart1(input: Sequence<String>): Int {
        val matrix = input.toList()
        val queue = LinkedList<Step>()
        val table = mutableMapOf<Triple<Position, Direction, Int>, Int>()

        fun tryAddToQueue(step: Step?, skip: Boolean = false) {
            step ?: return
            val value = table.getOrElse(step.asKey()) { Int.MAX_VALUE }
            if (value > step.score) {
                table[step.asKey()] = step.score
                if (!skip) queue.addLast(step)
            }
        }

        val startPosition = Position(0, 0)

        Step(
            startPosition,
            Direction.RIGHT,
            matrix.getScore(startPosition),
            oneDirectionSteps = 1
        ).also(::tryAddToQueue)
        Step(startPosition, Direction.DOWN, matrix.getScore(startPosition), oneDirectionSteps = 1).also(::tryAddToQueue)

        var result = Int.MAX_VALUE

        while (queue.isNotEmpty()) {
            val step = queue.pollFirst()

            if (matrix.isLastPosition(step.position)) result = min(result, step.score)

            if (step.oneDirectionSteps < 3) matrix.createNextStepIfExisted(step, step.direction).also(::tryAddToQueue)

            matrix.createNextStepIfExisted(step, step.direction.turnLeft()).also(::tryAddToQueue)
            matrix.createNextStepIfExisted(step, step.direction.turnRight()).also(::tryAddToQueue)
        }
        return result - matrix.getScore(startPosition)
    }

    fun solvePart2(input: Sequence<String>): Int {
        val matrix = input.toList()
        val queue = LinkedList<Step>()
        val table = mutableMapOf<Triple<Position, Direction, Int>, Int>()

        fun tryAddToQueue(step: Step?) {
            step ?: return
            val value = table.getOrElse(step.asKey()) { Int.MAX_VALUE }
            if (value > step.score) {
                table[step.asKey()] = step.score
                queue.addLast(step)
            }
        }

        val startPosition = Position(0, 0)

        Step(startPosition, Direction.RIGHT, matrix.getScore(startPosition), oneDirectionSteps = 1).also(::tryAddToQueue)

        var result = Int.MAX_VALUE

        while (queue.isNotEmpty()) {
            val step = queue.pollFirst()

            if (matrix.isLastPosition(step.position)) result = min(result, step.score)

            if (step.oneDirectionSteps < 10) matrix.createNextStepIfExisted(step, step.direction)?.let(::tryAddToQueue)

            if (step.oneDirectionSteps >= 4) {
                matrix.createNextStepIfExisted(step, step.direction.turnLeft())?.takeIf { matrix.hasEnoughSpace(it) }?.let(::tryAddToQueue)
                matrix.createNextStepIfExisted(step, step.direction.turnRight())?.takeIf { matrix.hasEnoughSpace(it) }?.let(::tryAddToQueue)
            }
        }
        return result - matrix.getScore(startPosition)
    }

    private fun List<String>.hasEnoughSpace(step: Step): Boolean {
        val diff = 3
        return when (step.direction) {
            Direction.RIGHT -> first().lastIndex >= step.position.x + diff
            Direction.TOP -> step.position.y >= diff
            Direction.LEFT -> step.position.x >= diff
            Direction.DOWN -> lastIndex >= step.position.y + diff
        }
    }

    private fun List<String>.createNextStepIfExisted(
        step: Step,
        nextDirection: Direction,
    ): Step? {
        val nextPosition = nextPosition(step.position, nextDirection) ?: return null
        val oneDirectionSteps = if (step.direction == nextDirection) step.oneDirectionSteps + 1 else 1
        return Step(nextPosition, nextDirection, score = step.score + getScore(nextPosition), oneDirectionSteps)
    }

    private fun List<String>.getScore(position: Position): Int = get(position.y)[position.x].digitToInt()

    private fun List<String>.isLastPosition(position: Position): Boolean {
        return position.x == first().lastIndex && position.y == lastIndex
    }

    private fun List<String>.nextPosition(position: Position, direction: Direction): Position? {
        return when {
            direction == Direction.RIGHT && position.x + 1 <= first().lastIndex -> position.copy(x = position.x + 1)
            direction == Direction.TOP && position.y - 1 >= 0 -> position.copy(y = position.y - 1)
            direction == Direction.LEFT && position.x - 1 >= 0 -> position.copy(x = position.x - 1)
            direction == Direction.DOWN && position.y + 1 <= lastIndex -> position.copy(y = position.y + 1)
            else -> null
        }
    }

    private fun Direction.turnLeft(): Direction = when (this) {
        Direction.RIGHT -> Direction.TOP
        Direction.TOP -> Direction.LEFT
        Direction.LEFT -> Direction.DOWN
        Direction.DOWN -> Direction.RIGHT
    }

    private fun Direction.turnRight(): Direction = when (this) {
        Direction.RIGHT -> Direction.DOWN
        Direction.TOP -> Direction.RIGHT
        Direction.LEFT -> Direction.TOP
        Direction.DOWN -> Direction.LEFT
    }

    private data class Step(
        val position: Position,
        val direction: Direction,
        val score: Int,
        val oneDirectionSteps: Int = 0
    )

    private fun Step.asKey(): Triple<Position, Direction, Int> {
        return Triple(position, direction, oneDirectionSteps)
    }

    private data class Position(
        val x: Int,
        val y: Int,
    )

    private enum class Direction {
        RIGHT, TOP, LEFT, DOWN;
    }

}