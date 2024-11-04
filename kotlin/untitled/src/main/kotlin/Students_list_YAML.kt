package main.kotlin

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.core.type.TypeReference

class Students_list_YAML(path: String) : Student_list(path) {
    private val objectMapper = ObjectMapper(YAMLFactory())

    // Форматирование студентов в YAML
    override fun formatToFile(studentList: MutableList<StudentSeriarizable>): String {
        return objectMapper.writeValueAsString(studentList)
    }

    // Парсинг YAML в список студентов
    override fun parseFile(content: String): MutableList<StudentSeriarizable> {
        return objectMapper.readValue(content, object : TypeReference<MutableList<StudentSeriarizable>>() {})
    }
}