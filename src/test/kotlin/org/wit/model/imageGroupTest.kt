package org.wit.model

import org.junit.jupiter.api.Assertions.*

internal class ImageGroupTest {
    @org.junit.jupiter.api.Test
    fun testAddImageDataHappyPath() {
        val imageData = ImageData("test", 100);
        val imageGroup = ImageGroup("group");
        imageGroup.addImageData(imageData)

        assertTrue(imageGroup.ids.contains(100))
    }

    @org.junit.jupiter.api.Test
    fun testAddImageDataFail() {
        val imageData = ImageData("test", 100);
        val imageGroup = ImageGroup("group");
        imageGroup.addImageData(imageData)
        imageGroup.addImageData(imageData)

        var count = 0;
        imageGroup.ids.forEach(
            fun(id: Int) {
                if(id == 100) count++;
            }
        )

        assertEquals(1, count)
    }
}