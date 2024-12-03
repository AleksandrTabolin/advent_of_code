import kotlin.system.measureTimeMillis


fun benchmark(cycles: Int = 100, block: () -> Unit) {
    val r = mutableListOf<Long>()
    repeat(cycles) {
        r.add(measureTimeMillis {
            block.invoke()
        })
    }
    println(r.average())
}