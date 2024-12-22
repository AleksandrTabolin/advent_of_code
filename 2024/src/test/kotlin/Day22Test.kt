import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
class Day22Test {

    private val input = """
        1
        10
        100
        2024
    """.trimIndent()

    private val input2 = """
        1
        2
        3
        2024
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(37327623, Day22.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day22")
        Assertions.assertEquals(18317943467, Day22.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input2.splitToSequence("\n")
        Assertions.assertEquals(23, Day22.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day22")
        Assertions.assertEquals(2018, Day22.solvePart2(input))
    }
}