import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day8Test {

    private val input = """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(14, Day8.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day8")
        Assertions.assertEquals(280, Day8.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(34, Day8.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day8")
        Assertions.assertEquals(958, Day8.solvePart2(input))
    }
}