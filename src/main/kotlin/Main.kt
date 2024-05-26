import java.io.File
import java.io.FileInputStream

fun main() {

    val cellManager = CellManager()
    cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

    println("Company with the highest average build weight: " + cellManager.getCompanyWithHighestAvgBuildWeight())
    println("Number of unique display types: " + cellManager.getUniqueValueCount("display_type"))
    println("Year with the most phone launches: " + cellManager.getYearWithMostPhonesLaunched())
}