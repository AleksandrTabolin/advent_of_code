import utils.getAllMaximalCliques

object Day23 {

    fun solvePart1(input: Sequence<String>): Int {
        val connections = input.parseInput()
        return connections.entries.fold(mutableSetOf<List<String>>()) { acc, (key, links) ->
            for (l in links) {
                val p = connections.getValue(l) intersect links
                for (vl in p) acc.add(listOf(key, l, vl).sorted())
            }
            acc
        }.count { clique -> clique.any { it.startsWith('t') } }
    }

    fun solvePart2(input: Sequence<String>): String {
        val connections = input.parseInput()
        return getAllMaximalCliques(connections).maxBy { it.size }.sorted().joinToString(",")
    }

    private fun Sequence<String>.parseInput() = map { it.split('-') }
        .fold(mutableMapOf<String, MutableSet<String>>()) { acc, item ->
            acc.addPair(item.first(), item.last())
        }

    private fun MutableMap<String, MutableSet<String>>.addPair(
        left: String,
        right: String
    ): MutableMap<String, MutableSet<String>> {
        getOrPut(left) { mutableSetOf() }.add(right)
        getOrPut(right) { mutableSetOf() }.add(left)
        return this
    }
}