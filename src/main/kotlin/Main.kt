import java.io.File
import java.io.PrintWriter

val tasksList = mutableListOf<String>()
var nameCustomer = ""

fun main() {
    println("Welcome to \"TasksListForSeverstal\"! ")

    println("Enter your name: ")
    nameCustomer = readln()

    readFromFile()
    information()
    readFromInput()
    println("Have a good day, $nameCustomer!")
    writeToFile()
}

fun readFromInput() {
    while (true) {
        when (readln().lowercase()) {
            "\\end" -> break
            "\\help" -> information()
            "\\view" -> viewTasks()
            "\\del" -> removeTasks()
            "\\change" -> changeTasks()
            "\\add" -> addTasks()
            else -> println("Unfortunately I didn't understand you, $nameCustomer")
        }
    }
}

fun addTasks() {
    println("$nameCustomer, enter the task you want to add: ")
    tasksList.add(readln())
    println("Task added successfully!")
}

fun removeTasks() {
    viewTasks()
    println("$nameCustomer, enter the number of the task you want to delete: ")
    val numberOfTask = readln().toInt()
    if (numberOfTask in 1..tasksList.size) {
        tasksList.removeAt(numberOfTask - 1)
        println("Task remove successfully!")
    } else {
        println("There is no task with this number...")
        removeTasks()
    }
}

fun changeTasks() {
    viewTasks()
    println("$nameCustomer, enter the number of the task you want to change: ")
    val numberOfTask = readln().toInt()
    if (numberOfTask !in 1..tasksList.size) {
        println("There is no task with this number...")
        changeTasks()
    }
    println("Would you like to change this task: ${tasksList[numberOfTask - 1]}?")
    when (readln().lowercase()) {
        "yes", "да", "+" -> {
            println("$nameCustomer, enter the modified task type: ")
            tasksList[numberOfTask - 1] = readln()
        }
        "no", "нет", "-" -> println("$nameCustomer: enter the command: ")
        else -> {
            println("Unfortunately I didn't understand you: $nameCustomer")
        }
    }
    println("Task change successfully!")
    viewTasks()
}

fun information() {
    println(
        """
Hello, $nameCustomer!
                  
This is a simple console application that will help you save your tasks.
Also, this application can delete and change your tasks, for this you will need to register a specific command.
Command List: 1) Deleting tasks - \del
              2) Changing tasks- \change
              3) Information - \help
              4) View available tasks - \view 
              5) End the program - \end 
              6) Add a task - \add"""
    )
}

fun viewTasks() {
    for (el in 1..tasksList.size) {
        if (tasksList[el - 1].contains("\\d - ".toRegex())) {
            println(tasksList[el - 1])
        } else println("$el - ${tasksList[el - 1]}")
    }
}

fun readFromFile() {
    val readListFromFile = File("input")
        .readLines()
    if (readListFromFile.isNotEmpty()) {
        tasksList += readListFromFile
    } else println("Your tasks are empty...")

}

fun writeToFile() {
    val writeInList = PrintWriter("input")
    for (el in 1..tasksList.size) {
        if (tasksList[el - 1].contains("\\d - ".toRegex())) {
            writeInList.println(tasksList[el - 1])
        } else writeInList.println("$el - ${tasksList[el - 1]}")
    }
    writeInList.close()
}