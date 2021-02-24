package com.tassiecomp.practice

fun main(array: Array<String>) {
    val user1: User = User("Sean", 30) //constructor

    val user2: User = User("                  Jason", 30) //constructor

    val user3: User = User(age = 60) //constructor //when we don't specify name,



}

class User(name: String = "No Name", var age: Int) {
    init {
        print("New user created. Name: ${this.name},Age: $age,")
    }

    //if we want to remove the space
    val name: String

    init {
        if (name.isBlank()){
            this.name = "No Name"
        } else {
            this.name = name.trim()
        }
        println(" Name: ${this.name}")
    }
}