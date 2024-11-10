package main.kotlin

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class Student_list_txt : Student_list(), StudentListStrategy {
    override fun readFromFile(path: String) {
        val file = File(path)
        val result = mutableListOf<Student>()
        var text: List<String> = listOf()
        try {
            text = file.readLines()
        } catch (e: FileNotFoundException) {
            println("File not found: $path")
        } catch (e: IOException) {
            println("Error reading from file: $path")
        }

        for (line in text) {
            var splited = line.split(" ")
            result.add(Student(
                splited.get(0).toInt(),
                splited.get(1),
                splited.get(2),
                splited.get(3),
                splited.getOrNull(4),
                splited.getOrNull(5),
                splited.getOrNull(6),
                splited.getOrNull(7),
            ))
        }
        data = result
    }

    override fun writeToFile(path: String) {
        val file = File(path)
        var text = ""
        for (line in data) text += line.toString() + "\n"
        file.writeText(text)
    }
}