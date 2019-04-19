package blackstone.com.mybike.data

import com.google.gson.annotations.SerializedName

data class StatusResult(@SerializedName("CODE") var code: String, @SerializedName("MESSAGE") var result: String)