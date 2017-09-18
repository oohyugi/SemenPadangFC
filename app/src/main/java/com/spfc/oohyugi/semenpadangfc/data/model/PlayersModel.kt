package com.spfc.oohyugi.semenpadangfc.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by oohyugi on 8/16/17.
 */
data class PlayersModel(
        @SerializedName("name")
        @Expose
        val name: String ,
        @SerializedName("nomor")
        @Expose
        val nomor: String,
        @SerializedName("posisi")
        @Expose
        val posisi: String,

        @SerializedName("img")
        @Expose
        val img: String


) :
        Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(nomor)
        parcel.writeString(posisi)
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayersModel> {
        override fun createFromParcel(parcel: Parcel): PlayersModel {
            return PlayersModel(parcel)
        }

        override fun newArray(size: Int): Array<PlayersModel?> {
            return arrayOfNulls(size)
        }
    }
}
