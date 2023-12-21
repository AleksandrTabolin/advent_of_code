import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.FileHelper

class Day7Test {

    private val input = """
          32T3K 765
          T55J5 684
          KK677 28
          KTJJT 220
          QQQJA 483
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(6440, Day7.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day7")
        Assertions.assertEquals(251029473, Day7.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(5905, Day7.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day7")
        Assertions.assertEquals(251003917, Day7.solvePart2(input))
    }
}