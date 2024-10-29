package main.kotlin
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class Student_list_txt {
    fun readFromTxt(path: String): MutableList<Student> {
        val file = File(path)
        var result = mutableListOf<Student>()
        var text: List<String> = listOf()

        try {
            text = file.readLines()
            println(text)
        }
        catch (e: FileNotFoundException) {
            println("Файл не найден")
        }
        catch (e: IOException) {
            println("Ошибка при чтении файла")
        }

        for (line in text) {
            var splitLine = line.split(" ")
            result.add(Student(
                splitLine.get(0), splitLine.get(1),
                splitLine.get(2), splitLine.getOrNull(3),
                splitLine.getOrNull(4), splitLine.getOrNull(5),
                splitLine.getOrNull(6)))
        }
        return result
    }

    fun writeInTxt(path: String, studentList: MutableList<Student>) {
        val file = File(path)
        var text = ""

        for (student in studentList) text += (student.toString() + "\n")
        file.writeText(text)
    }

    fun getById(id: Int, path: String): Student? {
        var list = readFromTxt(path)
        for (student in list) {
            if (student.id == id) return student
        }
        return null
    }

    fun get_k_n_student_short_list(n: Int, k: Int, path: String): Data_list<Student_short> {
        val allStudents = readFromTxt(path)
        val studentShortList = allStudents.map { Student_short(it) }
        val sublist = if (n < studentShortList.size) {
            studentShortList.subList(n, minOf(n + k, studentShortList.size))
        } else {
            emptyList()
        }

        return Data_list(sublist)
    }

    // Сортировка по ФамилияИнициалы
    fun sortBySurnameAndInitials(studentList: MutableList<Student_short>) {
        studentList.sortBy { student ->
            "${student.fullName?.split(" ")?.get(0)} ${student.fullName?.split(" ")?.get(1)?.first()}.${student.fullName?.split(" ")?.get(2)?.first()}"
        }
    }

    // Добавление объекта класса Student
    fun addStudent(student: Student, path: String) {
        val studentList = readFromTxt(path).toMutableList()
        student.id = BaseStudent.id_student
        studentList.add(student)
        writeInTxt(path, studentList)
    }

    // Замена элемента списка по ID
    fun replaceStudentById(student: Student, path: String) {
        val studentList = readFromTxt(path).toMutableList()
        val index = studentList.indexOfFirst { it.id == student.id }
        if (index != -1) {
            studentList[index] = student
            writeInTxt(path, studentList)
        }
    }

    // Удаление элемента списка по ID
    fun deleteStudentById(id: Int, path: String) {
        val studentList = readFromTxt(path).toMutableList()
        studentList.removeIf { it.id == id }
        writeInTxt(path, studentList)
    }

    // Получение количества элементов
    fun getStudentShortCount(path: String): Int {
        return readFromTxt(path).size
    }
}