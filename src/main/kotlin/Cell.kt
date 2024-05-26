/**
 * A cellphone with relevant information.
 *
 * @property oem the oem of the cellphone.
 * @property model the model of the cellphone.
 * @property launchAnnounced the launch announcement date of the cellphone.
 * @property launchStatus the launch status of the cellphone.
 * @property bodyDimensions the body dimensions of the cellphone.
 * @property bodyWeight the body weight of the cellphone.
 * @property bodySIM the body SIM of the cellphone.
 * @property displayType the display type of the cellphone.
 * @property displaySize the display size of the cellphone.
 * @property displayResolution the display resolution of the cellphone.
 * @property featuresSensors the features sensors of the cellphone.
 * @property platformOS the platform OS of the cellphone.
 *
 * @constructor creates a cellphone with relevant information.
 */
data class Cell(val oem: String?, val model: String?, val launchAnnounced: Int?,
                val launchStatus: String?, val bodyDimensions: String?, var bodyWeight: Float?,
                val bodySIM: String?, val displayType: String?, val displaySize: Float?,
                val displayResolution: String?, val featuresSensors: String?, val platformOS: String?) {

    // NOTE: parameters in the data class constructor (see above) have internal getter and setter methods.

    fun getColumn(columnName: String): Any? {

        when (columnName.lowercase()) {

            "oem" -> return oem
            "model" -> return model
            "launch_announced" -> return launchAnnounced
            "launch_status" -> return launchStatus
            "body_dimensions" -> return bodyDimensions
            "body_weight" -> return bodyWeight
            "body_sim" -> return bodySIM
            "display_type" -> return displayType
            "display_size" -> return displaySize
            "display_resolution" -> return displayResolution
            "features_sensors" -> return featuresSensors
            "platform_os" -> return platformOS

            else -> return null
        }
    }

    override fun toString(): String {

        return "Cell(oem=$oem, model=$model, launchAnnounced=$launchAnnounced, " +
                "launchStatus=$launchStatus, bodyDimensions=$bodyDimensions, " +
                "bodyWeight=$bodyWeight, bodySIM=$bodySIM, displayType=$displayType, " +
                "displaySize=$displaySize, displayResolution=$displayResolution, " +
                "featuresSensors=$featuresSensors, platformOS=$platformOS)"
    }

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cell

        if (oem != other.oem) return false
        if (model != other.model) return false
        if (launchAnnounced != other.launchAnnounced) return false
        if (launchStatus != other.launchStatus) return false
        if (bodyDimensions != other.bodyDimensions) return false
        if (bodyWeight != other.bodyWeight) return false
        if (bodySIM != other.bodySIM) return false
        if (displayType != other.displayType) return false
        if (displaySize != other.displaySize) return false
        if (displayResolution != other.displayResolution) return false
        if (featuresSensors != other.featuresSensors) return false
        if (platformOS != other.platformOS) return false

        return true
    }

    override fun hashCode(): Int {
        var result = oem?.hashCode() ?: 0
        result = 31 * result + (model?.hashCode() ?: 0)
        result = 31 * result + (launchAnnounced ?: 0)
        result = 31 * result + (launchStatus?.hashCode() ?: 0)
        result = 31 * result + (bodyDimensions?.hashCode() ?: 0)
        result = 31 * result + (bodyWeight?.hashCode() ?: 0)
        result = 31 * result + (bodySIM?.hashCode() ?: 0)
        result = 31 * result + (displayType?.hashCode() ?: 0)
        result = 31 * result + (displaySize?.hashCode() ?: 0)
        result = 31 * result + (displayResolution?.hashCode() ?: 0)
        result = 31 * result + (featuresSensors?.hashCode() ?: 0)
        result = 31 * result + (platformOS?.hashCode() ?: 0)
        return result
    }
}