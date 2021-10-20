package org.wit.model

import org.junit.jupiter.api.Assertions.*

internal class ImageDataTest {

    @org.junit.jupiter.api.Test
    fun testToString() {
        val imageData = ImageData("test", 100);
        assertEquals("(100) test", imageData.toString())
    }
}