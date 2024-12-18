import utils.*
import kotlin.math.min

object Day16 {

    fun solvePart1(input: Sequence<String>): Int {
        val maze = input.map { it.toCharArray() }.toList()
        val start = maze.indexesOf('S').first()
        val end = maze.indexesOf('E').first()
        return maze.markdown(start)[end.first][end.second].value
    }

    fun solvePart2(input: Sequence<String>): Int {
        val maze = input.map { it.toCharArray() }.toList()
        val start = maze.indexesOf('S').first()
        val end = maze.indexesOf('E').first()
        return maze.markdown(start).findAllPaths(end).size
    }

    private fun List<CharArray>.markdown(start: Pair<Int, Int>): List<List<TableEntry>> {
        val queue = ArrayDeque<Pair<Int, Step>>().apply {
            add(0 to Step(start))
        }

        val table = map { MutableList(it.size) { TableEntry() } }
        table[start.first][start.second].value = 0

        while (queue.isNotEmpty()) {
            val (currDist, currStep) = queue.removeFirst()

            Direction.entries.forEach { d ->
                val nextPos = currStep.pos.nextPoint(d)
                if (isInBound(nextPos) && getValue(nextPos) != '#') {
                    val distance = currDist + if (currStep.dir.isOrthogonal(d)) 1001 else 1
                    table[nextPos.first][nextPos.second].values[d.ordinal] =
                        min(distance, table[nextPos.first][nextPos.second].values[d.ordinal])
                }
            }

            if (currDist > table[currStep.pos.first][currStep.pos.second].value) continue

            Direction.entries.forEach { d ->
                val nextPos = currStep.pos.nextPoint(d)
                if (getValue(nextPos) != '#') {
                    val distance = currDist + if (currStep.dir.isOrthogonal(d)) 1001 else 1

                    if (distance < table[nextPos.first][nextPos.second].value) {
                        table[nextPos.first][nextPos.second].value = distance
                        queue.addLast(distance to Step(nextPos, d))
                    }
                }
            }
        }
        return table
    }

    private fun List<List<TableEntry>>.findAllPaths(end: Pair<Int, Int>): Set<Pair<Int, Int>> {
        val result = mutableSetOf<Pair<Int, Int>>()
        result.add(end)
        val endValue = get(end.first)[end.second].value
        Direction.entries.forEach { d ->
            val nextPoint = end.nextPoint(d)
            val diff = endValue - get(nextPoint.first)[nextPoint.second].value
            if (diff == 1001 || diff == 1) {
                findAllPaths(endValue, nextPoint, result)
            }
        }
        return result
    }

    private fun List<List<TableEntry>>.findAllPaths(
        prevValue: Int,
        point: Pair<Int, Int>,
        paths: MutableSet<Pair<Int, Int>>
    ) {
        val table = this
        val entry = table[point.first][point.second]
        paths.add(point)
        Direction.entries.asSequence().forEach { d ->
            val currValue = entry.values[d.ordinal]
            val diff = prevValue - currValue
            if (diff == 1001 || diff == 1) {
                findAllPaths(currValue, point.nextPoint(d.turnBack()), paths)
            }
        }
    }

    private data class Step(
        val pos: Pair<Int, Int>,
        val dir: Direction = Direction.RIGHT,
    )

    private class TableEntry(
        var values: IntArray = IntArray(4) { Int.MAX_VALUE },
        var value: Int = Int.MAX_VALUE
    )
}