

fun main() {
    val mainDirectory = File("/Projects/Programming")
    // write your code here
    val labDirectory = "/Projects/Programming/Project1"
    val reportFile = "/Projects/Programming/Report.pdf"
    mainDirectory.mkdir()
    val labfile = File(labDirectory)
    labfile.mkdir()
    val pdffile = File(reportFile)
    pdffile.createNewFile()

    val files: List<File> = mutableListOf(mainDirectory, labfile, pdffile)

    // do not touch the lines below
    files.forEach {
        if (it.path == "/Projects/Programming" && it.isDirectory) {
            print("true")
        } else if (it.path == "/Projects/Programming/Project1" && it.isDirectory) {
            print("true")
        } else if (it.path == "/Projects/Programming/Report.pdf" && it.isFile) {
            print("true")
        } else {
            print("false")
        }
    }
}