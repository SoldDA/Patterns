package main.kotlin

interface StudentListStrategy {
    var data: MutableList<Student>
    fun readFromFile(path: String)
    fun writeToFile(path: String)
}

class Student_Manager(private var strategy: StudentListStrategy): Student_list() {
    fun setStrategy(strategy: StudentListStrategy) {
        this.strategy = strategy
    }

    fun readFromFile(path: String) {
        strategy.readFromFile(path)
        this.data = strategy.data
    }

    fun writeToFile(path: String) {
        this.data = strategy.data
        strategy.writeToFile(path)
    }
}