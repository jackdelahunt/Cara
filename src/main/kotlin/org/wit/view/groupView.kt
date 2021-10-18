package org.wit.view

import javafx.beans.property.SimpleStringProperty
import javafx.scene.image.ImageView
import org.wit.fileSystem
import org.wit.imageDirectory
import org.wit.model.ImageGroup
import tornadofx.*
import java.io.File

class GroupView constructor(): Fragment("") {

    val imageGroup: ImageGroup by param()

    init {
        this.title = imageGroup.name
    }

    override val root = anchorpane {
        prefWidth = 740.0
        prefHeight = 540.0
        scrollpane {
            flowpane {
                useMaxWidth = true
                for (id in imageGroup.ids) {
                    val imageData = fileSystem().findById(id)!!
                    add(
                        ImageView(File("$imageDirectory/${imageData.name}").toURI().toString())
                    )
                }
            }
        }
    }
}