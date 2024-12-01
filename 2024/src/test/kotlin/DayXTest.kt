import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.DayX

class DayXTest {

    private val input = """
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(-1, DayX.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("dayX")
        Assertions.assertEquals(-1, DayX.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(-1, DayX.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("dayX")
        Assertions.assertEquals(-1, DayX.solvePart2(input))
    }
}