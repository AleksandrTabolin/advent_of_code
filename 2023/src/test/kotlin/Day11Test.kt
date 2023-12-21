import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.FileHelper

class Day11Test {

    private val input = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(374, Day11.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day11")
        Assertions.assertEquals(9974721, Day11.solvePart1(input))
    }

    @Test
    fun test1p2() {
        listOf(374L to 2L, 1030L to 10L, 8410L to 100L).forEach { (expected, placeHolderMultiplier) ->
            val input = input.splitToSequence("\n")
            Assertions.assertEquals(expected, Day11.solvePart2(input, placeHolderMultiplier))
        }
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day11")
        Assertions.assertEquals(702770569197, Day11.solvePart2(input, 1000000))
    }
}