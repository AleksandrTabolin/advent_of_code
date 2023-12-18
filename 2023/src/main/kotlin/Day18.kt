import kotlin.math.abs

object Day18 {

    fun solvePart1(input: Sequence<String>): Long {
        return input.map {
            val (direction, length, _) = it.split(" ")
            direction to length.toLong().also { println(it) }
        }.collectPositions().createMatrix().count()
    }

    fun solvePart2(input: Sequence<String>): Long {
        return input.map {
            val (_, _, color) = it.split(" ")
            val clearedColor = color.drop(2).dropLast(1)
            val direction = when (clearedColor.last()) {
                '0' -> "R"
                '1' -> "D"
                '2' -> "L"
                '3' -> "U"
                else -> throw IllegalStateException("Unknown direction '${clearedColor.last()}'")
            }
            val length = clearedColor.dropLast(1).toLong(16)

            direction to length
        }.collectPositions().createMatrix().count()
    }

    private fun List<List<Char>>.count(): Long {
        var resultCount = 0L
        for (row in this.indices) {
            var rowCount = 0L
            val line = get(row)
            val groups = line.splitToGroups().toList()

            for (i in groups.indices) {
                rowCount += groups[i].length()

                if (i == groups.lastIndex) continue

                var count = 0
                for (j in i + 1..groups.lastIndex) {
                    count += if (isPillarShaped(row, groups[j])) 2 else 1
                }

                if (count % 2 == 1) {
                    rowCount += (groups[i].second + 1 to groups[i + 1].first - 1).length()
                }
            }

            resultCount += rowCount
        }

        return resultCount
    }

    private fun List<List<Char>>.isPillarShaped(row: Int, interval: Pair<Int, Int>): Boolean {
        if (interval.length() == 1) return false
        if (row == 0 || row == lastIndex) return true
        val (start, end) = interval
        return get(row + 1)[start] == get(row + 1)[end] && get(row - 1)[start] == get(row - 1)[end]
    }


    private fun List<Char>.splitToGroups(): Sequence<Pair<Int, Int>> = sequence {
        val line = this@splitToGroups
        var start = -1
        var end = -1

        for (i in line.indices) {
            if (start == -1 && line[i] == '#') {
                start = i
            } else if (start != -1 && line[i] == '.' && line[i - 1] == '#') {
                end = i - 1
            } else if (start != -1 && line[i] == '#' && (line[i - 1] == '.' || i == line.lastIndex)) {
                end = i
            }

            if (start != -1 && end != -1) {
                yield(start to end)
                start = -1
                end = -1
            }
        }

        if (start != -1 && start == line.lastIndex) {
            yield(start to start)
        }
    }

    private fun Pair<Int, Int>.length() = second - first + 1

    private fun List<Position>.createMatrix(): List<List<Char>> {
        val sizeX = maxOf { it.x } + 1
        val sizeY = maxOf { it.y } + 1

        val matrix = buildList { repeat(sizeY) { add(buildList { repeat(sizeX) { add('.') } }.toMutableList()) } }

        forEach { matrix[it.y][it.x] = '#' }

        return matrix
    }

    private fun Sequence<Pair<String, Long>>.collectPositions(): List<Position> {
        val positions: List<Position> = fold(mutableListOf()) { acc, line ->
            val (direction, length) = line

            var prevPosition = acc.lastOrNull() ?: Position(0, 0).also(acc::add)

            repeat(length.toInt()) {
                prevPosition = when (direction) {
                    "R" -> prevPosition.copy(x = prevPosition.x + 1).also(acc::add)
                    "U" -> prevPosition.copy(y = prevPosition.y - 1).also(acc::add)
                    "L" -> prevPosition.copy(x = prevPosition.x - 1).also(acc::add)
                    "D" -> prevPosition.copy(y = prevPosition.y + 1).also(acc::add)
                    else -> throw IllegalStateException("unknown direction: '$direction'")
                }
            }
            acc
        }
        val offsetX = abs(kotlin.math.min(0, positions.minOf { it.x }))
        val offsetY = abs(kotlin.math.min(0, positions.minOf { it.y }))
        return positions.map { position ->
            Position(x = position.x + offsetX, y = position.y + offsetY)
        }
    }

    private data class Position(
        val x: Int,
        val y: Int,
    )
}