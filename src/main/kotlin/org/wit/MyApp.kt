package org.wit

import org.wit.styles.Styles
import org.wit.view.MainView
import tornadofx.App

class MyApp: App(MainView::class, Styles::class)