import kotlin.math.min

object Day14 {

    fun solvePart1(input: Sequence<String>, sizeX: Int, sizeY: Int): Int {
        val robots = input.map { it.parse() }.toList()
        repeat(times = 100) { robots.forEach { it.nextStep(sizeX, sizeY) } }
        return robots.countQuadrants(sizeX, sizeY).let { it.rb * it.rt * it.lb * it.lt }
    }

    fun solvePart2(input: Sequence<String>, sizeX: Int, sizeY: Int): Int {
        val robots = input.map { it.parse() }.toList()

        val windowSize = sizeX / 8
        val windowsX = IntArray(sizeX - windowSize + 1)
        val windowsY = IntArray(sizeY - windowSize + 1)

        var i = 1
        val attempts = 10000

        while (i < attempts) {
            robots.forEach { it.nextStep(sizeX, sizeY) }
            windowsX.clear()
            windowsY.clear()
            if (robots.check(sizeX, sizeY, windowsX, windowsY, windowSize)) break
            i += 1
        }
        if (i == attempts) throw IllegalStateException("Nothing found")
        return i
    }

    private fun List<Robot>.check(
        sizeX: Int,
        sizeY: Int,
        windowsX: IntArray,
        windowsY: IntArray,
        windowSize: Int
    ): Boolean {
        forEach { r ->
            for (i in r.p.first..min(r.p.first + windowSize, sizeX - windowSize - 1)) {
                windowsX[i] += 1
            }
            for (i in r.p.second..min(r.p.second + windowSize, sizeY - windowSize - 1)) {
                windowsY[i] += 1
            }
        }
        return size / windowsX.max() <= 2 && size / windowsY.max() <= 2
    }

    private fun IntArray.clear() {
        for (i in indices) set(i, 0)
    }

    private fun List<Robot>.countQuadrants(sizeX: Int, sizeY: Int): Quadrants {
        val qY = sizeY / 2
        val qX = sizeX / 2

        return fold(Quadrants()) { q, r ->
            val (x, y) = r.p
            q.apply {
                when {
                    x > qX -> when {
                        y > qY -> rb += 1
                        y < qY -> rt += 1
                    }

                    x < qX -> when {
                        y > qY -> lb += 1
                        y < qY -> lt += 1
                    }
                }
            }
        }
    }

    private fun String.parse(): Robot {
        return split(' ')
            .map { it.substring(it.indexOf('=') + 1).split(',') }
            .let {
                Robot(
                    p = it.first().first().toInt() to it.first().last().toInt(),
                    v = it.last().first().toInt() to it.last().last().toInt(),
                )
            }
    }

    private data class Quadrants(
        var lt: Int = 0,
        var lb: Int = 0,
        var rt: Int = 0,
        var rb: Int = 0,
    )

    private fun Robot.nextStep(sizeX: Int, sizeY: Int) {
        var nextX = (p.first + v.first) % sizeX
        var nextY = (p.second + v.second) % sizeY
        if (nextX < 0) nextX += sizeX
        if (nextY < 0) nextY += sizeY
        p = nextX to nextY
    }

    private data class Robot(var p: Pair<Int, Int>, val v: Pair<Int, Int>)
}