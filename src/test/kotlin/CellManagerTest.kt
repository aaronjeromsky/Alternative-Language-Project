import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.io.FileInputStream

class CellManagerTest {

    private val cellManager = CellManager()

    @Test
    fun readCsv() {

        // Empty file.
        assertNull(cellManager.readCsv(FileInputStream(File("src/test/resources/empty.csv"))))

        // Invalid file.
        assertNull(cellManager.readCsv(FileInputStream(File("src/test/resources/invalid.csv"))))

        // Valid file.
        assertNotNull(cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv"))))
    }

    @Test
    fun transformData() {

        // File for testing transformations.
        cellManager.readCsv(FileInputStream(File("src/test/resources/transform.csv")))

        val transformedCell = Cell("Huawei", "P40", 2020, "Available. Released 2020, April 07",
            "148.9 x 71.1 x 8.5 mm (5.86 x 2.80 x 0.33 in)", 175.0F,
            "Single SIM (Nano-SIM/eSIM) or Hybrid Dual SIM (Nano-SIM, dual stand-by)",
            "OLED capacitive touchscreen, 16M colors", 6.1F, "1080 x 2340 pixels, 19.5:9 ratio (~422 ppi density)",
            "Infrared face recognition, fingerprint (under display, optical), accelerometer, gyro, proximity, compass, color spectrum",
            "Android 10")

        // Row with sample data.
        assertEquals(transformedCell, cellManager.getCells()[0])
    }

    @Test
    fun missingData() {

        // File for testing transformations.
        cellManager.readCsv(FileInputStream(File("src/test/resources/missing.csv")))

        val emptyCell = Cell(null, null, null, null,
            null, null, null, null,
            null, null, null, null)

        // Row with missing values, empty strings, and dashes.
        assertEquals(emptyCell, cellManager.getCells()[0])
    }

    @Test
    fun getCells() {

        cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

        val expected = Cell("Benefon","Vega",1999,"Discontinued",
            "145 x 56 x 23 mm (5.71 x 2.20 x 0.91 in)",190F,"Mini-SIM",
            "Monochrome graphic", null,"6 lines","V1", null)

        assertEquals(expected, cellManager.getCells()[0])
    }

    @Test
    fun getHighestAvgBuildWeight() {

        cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

        assertEquals("HP", cellManager.getHighestAvgBuildWeight())
    }

    @Test
    fun getSeparateAnnouncementAndLaunchYears() {

        cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

        val expected: ArrayList<Cell?> = arrayListOf()

        expected.add(cellManager.getCells()[297])
        expected.add(cellManager.getCells()[298])
        expected.add(cellManager.getCells()[825])

        val actual: List<Cell?> = cellManager.getSeparateAnnouncementAndLaunchYears()

        assertEquals(expected[0], actual[0])
        assertEquals(expected[1], actual[1])
        assertEquals(expected[2], actual[2])
    }

    @Test
    fun getPhonesWithOneFeatureSensor() {

        cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

        assertEquals(419, cellManager.getPhonesWithOneFeatureSensor())
    }

    @Test
    fun getYearWithMostPhonesLaunched() {

        cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

        assertEquals(2019, cellManager.getYearWithMostPhonesLaunched())
    }

    @Test
    fun getColumnMean() {

        cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

        assertEquals(5.620148F, cellManager.getColumnMean("display_size"))
    }

    @Test
    fun getColumnMedian() {

        cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

        assertEquals(6.35F, cellManager.getColumnMedian("display_size"))
    }

    @Test
    fun getUniqueValueCount() {

        cellManager.readCsv(FileInputStream(File("src/main/resources/cells.csv")))

        assertEquals(74, cellManager.getUniqueValueCount("display_type"))
    }
}