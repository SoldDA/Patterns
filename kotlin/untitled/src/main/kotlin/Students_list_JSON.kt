package main.kotlin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Students_list_JSON(path: String) : Student_list(path) {
    private val gson = Gson()

    // Форматирование студентов в JSON
    override fun formatToFile(studentList: MutableList<StudentSeriarizable>): String {
        return gson.toJson(studentList)
    }

    // Парсинг JSON в список студентов
    override fun parseFile(content: String): MutableList<StudentSeriarizable> {
        val listType = object : TypeToken<MutableList<StudentSeriarizable>>() {}.type
        return gson.fromJson(content, listType) ?: mutableListOf()
    }
}