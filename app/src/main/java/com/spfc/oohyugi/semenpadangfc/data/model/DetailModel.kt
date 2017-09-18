package com.spfc.oohyugi.semenpadangfc.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by oohyugi on 8/16/17.
 */

class DetailModel : Serializable {
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("date")
    @Expose
    var date: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("img")
    @Expose
    var img: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
}
