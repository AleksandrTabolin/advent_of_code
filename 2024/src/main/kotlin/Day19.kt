import utils.withCache

object Day19 {

    fun solvePart1(input: Sequence<String>): Int {
        val (patterns, variants) = input.parseInput()
        val cache = mutableMapOf<String, Long>()
        return variants.count { countVariants(cache, it, patterns) > 0 }
    }

    fun solvePart2(input: Sequence<String>): Long {
        val (patterns, variants) = input.parseInput()
        val cache = mutableMapOf<String, Long>()
        return variants.sumOf { countVariants(cache, it, patterns) }
    }

    private fun countVariants(cache: MutableMap<String, Long>, variant: String, patterns: List<String>): Long {
        return cache.withCache(variant) { v ->
            patterns.asSequence().filter(variant::startsWith)
                .sumOf { if (it == v) 1 else countVariants(cache, v.substring(it.length), patterns) }
        }
    }

    private fun Sequence<String>.parseInput() = toList().let { it.first().split(", ") to it.drop(2) }
}