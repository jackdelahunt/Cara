package org.wit.view

import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.text.FontWeight
import org.wit.fileSystem
import org.wit.imageDirectory
import org.wit.model.ImageGroup
import tornadofx.*
import java.io.File

class GroupPreview constructor(imageGroup: ImageGroup): View() {


        val previewImageFile = if(imageGroup.ids.size > 0) {
            val name = fileSystem().findById(imageGroup.ids[0])!!.name
            File("$imageDirectory/$name").toURI().toString()
        } else {
            {}.javaClass.getResource("/empty.png")!!.toURI().toString()
        }

        override val root = vbox {
        prefWidth = 740.0

        hbox {
            alignment = Pos.CENTER
            label(imageGroup.name) {

                useMaxWidth = true
                vgrow = Priority.ALWAYS

                style {
                    fontWeight = FontWeight.BOLD
                    fontSize = 20.px
                }
            }

            button("Delete") {
                setOnAction {
                    fileSystem().deleteGroup(imageGroup)
                }
            }
        }

        imageview(previewImageFile) {
            alignment = Pos.CENTER
            onLeftClick {
                find<GroupView>("imageGroup" to imageGroup).openModal()
            }
        }
    }
}