package main.kotlin

import java.io.File
import java.io.IOException

open class Student_list(private val path: String) {

    fun readFromFile(): MutableList<StudentSeriarizable> {
        return try {
            parseFile(File(path).readText())
        } catch (e: IOException) {
            println("Ошибка чтения файла: ${e.message}")
            mutableListOf()
        }
    }

    // Запись студентов в файл
    fun writeToFile(studentList: MutableList<StudentSeriarizable>) {
        File(path).writeText(formatToFile(studentList))
    }

    // Получение следующего уникального ID
    fun getNextId(): Int {
        val allStudents = getAllStudents()
        return if (allStudents.isNotEmpty()) {
            allStudents.maxOf { it.id } + 1
        } else {
            1 // Если студентов еще нет, id = 1
        }
    }

    // Получение всех студентов
    fun getAllStudents(): MutableList<StudentSeriarizable> {
        return readFromFile()
    }

    // Получение студента по ID
    fun getStudentById(id: Int): StudentSeriarizable? {
        return readFromFile().find { it.id == id }
    }

    // Удаление студента по ID
    fun deleteStudentById(id: Int) {
        val students = readFromFile().toMutableList()
        students.removeIf { it.id == id }
        writeToFile(students)
    }

    // Получение количества студентов
    fun getStudentCount(): Int {
        return readFromFile().size
    }

    // Добавление нового студента
    fun addStudent(student: Student) {
        val newStudentId = StudentSeriarizable(
            id = getNextId(),
            lastName = student.lastName,
            firstName = student.firstName,
            middleName = student.middleName,
            phone = student.phone,
            telegram = student.telegram,
            email = student.email,
            git = student.git
        )
        val students = readFromFile().toMutableList()
        students.add(newStudentId)
        writeToFile(students)
    }

    // Замена существующего студента по ID
    fun replaceStudentById(updatedStudent: StudentSeriarizable) {
        val students = readFromFile().toMutableList()
        val index = students.indexOfFirst { it.id == updatedStudent.id }
        if (index != -1) {
            students[index] = updatedStudent
            writeToFile(students)
        } else {
            println("Студент с ID ${updatedStudent.id} не найден.")
        }
    }

    // Сортировка студентов по Фамилия и Инициалы
    fun sortBySurnameAndInitials(students: MutableList<Student_short>) {
        students.sortBy { student ->
            val names = student.fullName?.split(" ")
            "${names?.get(0)} ${names?.drop(1)?.joinToString(" ") { it.first().toString() + "." }}"
        }
    }

    // Метод для парсинга файла (переопределяется в дочерних классах)
    protected open fun parseFile(content: String): MutableList<StudentSeriarizable> {
        return mutableListOf()
    }

    // Метод для форматирования данных перед записью в файл (переопределяется в дочерних классах)
    protected open fun formatToFile(studentList: MutableList<StudentSeriarizable>): String {
        return ""
    }

    // Получение списка всех студентов как Student_short
    fun getAllStudentsShort(): List<Student_short> {
        return readFromFile().map { Student_short(it) }
    }
}