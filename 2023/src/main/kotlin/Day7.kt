object Day7 {

    fun solvePart1(input: Sequence<String>): Int {
        return process(
            input = input,
            cardTypes = listOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'),
        ) { cardTypes, cards ->
            cards
                .countCards()
                .calcPoints(isDistinct = { cards.isDistinct(cardTypes) })
        }
    }

    fun solvePart2(input: Sequence<String>): Int {
        return process(
            input = input,
            cardTypes = listOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'),
        ) { cardTypes, cards ->
            cards
                .countCards { it != 'J' }
                .apply {
                    val jokersCount = cards.count { it == 'J' }
                    if (jokersCount == 5) {
                        set('J', 5)
                    } else if (jokersCount > 0) {
                        val (card, count) = entries.maxBy { (_, count) -> count }
                        set(card, count + jokersCount)
                    }
                }
                .calcPoints(isDistinct = { cards.isDistinct(cardTypes) })
        }
    }

    private inline fun String.countCards(filter: ((Char) -> Boolean) = { true }): MutableMap<Char, Int> {
        val cards = this
        val counter = mutableMapOf<Char, Int>()
        for (card in cards) {
            if (filter.invoke(card)) {
                counter.putIfAbsent(card, 0)
                counter[card] = counter.getValue(card) + 1
            }
        }
        return counter
    }

    private inline fun Map<Char, Int>.calcPoints(isDistinct: () -> Boolean): Int {
        return when {
            hasSize(5) -> 7
            hasSize(4) -> 6
            hasSizes(3, 2) -> 5
            hasSize(3) -> 4
            hasSizes(2, 2) -> 3
            hasSize(2) -> 2
            isDistinct.invoke() -> 1
            else -> 0
        }
    }

    private fun process(
        input: Sequence<String>,
        cardTypes: List<Char>,
        calcPoints: (cardTypes: List<Char>, cards: String) -> Int
    ): Int {
        return input
            .map { line ->
                val (cards, bid) = line.split(' ')
                val points = calcPoints(cardTypes, cards)
                Triple(cards, bid.toInt(), points)
            }
            .sortedWith { a, b ->
                compareValues(a.third, b.third).takeIf { it != 0 } ?: compareCards(cardTypes, a.first, b.first)
            }
            .mapIndexed { index, (_, bid, _) -> (index + 1) * bid }
            .sum()
    }

    private fun Map<Char, Int>.hasSize(size: Int): Boolean {
        return values.any { it == size }
    }

    private fun Map<Char, Int>.hasSizes(size1: Int, size2: Int): Boolean {
        val key1 = entries.find { (_, value) -> value == size1 }?.key ?: return false
        return entries.any { (key, value) -> value == size2 && key1 != key }
    }

    private fun String.isDistinct(cardTypes: List<Char>): Boolean {
        val indexes = map { cardTypes.indexOf(it) }.sorted()

        var prevIndex = -1
        for (index in indexes) {
            if (prevIndex != -1 && index - prevIndex != 1) return false
            prevIndex = index
        }
        return true
    }

    private fun compareCards(cardTypes: List<Char>, left: String, right: String): Int {
        for (i in left.indices) {
            if (cardTypes.indexOf(left[i]) > cardTypes.indexOf(right[i])) return 1
            if (cardTypes.indexOf(left[i]) < cardTypes.indexOf(right[i])) return -1
        }
        return 0
    }
}