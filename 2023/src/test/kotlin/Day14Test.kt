import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.FileHelper

class Day14Test {

    private val input = """
        O....#....
        O.OO#....#
        .....##...
        OO.#O....O
        .O.....O#.
        O.#..O.#.#
        ..O..#O..O
        .......O..
        #....###..
        #OO..#....
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(136, Day14.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day14")
        Assertions.assertEquals(110090, Day14.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(64, Day14.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day14")
        Assertions.assertEquals(95254, Day14.solvePart2(input))
    }
}