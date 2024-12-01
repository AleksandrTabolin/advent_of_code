import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day21Test {

    private val input = """
        ...........
        .....###.#.
        .###.##..#.
        ..#.#...#..
        ....#.#....
        .##..S####.
        .##..#...#.
        .......##..
        .##.#.####.
        .##..##.##.
        ...........
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(16, Day21.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day21")
        Assertions.assertEquals(-1, Day21.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(-1, Day21.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day21")
        Assertions.assertEquals(-1, Day21.solvePart2(input))
    }
}