import utils.*
import kotlin.math.abs
import kotlin.math.max

object Day20 {

    fun solve(input: Sequence<String>, maxCheatDist: Int, needSave: Int): Int {
        val board = input.parseInput()
        val start = board.indexesOf('S').first()
        return board.markdownPath(start).collectCheats(start, maxCheatDist).entries.sumOf { if (it.key >= needSave)  it.value else 0 }
    }

    private fun List<CharArray>.markdownPath(start: Pos): List<IntArray> {
        val table = map { IntArray(it.size) { Int.MAX_VALUE } }
        var pos: Pos? = start
        val visited = mutableSetOf<Pos>()
        var dist = 0
        while (pos != null) {
            val curr = pos
            visited.add(curr)
            table[curr.first][curr.second] = dist
            dist += 1
            pos = pointsInFourDirections(curr)
                .filter { isInBound(it) && getValue(it) != '#' && it !in visited }
                .firstOrNull()
        }
        return table
    }

    private fun List<IntArray>.collectCheats(start: Pos, maxCheatDist: Int): Map<Int, Int> {
        val table = this
        val result = mutableMapOf<Int, Int>()

        var pos: Pos? = start
        while (pos != null) {
            val curr = pos
            for (di in -maxCheatDist..maxCheatDist) {
                for (dj in -maxCheatDist..maxCheatDist) {
                    val i = curr.first + di
                    val j = curr.second + dj
                    if (i in indices && j in get(i).indices && abs(di) + abs(dj) <= maxCheatDist && table[i][j] != Int.MAX_VALUE) {
                        val dist = table[i][j] - table[curr.first][curr.second] - 2
                        val cheatDist = abs(di) + abs(dj) - 2
                        val save = max(0,dist - cheatDist)
                        if (save > 0) {
                            result[save] = result.getOrDefault(save , 0) + 1
                        }
                    }
                }
            }
            pos = nextTrackPos(pos)
        }
        return result
    }

    private fun List<IntArray>.nextTrackPos(pos: Pos): Pos? {
        for (d in Direction.entries) {
            val next = pos.nextPoint(d)
            if (get(next.first)[next.second] - get(pos.first)[pos.second] == 1) {
                return next
            }
        }
        return null
    }

    private fun Sequence<String>.parseInput() = map { it.toCharArray() }.toList()
}