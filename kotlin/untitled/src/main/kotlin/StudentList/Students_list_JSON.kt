package main.kotlin.StudentList

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

import main.kotlin.*

class Students_list_JSON : Student_list_super(), StudentListStrategy {
    override fun readFromFile(path: String) {
        val listType = object : TypeToken<MutableList<Student>>() {}.type
        val gson = Gson()
        val file = File(path)
        var text: String = ""
        try {
            text = file.readText()
        } catch (e: FileNotFoundException) {
            println("File not found: $path")
        } catch (e: IOException) {
            println("Error reading from file: $path")
        }

        data = gson.fromJson(text, listType) ?: mutableListOf()
    }

    override fun writeToFile(path: String) {
        var gson = Gson()
        var JSON = gson.toJson(data)
        val file = File(path)
        file.writeText(JSON)
    }
}