import utils.takeDigits
import utils.withCache

object Day21 {

    fun solve(input: Sequence<String>, n: Int): Long {
        val cache = mutableMapOf<CacheKey, Long>()
        return input.sumOf { code ->
            sequence { find(code, dict = Day21Dict.DIALS) }
                .map { findShortestKeyPath(it, n, cache) }
                .min() * code.takeDigits()
        }
    }

    private fun findShortestKeyPath(code: String, maxLevel: Int, cache: MutableMap<CacheKey, Long>): Long {
        return code.indices.fold(0L) { acc, i ->
            acc + findShortestKeyPath(if (i == 0) 'A' else code[i - 1], code[i], 0, maxLevel - 1, cache)
        }
    }

    private fun findShortestKeyPath(from: Char, to: Char, level: Int, maxLevel: Int, cache: MutableMap<CacheKey, Long>): Long {
        if (level == maxLevel) return getArrowPath(from, to).minOf { it.length.toLong() }
        return cache.withCache(CacheKey(from, to, level)) {
            getArrowPath(from, to).minOf { subPath ->
                subPath.indices.fold(0L) { acc, i ->
                    acc + findShortestKeyPath(if (i == 0) 'A' else subPath[i - 1], subPath[i], level + 1, maxLevel, cache)
                }
            }
        }
    }

    private fun getArrowPath(from: Char, to: Char) = Day21Dict.ARROWS.getValue(from).getValue(to)

    private suspend fun SequenceScope<String>.find(
        code: String,
        i: Int = 0,
        result: String = "",
        dict: Map<Char, Map<Char, List<String>>>,
    ) {
        if (i == code.length) yield(result)
        else dict.getValue(if (i == 0) 'A' else code[i - 1]).getValue(code[i]).forEach {
            find(code, i + 1, result + it, dict)
        }
    }

    private data class CacheKey(val from: Char, val to: Char, val level: Int)
}