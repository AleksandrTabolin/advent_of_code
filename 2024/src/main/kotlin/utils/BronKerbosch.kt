package utils

fun <V> getAllMaximalCliques(graph: Map<V, Set<V>>): MutableList<Set<V>> {
    val result = mutableListOf<Set<V>>()
    findCliques(graph, mutableListOf(), graph.keys.toMutableList(), mutableListOf(), result)
    return result
}

private fun <V> findCliques(
    graph: Map<V, Set<V>>,
    potentialClique: MutableList<V>,
    candidates: MutableList<V>,
    alreadyFound: MutableList<V>,
    result: MutableList<Set<V>>
) {
    val candidatesArray = candidates.toMutableList()
    if (!checkEnd(graph, candidates, alreadyFound)) {
        for (candidate in candidatesArray) {
            val newCandidates = mutableListOf<V>()
            val newAlreadyFound = mutableListOf<V>()

            potentialClique.add(candidate)
            candidates.remove(candidate)

            candidates.asSequence().filter { it in graph.getValue(candidate) }.toCollection(newCandidates)
            alreadyFound.asSequence().filter { it in graph.getValue(candidate) }.toCollection(newAlreadyFound)

            if (newCandidates.isEmpty() && newAlreadyFound.isEmpty()) {
                result.add(potentialClique.toSet())
            } else {
                findCliques(
                    graph,
                    potentialClique,
                    newCandidates,
                    newAlreadyFound,
                    result
                )
            }

            alreadyFound.add(candidate)
            potentialClique.remove(candidate)
        }
    }
}

private fun <V> checkEnd(
    graph: Map<V, Set<V>>,
    candidates: List<V>,
    alreadyFound: List<V>
): Boolean {
    var end = false
    var edgecounter: Int
    for (found in alreadyFound) {
        edgecounter = 0
        for (candidate in candidates) {
            if (candidate in graph.getValue(found)) {
                edgecounter++
            }
        }
        if (edgecounter == candidates.size) {
            end = true
        }
    }
    return end
}