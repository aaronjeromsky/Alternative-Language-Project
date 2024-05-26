import java.io.File
import java.io.FileInputStream

fun main() {

    val cellManager = CellManager()
    cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

    println("Company with the highest average build weight: " + cellManager.getHighestAvgBuildWeight())

    val delayedCells: List<Cell> = cellManager.getSeparateAnnouncementAndLaunchYears()

    println("Phones with a release year later than their announcement year:")

    for (cell in delayedCells) {

        println("\tOEM: " + cell.oem + ", Model: " + cell.model)
    }
    println("Number of phones with only one feature sensor: " + cellManager.getPhonesWithOneFeatureSensor())
    println("Year with the most phone launches (after 1999): " + cellManager.getYearWithMostPhonesLaunched())
    println("Mean (average) phone display size: " + cellManager.getColumnMean("display_size"))
    println("Median (middle) phone display size: " + cellManager.getColumnMedian("display_size"))
    println("Number of unique display types: " + cellManager.getUniqueValueCount("display_type"))
}