import java.io.File

fun main() {
    val pattern = "do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)".toRegex()

    val mulOperations = pattern.findAll(File("input.txt").readText())
    var enabled = true
    var sum = 0
    for (match in mulOperations) {
        when (match.value) {
            "do()" -> enabled = true
            "don't()" -> enabled = false
            else -> {
                val (a, b) = match.destructured
                sum += if (enabled) a.toInt() * b.toInt() else 0
            }
        }
    }
    println(sum)
}
