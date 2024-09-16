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
        "https://github.com/SolDA"
    )

    val student2 = Student(
        2,
        "Прокопенко",
        "Евгений",
        "Константинович",
        null,
        "@OutcustBur",
        null,
         null
    )

    val student3 = Student.createStudent(
        "ID" to 3,
        "Фамилия" to "Шестак",
        "Имя" to "Виктория",
        "Отчество" to "Александровна",
        "Телефон" to "+79833256666",
        "GitHub" to "https://vikya_f"
    )

    println("Информация о студентах:")
    println(student1.toString())
    println(student2.toString())
    println(student3.toString())
    println("\nВалидация студента1: ${student1.validate()}")
    println("\nВалидация студента2: ${student2.validate()}")
}
