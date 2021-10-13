package org.wit.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.text.FontWeight
import org.wit.controller.ImageViewController
import org.wit.fileSystem
import org.wit.imageDirectory
import org.wit.model.ImageData
import tornadofx.*
import java.io.File

class ImageIconView constructor(imageData: ImageData): View() {

    val controller: ImageViewController by inject()
    val input = SimpleStringProperty()

    init {
        input.value = imageData.name
    }

    override val root = hbox {
        imageview(File("$imageDirectory/${imageData.name}").toURI().toString())
        borderpane {
            prefWidth = 410.0
            top = textfield(input) {
                hgrow = Priority.ALWAYS
                useMaxWidth = true
                alignment = Pos.CENTER
            }
            center = button("rename") {
                hgrow = Priority.ALWAYS
                useMaxWidth = true
                setOnAction {
                    controller.rename(imageData.id, input.value!!)
                }
            }

            bottom = button("Add to group") {
                hgrow = Priority.ALWAYS
                useMaxWidth = true
                setOnAction {
                    find<GroupAdderFragment>(
                        mapOf("imageData" to imageData)
                    ).openModal()
                }
            }
        }
    }
}

class GroupAdderFragment: Fragment("") {
    val imageData: ImageData = params["imageData"] as ImageData
    val input = SimpleStringProperty()

    override val root = vbox {
        prefWidth = 200.0
        prefHeight = 150.0
        label("All groups") {
            style {
                fontWeight = FontWeight.BOLD
            }
        }
        for(group in fileSystem().imageGroupArray) {
            add(
                label(group.name) {  }
            )
        }
        form {
            fieldset("Add to group") {
                field("name") {
                    textfield(input) {  }
                }
            }
            button("Commit") {
                setOnAction {
                    if(fileSystem().addToGroup(imageData.id, input.value!!))
                        close()
                }
            }
        }
    }
}