package srj.proyecto.supremtournamentsapp.clases

import android.os.Parcel
import android.os.Parcelable

data class Gestor(
    val contrasenya: String,
    val email: String,
    val idGestor: Int,
    val nombre: String


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(contrasenya)
        parcel.writeString(email)
        parcel.writeInt(idGestor)
        parcel.writeString(nombre)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Gestor> {
        override fun createFromParcel(parcel: Parcel): Gestor {
            return Gestor(parcel)
        }

        override fun newArray(size: Int): Array<Gestor?> {
            return arrayOfNulls(size)
        }
    }
}