import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day21Test {

    private val input = """
        029A
        980A
        179A
        456A
        379A
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(126384, Day21.solve(input, 2))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day21")
        Assertions.assertEquals(203814, Day21.solve(input, 2))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day21")
        Assertions.assertEquals(248566068436630, Day21.solve(input, 25))
    }
}