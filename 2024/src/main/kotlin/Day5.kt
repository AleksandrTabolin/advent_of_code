import java.util.*

object Day5 {

    fun solvePart1(input: Sequence<String>): Int {
        val (rules, seq) = input.parseInput()
        return seq.filter { it.isCorrect(rules) }.sumOf { it[it.size / 2] }
    }

    fun solvePart2(input: Sequence<String>): Int {
        val (rules, seq) = input.parseInput()
        return seq.filter { !it.isCorrect(rules) }.map { it.reorder(rules) }.sumOf { it[it.size / 2] }
    }

    private fun List<Int>.reorder(rules: Map<Int, Set<Int>>): List<Int> {
        val candidates = LinkedList(this)
        val result = mutableListOf<Int>()

        var candidate = candidates.pollLast()
        while (candidate != null) {
            var success = true
            for (c in candidates) {
                val r = rules[candidate] ?: break
                if (c in r) {
                    success = false
                    break
                }
            }
            if (success) {
                result.add(candidate)
            } else {
                candidates.addFirst(candidate)
            }
            candidate = candidates.pollLast()
        }
        return result
    }

    private fun List<Int>.isCorrect(rules: Map<Int, Set<Int>>): Boolean {
        for (i in lastIndex downTo 1) {
            val a = rules[get(i)] ?: continue
            for (j in i - 1 downTo 0) {
                if (get(j) in a) return false
            }
        }
        return true
    }

    private fun Sequence<String>.parseInput(): Pair<Map<Int, Set<Int>>, List<List<Int>>> {
        val rules = mutableMapOf<Int, MutableSet<Int>>()
        val seq = mutableListOf<List<Int>>()

        var isFirstPart = true

        forEach { line ->
            if (line.isBlank()) {
                isFirstPart = false
            } else if (isFirstPart) {
                line.split('|').let { (key, value) ->
                    rules.getOrPut(key.toInt()) { mutableSetOf() }.add(value.toInt())
                }
            } else {
                seq.add(line.split(',').map { it.toInt() })
            }
        }
        return rules to seq
    }
}