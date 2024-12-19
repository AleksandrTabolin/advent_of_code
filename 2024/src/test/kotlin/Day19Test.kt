import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day19Test {

    private val input = """
        r, wr, b, g, bwu, rb, gb, br

        brwrr
        bggr
        gbbr
        rrbgbr
        ubwu
        bwurrg
        brgr
        bbrgwb
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(6, Day19.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day19")
        Assertions.assertEquals(255, Day19.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(16, Day19.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day19")
        Assertions.assertEquals(621820080273474, Day19.solvePart2(input))
    }
}