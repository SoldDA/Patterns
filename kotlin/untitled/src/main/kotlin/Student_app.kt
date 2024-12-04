package main.kotlin

import main.kotlin.StudentList.Student_list
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.collections.ObservableList
import javafx.scene.text.Font
import javafx.scene.layout.GridPane
import javafx.stage.Modality

class Student_app : Application() {

    private lateinit var tableView: TableView<Student>
    private lateinit var students: MutableList<Student>
    private val pg = Student_list("pg")
    private var page = 0
    private val itemsPerPage = 3

    private lateinit var pageLabel: Label
    private lateinit var nameField: TextField
    private lateinit var phoneFilterVar: ObservableList<String>
    private lateinit var phoneComboBox: ComboBox<String>
    private lateinit var phoneField: TextField
    private lateinit var telegramFilterVar: ObservableList<String>
    private lateinit var telegramComboBox: ComboBox<String>
    private lateinit var telegramField: TextField
    private lateinit var emailFilterVar: ObservableList<String>
    private lateinit var emailComboBox: ComboBox<String>
    private lateinit var emailField: TextField
    private lateinit var gitFilterVar: ObservableList<String>
    private lateinit var gitComboBox: ComboBox<String>
    private lateinit var gitField: TextField

    private var currentFilter: String = ""

    override fun start(stage: Stage) {
        tableView = TableView<Student>()
        val idColumn = TableColumn<Student, Int>("ID").apply {
            cellValueFactory = PropertyValueFactory("id")
        }
        val lastNameColumn = TableColumn<Student, String>("Фамилия").apply {
            cellValueFactory = PropertyValueFactory("lastName")
        }
        val firtsNameColumn = TableColumn<Student, String>("Имя").apply {
            cellValueFactory = PropertyValueFactory("firstName")
        }
        val middleNameColumn = TableColumn<Student, String>("Отчество").apply {
            cellValueFactory = PropertyValueFactory("middleName")
        }
        val phoneColumn = TableColumn<Student, String>("Телефон").apply {
            cellValueFactory = PropertyValueFactory("phone")
        }
        val telegramColumn = TableColumn<Student, String>("Телеграм").apply {
            cellValueFactory = PropertyValueFactory("telegram")
        }
        val emailColumn = TableColumn<Student, String>("Почта").apply {
            cellValueFactory = PropertyValueFactory("email")
        }
        val gitColumn = TableColumn<Student, String>("Гит").apply {
            cellValueFactory = PropertyValueFactory("git")
        }

        tableView.columns.addAll(
            idColumn,
            lastNameColumn,
            firtsNameColumn,
            middleNameColumn,
            phoneColumn,
            telegramColumn,
            emailColumn,
            gitColumn,
        )

        students = pg.getKNStudent(0, itemsPerPage, currentFilter)

        val prevButton = Button("<").apply {
            setOnAction {
                if (page > 0) {
                    page--
                    update()
                }
            }
        }

        val nextButton = Button(">").apply {
            setOnAction {
                if ((page + 1) * itemsPerPage < pg.getStudentCount()) {
                    page++
                    update()
                }
            }
        }

        pageLabel = Label()
        updatePageLabel()

        val tableButtonBox = HBox(prevButton, pageLabel, nextButton)
        tableButtonBox.alignment = Pos.BASELINE_CENTER

        tableView.setPrefSize(415.0, 388.0)

        val table = VBox(tableView, tableButtonBox)

        nameField = TextField().apply { setOnAction { update() } }
        nameField.promptText = "Фамилия Имя Отчество"

        phoneFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
        phoneComboBox = ComboBox(phoneFilterVar).apply { setOnAction { update() } }
        phoneComboBox.value = "Не важно"

        val phoneLabel = Label()
        phoneLabel.text = "Телефон: "
        phoneLabel.alignment = Pos.BASELINE_CENTER
        phoneLabel.font = Font.font(14.0)
        phoneLabel.setPrefSize(100.0, 13.0)
        val phoneBox = HBox(phoneLabel, phoneComboBox)

        phoneField = TextField().apply { setOnAction { update() } }
        phoneField.promptText = "Телефон студента"

        telegramFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
        telegramComboBox = ComboBox(telegramFilterVar).apply { setOnAction { update() } }
        telegramComboBox.value = "Не важно"

        val telegramLabel = Label()
        telegramLabel.text = "Телеграм: "
        telegramLabel.alignment = Pos.BASELINE_CENTER
        telegramLabel.font = Font.font(14.0)
        telegramLabel.setPrefSize(100.0, 13.0)
        val telegramBox = HBox(telegramLabel, telegramComboBox)

        telegramField = TextField().apply { setOnAction { update() } }
        telegramField.promptText = "Телеграм студента"

        emailFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
        emailComboBox = ComboBox(emailFilterVar).apply { setOnAction { update() } }
        emailComboBox.value = "Не важно"

        val mailLabel = Label()
        mailLabel.text = "Почта: "
        mailLabel.alignment = Pos.BASELINE_CENTER
        mailLabel.font = Font.font(14.0)
        mailLabel.setPrefSize(100.0, 13.0)
        val mailBox = HBox(mailLabel, emailComboBox)

        emailField = TextField().apply { setOnAction { update() } }
        emailField.promptText = "Почта студента"

        gitFilterVar = FXCollections.observableArrayList("Да", "Нет", "Не важно")
        gitComboBox = ComboBox(gitFilterVar).apply { setOnAction { update() } }
        gitComboBox.value = "Не важно"

        val gitLabel = Label()
        gitLabel.text = "Гит: "
        gitLabel.alignment = Pos.BASELINE_CENTER
        gitLabel.font = Font.font(14.0)
        gitLabel.setPrefSize(100.0, 13.0)
        val gitBox = HBox(gitLabel, gitComboBox)

        gitField = TextField().apply { setOnAction { update() } }
        gitField.promptText = "Гит студента"

        update()

        val addButton = Button("Добавить").apply {
            setOnAction {
                openWindow(0, "", "", "", "", "", "", "")
            }
        }

        val changeButton = Button("Изменить").apply {
            setOnAction {
                val selected = tableView.selectionModel.selectedItem
                if (selected != null) {
                    openWindow(
                        selected.id,
                        selected.lastName,
                        selected.firstName,
                        selected.middleName,
                        selected.phone ?: "",
                        selected.telegram ?: "",
                        selected.email ?: "",
                        selected.git ?: ""
                    )
                }
            }
        }

        val deleteButton = Button("Удалить").apply {
            setOnAction {
                val selected = tableView.selectionModel.selectedItem
                if (selected != null) {
                    pg.deleteStudentById(selected.id)
                    update()
                }
            }
        }

        val updateButton = Button("Обновить таблицу").apply {
            setOnAction {
                update()
            }
        }

        addButton.setPrefSize(200.0, 13.0)
        changeButton.setPrefSize(200.0, 13.0)
        deleteButton.setPrefSize(200.0, 13.0)
        updateButton.setPrefSize(200.0, 13.0)

        val filterBox = VBox(
            nameField,
            phoneBox, phoneField,
            telegramBox, telegramField,
            mailBox, emailField,
            gitBox, gitField
        )

        val buttonBox = VBox(filterBox, addButton, changeButton, deleteButton, updateButton)
        val mainBox = HBox(table, buttonBox)
        val scene = Scene(mainBox)
        stage.title = "Таблица студентов"
        stage.scene = scene
        stage.show()
    }

