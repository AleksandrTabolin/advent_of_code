object Day8 {

    fun solvePart1(input: Sequence<String>): Int {
        val board = input.toList().map { it.toCharArray() }
        board.collect().forEach { it.forAllVariant { l, r -> board.markAntiNodeIfExists1(l, r) } }
        return board.sumOf { line -> line.count { it == '#' } }
    }

    fun solvePart2(input: Sequence<String>): Int {
        val board = input.toList().map { it.toCharArray() }
        board.collect().forEach { it.forAllVariant { l, r -> board.markAntiNodeIfExists2(l, r) } }
        return board.sumOf { line -> line.count { it != '.' } }
    }

    private inline fun List<Pair<Int, Int>>.forAllVariant(block: (l: Pair<Int, Int>, r: Pair<Int, Int>) -> Unit) {
        for (i in 0 until lastIndex) {
            for (j in (i + 1)..lastIndex) {
                block.invoke(get(i), get(j))
            }
        }
    }

    private fun List<CharArray>.markAntiNodeIfExists1(l: Pair<Int, Int>, r: Pair<Int, Int>) {
        val dx = l.second - r.second
        val dy = l.first - r.first
        l.applyDiff(dx, dy).let { if (isInBound(it)) mark(it) }
        r.applyDiff(-dx, -dy).let { if (isInBound(it)) mark(it) }
    }

    private fun List<CharArray>.markAntiNodeIfExists2(l: Pair<Int, Int>, r: Pair<Int, Int>) {
        val dx = l.second - r.second
        val dy = l.first - r.first

        var node = l.applyDiff(dx, dy)
        while (isInBound(node)) {
            mark(node)
            node = node.applyDiff(dx, dy)
        }
        node = r.applyDiff(-dx, -dy)
        while (isInBound(node)) {
            mark(node)
            node = node.applyDiff(-dx, -dy)
        }
    }

    private fun List<CharArray>.collect(): List<List<Pair<Int, Int>>> {
        val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        for (i in indices) {
            for (j in get(i).indices) {
                val ch = get(i)[j]
                if (ch == '.') continue
                antennas.getOrPut(ch) { mutableListOf() }.add(i to j)
            }
        }
        return antennas.values.toList()
    }

    private fun List<CharArray>.mark(p: Pair<Int, Int>) {
        get(p.first)[p.second] = '#'
    }

    private fun List<CharArray>.isInBound(p: Pair<Int, Int>) = p.first in indices && p.second in get(p.first).indices

    private fun Pair<Int, Int>.applyDiff(dx: Int, dy: Int) = first + dy to second + dx
}