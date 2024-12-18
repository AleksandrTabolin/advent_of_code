import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day17Test {

    private val input1 = """
        Register A: 729
        Register B: 0
        Register C: 0

        Program: 0,1,5,4,3,0
    """.trimIndent()

    private val input2 = """
        Register A: 2024
        Register B: 0
        Register C: 0

        Program: 0,3,5,4,3,0
    """.trimIndent()


    @Test
    fun testOpt() {
        var reg = Day17.Registers(c = 9)
        Day17.compute(reg, listOf(2, 6))
        assertEquals(reg.b, 1)

        reg = Day17.Registers(a = 10)
        assertEquals(Day17.compute(reg, listOf(5, 0, 5, 1, 5, 4)), listOf(0, 1, 2))

        reg = Day17.Registers(a = 2024)
        assertEquals(Day17.compute(reg, listOf(0, 1, 5, 4, 3, 0)), listOf(4, 2, 5, 6, 7, 7, 7, 7, 3, 1, 0))
        assertEquals(reg.a, 0)

        reg = Day17.Registers(b = 29)
        Day17.compute(reg, listOf(1, 7))
        assertEquals(reg.b, 26)

        reg = Day17.Registers(b = 2024, c = 43690)
        Day17.compute(reg, listOf(4, 0))
        assertEquals(reg.b, 44354)
    }

    @Test
    fun test1p1() {
        val input = input1.splitToSequence("\n")
        Assertions.assertEquals("4,6,3,5,6,3,5,2,1,0", Day17.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day17")
        Assertions.assertEquals("7,4,2,5,1,4,6,0,4", Day17.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input2.splitToSequence("\n")
        Assertions.assertEquals(117440, Day17.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day17")

        Assertions.assertEquals(164278764924605, Day17.solvePart2(input))
    }
}