package main.kotlin

import java.io.DataInput

class Student_short : BaseStudent {
    private var fullName: String? = null
        set(value) {
            field = value
        }
        get() {
            return field
        }
    private var git: String? = null
        set(value) {
            field = value
        }
        get() {
            return field
        }
    private var contact: String? = null
        set(value) {
            field = value
        }
        get() {
            return field
        }

    constructor(student: Student) {
        id = id_student
        fullName = student.getFullName()
        git = student.getGitLink()
        contact = student.getContactInfo()
    }

    constructor(input: String) {
        id = id_student
        fullName = input.split(" ").getOrNull(0)
        git = input.split(" ").getOrNull(1)
        contact = input.split(" ").getOrNull(2)
    }

    override fun toString(): String {
        return """
            ID: $id
            ФИО: $fullName
            GitHub: $git
            Контакт: $contact
        """.trimIndent()
    }
}