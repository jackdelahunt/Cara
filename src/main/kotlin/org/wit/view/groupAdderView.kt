package org.wit.view

import javafx.beans.property.SimpleStringProperty
import javafx.scene.text.FontWeight
import org.wit.fileSystem
import org.wit.model.ImageData
import tornadofx.*

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