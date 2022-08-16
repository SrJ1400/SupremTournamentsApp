package srj.proyecto.supremtournamentsapp.serviciosApi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import srj.proyecto.supremtournamentsapp.clases.TorneoIndividual

interface ProveedorServicios {

    @GET("torneoindividual/fechaposterioractualyplazaslibres")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend  fun getTorneoPorDisponibles(): Response<ArrayList<TorneoIndividual>>

    @GET("torneoindividual/fechafinalizacion/asc")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend  fun getTorneoPorFechaFinAsc(): Response<ArrayList<TorneoIndividual>>

    @GET("torneoindividual/fechafinalizacion/desc")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend  fun getTorneoPorFechaFinDesc(): Response<ArrayList<TorneoIndividual>>

    @GET("torneoindividual/edad/true")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend  fun getTorneoPorMenoresTrue(): Response<ArrayList<TorneoIndividual>>

    @GET("torneoindividual/edad/false")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend  fun getTorneoPorMenoresFalse(): Response<ArrayList<TorneoIndividual>>

    @GET("torneoindividual/nivel/asc")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend  fun getTorneoPorNivelAsc(): Response<ArrayList<TorneoIndividual>>

    @GET("torneoindividual/nivel/desc")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend  fun getTorneoPorNivelDesc(): Response<ArrayList<TorneoIndividual>>

   @GET("torneoindividual/gestor/{nombre}")
   @Headers("Accept: application/json", "Content-Type: application/json")
   suspend fun getTorneoPorNombreGestor(@Path("nombre") id: String): Response<ArrayList<TorneoIndividual>>

   @GET("torneoindividual/nivel/{nivel}")
   @Headers("Accept: application/json", "Content-Type: application/json")
   suspend fun getTorneoPorNivel(@Path("nivel") id: Int): Response<ArrayList<TorneoIndividual>>

}