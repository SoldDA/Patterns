package main.kotlin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException

class Students_list_JSON(private val path: String) {
    private val gson = Gson()

    // Чтение всех значений из файла
    fun readFromJson(): MutableList<StudentSeriarizable> {
        return try {
            val fileContent = File(path).readText()
            val listType = object : TypeToken<MutableList<StudentSeriarizable>>() {}.type
            gson.fromJson(fileContent, listType) ?: mutableListOf()
        } catch (e: IOException) {
            println("Ошибка чтения файла: ${e.message}")
            mutableListOf()
        }
    }

    // Запись всех значений в файл
    fun writeToJson(sList: MutableList<StudentSeriarizable>) {
        val jsonData = gson.toJson(sList)
        File(path).writeText(jsonData)
    }

    // Получить объект класса Student по ID
    fun getStudentById(id: Int): StudentSeriarizable? {
        return readFromJson().find { it.id == id }
    }

    // get_k_n_student_short_list
    fun get_k_n_student_short_list(n: Int, k: Int): Data_list_student_short {
        val allStudents = readFromJson().map { Student_short(it) }
        return Data_list_student_short(allStudents.drop(n).take(k))
    }

    // Сортировать элементы по набору ФамилияИнициалы
    fun sortBySurnameAndInitials(students: MutableList<Student_short>) {
        students.sortBy { student ->
            "${student.fullName?.split(" ")?.get(0)} ${student.fullName?.split(" ")?.get(1)?.first()}.${student.fullName?.split(" ")?.get(2)?.first()}"
        }
    }

    // Добавить объект класса Student в список
    fun addStudent(student: Student) {
        val studentList = readFromJson().toMutableList()
        val newId = generateNewId(studentList)

        val newStudent = Student(
            LastName = student.lastName,
            FirstName = student.firstName,
            MiddleName = student.middleName,
            Phone = student.phone,
            Telegram = student.telegram,
            Email = student.email,
            GitHub = student.git
        ).apply { id = newId }
        val newStudentSeriarizable = StudentSeriarizable(newStudent)

        studentList.add(newStudentSeriarizable)
        writeToJson(studentList)
    }

    private fun generateNewId(student: List<StudentSeriarizable>): Int {
        return (student.maxOfOrNull { it.id } ?: 0) + 1
    }

    // Заменить элемент списка по ID
    fun replaceStudentById(id: Int, updatedStudent: Student) {
        val studentList = readFromJson().toMutableList()
        val index = studentList.indexOfFirst { it.id == id }
        if (index != -1) {
            val updaterStudentSeriarizable = StudentSeriarizable(
                id = id,
                lastName = updatedStudent.lastName,
                firstName = updatedStudent.firstName,
                middleName = updatedStudent.middleName,
                phone = updatedStudent.phone,
                telegram = updatedStudent.telegram,
                email = updatedStudent.email,
                git = updatedStudent.git
            )
            studentList[index] = updaterStudentSeriarizable
            writeToJson(studentList)
        } else {
            println("Студент с ID $id не найден.")
        }
    }


    // Удалить элемент списка по ID
    fun deleteStudentById(id: Int) {
        val studentList = readFromJson().toMutableList()
        studentList.removeIf { it.id == id }
        writeToJson(studentList)
    }

    // get_student_short_count
    fun getStudentShortCount(): Int {
        return readFromJson().size
    }
}