package blackstone.com.mybike.data

import com.google.gson.annotations.SerializedName

data class BikeRow(@SerializedName("rackTotCnt") var rackTotCnt: String,
                   @SerializedName("stationName") var stationName: String,
                   @SerializedName("parkingBikeTotCnt") var parkingBikeTotCnt: String,
                   @SerializedName("shared") var shared: String,
                   @SerializedName("stationLatitude") var stationLatitude: String,
                   @SerializedName("stationLongitude") var stationLongitude: String,
                   @SerializedName("stationId") var stationId: String)