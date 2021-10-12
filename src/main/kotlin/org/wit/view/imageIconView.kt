package org.wit.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import org.wit.controller.ImageViewController
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
        }
    }
}