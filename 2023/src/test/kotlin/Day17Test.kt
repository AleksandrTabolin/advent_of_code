import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day17Test {

    private val input = """
        2413432311323
        3215453535623
        3255245654254
        3446585845452
        4546657867536
        1438598798454
        4457876987766
        3637877979653
        4654967986887
        4564679986453
        1224686865563
        2546548887735
        4322674655533
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(102, Day17.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day17")
        Assertions.assertEquals(870, Day17.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(-1, Day17.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day17")
        Assertions.assertEquals(-1, Day17.solvePart2(input))
    }
}