    private fun update() {
        currentFilter = ""
        when {
            nameField.text.isNotEmpty() ||
                    gitComboBox.value != "Не важно" ||
                    phoneComboBox.value != "Не важно" ||
                    telegramComboBox.value != "Не важно" ||
                    emailComboBox.value != "Не важно" -> currentFilter += "WHERE"
        }

        //FIO
        if (nameField.text.isNotEmpty()) {
            val parts = nameField.text.split(" ")
            currentFilter += " lastName = '${parts.getOrElse(0) { "" }}' AND firstName = '${parts.getOrElse(1) { "" }}' AND middleName = '${
                parts.getOrElse(
                    2
                ) { "" }
            }'"
        }

        //git
        if (gitComboBox.value != "Не важно") {
            currentFilter += when {
                currentFilter.startsWith("WHERE") -> ""
                else -> " AND"
            }

            currentFilter += when (gitComboBox.value) {
                "Нет" -> " git is NULL"
                "Да" -> {
                    var result = " git is NOT NULL"
                    if (gitField.text.isNotEmpty()) result += " AND git = '${gitField.text}'"
                    result
                }

                else -> ""
            }
        }

        //phone
        if (phoneComboBox.value != "Не важно") {
            currentFilter += when {
                currentFilter.startsWith("WHERE") -> ""
                else -> " AND"
            }

            currentFilter += when (phoneComboBox.value) {
                "Нет" -> " phone is NULL"
                "Да" -> {
                    var result = " phone is NOT NULL"
                    if (phoneField.text.isNotBlank()) {
                        result += " AND phone = '${phoneField.text}'"
                    }
                    result
                }

                else -> ""
            }
        }

