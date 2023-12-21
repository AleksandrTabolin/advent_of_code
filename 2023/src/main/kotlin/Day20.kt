import utils.MathUtil
import java.util.*

object Day20 {

    fun solvePart1(input: Sequence<String>): Long {
        val modules = input.parseInput()
        val counter = SignalCounter()

        repeat(1000) {
            modules.startCycle(counter)
        }

        return counter.lowCounter * counter.highCounter
    }

    fun solvePart2(input: Sequence<String>): Long {
        val modules = input.parseInput()

        var counter = 0L
        val cycles = mutableMapOf<String, Long>()

        val module = modules.values.find { it.outputs.contains("rx") } as Module.Conjunction

        while (true) {
            counter += 1
            modules.startCycle(
                afterSignal = { signal ->
                    if (signal.to == module.name ) {
                        module.state.entries.forEach {(key, value) ->
                            if (value && key !in cycles) {
                                cycles[key] = counter
                            }
                        }
                    }
                }
            )
            if (cycles.size == module.state.size) break
        }
        return MathUtil.findLCM(cycles.values.toList())
    }

    private fun Map<String, Module>.startCycle(
        counter: SignalCounter? = null,
        afterSignal: (signal: Signal) -> Unit = {}

    ) {
        val queue = LinkedList<Signal>().apply {
            add(Signal(from = "Button", to = "broadcaster"))
            counter?.count(false)
        }

        while (queue.isNotEmpty()) {
            val signal = queue.pop()
            val module = get(signal.to)
            module ?: continue

            val signalValue: Boolean? = when (module) {
                is Module.Broadcast -> signal.value
                is Module.Conjunction -> {
                    module.state[signal.from] = signal.value
                    !module.state.values.all { it }
                }

                is Module.FlipFlop -> {
                    if (!signal.value) {
                        module.state = !module.state
                        module.state
                    } else {
                        null
                    }
                }
            }

            if (signalValue == null) continue

            afterSignal.invoke(signal)

            module.outputs.forEach { nextModule ->
                counter?.count(signalValue)
                queue.addLast(Signal(module.name, nextModule, signalValue))
            }
        }
    }

    private fun Sequence<String>.parseInput(): Map<String, Module> {
        val result = mutableMapOf<String, Module>()

        forEach { line ->
            when {
                line.startsWith("broadcaster") -> {
                    val (name, outputs) = line.split(" -> ")
                    result[name] = Module.Broadcast(name, outputs = outputs.split(", "))
                }

                line.startsWith("%") -> {
                    val (left, outputs) = line.split(" -> ")
                    val name = left.drop(1)
                    result[name] = Module.FlipFlop(name = name, outputs = outputs.split(", "))
                }

                line.startsWith("&") -> {
                    val (left, outputs) = line.split(" -> ")
                    val name = left.drop(1)
                    result[name] = Module.Conjunction(name = name, outputs = outputs.split(", "))
                }
            }
        }
        result.entries.forEach { (name, module) ->
            module.outputs.forEach { outputName ->
                (result[outputName] as? Module.Conjunction)?.state?.put(name, false)
            }
        }
        return result
    }

    data class Signal(val from: String, val to: String, val value: Boolean = false)

    class SignalCounter {
        var highCounter = 0L
        var lowCounter = 0L

        fun count(signal: Boolean) {
            if (signal) {
                highCounter += 1
            } else {
                lowCounter += 1
            }
        }

        override fun toString(): String {
            return "SignalCounter[high: $highCounter, low: $lowCounter]"
        }
    }

    sealed class Module {
        abstract val name: String
        abstract val outputs: List<String>

        data class Broadcast(override val name: String, override val outputs: List<String>) : Module()
        data class FlipFlop(override val name: String, var state: Boolean = false, override val outputs: List<String>) :
            Module()

        data class Conjunction(
            override val name: String,
            val state: MutableMap<String, Boolean> = mutableMapOf(),
            override val outputs: List<String>
        ) : Module()
    }
}