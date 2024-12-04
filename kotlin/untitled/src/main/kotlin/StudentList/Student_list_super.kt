package main.kotlin.StudentList
import main.kotlin.*

open class Student_list_super {
    var data: MutableList<Student> = mutableListOf()

    // Получение студента по ID
    fun getStudentById(id: Int): Student? {
        for (student in data) {
            if (student.id == id) return student
        }
        return null
    }

    // Получение подсписка студентов
    fun get_k_n_Student_Short(k: Int, n: Int): MutableList<Student_short> {
        var stud = data.subList((k - 1) * n + 1, n)
        var sstud = stud.map{Student_short(it)} as MutableList<Student_short>
        return sstud
    }

    fun getKNStudent(k: Int, n: Int) : MutableList<Student>
    {
        var s = data.subList((k-1)*n+1,n)
        return s
    }

    // Удаление студента по ID
    fun deleteStudentById(id: Int) {
        val students = getStudentById(id)
        var i = data.indexOf(students)
        data.removeAt(i)
    }

    // Получение количества студентов
    fun getStudentCount(): Int {
        return data.size
    }

    // Добавление нового студента
    fun addStudent(student: Student) {
        data.add(student)
    }

    // Замена существующего студента по ID
    fun replaceStudentById(id: Int, student: Student) {
        var students = getStudentById(id)
        var i = data.indexOf(students)
        data.set(i, student)
    }

    // Сортировка студентов по Фамилия и Инициалы
    fun sortBySurnameAndInitials() {
        data.sortBy { it.getFullName() }
    }
}