package main.kotlin

fun main() {
    var manager = Student_Manager(Student_list_txt())
    manager.readFromFile("students.txt")
    println(manager.data)

    manager.setStrategy(Students_list_JSON())
    manager.readFromFile("students.json")
    println(manager.getStudentById(3))

    manager.setStrategy(Students_list_YAML())
    manager.readFromFile("students.yaml")
    println(manager.getStudentById(4))
}