import kotlin.math.max

object Day2 {

    fun solvePart1(input: Sequence<String>): Int {
        val limits = mapOf("red" to 12, "green" to 13, "blue" to 14)

        return input.map { line ->
            val (gameId, gameSets) = parseGame(line)
            gameSets.all { gameSet ->
                gameSet.all { (color, size) -> limits.getValue(color) >= size }
            }.let { possible -> if (possible) gameId else 0 }
        }.sum()
    }

    fun solvePart2(input: Sequence<String>): Int {
        return input.map { line ->
            val (_, gameSets) = parseGame(line)

            var red = 0
            var green = 0
            var blue = 0

            gameSets.forEach { gameSet ->
                gameSet["red"]?.let { red = max(red, it) }
                gameSet["green"]?.let { green = max(green, it) }
                gameSet["blue"]?.let { blue = max(blue, it) }
            }

            red * green * blue
        }.sum()
    }

    private fun parseGame(line: String) : Pair<Int, List<Map<String, Int>>> {
        val (idPart, gameSetsPart) = line.split(": ")
        val (_, gameId) = idPart.split(" ")
        return gameId.toInt() to gameSetsPart.parseGameSetList()
    }

    private fun String.parseGameSetList(): List<Map<String, Int>> {
        return split("; ").map { gameSets ->
            mutableMapOf<String, Int>().apply {
                gameSets.split(", ").forEach {
                    val (size, color) = it.split(" ")
                    put(color, size.toInt())
                }
            }
        }
    }
}