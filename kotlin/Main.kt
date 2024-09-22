import main.kotlin.Student

fun main() {
    val Danil = Student(
        "Спиридонов",
        "Данил",
        "Александрович",
        "79833256191",
        "@danya_sp",
        "streleckiy.io@mail.ru",
        "https://github.com/SolDA"
    )

    val Zhenya = Student(
        "Прокопенко",
        "Евгений",
        "Константинович"
    )

    println("Информация о студентах:")
    println(Danil.toString())
    println(Zhenya.toString())
    Zhenya.setContacts("12345678910", "@kashpo", "kashpo@mail.ru")
    println(Zhenya.toString())
}
