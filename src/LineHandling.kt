
fun charHandling(from: Int, to: Int, line: String): String{
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

fun wordHandling(from: Int, to: Int, line: String): String{
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