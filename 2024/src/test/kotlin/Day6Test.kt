import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day6Test {

    private val input = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(41, Day6.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day6")
        Assertions.assertEquals(4663, Day6.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(6, Day6.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day6")
        Assertions.assertEquals(1530, Day6.solvePart2(input))
    }
}