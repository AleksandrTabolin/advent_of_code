object Day12 {

    private val up = -1 to 0
    private val down = 1 to 0
    private val left = 0 to -1
    private val right = 0 to 1

    private fun Pair<Int, Int>.left(): Pair<Int, Int> {
        return when (this) {
            up -> left
            down -> right
            right -> up
            left -> down
            else -> throw IllegalStateException("$this")
        }
    }

    private fun Pair<Int, Int>.right(): Pair<Int, Int> {
        return when (this) {
            up -> right
            down -> left
            right -> down
            left -> up
            else -> throw IllegalStateException("$this")
        }
    }


    private val directions = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)


    fun solvePart1(input: Sequence<String>): Int {
        val board = input.toList().map { it.toCharArray() }
        var result = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (i to j !in visited) {
                    val (area, perimeter) = board.count(i, j, visited)
                    result += area * perimeter
                }
            }
        }
        return result
    }

    fun solvePart2(input: Sequence<String>): Int {
        val board = input.toList().map { it.toCharArray() }
        var result = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (i to j !in visited) {
                    val items = mutableListOf<Pair<Int, Int>>()
                    board.collect(i, j, visited, items)
                    val sides = board.countSides(items)
                    val area = items.size
                    result += sides * area
                }
            }
        }
        return result
    }

    private fun List<CharArray>.countSides(items: List<Pair<Int, Int>>): Int {
        val innerVisited = mutableSetOf<Step>()
        var result = 0
        for (item in items) {
            val up = item.first - 1 to item.second
            if (isInBound(up) && get(up.first)[up.second] == get(item.first)[item.second]) {
                continue
            }
            val step = Step(item.first, item.second, left)

            if (step !in innerVisited) {
                result += countSides(step, innerVisited)
            }
        }
        return result
    }

    private fun List<CharArray>.collect(
        i: Int,
        j: Int,
        visited: MutableSet<Pair<Int, Int>>,
        result: MutableList<Pair<Int, Int>>
    ) {
        val char = get(i)[j]
        result.add(i to j)
        visited.add(i to j)
        for (d in directions) {
            val nextI = i + d.first
            val nextJ = j + d.second
            if (isInBound(nextI to nextJ) && get(nextI)[nextJ] == char && nextI to nextJ !in visited) {
                collect(nextI, nextJ, visited, result)
            }
        }
    }

    private fun List<CharArray>.countSides(step: Step, visited: MutableSet<Step>): Int {
        if (step in visited) return 0
        visited.add(step)

        var sides = 0
        val nextStep: Step

        if (isRightSame(step)) {
            sides += 1
            nextStep = step.copy(direction = step.direction.right()).nextStep()
        } else if (isNextStepSame(step)) {
            nextStep = step.nextStep()
        } else {
            sides += 1
            nextStep = step.copy(direction = step.direction.left())
        }
        sides += countSides(nextStep, visited)
        return sides
    }

    private fun List<CharArray>.isNextStepSame(step: Step): Boolean = isNextStepSame(step.i, step.j, step.direction)

    private fun List<CharArray>.isRightSame(step: Step): Boolean {
        val up = step.direction.right()
        return isNextStepSame(step.i, step.j, up)
    }

    private fun List<CharArray>.isNextStepSame(i: Int, j: Int, diff: Pair<Int, Int>): Boolean {
        val nextI = i + diff.first
        val nextJ = j + diff.second
        return isInBound(nextI to nextJ) && get(nextI)[nextJ] == get(i)[j]
    }

    private fun List<CharArray>.count(i: Int, j: Int, visited: MutableSet<Pair<Int, Int>>): Pair<Int, Int> {
        var area = 1
        var perimeter = 0
        val char = get(i)[j]
        visited.add(i to j)
        for (d in directions) {
            val nextI = i + d.first
            val nextJ = j + d.second
            if (!isInBound(nextI to nextJ) || get(nextI)[nextJ] != char) {
                perimeter += 1
            } else if (nextI to nextJ !in visited) {
                val (a, p) = count(nextI, nextJ, visited)
                area += a
                perimeter += p
            }
        }
        return area to perimeter
    }

    private fun List<CharArray>.isInBound(p: Pair<Int, Int>) = p.first in indices && p.second in get(p.first).indices

    private data class Step(
        val i: Int,
        val j: Int,
        val direction: Pair<Int, Int>
    )

    private fun Step.nextStep(): Step = Step(i + direction.first, j + direction.second, direction)

}