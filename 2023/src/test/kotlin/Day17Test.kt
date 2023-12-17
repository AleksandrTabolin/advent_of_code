import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day17Test {

    private val input1 = """
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

    private val input2 = """
        111111111111
        999999999991
        999999999991
        999999999991
        999999999991
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input1.splitToSequence("\n")
        Assertions.assertEquals(102, Day17.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day17")
        Assertions.assertEquals(870, Day17.solvePart1(input))
    }

    @Test
    fun test1p2() {
        Assertions.assertEquals(94, Day17.solvePart2(input1.splitToSequence("\n")))
    }

    @Test
    fun test2p2() {
        Assertions.assertEquals(71, Day17.solvePart2(input2.splitToSequence("\n")))
    }

    @Test
    fun test3p2() {
        //1040, 1066, 1120, 1154 too high
        Assertions.assertEquals(-1, Day17.solvePart2(FileHelper.readByLines("day17")))
    }
}