import java.io.BufferedReader
import java.io.InputStream
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CellManager {

    private var cells: HashMap<Int, Cell> = HashMap()

    /**
     * Reads a CSV file and returns a list of cells.
     *
     * @param inputStream the file to be read (as a file input stream).
     * @return list of cell objects (list will be empty if file format is invalid).
     */
    fun readCsv(inputStream: InputStream): HashMap<Int, Cell>? {

        try {

            if (cells.isNotEmpty()) {
                cells.clear()
            }

            val reader: BufferedReader = inputStream.bufferedReader()
            val header: String = reader.readLine()
            val lines: List<Cell> = reader.lineSequence()

                .filter {

                    it.isNotBlank()

                }.map {

                    // Regex splits string on commas (but ignores commas inside double quotation marks).
                    val lineRegex: Regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)".toRegex()
                    val tokens: List<String> = it.split(lineRegex, limit = 12)

                    // OEM
                    var oem: String? = tokens[0]

                    if (oem != null) {
                        if (oem.isBlank()) {
                            oem = null
                        }
                    }

                    // Model
                    var model: String? = tokens[1]

                    if (model != null) {
                        if (model.isBlank()) {
                            model = null
                        }
                    }

                    // Launched announced
                    val launchAnnounced: String = tokens[2].replace("\"", "")
                    var launchAnnouncedParseInt: Int? = null

                    // Regex expects at least one occurrence of exactly four consecutive digits.
                    // The consecutive digits can be located anywhere within the string.
                    val launchAnnouncedRegex: Regex = ".*?[0-9]{4}.*?".toRegex()

                    if (launchAnnounced.matches(launchAnnouncedRegex)) {
                        // Use regex to remove whitespace and letters.
                        launchAnnouncedParseInt = "(\\d+)".toRegex().find(launchAnnounced)?.value?.toInt()
                    }

                    // Launch Status
                    var launchStatus: String? = tokens[3].replace("\"", "")

                    if (launchStatus != null) {

                        if (!launchStatus.contentEquals("Discontinued") &&
                            !launchStatus.contentEquals("Cancelled")) {

                            // Regex expects the substring "Available\. Released " and exactly
                            // four consecutive digits. The consecutive digits can have text afterwards.
                            val launchStatusRegex: Regex = "^Available\\. Released [0-9]{4}.*?".toRegex()

                            if (!launchStatus.matches(launchStatusRegex)) {

                                launchStatus = null
                            }
                        }
                    }

                    // Body dimensions
                    var bodyDimensions: String? = tokens[4]

                    // Regex expects at least one digit and letter anywhere in the string.
                    // The letter(s) case does not matter.
                    if (bodyDimensions != null) {
                        if (!bodyDimensions.matches(".*?[a-z].*?".toRegex(RegexOption.IGNORE_CASE))) {
                            if (!bodyDimensions.matches(".*?\\d.*?".toRegex())) {
                                bodyDimensions = null
                            }
                        }
                    }

                    // Body weight
                    val bodyWeight: String = tokens[5]

                    // Regex expects at least on digit and the letter "g" afterwards.
                    // Decimal numbers are allowed as long as there is at least one digit afterwards.
                    // There can be any amount of whitespace between the number and "g", but nothing else.
                    // Text after "g" is allowed, but not before the digit(s).
                    val bodyWeightRegex: Regex = "^[1-9]\\d*(\\.\\d+)?\\s*g.*?".toRegex(RegexOption.IGNORE_CASE)
                    var bodyWeightParseFloat: Float? = null

                    if (bodyWeight.matches(bodyWeightRegex)) {
                        // Use regex to remove whitespace, the letter "g", and everything after that.
                        bodyWeightParseFloat = bodyWeight.lowercase(Locale.getDefault()).
                        replace("\\s*g.*".toRegex(), "").toFloat()
                    }

                    // Body SIM
                    var bodySIM: String? = tokens[6].replace("\"", "")

                    if (bodySIM != null) {
                        if (bodySIM.isNotBlank()) {
                            if (bodySIM.contentEquals("Yes") ||
                                bodySIM.contentEquals("No")) {
                                bodySIM = null
                            }
                        } else {
                            bodySIM = null
                        }
                    }

                    // Display type
                    var displayType: String? = tokens[7].replace("\"", "")

                    if (displayType != null) {
                        if (displayType.isBlank()) {
                            displayType = null
                        }
                    }

                    // Display size
                    val displaySize: String = tokens[8].replace("\"", "")
                    var displaySizeParseFloat: Float? = null

                    // Regex expects at least on digit and the substring "inches" afterwards.
                    // Decimal numbers are allowed as long as there is at least one digit afterwards.
                    // There can be any amount of whitespace between the number and "inches", but nothing else.
                    // Text after "inches" is allowed, but not before the digit(s).
                    val displaySizeRegex: Regex = "^[1-9]\\d*(\\.\\d+)?\\s*inches.*?".toRegex(RegexOption.IGNORE_CASE)

                    if (displaySize.matches(displaySizeRegex)) {
                        // Use regex to remove whitespace, the letter "g", and everything after that.
                        displaySizeParseFloat = displaySize.lowercase(Locale.getDefault()).
                        replace("\\s*inches.*".toRegex(), "").toFloat()
                    }

                    // Display resolution
                    var displayResolution: String? = tokens[9].replace("\"", "")

                    if (displayResolution != null) {
                        if (displayResolution.isBlank()) {
                            displayResolution = null
                        }
                    }

                    // Feature sensors
                    var featuresSensors: String? = tokens[10].replace("\"", "")

                    if (featuresSensors != null) {
                        if (!featuresSensors.contentEquals("V1")) {
                            if (!featuresSensors.contains("[a-z]".toRegex(RegexOption.IGNORE_CASE))) {
                                featuresSensors = null
                            }
                        }
                    }

                    // Platform OS
                    var platformOS: String? = tokens[11].replace("\"", "").split(",")[0]

                    if (platformOS != null) {
                        if (!platformOS.contains("[a-z]".toRegex(RegexOption.IGNORE_CASE))) {
                            platformOS = null
                        }
                    }

                    Cell(
                        oem, model, launchAnnouncedParseInt, launchStatus,
                        bodyDimensions, bodyWeightParseFloat, bodySIM, displayType,
                        displaySizeParseFloat, displayResolution, featuresSensors, platformOS
                    )

                }.toList()

            // Put each cellphone into the hashmap from lines.
            for (index in lines.indices) {

                // Ignore duplicate columns (lines).
                if (!cells.containsValue(lines[index])) {
                    cells[index] = lines[index]
                }
            }
            return cells

        } catch (exception: Exception) {

            return null
        }
    }

    fun getCells(): HashMap<Int, Cell> {
        return cells
    }

    fun getHighestAvgBuildWeight(): String {

        var index: Int
        val uniqueCount: ArrayList<String> = arrayListOf()
        val weightSums: ArrayList<Float> = arrayListOf()
        val weightCounts: ArrayList<Int> = arrayListOf()
        val avgWeights: ArrayList<Float> = arrayListOf()

        for ((key, value) in cells) {

            if (!uniqueCount.contains(value.oem) && value.oem != null) {

                uniqueCount.add(value.oem)
                weightSums.add(0F)
                weightCounts.add(0)
                avgWeights.add(0F)
            }
            if (uniqueCount.contains(value.oem)) {

                index = uniqueCount.indexOf(value.oem)

                if (value.bodyWeight != null) {

                    weightSums[index] = weightSums[index] + value.bodyWeight!!
                }
                weightCounts[index] += 1
            }
        }
        for (oem in uniqueCount.indices) {

            avgWeights[oem] = weightSums[oem] / weightCounts[oem]
        }
        return uniqueCount[avgWeights.indices.maxBy { avgWeights[it] }]
    }

    fun getSeparateAnnouncementAndLaunchYears(): List<Cell> {

        val delayedCells: ArrayList<Cell> = arrayListOf()
        var releaseDate: Int?

        for ((key, value) in cells) {

            if (!delayedCells.contains(value) && value.launchAnnounced != null) {

                releaseDate = value.launchStatus?.let { "(\\d+)".toRegex().find(it)?.value?.toInt() }

                if (releaseDate != null && releaseDate != value.launchAnnounced) {

                    delayedCells.add(value)
                }
            }
        }
        return delayedCells
    }

    fun getPhonesWithOneFeatureSensor(): Int {

        var oneFeatureSensorCount = 0

        for ((key, value) in cells) {

            // Multiple feature sensors always use commas to separate them.
            // By ignoring feature sensors with commas we get phones with only one feature.
            if (value.featuresSensors != null && !value.featuresSensors.contains(",")) {

                oneFeatureSensorCount++
            }
        }
        return oneFeatureSensorCount
    }

    fun getYearWithMostPhonesLaunched(): Int {

        val launchYears: ArrayList<Int> = arrayListOf()
        val phonesLaunched: ArrayList<Int> = arrayListOf()

        for ((key, value) in cells) {

            if (!launchYears.contains(value.launchAnnounced) && value.launchAnnounced != null &&
                value.launchAnnounced > 1999) {

                launchYears.add(value.launchAnnounced)
                phonesLaunched.add(1)
            }
            if (launchYears.contains(value.launchAnnounced) && value.launchAnnounced != null) {

                phonesLaunched[launchYears.indexOf(value.launchAnnounced)] += 1
            }
        }
        return launchYears[phonesLaunched.indices.maxBy { phonesLaunched[it] }]
    }

    fun getColumnMean(columnName: String): Float? {

        if (cells.size > 0) {

            var columnData: Float
            var columnSum = 0F
            var columnCount = 0
            val acceptableColumnNames: ArrayList<String> = arrayListOf()

            acceptableColumnNames.add("launch_announced")
            acceptableColumnNames.add("body_weight")
            acceptableColumnNames.add("display_size")

            // Check if there is a column by that name.
            // Also, prevent using a column of strings (getting the mean won't make sense).
            if (!acceptableColumnNames.contains(columnName)) {
                return null
            }
            for ((key, value) in cells) {

                if (value.getColumn(columnName) != null) {

                    columnData = value.getColumn(columnName) as Float
                    columnSum += columnData
                    columnCount++
                }
            }
            return (columnSum / columnCount)
        }
        return null
    }

    fun getColumnMedian(columnName: String): Float? {

        if (cells.size > 0) {

            val rowData: ArrayList<Float> = arrayListOf()
            val acceptableColumnNames: ArrayList<String> = arrayListOf()

            acceptableColumnNames.add("launch_announced")
            acceptableColumnNames.add("body_weight")
            acceptableColumnNames.add("display_size")

            // Check if there is a column by that name.
            // Also, prevent using a column of strings (getting the mean won't make sense).
            if (!acceptableColumnNames.contains(columnName)) {
                return null
            }
            for ((key, value) in cells) {

                if (value.getColumn(columnName) != null) {

                    rowData.add(value.getColumn(columnName).toString().toFloat())
                }
            }
            rowData.sort()

            if (rowData.size % 2 == 1) {
                return rowData[rowData.size / 2]
            } else {
                return (rowData[rowData.size / 2 - 1] + rowData[rowData.size / 2]) / 2
            }
        }
        return null
    }

    fun getUniqueValueCount(columnName: String): Int? {

        if (cells.size > 0) {

            var columnData: String
            val uniqueCount: ArrayList<String> = arrayListOf()

            // Check if there is a column by that name.
            if (cells[0]?.getColumn(columnName) == null) {
                return null
            }

            for ((key, value) in cells) {

                columnData = value.getColumn(columnName).toString()

                if (!uniqueCount.contains(columnData) && columnData.isNotBlank()) {

                    uniqueCount.add(value.getColumn(columnName).toString())
                }
            }
            return uniqueCount.size
        }
        return null
    }
}