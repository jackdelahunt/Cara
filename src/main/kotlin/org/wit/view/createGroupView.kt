package org.wit.view

import javafx.beans.property.SimpleStringProperty
import org.wit.fileSystem
import tornadofx.*

class CreateGroupView: Fragment("Create a group") {

    val input = SimpleStringProperty()

    override val root = form {
        fieldset("Create a group") {
            field("name") {
                textfield(input) {  }
            }
        }
        button("Commit") {
            setOnAction {
                if(fileSystem().createGroup(input.value!!))
                    close()
            }
        }
    }
}