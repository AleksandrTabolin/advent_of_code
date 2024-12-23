object Day21Dict {
    val DIALS = mapOf(
        '7' to mapOf(
            '7' to listOf("A"),
            '4' to listOf("vA"),
            '8' to listOf(">A"),
            '1' to listOf("vvA"),
            '5' to listOf("v>A", ">vA"),
            '9' to listOf(">>A"),
            '2' to listOf("vv>A", "v>vA", ">vvA"),
            '6' to listOf("v>>A", ">v>A", ">>vA"),
            '0' to listOf("vv>vA", "v>vvA", ">vvvA"),
            '3' to listOf("vv>>A", "v>v>A", "v>>vA", ">vv>A", ">v>vA", ">>vvA"),
            'A' to listOf("vv>v>A", "vv>>vA", "v>vv>A", "v>v>vA", "v>>vvA", ">vvv>A", ">vv>vA", ">v>vvA", ">>vvvA")
        ),
        '8' to mapOf(
            '8' to listOf("A"),
            '5' to listOf("vA"),
            '7' to listOf("<A"),
            '9' to listOf(">A"),
            '2' to listOf("vvA"),
            '4' to listOf("v<A", "<vA"),
            '6' to listOf("v>A", ">vA"),
            '0' to listOf("vvvA"),
            '1' to listOf("vv<A", "v<vA", "<vvA"),
            '3' to listOf("vv>A", "v>vA", ">vvA"),
            'A' to listOf("vvv>A", "vv>vA", "v>vvA", ">vvvA")
        ),
        '9' to mapOf(
            '9' to listOf("A"),
            '6' to listOf("vA"),
            '8' to listOf("<A"),
            '3' to listOf("vvA"),
            '5' to listOf("v<A", "<vA"),
            '7' to listOf("<<A"),
            'A' to listOf("vvvA"),
            '2' to listOf("vv<A", "v<vA", "<vvA"),
            '4' to listOf("v<<A", "<v<A", "<<vA"),
            '0' to listOf("vvv<A", "vv<vA", "v<vvA", "<vvvA"),
            '1' to listOf("vv<<A", "v<v<A", "v<<vA", "<vv<A", "<v<vA", "<<vvA")
        ),
        '4' to mapOf(
            '4' to listOf("A"),
            '7' to listOf("^A"),
            '1' to listOf("vA"),
            '5' to listOf(">A"),
            '8' to listOf("^>A", ">^A"),
            '2' to listOf("v>A", ">vA"),
            '6' to listOf(">>A"),
            '9' to listOf("^>>A", ">^>A", ">>^A"),
            '0' to listOf("v>vA", ">vvA"),
            '3' to listOf("v>>A", ">v>A", ">>vA"),
            'A' to listOf("v>v>A", "v>>vA", ">vv>A", ">v>vA", ">>vvA")
        ),
        '5' to mapOf(
            '5' to listOf("A"),
            '8' to listOf("^A"),
            '2' to listOf("vA"),
            '4' to listOf("<A"),
            '6' to listOf(">A"),
            '7' to listOf("^<A", "<^A"),
            '9' to listOf("^>A", ">^A"),
            '0' to listOf("vvA"),
            '1' to listOf("v<A", "<vA"),
            '3' to listOf("v>A", ">vA"),
            'A' to listOf("vv>A", "v>vA", ">vvA")
        ),
        '6' to mapOf(
            '6' to listOf("A"),
            '9' to listOf("^A"),
            '3' to listOf("vA"),
            '5' to listOf("<A"),
            '8' to listOf("^<A", "<^A"),
            'A' to listOf("vvA"),
            '2' to listOf("v<A", "<vA"),
            '4' to listOf("<<A"),
            '7' to listOf("^<<A", "<^<A", "<<^A"),
            '0' to listOf("vv<A", "v<vA", "<vvA"),
            '1' to listOf("v<<A", "<v<A", "<<vA")
        ),
        '1' to mapOf(
            '1' to listOf("A"),
            '4' to listOf("^A"),
            '2' to listOf(">A"),
            '7' to listOf("^^A"),
            '5' to listOf("^>A", ">^A"),
            '0' to listOf(">vA"),
            '3' to listOf(">>A"),
            '8' to listOf("^^>A", "^>^A", ">^^A"),
            '6' to listOf("^>>A", ">^>A", ">>^A"),
            'A' to listOf(">v>A", ">>vA"),
            '9' to listOf("^^>>A", "^>^>A", "^>>^A", ">^^>A", ">^>^A", ">>^^A")
        ),
        '2' to mapOf(
            '2' to listOf("A"),
            '5' to listOf("^A"),
            '0' to listOf("vA"),
            '1' to listOf("<A"),
            '3' to listOf(">A"),
            '8' to listOf("^^A"),
            '4' to listOf("^<A", "<^A"),
            '6' to listOf("^>A", ">^A"),
            'A' to listOf("v>A", ">vA"),
            '7' to listOf("^^<A", "^<^A", "<^^A"),
            '9' to listOf("^^>A", "^>^A", ">^^A")
        ),
        '3' to mapOf(
            '3' to listOf("A"),
            '6' to listOf("^A"),
            'A' to listOf("vA"),
            '2' to listOf("<A"),
            '9' to listOf("^^A"),
            '5' to listOf("^<A", "<^A"),
            '0' to listOf("v<A", "<vA"),
            '1' to listOf("<<A"),
            '8' to listOf("^^<A", "^<^A", "<^^A"),
            '4' to listOf("^<<A", "<^<A", "<<^A"),
            '7' to listOf("^^<<A", "^<^<A", "^<<^A", "<^^<A", "<^<^A", "<<^^A")
        ),
        '0' to mapOf(
            '0' to listOf("A"),
            '2' to listOf("^A"),
            'A' to listOf(">A"),
            '5' to listOf("^^A"),
            '1' to listOf("^<A"),
            '3' to listOf("^>A", ">^A"),
            '8' to listOf("^^^A"),
            '4' to listOf("^^<A", "^<^A"),
            '6' to listOf("^^>A", "^>^A", ">^^A"),
            '7' to listOf("^^^<A", "^^<^A", "^<^^A"),
            '9' to listOf("^^^>A", "^^>^A", "^>^^A", ">^^^A")
        ),
        'A' to mapOf(
            'A' to listOf("A"),
            '3' to listOf("^A"),
            '0' to listOf("<A"),
            '6' to listOf("^^A"),
            '2' to listOf("^<A", "<^A"),
            '9' to listOf("^^^A"),
            '5' to listOf("^^<A", "^<^A", "<^^A"),
            '1' to listOf("^<<A", "<^<A"),
            '8' to listOf("^^^<A", "^^<^A", "^<^^A", "<^^^A"),
            '4' to listOf("^^<<A", "^<^<A", "^<<^A", "<^^<A", "<^<^A"),
            '7' to listOf("^^^<<A", "^^<^<A", "^^<<^A", "^<^^<A", "^<^<^A", "^<<^^A", "<^^^<A", "<^^<^A", "<^<^^A")
        )
    )

    val ARROWS = mapOf(
        '^' to mapOf(
            '^' to listOf("A"),
            'v' to listOf("vA"),
            'A' to listOf(">A"),
            '<' to listOf("v<A"),
            '>' to listOf("v>A", ">vA")
        ),
        'A' to mapOf(
            'A' to listOf("A"),
            '>' to listOf("vA"),
            '^' to listOf("<A"),
            'v' to listOf("v<A", "<vA"),
            '<' to listOf("v<<A", "<v<A")
        ),
        '<' to mapOf(
            '<' to listOf("A"),
            'v' to listOf(">A"),
            '^' to listOf(">^A"),
            '>' to listOf(">>A"),
            'A' to listOf(">^>A", ">>^A")
        ),
        'v' to mapOf(
            'v' to listOf("A"),
            '^' to listOf("^A"),
            '<' to listOf("<A"),
            '>' to listOf(">A"),
            'A' to listOf("^>A", ">^A")
        ),
        '>' to mapOf(
            '>' to listOf("A"),
            'A' to listOf("^A"),
            'v' to listOf("<A"),
            '^' to listOf("^<A", "<^A"),
            '<' to listOf("<<A")
        )
    )
}