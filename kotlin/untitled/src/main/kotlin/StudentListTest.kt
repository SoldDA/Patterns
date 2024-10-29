package main.kotlin
import java.io.File

fun main() {
    val path = "students.json"
    val studentListJson = Students_list_JSON(path)

    // Тест 1: Добавление студента
    val student1 = Student("Спиридонов", "Данил", "Александрович", "+79833256191", "@danya_sp", "streleckiy.io@mail.ru", "https://github.com/SolDA")
//    studentListJson.addStudent(student1)
//    println("Добавлен студент: $student1")

    // Тест 2: Чтение всех студентов
//    val students = studentListJson.readFromJson()
//    println("Список студентов: $students")

    // Тест 3: Получение студента по ID
//    val foundStudent = studentListJson.getStudentById(4)
//    println("Найден студент: $foundStudent")

    // Тест 4: Получение подсписка студентов
//    val shortList = studentListJson.get_k_n_student_short_list(0, 2)
//    println("Получен короткий список студентов: ${shortList.data}")

    // Тест 7: Замена студента по ID
//    studentListJson.replaceStudentById(2, student1)
//    println("Обновлённый студент: ${studentListJson.getStudentById(student1.id)}")
//    val students = studentListJson.readFromJson()
//    println("Список студентов: $students")

    // Тест 8: Удаление студента по ID
//    studentListJson.deleteStudentById(2)
//    println("После удаления студента по ID, список стал: ${studentListJson.readFromJson()}")

    // Тест 9: Получение количества студентов
//    val count = studentListJson.getStudentShortCount()
//    println("Количество студентов: $count")

    // Тест 10: Сортировка студентов
//    val studentsToSort = studentListJson.readFromJson().map { Student_short(it) }.toMutableList()
//    studentListJson.sortBySurnameAndInitials(studentsToSort)
//    println("Сортировка студентов: $studentsToSort")
}