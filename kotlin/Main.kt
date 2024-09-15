import main.kotlin.Student

fun main() {
    val student1 = Student(
        1,
        "Спиридонов",
        "Данил",
        "Александрович",
        "79833256191",
        "@danya_sp",
        "streleckiy.io@mail.ru",
        "github.com/SolDA"
    )

    val student2 = Student(
        1,
        "Прокопенко",
        "Евгений",
        "Константинович",
        "",
        "@OutcustBur",
        "",
        ""
    )

    println("Информация о студентах:")
    println(student1)
    println()
    println(student2)
    println("\nВалидация телефона студента1: ${student1.isValidPhone()}")

    try {
        val invalidStudent = Student(3, "Invalid", "User", "1234567890")
    } catch (e: Exception) {
        println("/nОшибка при внесения студента в список: $e")
    }

    val validStudent = Student(4, "Valid", "User", "Middle Name", "79123456789", "@chto.com", "val_user")
    println("\nvalidStudent:")
    println(validStudent)

    println("\nВалидация студента: ${validStudent.validate()}")

    val contacts = mapOf("phone" to "+79833256191", "telegram" to "@danya_sp", "email" to "streleckiy.io@mail.ru", "github" to "github.com/SolDA")

}