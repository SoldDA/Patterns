package main.kotlin

import java.io.File
import java.io.IOException

class Student_list_txt(path: String) : Student_list(path) {

    // Форматирование студентов в текстовый формат
    override fun formatToFile(studentList: MutableList<StudentSeriarizable>): String {
        return studentList.joinToString(separator = "\n") { student ->
            "${student.id} ${student.lastName} ${student.firstName} ${student.middleName} " +
                    "${student.phone ?: ""} ${student.telegram ?: ""} ${student.email ?: ""} ${student.git ?: ""}"
        }
    }

    // Парсинг текстового формата в список студентов
    override fun parseFile(content: String): MutableList<StudentSeriarizable> {
        return content.lineSequence()
            .filter { it.isNotBlank() }  // Пропускаем пустые строки
            .map { line ->
                val splitLine = line.split(" ")

                // Обработка случаев, когда строка может не содержать всех данных
                StudentSeriarizable(
                    id = splitLine.getOrNull(0)?.toInt() ?: 0, // ID теперь считывается
                    lastName = splitLine.getOrNull(1) ?: "",
                    firstName = splitLine.getOrNull(2) ?: "",
                    middleName = splitLine.getOrNull(3) ?: "",
                    phone = splitLine.getOrNull(4),
                    telegram = splitLine.getOrNull(5),
                    email = splitLine.getOrNull(6),
                    git = splitLine.getOrNull(7)
                )
            }.toMutableList()
    }
}
