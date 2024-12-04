package main.kotlin

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import main.kotlin.Data_list

import main.kotlin.StudentList.Student_list_interface

class Students_list_DB private constructor(): Student_list_interface {

    companion object {

        @Volatile
        private var INSTANCE: Students_list_DB? = null

        fun getInstance(): Students_list_DB = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Students_list_DB().also { INSTANCE = it }
        }
    }
    private lateinit var connection: Connection

    init {
        try {
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/my_database",
                "postgres",
                "qwerty12345"
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun executeQuery(query: String): ResultSet? {
        return try {
            val stmt = connection.createStatement()
            stmt.executeQuery(query)
        } catch (e: Exception) {
            // e.printStackTrace()
            null
        }
    }

    // Получение студента по ID
    override fun getStudentById(id: Int): Student? {
        val res = executeQuery("SELECT * FROM student WHERE id = ${id};")
        if (res != null) {
            while (res.next()) {
                for (stud in 1..res.metaData.columnCount) print("${res.getString(stud)}\t")
                println()
            }
        }
        return null
    }

    // Получение подсписка студентов
    override fun get_k_n_Student_Short(k: Int, n: Int, filter: String): MutableList<Student_short> {
        val result = executeQuery("SELECT * FROM student ${filter} ORDER BY id LIMIT ${n} OFFSET ${k*n};")
        var input = ""
        var sl=mutableListOf<Student>()
        if (result != null) {
            while (result.next()) {
                input = ""
                for (i in 2..result.metaData.columnCount) {
                    input+=result.getString(i)+" "
                }
                sl.add(Student(input, result.getInt(1)))
            }
        }
        var ss = sl.map{Student_short(it)} as MutableList<Student_short>

        return ss
    }

    override fun getKNStudent(k:Int,n:Int, filter: String): MutableList<Student>
    {
        val result = executeQuery("SELECT * FROM student ${filter} ORDER BY id LIMIT ${n} OFFSET ${k*n};")
        var input = ""
        var sl=mutableListOf<Student>()
        if (result != null) {
            while (result.next()) {
                input = ""
                for (i in 2..result.metaData.columnCount) {
                    input+=result.getString(i)+" "
                }
//                println(input)
                sl.add(Student(input,result.getInt(1)))
            }
        }

        return sl
    }

    override fun addStudent(student: Student) {
        val input = buildString {
            append("'${student.lastName}', '${student.firstName}', '${student.middleName}'")
            append(", ${student.phone?.let { "'$it'" } ?: "NULL"}")
            append(", ${student.telegram?.let { "'$it'" } ?: "NULL"}")
            append(", ${student.email?.let { "'$it'" } ?: "NULL"}")
            append(", ${student.git?.let { "'$it'" } ?: "NULL"}")
        }
        executeQuery("INSERT INTO student (lastName, firstName, middleName, phone, telegram, email, git) VALUES ($input);")
    }

    override fun deleteStudentById(id: Int) {
        executeQuery("DELETE FROM student WHERE id=${id};")
    }

    override fun getStudentCount(): Int {
        val res = executeQuery("SELECT COUNT(*) FROM student;")
        return if (res?.next() == true) res.getInt("count")
        else 0
    }

    override fun replaceStudentById(id: Int, student: Student) {
        val input = buildString {
            append("'${student.lastName}', '${student.firstName}', '${student.middleName}'")
            append(", ${student.phone?.let { "'$it'" } ?: "NULL"}")
            append(", ${student.telegram?.let { "'$it'" } ?: "NULL"}")
            append(", ${student.email?.let { "'$it'" } ?: "NULL"}")
            append(", ${student.git?.let { "'$it'" } ?: "NULL"}")
        }

        executeQuery("UPDATE student SET (lastName, firstName, middleName, phone, telegram, email, git) = ($input) WHERE id = $id;")
    }
}

/*fun main() {
    val db = Students_list_DB.getInstance()
    val student1 = Student(
        _lastName = "Сожеленко",
        _firstName = "Евгений",
        _middleName = "Константинович",
        _phone = "+78005553535",
        _telegram = "@chenya",
        _email = "chenya.prok@mail.ru",
        _git = "https://github.com/YA-KU-ZA"
    )

    //db.addStudent(student1)
    //db.deleteStudent(7)
    //println(db.getCount())
    //db.replaceStudent(8, student1)
    println( db.get_k_n_Student_Short(1, 2))
}*/