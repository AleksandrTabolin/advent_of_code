import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day15Test {

    private val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"

    @Test
    fun test1p1() {
        Assertions.assertEquals(1320, Day15.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readText("day15")
        Assertions.assertEquals(508498, Day15.solvePart1(input))
    }

    @Test
    fun test1p2() {
        Assertions.assertEquals(145, Day15.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readText("day15")
        Assertions.assertEquals(279116, Day15.solvePart2(input))
    }
}