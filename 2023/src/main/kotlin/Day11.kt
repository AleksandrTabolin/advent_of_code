import kotlin.math.abs

object Day11 {

    fun solvePart1(input: Sequence<String>): Int {
        val galaxyImage = input.map { it.toList() }.toList().expandGalaxy()
        val galaxyPositions = findGalaxyPositions(galaxyImage)

        val visited = mutableSetOf<Pair<Int, Int>>()
        return galaxyPositions.sumOf { galaxyPosition ->
            visited.add(galaxyPosition)
            galaxyPositions.filter { it !in visited }.sumOf { minPath(galaxyPosition, it) }
        }
    }

    fun solvePart2(input: Sequence<String>, placeHolderMultiplier: Long): Long {
        val placeHolder = '*'
        val galaxyImage = input.map { it.toList() }.toList().expandGalaxyWithPlaceHolder(placeHolder)
        val galaxyPositions = findGalaxyPositions(galaxyImage)

        val visited = mutableSetOf<Pair<Int, Int>>()
        return galaxyPositions.sumOf { galaxyPosition ->
            visited.add(galaxyPosition)
            galaxyPositions
                .filter { it !in visited }
                .sumOf { position -> minPath(galaxyPosition, position, galaxyImage, placeHolder, placeHolderMultiplier) }
        }
    }

    private fun findGalaxyPositions(galaxyImage: List<List<Char>>): List<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        for (i in galaxyImage.indices) {
            for (j in galaxyImage[i].indices) {
                if (galaxyImage[i][j] == '#') result.add(i to j)
            }
        }
        return result
    }

    private fun minPath(left: Pair<Int, Int>, right: Pair<Int, Int>): Int {
        return abs(left.first - right.first) + abs(left.second - right.second)
    }

    private fun minPath(
        leftPath: Pair<Int, Int>,
        rightPath: Pair<Int, Int>,
        galaxyImage: List<List<Char>>,
        placeHolder: Char,
        placeHolderMultiplier: Long
    ): Long {
        val (top, bottom) = if (leftPath.first > rightPath.first) rightPath to leftPath else leftPath to rightPath
        val (right, left) = if (leftPath.second > rightPath.second) leftPath to rightPath else rightPath to leftPath

        var result = 0L
        for (y in (top.first + 1)..bottom.first) {
            val x = top.second
            val step = if (galaxyImage[y][x] == placeHolder) placeHolderMultiplier else 1
            result += step
        }

        for (x in (left.second + 1)..right.second) {
            val y = left.first
            val step = if (galaxyImage[y][x] == placeHolder) placeHolderMultiplier else 1
            result += step
        }

        return result
    }

    private fun List<List<Char>>.expandGalaxy(): List<List<Char>> {
        val galaxyImage = this
        return fold(mutableListOf<List<Char>>()) { acc, row ->
            acc.apply {
                add(row)
                if ('#' !in row) add(row)
            }
        }.map { row ->
            row.foldIndexed(mutableListOf()) { index, acc, cell ->
                acc.apply {
                    add(cell)
                    if (galaxyImage.all { it[index] != '#' }) add(cell)
                }
            }
        }
    }

    private fun List<List<Char>>.expandGalaxyWithPlaceHolder(placeHolder: Char): List<List<Char>> {
        val galaxyImage = this
        return fold(mutableListOf<List<Char>>()) { acc, row ->
            acc.apply {
                if ('#' !in row) {
                    add(row.map { placeHolder })
                } else {
                    add(row)
                }
            }
        }.map { row ->
            row.foldIndexed(mutableListOf()) { index, acc, cell ->
                acc.apply {
                    if (galaxyImage.all { it[index] != '#' }) {
                        add(placeHolder)
                    } else {
                        add(cell)
                    }
                }
            }
        }
    }
}