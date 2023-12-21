object FileHelper {
    fun readByLines(fileName: String): Sequence<String> {
        return this::class.java.getResourceAsStream(fileName).bufferedReader().lineSequence()
    }

    fun readText(fileName: String): String {
        return this::class.java.getResourceAsStream(fileName).bufferedReader().readText()
    }
}


