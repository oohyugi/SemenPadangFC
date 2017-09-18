package com.spfc.oohyugi.semenpadangfc.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by oohyugi on 8/16/17.
 */
data class NewsModel(
        @SerializedName("title")
        @Expose
        val title: String ,
        @SerializedName("date")
        @Expose
        val date: String,
        @SerializedName("description")
        @Expose
        val description: String,
        @SerializedName("author")
        @Expose
        val author: String,
        @SerializedName("img")
        @Expose
        val img: String,
        @SerializedName("url")
        @Expose
        val url: String

) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(title)
                parcel.writeString(date)
                parcel.writeString(description)
                parcel.writeString(author)
                parcel.writeString(img)
                parcel.writeString(url)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<NewsModel> {
                override fun createFromParcel(parcel: Parcel): NewsModel {
                        return NewsModel(parcel)
                }

                override fun newArray(size: Int): Array<NewsModel?> {
                        return arrayOfNulls(size)
                }
        }
}

