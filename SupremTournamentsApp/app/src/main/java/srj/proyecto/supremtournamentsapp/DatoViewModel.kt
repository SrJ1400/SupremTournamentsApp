package srj.proyecto.supremtournamentsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import srj.proyecto.supremtournamentsapp.clases.TorneoIndividual
class DatoViewModel: ViewModel() {
    private val liveData= MutableLiveData<TorneoIndividual>()
    val getItem: LiveData<TorneoIndividual> get() = liveData
    fun setItem(item: TorneoIndividual) {
        liveData.value = item
    }
}