import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day23Test {

    private val input = """
        kh-tc
        qp-kh
        de-cg
        ka-co
        yn-aq
        qp-ub
        cg-tb
        vc-aq
        tb-ka
        wh-tc
        yn-cg
        kh-ub
        ta-co
        de-co
        tc-td
        tb-wq
        wh-td
        ta-ka
        td-qp
        aq-cg
        wq-ub
        ub-vc
        de-ta
        wq-aq
        wq-vc
        wh-yn
        ka-de
        kh-ta
        co-tc
        wh-qp
        tb-vc
        td-yn
    """.trimIndent()

    @Test
    fun test1p1() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals(7, Day23.solvePart1(input))
    }

    @Test
    fun test2p1() {
        val input = FileHelper.readByLines("day23")
        Assertions.assertEquals(1054, Day23.solvePart1(input))
    }

    @Test
    fun test1p2() {
        val input = input.splitToSequence("\n")
        Assertions.assertEquals("co,de,ka,ta", Day23.solvePart2(input))
    }

    @Test
    fun test2p2() {
        val input = FileHelper.readByLines("day23")
        Assertions.assertEquals("ch,cz,di,gb,ht,ku,lu,tw,vf,vt,wo,xz,zk", Day23.solvePart2(input))
    }
}