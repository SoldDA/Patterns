package main.kotlin.StudentList

import main.kotlin.*

interface Student_list_interface {
    fun getStudentById(id: Int ): Student?
    fun get_k_n_Student_Short(k: Int, n: Int, filter: String): MutableList<Student_short>
    fun getKNStudent(k: Int, n: Int, filter: String): MutableList<Student>
    fun deleteStudentById(id: Int)
    fun getStudentCount(): Int
    fun addStudent(student: Student)
    fun replaceStudentById(id: Int, student: Student)
}


class Student_list_adapter(var path: String): Student_list_interface {
    private var student_list: Student_Manager? = null

    init {
        if (path.split('.')[1] == "txt") student_list = Student_Manager(Student_list_txt())
        if (path.split('.')[1] == "json") student_list = Student_Manager(Students_list_JSON())
        if (path.split('.')[1] == "yaml") student_list = Student_Manager(Students_list_YAML())
    }

    override fun getStudentById(id: Int): Student? {
        return student_list?.getStudentById(id)
    }

    // Получение подсписка студентов
    override fun get_k_n_Student_Short(k: Int, n: Int, filter: String): MutableList<Student_short> {
        return student_list?.get_k_n_Student_Short(k, n) ?: mutableListOf()
    }

    override fun getKNStudent(k: Int, n: Int, filter: String): MutableList<Student> {
        return student_list?.getKNStudent(k, n) ?: mutableListOf()
    }

    // Удаление студента по ID
    override fun deleteStudentById(id: Int) {
        student_list?.deleteStudentById(id)
    }

    // Получение количества студентов
    override fun getStudentCount(): Int {
        return student_list?.getStudentCount()?: 0
    }

    // Добавление нового студента
    override fun addStudent(student: Student) {
        student_list?.addStudent(student)
    }

    // Замена существующего студента по ID
    override fun replaceStudentById(id: Int, student: Student) {
        student_list?.replaceStudentById(id, student)
    }
}


class Student_list(path: String): Student_list_interface {
    private var student_list: Student_list_interface? = null

    init {
        if (path == "pg") student_list = Students_list_DB.getInstance()
        else student_list = Student_list_adapter(path)
    }

    // Получение студента по ID
    override fun getStudentById(id: Int): Student? {
        return student_list?.getStudentById(id)
    }

    // Получение подсписка студентов
    override fun get_k_n_Student_Short(k: Int, n: Int, filter: String): MutableList<Student_short> {
        return student_list?.get_k_n_Student_Short(k, n, filter) ?: mutableListOf()
    }

    override fun getKNStudent(k: Int, n: Int, filter: String): MutableList<Student> {
        return student_list?.getKNStudent(k, n, filter) ?: mutableListOf()
    }

    // Удаление студента по ID
    override fun deleteStudentById(id: Int) {
        student_list?.deleteStudentById(id)
    }

    // Получение количества студентов
    override fun getStudentCount(): Int {
        return student_list?.getStudentCount()?: 0
    }

    // Добавление нового студента
    override fun addStudent(student: Student) {
        student_list?.addStudent(student)
    }

    // Замена существующего студента по ID
    override fun replaceStudentById(id: Int, student: Student) {
        student_list?.replaceStudentById(id, student)
    }
}