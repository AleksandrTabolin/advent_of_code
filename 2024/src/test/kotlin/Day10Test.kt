import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day10Test {

    private val input = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(36, Day10.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day10")
        Assertions.assertEquals(825, Day10.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(81, Day10.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day10")
        Assertions.assertEquals(1805, Day10.solvePart2(input))
    }
}