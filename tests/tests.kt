

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Test
import java.io.File


class Tests {

    private fun assertFileContent(name: String, expectedContent: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expectedContent, content)
    }

    @Test
    fun toFile() {
        toFile("temp.txt", "line")
        assertFileContent("temp.txt",
                "line")
        File("temp.txt").delete()

        toFile("temp.txt", "line1" + "\n" + "line2")
        assertFileContent("temp.txt",
                """line1
line2""")
        File("temp.txt").delete()
    }

    @Test
    fun lineHandling(){
        assertEquals("1 ", lineHandling('c', 1, 2, "1 2 3 4 5 6"))
        assertEquals("1 6", lineHandling('w', 1, 2, "1 6 3 4 5 6"))
        assertEquals("mother father", lineHandling('w', 1, 2, "mother     father sister brother"))
        assertEquals("", lineHandling('f', 1, 2, "1 2 3 4 5 6"))
        assertEquals("3 4 ", lineHandling('w', 3, -1, "1 2 3 4 "))
    }

    @Test
    fun argParsing(){
        var arr = ArrayList<String>()
        var args = ArrayList<String>()
        arr.add("file")
        arr.add("2")
        arr.add("7")
        args.add("file")
        args.add("2-7")
        assertEquals(arr,argParsing(args))

        arr.clear()
        args.clear()
        arr.add("")
        arr.add("5")
        arr.add("7")
        args.add("")
        args.add("5-7")
        assertEquals(arr,argParsing(args))

        arr.clear()
        args.clear()
        args.add("file")
        args.add("5-")
        arr.add("file")
        arr.add("5")
        arr.add("-1")
        assertEquals(arr, argParsing(args))
    }
}

