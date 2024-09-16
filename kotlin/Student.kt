package main.kotlin

class Student (
    private val id: Int,
    private var lastName: String,
    private var firstName: String,
    private var middleName: String,
    private var phone: String? = null,
    private var telegram: String? = null,
    private var email: String? = null,
    private var github: String? = null
) {
    fun getId(): Int {
        return id
    }

    fun getLastName(): String {
        return lastName
    }
    fun setLastName(newLastName: String) {
        lastName = newLastName
    }

    fun getFirstName(): String {
        return firstName
    }
    fun setFirstName(newFirstName: String) {
        firstName = newFirstName
    }

    fun getMiddleName(): String {
        return middleName
    }
    fun setMiddleName(newMiddleName: String) {
        middleName = newMiddleName
    }

    fun getPhone(): String {
        return phone ?: "Не найдено"
    }
    fun setPhone(newPhone: String?) {
        phone = newPhone
    }

    fun getTelegram(): String {
        return telegram ?: "Не найдено"
    }
    fun setTelegram(newTelegram: String?) {
        telegram = newTelegram
    }

    fun getEmail(): String {
        return email ?: "Не найдено"
    }
    fun setEmail(newEmail: String?) {
        email = newEmail
    }

    fun getGitHub(): String {
        return github ?: "Не найдено"
    }
    fun setGitHub(newGitHub: String?) {
        github = newGitHub
    }

    init {
        require(lastName.isNotBlank()) {"Фамилия должна быть указана"}
        require(firstName.isNotBlank()) {"Имя должно быть указано"}
        phone?.let { require(isValidPhone(it)) { "Неверный формат телефона" } }
        email?.let { require(isValidEmail(it)) { "Неверный формат почты" } }
        github?.let { require(isValidGit(it)) { "Неверный формат гита" } }
    }
    companion object {
        fun isValidPhone(phone: String): Boolean {
            return phone.matches(Regex("^\\+?\\d{11}$"))
        }
        fun isValidEmail(email: String): Boolean {
            return email.matches(Regex("^[a-zA-Z0-9.%_+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"))
        }
        fun isValidGit(github: String): Boolean {
            return github.matches(Regex("^https://github\\.com/[a-zA-Z0-9_-]+/?\$"))
        }
        fun createStudent(vararg args: Pair<String, Any?>): Student {
            val map = args.toMap()
            return Student(
                id = map["ID"] as Int,
                lastName = map["Фамилия"] as String,
                firstName = map["Имя"] as String,
                middleName = map["Отчество"] as String,
                phone = map["Телефон"] as String?,
                email = map["Почта"] as String?,
                github = map["Гит"] as String?
            )
        }
    }

    override fun toString(): String {
        return """
            ID: $id
            Фамилия: $lastName
            Имя: $firstName
            Отчество: ${middleName}
            Телефон: ${phone}
            Telegram: ${telegram}
            E-mail: ${email}
            GitHub: ${github}
        """.trimIndent()
    }

    fun validate(): Boolean {
        return hasGit() && hasAnyContact()
    }

    private fun hasGit(): Boolean {
        return !github.isNullOrEmpty()
    }

    private fun hasAnyContact(): Boolean {
        return !phone.isNullOrEmpty() || !email.isNullOrEmpty() || telegram.isNullOrEmpty()
    }

    fun setContacts(phone: String? = null, telegram: String? = null, email: String? = null) {
        this.phone = phone?.takeIf { isValidPhone(it) }
        this.telegram = telegram
        this.email = email?.takeIf { isValidEmail(it) }
    }
}
