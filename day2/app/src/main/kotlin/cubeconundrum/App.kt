/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package cubeconundrum

import java.io.File
import kotlin.text.filter
import kotlin.math.*

fun returnIDs(fileName: String): Int {
    val currentDir = System.getProperty("user.dir")
    val file: File = File("$currentDir/src/main/resources/$fileName")
    var ids: Int = 0
    file.forEachLine() {
        val text: String = it
        val regex: Regex = Regex("(\\d+ red|\\d+ blue|\\d+ green)")
        val matches = regex.findAll(text)
        val names: String = matches.map { it.groupValues[1] }.joinToString().trim()
        val revealedCubes: List<String> = names.split(",")
        var flag: Boolean = true
        for (revelation in revealedCubes) {
            if ("red" in revelation) {
                flag = (flag && ((revelation.filter { it.isDigit() }).toInt() <= 12))
            } else if ("green" in revelation) {
                flag = (flag && ((revelation.filter { it.isDigit() }).toInt() <= 13))
            } else {
                flag = (flag && ((revelation.filter { it.isDigit() }).toInt() <= 14))
            }
        }
        if (flag) {
            val getNumber: Regex = Regex("Game \\d+")
            val m = getNumber.find(text)
            ids += (m?.value!!.filter { it.isDigit() }).toInt()
        }
    }
    return ids
}

fun powerSetOfCubes(fileName: String): Int {
    val currentDir = System.getProperty("user.dir")
    val file: File = File("$currentDir/src/main/resources/$fileName")
    var powerSets: Int = 0
    file.forEachLine() {
        val text: String = it
        val regex: Regex = Regex("(\\d+ red|\\d+ blue|\\d+ green)")
        val matches = regex.findAll(text)
        val names: String = matches.map { it.groupValues[1] }.joinToString().trim()
        val revealedCubes: List<String> = names.split(",")
        var red: Int = 0
        var green: Int = 0
        var blue: Int = 0
        for (revelation in revealedCubes) {
            if ("red" in revelation) {
                red = max(red, (revelation.filter { it.isDigit() }).toInt())
            } else if ("green" in revelation) {
                green = max(green, (revelation.filter { it.isDigit() }).toInt())
            } else {
                blue = max(blue, (revelation.filter { it.isDigit() }).toInt())
            }
        }
        powerSets += (red * green * blue)
    }
    return powerSets
}

fun main() {
    val ids: Int = returnIDs("input.txt")
    println("Sum of all IDs: $ids")
    val powerSet: Int = powerSetOfCubes("input.txt")
    println("Sum of all power sets: $powerSet")
}
