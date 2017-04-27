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
    fun charHandlingTest(){
        assertEquals("2 3",charHandling(3,5,"1 2 3 4 5"))
        assertEquals("234", charHandling(2,4,"123456"))
    }

    @Test
    fun wordHandlingTest(){
        assertEquals("3 4 5", wordHandling(3,5,"1 2 3 4 5 6"))
        assertEquals("1 2 3", wordHandling(1,3,"1 2 3 "))
    }

    @Test
    fun argParsing() {
        val arr = ArrayList<String>()
        val args = ArrayList<String>()
        arr.add("file")
        arr.add("2")
        arr.add("7")
        args.add("file")
        args.add("2-7")
        assertEquals(arr, argParsing(args))

        arr.clear()
        args.clear()
        arr.add("")
        arr.add("5")
        arr.add("7")
        args.add("")
        args.add("5-7")
        assertEquals(arr, argParsing(args))

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