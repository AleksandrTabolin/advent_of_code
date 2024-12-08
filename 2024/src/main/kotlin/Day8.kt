object Day8 {

    fun solvePart1(input: Sequence<String>): Int {
        val board = input.toList().map { it.toCharArray() }
        board.markAntinodes { l, r -> board.markAntinodesIfExists(l, r) }
        return board.sumOf { line -> line.count { it == '#' } }
    }

    fun solvePart2(input: Sequence<String>): Int {
        val board = input.toList().map { it.toCharArray() }
        board.markAntinodes { l, r -> board.markAntinodesIfExists(l, r, all = true) }
        return board.sumOf { line -> line.count { it != '.' } }
    }

    private fun List<CharArray>.markAntinodesIfExists(l: Pair<Int, Int>, r: Pair<Int, Int>, all: Boolean = false) {
        val dx = l.second - r.second
        val dy = l.first - r.first
        mark(l, dx, dy, all)
        mark(r, -dx, -dy, all)
    }

    private fun List<CharArray>.mark(input: Pair<Int, Int>, dx: Int, dy: Int, all: Boolean) {
        var node = input.applyDiff(dx, dy)
        while (isInBound(node)) {
            mark(node)
            if (!all) break
            node = node.applyDiff(dx, dy)
        }
    }

    private fun List<CharArray>.markAntinodes(marker: (l: Pair<Int, Int>, r: Pair<Int, Int>) -> Unit) {
        collectAntennas().forEach { antennas ->
            for (i in 0 until antennas.lastIndex)
                for (j in (i + 1)..antennas.lastIndex)
                    marker.invoke(antennas[i], antennas[j])
        }
    }

    private fun List<CharArray>.collectAntennas(): List<List<Pair<Int, Int>>> {
        return foldIndexed(mutableMapOf<Char, MutableList<Pair<Int, Int>>>()) { i, acc, line ->
            acc.apply { line.forEachIndexed { j, ch -> if (ch != '.') getOrPut(ch) { mutableListOf() }.add(i to j) } }
        }.values.toList()
    }

    private fun List<CharArray>.mark(p: Pair<Int, Int>) {
        get(p.first)[p.second] = '#'
    }

    private fun List<CharArray>.isInBound(p: Pair<Int, Int>) = p.first in indices && p.second in get(p.first).indices

    private fun Pair<Int, Int>.applyDiff(dx: Int, dy: Int) = first + dy to second + dx
}