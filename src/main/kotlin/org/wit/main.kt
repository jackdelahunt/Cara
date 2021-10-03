package org.wit

import java.io.File

val fs = FileSystem();

fun main() {
    //launch<MyApp>()
    fs.sync()

    var input = -1
    while(input != 0) {
        input = menu()
        when(input) {
            1 -> listImageData()
            2 -> renameImage()
            0 -> {}
        }
        println()
    }
    return;
}

fun menu(): Int {
    println("1: List all")
    println("2: Rename")
    println("0: Exit")
    println("--------")
    print("> ")

    val input = readLine()!!
    return input.toInt()
}

fun listImageData() {
    for(imageData in fs.imageDataArray) {
        println(imageData.toString())
    }
}

fun renameImage() {
    print("Id>")
    val id = readLine()!!.toInt()
    print("Rename>")
    val newName = readLine()!!

    var foundImageData = fs.findById(id)!!
    File("$imageDirectory/${foundImageData.name}")
        .renameTo(File("$imageDirectory/$newName"))
    foundImageData.name = newName
    fs.save()
}



