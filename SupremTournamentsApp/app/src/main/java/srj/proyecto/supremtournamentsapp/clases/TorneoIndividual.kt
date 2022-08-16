package srj.proyecto.supremtournamentsapp.clases

import android.os.Parcel
import android.os.Parcelable

data class TorneoIndividual(
    val descripcionCompleta: String,
    val descripcionCorta: String,
    val fechaFinalizacion: Long,
    val fechaInicio: Long,
    val fotoUrlTorneoIndividual: String,
    val idGestor: Gestor,
    val idTorneoIndividual: Int,
    val menoresEdad: Boolean,
    val nivel: Int,
    val nombreTorneoIndividual: String,
    val solicitudesMaximos: Int


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readParcelable(Gestor::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(descripcionCompleta)
        parcel.writeString(descripcionCorta)
        parcel.writeLong(fechaFinalizacion)
        parcel.writeLong(fechaInicio)
        parcel.writeString(fotoUrlTorneoIndividual)
        parcel.writeParcelable(idGestor, flags)
        parcel.writeInt(idTorneoIndividual)
        parcel.writeByte(if (menoresEdad) 1 else 0)
        parcel.writeInt(nivel)
        parcel.writeString(nombreTorneoIndividual)
        parcel.writeInt(solicitudesMaximos)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "TorneoIndividual(descripcionCompleta='$descripcionCompleta', descripcionCorta='$descripcionCorta', fechaFinalizacion=$fechaFinalizacion, fechaInicio=$fechaInicio, fotoUrlTorneoIndividual='$fotoUrlTorneoIndividual', idGestor=$idGestor, idTorneoIndividual=$idTorneoIndividual, menoresEdad=$menoresEdad, nivel=$nivel, nombreTorneoIndividual='$nombreTorneoIndividual', solicitudesMaximos=$solicitudesMaximos)"
    }

    companion object CREATOR : Parcelable.Creator<TorneoIndividual> {
        override fun createFromParcel(parcel: Parcel): TorneoIndividual {
            return TorneoIndividual(parcel)
        }

        override fun newArray(size: Int): Array<TorneoIndividual?> {
            return arrayOfNulls(size)
        }
    }


}