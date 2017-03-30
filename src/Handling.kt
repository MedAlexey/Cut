import java.io.File

fun lineHandling(wOrC: Char, from: Int, to: Int, line: String): String {   // обрабатывает строку
    if (wOrC == 'c') {
        val result = StringBuilder()

        if (line.length < from) return ""
        else if (to == -1){
            for (i in from..line.length) {
                result.append(line[i - 1])
            }
        }
        else for (i in from..Math.min(to, line.length)) {
            result.append(line[i - 1])
        }
        return result.toString()
    }

    if (wOrC == 'w') {
        val result = StringBuilder()
        val parts = line.split(Regex("""\s+"""))

        if (from > parts.size) return ""
        else if(to == -1){
            for (i in from..parts.size) {
                result.append(parts[i - 1] + " ")
            }
        }
        else for (i in from..Math.min(to, parts.size)) {
            result.append(parts[i - 1] + " ")
        }
        result.deleteCharAt(result.length - 1)
        return result.toString()
    }
    return ""
}


fun toFile(ofile: String, line: String) {       //запись в файл
    val writer = File(ofile).bufferedWriter()
    writer.write(line)
    writer.close()
}


fun fromFile(wOrC: Char, file: String, from: Int, to: Int): String {
    val result = StringBuilder()
    for (line in File(file).readLines()) {
        val newLine = lineHandling(wOrC, from, to, line)
        result.append(newLine + "\n")
    }
    return result.toString()
}

fun argParsing(list: List<String>): List<String> {
    var from = ""
    var to = ""
    var file = ""
    val result = ArrayList<String>()
    var parts = list[list.size - 1].split(Regex("""\d+"""))   //если цифры нет, по пустое
    if (parts.size == 3 && parts[0] == "" && parts[1] == "-" && parts[2] == "") {
        parts = list[list.size - 1].split(Regex("""-"""))
        from = parts[0]
        to = parts[1]
    }
    if (parts.size == 2 && parts[0] == "" && parts[1] == "-"){
        parts = list[list.size - 1].split(Regex("""-"""))
        from = parts[0]
        to = "-1"
    }
    if (list.size == 2) file = list[0]
    result.add(0, file)
    result.add(1, from)
    result.add(2, to)

    return result
}