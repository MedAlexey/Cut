
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