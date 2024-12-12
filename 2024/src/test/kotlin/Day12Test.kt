import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day12Test {

    private val input = """
RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(1930, Day12.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day12")
        Assertions.assertEquals(1518548, Day12.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(1206, Day12.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day12")
        Assertions.assertEquals(909564, Day12.solvePart2(input))
    }
}