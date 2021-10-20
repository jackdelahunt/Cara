package org.wit.model

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.wit.fileSystem
import org.wit.imageDirectory
import java.io.File

internal class FileSystemTest {

    @BeforeEach
    fun setUp() {
        var file = File("${imageDirectory}/test.png")
        if(!file.exists())
            file.createNewFile()

        fileSystem().sync()
    }

    @AfterEach
    fun tearDown() {
        var testFile = File("${imageDirectory}/test-rename.png")
        if(testFile.exists())
            testFile.delete()
    }

    @Test
    fun findById() {
        val testData = fileSystem().findByName("test.png")!!
        val testDataById = fileSystem().findById(testData.id)!!
        assertEquals(testData, testDataById)
    }

    @Test
    fun rename() {
        var testData = fileSystem().findByName("test.png")!!
        fileSystem().rename(testData.id, "test-rename.png")
        assertNotNull(fileSystem().findByName("test-rename.png"))
    }

    @Test
    fun addToGroup() {
        fileSystem().createGroup("group")
        val imageData = fileSystem().findByName("test.png")!!
        fileSystem().addToGroup(imageData.id, "group")
        assertTrue(fileSystem().getGroup("group")!!.ids.contains(imageData.id))
    }

    @Test
    fun getGroup() {
        fileSystem().createGroup("getgroup")
        assertNotNull(fileSystem().getGroup("getgroup"))
    }

    @Test
    fun createGroup() {
        fileSystem().createGroup("testGroup")
        var contains = false
        for(group in fileSystem().imageGroupArray) {
            if(group.name == "testGroup") {
                contains = true
                break
            }
        }

        assertTrue(contains)
    }
}