        //telegram
        if (telegramComboBox.value != "Не важно") {
            currentFilter += when {
                currentFilter.startsWith("WHERE") -> ""
                else -> " AND"
            }

            currentFilter += when (telegramComboBox.value) {
                "Нет" -> " telegram is NULL"
                "Да" -> {
                    var result = " telegram is NOT NULL"
                    if (telegramField.text.isNotBlank()) {
                        result += " AND telegram = '${telegramField.text}'"
                    }
                    result
                }

                else -> ""
            }
        }

        //email
        if (emailComboBox.value != "Не важно") {
            currentFilter += when {
                currentFilter.startsWith("WHERE") -> ""
                else -> " AND"
            }

            currentFilter += when (emailComboBox.value) {
                "Нет" -> " email is NULL"
                "Да" -> {
                    var result = " email is NOT NULL"
                    if (emailField.text.isNotBlank()) {
                        result += " AND email = '${emailField.text}'"
                    }
                    result
                }

                else -> ""
            }
        }

        students = pg.getKNStudent(page, itemsPerPage, currentFilter)
        updatePageLabel()
        tableView.items.setAll(students)
    }

    private fun updatePageLabel() {
        pageLabel.text = "${page + 1}/${
            Math.ceil(pg.getKNStudent(0, pg.getStudentCount(), currentFilter).size.toDouble() / itemsPerPage.toDouble()).toInt()
        }"
    }

    private fun openWindow(
        id: Int = 0,
        lastName: String,
        firstName: String,
        middleName: String,
        phone: String,
        telegram: String,
        email: String,
        git: String
    ) {
        val modalStage = Stage()
        modalStage.initModality(Modality.APPLICATION_MODAL)
        modalStage.title = "Ввод данных"

        val grid = GridPane()
        grid.padding = javafx.geometry.Insets(10.0)
        grid.hgap = 10.0
        grid.vgap = 10.0

        val lastNameField = TextField()
        val firstNameField = TextField()
        val middleNameField = TextField()
        val phoneField = TextField()
        val telegramField = TextField()
        val emailField = TextField()
        val gitField = TextField()

        lastNameField.text = lastName
        firstNameField.text = firstName
        middleNameField.text = middleName
        phoneField.text = phone
        telegramField.text = telegram
        emailField.text = email
        gitField.text = git

        grid.add(Label("Фамилия:"), 0, 1)
        grid.add(Label("Имя:"), 0, 2)
        grid.add(Label("Отчество:"), 0, 3)
        grid.add(Label("Телефона:"), 0, 4)
        grid.add(Label("Телеграмм:"), 0, 5)
        grid.add(Label("Почта:"), 0, 6)
        grid.add(Label("Гит:"), 0, 7)

        grid.add(lastNameField, 1, 1)
        grid.add(firstNameField, 1, 2)
        grid.add(middleNameField, 1, 3)
        grid.add(phoneField, 1, 4)
        grid.add(telegramField, 1, 5)
        grid.add(emailField, 1, 6)
        grid.add(gitField, 1, 7)

        val submitButton = Button("Отправить")
        submitButton.setOnAction {
            println("Фамилия: ${lastNameField.text}")
            println("Имя: ${firstNameField.text}")
            println("Отчество: ${middleNameField.text}")
            println("Телефон: ${phoneField.text}")
            println("Телеграмм: ${telegramField.text}")
            println("Почта: ${emailField.text}")
            println("Гит: ${gitField.text}")

            if (id == 0) {
                pg.addStudent(
                    Student(
                        _lastName = lastNameField.text,
                        _firstName = firstNameField.text,
                        _middleName = middleNameField.text,
                        _phone = phoneField.text,
                        _telegram = telegramField.text,
                        _email = emailField.text,
                        _git = gitField.text
                    )
                )
                update()
            }
            modalStage.close()
        }
        grid.add(submitButton, 1, 8)

        val scene = Scene(grid, 400.0, 300.0)
        modalStage.scene = scene
        modalStage.showAndWait()
    }
}

fun main() {
    Application.launch(Student_app::class.java)
}