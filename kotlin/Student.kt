package main.kotlin

class Student {
    var id: Int = -1
        set(value) {
            if (value > 0) field = value
        }
        get() {
            return field
        }

    var lastName: String = ""
        set(value) {
            if (isValidNames(value)) field = value
            else field = ""
        }
        get() {
            return field
        }
    var firstName: String = ""
        set(value) {
            if (isValidNames(value)) field = value
            else field = ""
        }
        get() {
            return field
            }
    var middleName: String = ""
        set(value) {
            if (isValidNames(value)) field = value
            else field = ""
        }
        get() {
            return field
        }
    var phone: String? = null
        set(value) {
            if (isValidPhone(value)) field = value
        }
        get() {
            return field
        }
    var telegram: String? = null
        set(value) {
            if (isValidTelegram(value)) field = value
        }
        get() {
            return field
        }
    var email: String? = null
        set(value) {
            if (isValidEmail(value)) field = value
        }
        get() {
            return field
        }
    var github: String? = null
        set(value) {
            if (isValidGit(value)) field = value
        }
        get() {
            return field
        }

    companion object
    {
        var id_student = 0
        private fun isValidPhone(phone: String?): Boolean {
            return phone?.matches(Regex("^\\+?\\d{11}$")) ?: true
        }
        private fun isValidEmail(email: String?): Boolean {
            return email?.matches(Regex("^[a-zA-Z0-9.%_+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")) ?: true
        }
        private fun isValidGit(github: String?): Boolean {
            return github?.matches(Regex("^https://github\\.com/[a-zA-Z0-9_-]+/?\$")) ?: true
        }
        private fun isValidNames(value: String): Boolean {
            return value.matches(Regex("[А-Я]{1}[а-я]*"))
        }
        private fun isValidTelegram(value: String?): Boolean {
            return value?.matches(Regex("""\@{1}.*""")) ?: true
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

    fun setContacts(Phone: String? = null, Telegram: String? = null, Mail: String? = null) {
        if (Phone != null && isValidPhone(Phone)) phone = Phone
        if (Telegram != null && isValidTelegram(Telegram)) telegram = Telegram
        if (Mail != null && isValidEmail(Mail)) email = Mail
    }

    init {
        id_student++
    }

    constructor(LastName:String, FirstName:String, MiddleName:String) {
        id = id_student
        lastName = LastName
        firstName = FirstName
        middleName = MiddleName
    }

    constructor(LastName: String, FirstName: String, MiddleName: String, Phone: String? = null, Telegram: String? = null, Email: String? = null, GitHub:String? = null) {
        id = id_student
        lastName = LastName
        firstName = FirstName
        middleName = MiddleName
        phone = Phone
        telegram = Telegram
        email = Email
        github = GitHub
    }

    constructor(hashStudent: HashMap<String, Any?>) {
        id = id_student
        lastName = hashStudent["lastname"].toString()
        firstName = hashStudent["firstName"].toString()
        middleName = hashStudent["middleName"].toString()
        phone = hashStudent.getOrDefault("phone", null).toString()
        telegram = hashStudent.getOrDefault("telegram", null).toString()
        email = hashStudent.getOrDefault("email", null).toString()
        github = hashStudent.getOrDefault("github", null).toString()
    }
}
