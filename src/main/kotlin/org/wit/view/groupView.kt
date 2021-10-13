package org.wit.view

import javafx.scene.text.FontWeight
import org.wit.fileSystem
import org.wit.model.ImageGroup
import tornadofx.View
import tornadofx.label
import tornadofx.style
import tornadofx.vbox

class groupView constructor(imageGroup: ImageGroup): View() {

    override val root = vbox {
        label(imageGroup.name) {
            style {
                fontWeight = FontWeight.BOLD
            }
        }
        vbox {
            for (id in imageGroup.ids) {
                label(fileSystem().findById(id)!!.name) {  }
            }
        }
    }
}