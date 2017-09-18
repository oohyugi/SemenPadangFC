package com.spfc.oohyugi.semenpadangfc.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by oohyugi on 8/16/17.
 */
data class BaseModel<T>(
        @SerializedName("code")
        @Expose
        val code: Int? = null,
        @SerializedName("message")
        @Expose
        val message: String? = null,
        @SerializedName("data")
        @Expose
        val data: T
)
