package org.wit

import org.wit.styles.Styles
import org.wit.view.AllImagesView
import tornadofx.App

class MyApp: App(AllImagesView::class, Styles::class) {
    override fun stop() {
        super.stop()
        fileSystem().save()
    }
}