object Day22 {

    private const val SEQUENCE_SIZE = 4

    fun solvePart1(input: Sequence<String>): Long {
        return input.map { generateSecretNumbers(it.toLong()).take(2000).last() }.sum()
    }

    fun solvePart2(input: Sequence<String>): Long {
        return input.map { it.toLong().bestOffer(2000) }
            .fold(mutableMapOf<String, Long>().withDefault { 0 }) { acc, item ->
                item.forEach { (key, value) -> acc[key] = acc.getValue(key) + value }
                acc
            }
            .maxOf { it.value }
    }

    private fun Long.bestOffer(n: Int): Map<String, Long> {
        val result = mutableMapOf<String, Long>().withDefault { 0 }
        var prev = this % 10
        val deque = ArrayDeque<Long>().also { it.addFirst(prev) }
        generateSecretNumbers(this).map { it % 10 }.take(n).forEach { number ->
            val diff = number - prev
            if (deque.size == SEQUENCE_SIZE) deque.removeLast()
            deque.addFirst(diff)
            val key = deque.asString()
            if (deque.size == SEQUENCE_SIZE && key !in result) result[key] = number
            prev = number
        }
        return result
    }

    private fun ArrayDeque<Long>.asString(): String {
        val input = this
        return buildString {
            for (i in input.lastIndex downTo 0) {
                append(input[i])
                if (i > 0) append(',')
            }
        }
    }

    private fun generateSecretNumbers(seed: Long): Sequence<Long> = sequence {
        var secret = seed
        while (true) {
            secret = secret.mixAndPrune(secret * 64)
            secret = secret.mixAndPrune(secret / 32)
            secret = secret.mixAndPrune(secret * 2048)
            yield(secret)
        }
    }

    private fun Long.mixAndPrune(v: Long) = (this xor v) % 16777216
}