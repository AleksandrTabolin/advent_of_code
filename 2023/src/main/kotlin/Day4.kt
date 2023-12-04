import kotlin.math.max
import kotlin.math.min

object Day4 {

    private val regex = Regex("\\s+")

    fun solvePart1(input: Sequence<String>): Int {
        return input
            .map { line -> line.parseCard() }
            .map { (winNumber, numbers) -> winNumber.intersect(numbers.toSet()).size }
            .map { size -> size.calcProgression() }
            .sum()
    }

    fun solvePart2(input: Sequence<String>): Int {
        val cards = input.map { it.parseCard() }.toList()
        val winCards = MutableList(cards.size) { 1 }

        for (index in cards.indices) {
            val (winNumber, numbers) = cards[index]
            val numbersWin = winNumber.intersect(numbers.toSet()).size
            val from = index + 1
            if (from == cards.size) continue

            val to = min(cards.size - 1, index + numbersWin)

            for (i in from..to) {
                winCards[i] += winCards[index]
            }
        }

        return winCards.sum()
    }


    private fun Int.calcProgression(): Int {
        if (this == 0) return 0
        var result = 1
        repeat(max(0, this - 1)) {
            result *= 2
        }
        return result
    }

    private fun String.parseCard(): Pair<List<String>, List<String>> {
        val (winNumber, numbers) = substringAfter(": ").split(" | ")
        return winNumber.trim().split(regex) to numbers.trim().split(regex)
    }
}