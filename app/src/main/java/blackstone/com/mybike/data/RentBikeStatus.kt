package blackstone.com.mybike.data

import com.google.gson.annotations.SerializedName

data class RentBikeStatus(@SerializedName("list_total_count") var list_total_count: String, @SerializedName("RESULT") var result: StatusResult, @SerializedName("row") var bikeRowList: ArrayList<BikeRow>)