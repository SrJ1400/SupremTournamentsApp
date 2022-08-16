package srj.proyecto.supremtournamentsapp.serviciosApi

import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import srj.proyecto.supremtournamentsapp.clases.TorneoIndividual

object ApiRestAdapter {

    fun crearRetrofit(): ProveedorServicios {

        val url = "http://ubuntusrj.eastus.cloudapp.azure.com:8081/api/supremtournaments/"
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ProveedorServicios::class.java)
    }

    fun seleccionarTorneoDisponibles(): Deferred<ArrayList<TorneoIndividual>> {
        val proveedorServicios: ProveedorServicios = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            var datosDev=ArrayList<TorneoIndividual>()
            val response: Response<ArrayList<TorneoIndividual>>
            response=proveedorServicios.getTorneoPorDisponibles()
            if (response.isSuccessful) {
                datosDev = response.body()!!
            }
            datosDev
        }
    }


    fun seleccionarTorneoPorFechaFinalizacionAsc(): Deferred<ArrayList<TorneoIndividual>> {
        val proveedorServicios: ProveedorServicios = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            var datosDev=ArrayList<TorneoIndividual>()
            val response: Response<ArrayList<TorneoIndividual>>
            response=proveedorServicios.getTorneoPorFechaFinAsc()
            if (response.isSuccessful) {
                datosDev = response.body()!!
            }
            datosDev
        }
    }

    fun seleccionarTorneoPorFechaFinalizacionDesc(): Deferred<ArrayList<TorneoIndividual>> {
        val proveedorServicios: ProveedorServicios = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            var datosDev=ArrayList<TorneoIndividual>()
            val response: Response<ArrayList<TorneoIndividual>>
            response=proveedorServicios.getTorneoPorFechaFinDesc()
            if (response.isSuccessful) {
                datosDev = response.body()!!
            }
            datosDev
        }
    }

    fun seleccionarTorneoPorMenoresTrue(): Deferred<ArrayList<TorneoIndividual>> {
        val proveedorServicios: ProveedorServicios = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            var datosDev=ArrayList<TorneoIndividual>()
            val response: Response<ArrayList<TorneoIndividual>>
            response=proveedorServicios.getTorneoPorMenoresTrue()
            if (response.isSuccessful) {
                datosDev = response.body()!!
            }
            datosDev
        }
    }

    fun seleccionarTorneoPorMenoresFalse(): Deferred<ArrayList<TorneoIndividual>> {
        val proveedorServicios: ProveedorServicios = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            var datosDev=ArrayList<TorneoIndividual>()
            val response: Response<ArrayList<TorneoIndividual>>
            response=proveedorServicios.getTorneoPorMenoresFalse()
            if (response.isSuccessful) {
                datosDev = response.body()!!
            }
            datosDev
        }
    }

    fun seleccionarTorneoPorNivelAsc(): Deferred<ArrayList<TorneoIndividual>> {
        val proveedorServicios: ProveedorServicios = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            var datosDev=ArrayList<TorneoIndividual>()
            val response: Response<ArrayList<TorneoIndividual>>
            response=proveedorServicios.getTorneoPorNivelAsc()
            if (response.isSuccessful) {
                datosDev = response.body()!!
            }
            datosDev
        }
    }

    fun seleccionarTorneoPorNivelDesc(): Deferred<ArrayList<TorneoIndividual>> {
        val proveedorServicios: ProveedorServicios = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            var datosDev=ArrayList<TorneoIndividual>()
            val response: Response<ArrayList<TorneoIndividual>>
            response=proveedorServicios.getTorneoPorNivelDesc()
            if (response.isSuccessful) {
                datosDev = response.body()!!
            }
            datosDev
        }
    }


    fun seleccionarTorneoPorNombreGestor(nombreGestor:String): Deferred<ArrayList<TorneoIndividual>> {
        val proveedorServicios: ProveedorServicios = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            var datosDev=ArrayList<TorneoIndividual>()
            val response: Response<ArrayList<TorneoIndividual>>
            response=proveedorServicios.getTorneoPorNombreGestor(nombreGestor)
            if (response.isSuccessful) {
                datosDev = response.body()!!
            }
            datosDev
        }
    }


    fun seleccionarTorneoPorNivel(nivel:Int): Deferred<ArrayList<TorneoIndividual>> {
        val proveedorServicios: ProveedorServicios = crearRetrofit()
        return CoroutineScope(Dispatchers.IO).async {
            var datosDev=ArrayList<TorneoIndividual>()
            val response: Response<ArrayList<TorneoIndividual>>
            response=proveedorServicios.getTorneoPorNivel(nivel)
            if (response.isSuccessful) {
                datosDev = response.body()!!
            }
            datosDev
        }
    }
}