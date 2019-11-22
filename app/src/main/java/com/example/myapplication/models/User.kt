package com.example.myapplication.models

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

@SuppressLint("ParcelCreator")
class User : Parcelable {


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(parcel: Parcel): User {
                return User(parcel)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }
    }

    var Phone: String = ""
    var Code: String = ""
    var Name: String = ""
    var Date_birthday: String = ""
    var Gender: String = ""
    var Password: String = ""
    var PhotoUri: Uri? = null
    var PhotoCircleUri: Uri? = null


    constructor (parcel: Parcel) {
        Phone = parcel.readString().toString()
        Code = parcel.readString().toString()
        Name = parcel.readString().toString()
        Gender = parcel.readString().toString()
        Date_birthday = parcel.readString().toString()
        Password = parcel.readString().toString()
        PhotoUri = parcel.readParcelable(Uri::class.java.classLoader)
        PhotoCircleUri = parcel.readParcelable(Uri::class.java.classLoader)
    }


    constructor()

    fun newUserBuilder(): Builder {
        return User().Builder()
    }

    fun createUserBuilder(user: User): Builder {
        return User()
            .Builder()
            .setUser_Phone(user.getUserPhone())
            .setSms_Code_Value(user.getUserSMSCode())
            .setUser_Name(user.getUserName())
            .setUser_DateOfBirth(user.getUserDateOfBirth())
            .setUser_Password(user.getUserPassword())
            .setUser_PhotoCircleUri(user.getUserPhotoCircleUri())
            .setUser_Gender(user.getUserGender())
            // .setUser_Photo(user.getUser_Photo())
            .setUser_PhotoUri(user.getUserPhotoUri())

    }

    fun getUserPhone(): String{
        return Phone
    }

    fun getUserName(): String? {
        return Name
    }

    fun getUserSMSCode(): String? {
        return Code
    }

    fun getUserGender(): String? {
        return Gender
    }

    fun getUserDateOfBirth(): String? {
        return Date_birthday
    }

    fun getUserPassword(): String? {
        return Password
    }

    fun getUserPhotoUri(): Uri? {
        return this.PhotoUri
    }

    fun getUserPhotoCircleUri(): Uri? {
        return PhotoCircleUri
    }


    inner class Builder {
        fun setUser_Phone(user_Phone: String?): Builder {
            if (user_Phone != null && user_Phone != "") {
                this@User.Phone = user_Phone
            }
            return this
        }

        fun setSms_Code_Value(user_Code: String?): Builder {
            if (user_Code != null && user_Code != "") {
                this@User.Code = user_Code
            }
            return this
        }

        fun setUser_Name(user_Name: String?): Builder {
            if (user_Name != null && user_Name != "") {
                this@User.Name = user_Name
            }
            return this
        }

        fun setUser_Gender(user_Gender: String?): Builder {
            if (user_Gender != null && user_Gender != "") {
                this@User.Gender = user_Gender
            }
            return this
        }

        fun setUser_DateOfBirth(user_DateOfBirth: String?): Builder {
            if (user_DateOfBirth != null && user_DateOfBirth != "") {
                this@User.Date_birthday = user_DateOfBirth
            }
            return this
        }

        fun setUser_Password(user_Password: String?): Builder {
            if (user_Password != null && user_Password != "") {
                this@User.Password = user_Password
            }
            return this
        }

        fun setUser_PhotoUri(user_PhotoUri: Uri?): Builder {
            if (user_PhotoUri != null) {
                this@User.PhotoUri = user_PhotoUri
            }
            return this
        }

        fun setUser_PhotoCircleUri(user_PhotoCircleUri: Uri?): Builder {
            if (user_PhotoCircleUri != null) {
                this@User.PhotoCircleUri = user_PhotoCircleUri
            }
            return this
        }

        fun build(): User {
            return this@User
        }


    }


    /*constructor(Phone: String, FIO: String, Date_birthday: Date, Sex: String){
        this.Phone = Phone
        this.FIO = FIO
        this.Date_birthday = Date_birthday
        this.Sex = Sex
    }*/
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(Phone)
        dest?.writeString(Code)
        dest?.writeString(Name)
        dest?.writeString(Gender)
        dest?.writeString(Date_birthday)
        dest?.writeString(Password)
        dest?.writeParcelable(PhotoUri, flags)
        dest?.writeParcelable(PhotoCircleUri, flags)
    }

    /*override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Phone)
        parcel.writeString(Code)
        *//*parcel.writeString(FIO)
        parcel.writeString(Sex)*//*
    }*/

    override fun describeContents(): Int {
        return 0
    }


}

