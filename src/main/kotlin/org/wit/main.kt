package org.wit
val fs = FileSystem();

fun main() {
    //launch<MyApp>()
    fs.sync()

    var input = -1
    while(input != 0) {
        input = menu()
        when(input) {
            1 -> listImageData()
            0 -> {}
        }
    }
    return;
}

fun menu(): Int {
    println("1: List all")
    println("0: Exit")
    println("--------")
    print("> ")

    val input = readLine()!!
    return input.toInt()
}

fun listImageData() {
    for(imageData in fs.imagesData) {
        println(imageData.toString())
    }
}



