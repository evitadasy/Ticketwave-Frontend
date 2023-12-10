package com.example.testing

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Event(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("img")
    val img: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("tickets")
    val tickets: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("pricingdetails")
    val pricingdetails: String?,

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(city)
        parcel.writeString(date)
        parcel.writeString(description)
        parcel.writeString(img)
        parcel.writeString(location)
        parcel.writeValue(price)
        parcel.writeValue(tickets)
        parcel.writeString(title)
        parcel.writeString(type)
        parcel.writeString(category)
        parcel.writeString(pricingdetails)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}