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

    fun isValidPhone(): Boolean {
        return phone != null && phone!!.length == 11 && phone!!.startsWith("7")
    }

    fun validate(): Boolean {
        return isValidPhone() && (telegram != null || email != null || github != null)
    }

    fun setContacts(contacts: Map<String, String>) {
        phone = contacts["phone"]
        telegram = contacts["telegram"]
        email = contacts["email"]
        github = contacts["github"]
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
}