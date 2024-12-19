object Day19 {

    fun solvePart1(input: Sequence<String>): Int {
        val (patterns, variants) = input.parseInput()
        val cache = mutableMapOf<String, Long>()
        return variants.count { countVariants(cache, it, patterns) > 0 }
    }

    fun solvePart2(input: Sequence<String>): Long {
        val (patterns, variants) = input.parseInput()
        val cache = mutableMapOf<String, Long>()
        return variants.sumOf { v -> countVariants(cache, v, patterns) }
    }

    private fun countVariants(cache: MutableMap<String, Long>, variant: String, patterns: List<String>): Long {
        if (variant in cache) return cache.getValue(variant)
        return patterns.asSequence().filter(variant::startsWith).sumOf { pattern ->
            if (pattern == variant) 1 else countVariants(cache, variant.substring(pattern.length), patterns)
        }.also { cache[variant] = it }
    }

    private fun Sequence<String>.parseInput(): Pair<List<String>, List<String>> {
        return this.toList().let { it.first().split(", ") to it.drop(2) }
    }
}