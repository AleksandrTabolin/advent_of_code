import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day13Test {

    private val input = """
        #.##..##.
        ..#.##.#.
        ##......#
        ##......#
        ..#.##.#.
        ..##..##.
        #.#.##.#.
        
        #...##..#
        #....#..#
        ..##..###
        #####.##.
        #####.##.
        ..##..###
        #....#..#
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(405, Day13.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day13")
        Assertions.assertEquals(30518, Day13.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(400, Day13.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day13")
        Assertions.assertEquals(36735, Day13.solvePart2(input))
    }
}