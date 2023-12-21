import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day3Test {

    private val input = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...${'$'}.*....
        .664.598..                       
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(4361, Day3.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day3")
        Assertions.assertEquals(553079, Day3.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(467835, Day3.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day3")
        Assertions.assertEquals(84363105, Day3.solvePart2(input))
    }
}