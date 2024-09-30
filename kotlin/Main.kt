import main.kotlin.DataTable
import main.kotlin.Student
import main.kotlin.Student_short

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
    println(Danil.toString() + "\n")
    println(Zhenya.toString() + "\n")
    Zhenya.setContacts("12345678910", "@kashpo", "kashpo@mail.ru")
    println(Zhenya.toString() + "\n")

    // laba_2

    val studentString = "Спиридонов Данил Александрович 79833256191 @danya_sp streleckiy.io@mail.ru https://github.com/SolDA"

    val student = Student(studentString)
    println(student.toString() + "\n")
    println(student.getInfo())
    println("\nРазделение на составляющие:")
    println("Full Name: ${student.getFullName()}")
    println("GitHub Link: ${student.getGitLink()}")
    println("Contact Info: ${student.getContactInfo()}\n")

    var Danil_short = Student_short(Danil)
    println(Danil_short.toString())

    println("\nЗапись и чтение")
    var studentList = Student.readFile("input.txt")
    for (stud in studentList) println(student)
    Student.writeFile("output.txt", studentList)

    println("\nData_Table\n")
    var elem = DataTable(arrayOf(arrayOf(15, 23, 8), arrayOf(16, 24, 9), arrayOf("One", "Two", "Three")))
    println(elem.getElementByNumber(0, 1))
    println(elem.getRows())
    println(elem.getColumns())
}


/*
* classDiagram
    class Student {
        - id: Int
        - lastName: String
        - firstName: String
        - middleName: String
        - phone: String?
        - telegram: String?
        - email: String?
        - github: String?

        + validate(): Boolean
        + setContacts(Phone?: String, Telegram?: String, Mail?: String): Unit
        + toString(): String

        + constructor(LastName: String, FirstName: String, MiddleName: String)
        + constructor(LastName: String, FirstName: String, MiddleName: String, Phone: String?, Telegram: String?, Email: String?, GitHub: String?)
        + constructor(hashStudent: HashMap<String, Any?>)

        + hasGit(): Boolean
        + hasAnyContact(): Boolean

        + equals(other: Object): Boolean
        + hashCode(): Int
    }

    class Main {
        + main()
    }*/
