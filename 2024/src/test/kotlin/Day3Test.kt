import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day3Test {

    private val input1 = """
        xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
    """.trimIndent()

    private val input2 = """
        xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    """

    @Test
    fun test1p1() {
        val input = input1.splitToSequence("\n")
        Assertions.assertEquals(161, Day3.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day3")
        Assertions.assertEquals(153469856, Day3.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input2.splitToSequence("\n")
        Assertions.assertEquals(48, Day3.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day3")
        Assertions.assertEquals(77055967, Day3.solvePart2(input))
    }
}