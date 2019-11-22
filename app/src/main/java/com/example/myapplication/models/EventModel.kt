package com.example.myapplication.models

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

//TODO Нет подарков и контактов
class EventModel private constructor(
    val eventName: String = "",
    val eventDate: String = "",
    val eventDescription: String = "",
    val eventPlace: String = "",
    val imageUri: Uri? = null

) {

    companion object{
        val CREATOR: Parcelable.Creator<Builder> = object : Parcelable.Creator<Builder> {
            override fun createFromParcel(`in`: Parcel): Builder {
                return Builder(`in`)
            }

            override fun newArray(size: Int): Array<Builder?> {
                return arrayOfNulls(size)
            }
        }
    }

    @SuppressLint("ParcelCreator")
    class Builder: Parcelable{
        var eventName: String = ""
        var eventDate: String = ""
        var eventDescription: String = ""
        var eventPlace: String = ""
        var imageUri: Uri? = null

        constructor()

        fun name(eventName : String): Builder{
            this.eventName = eventName
            return this
        }

        fun date(eventDate: String): Builder{
            this.eventDate = eventDate
            return this
        }

        fun description(description: String): Builder{
            eventDescription = description
            return this
        }

        fun place(eventPlace: String):Builder{
            this.eventPlace = eventPlace
            return this
        }

        fun uri(imageUri: Uri?):Builder{
            this.imageUri = imageUri
            return this
        }

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(eventName)
            dest?.writeString(eventDate)
            dest?.writeString(eventPlace)
            dest?.writeString(eventDescription)
            dest?.writeParcelable(imageUri, flags)
        }



        constructor(parcel:Parcel){
            eventName = parcel.readString().toString()
            eventDate = parcel.readString().toString()
            eventPlace = parcel.readString().toString()
            eventDescription = parcel.readString().toString()
            imageUri = parcel.readParcelable(Uri::class.java.classLoader)
        }

        override fun describeContents(): Int {
            return 0
        }


        fun build(): EventModel{
            return EventModel(eventName, eventDate, eventDescription, eventPlace, imageUri)
        }

        companion object CREATOR : Parcelable.Creator<Builder> {
            override fun createFromParcel(parcel: Parcel): Builder {
                return Builder(parcel)
            }

            override fun newArray(size: Int): Array<Builder?> {
                return arrayOfNulls(size)
            }
        }
    }
}