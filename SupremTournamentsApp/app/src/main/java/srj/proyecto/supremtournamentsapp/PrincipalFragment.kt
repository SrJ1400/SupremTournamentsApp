package srj.proyecto.supremtournamentsapp

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iphotoraterest.Adaptador
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import srj.proyecto.supremtournamentsapp.clases.TorneoIndividual
import srj.proyecto.supremtournamentsapp.databinding.FragmentPrincipalBinding
import srj.proyecto.supremtournamentsapp.serviciosApi.ApiRestAdapter


class PrincipalFragment : Fragment() {

    lateinit var torneos: ArrayList<TorneoIndividual>
    lateinit var binding: FragmentPrincipalBinding

    var adaptador: Adaptador? = null
    var opcionSelecionada:Gets = Gets.TorneoPorDisponibles
    var nombreGestor:String = ""
    var nivelTorneoIndividual:Int = 0



    private val model:DatoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPrincipalBinding.inflate(layoutInflater)
        cargarDatos(opcionSelecionada)


        //Con este trozo de código puede indicar que hacer al usar el swipe (deslizar el dedo de arriba a abajo)
        binding.swipeRefreshLayout.setOnRefreshListener {
            cargarDatos(opcionSelecionada)
            binding.swipeRefreshLayout.isRefreshing = false
        }


        var opcionesSpiner = resources.getStringArray(R.array.opcionesGet)
        binding.spinner.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, opcionesSpiner)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Snackbar.make(binding.root,"Elige una opcion", Snackbar.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, seleccionado: Int, p3: Long) {
                binding.inputParametro.setVisibility(View.INVISIBLE);
                binding.inputParametro.text = null

                //Hace un get cuando se pulsa el enter del teclado de Android, también muestra una snackbar si esta vacío
                binding.inputParametro.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
                    if (i == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                        if (!binding.inputParametro.text.isNullOrBlank() ){
                            when(seleccionado){
                                7 -> {
                                    opcionSelecionada = Gets.TorneoPorNombreGestor
                                    nombreGestor = binding.inputParametro.text.toString()
                                    cargarDatos(opcionSelecionada)
                                }

                                8-> {
                                    opcionSelecionada = Gets.TorneoPorNivel
                                    nivelTorneoIndividual = Integer.parseInt(binding.inputParametro.text.toString())
                                    cargarDatos(opcionSelecionada)
                                }
                            }
                        }
                        else{
                            Snackbar.make(binding.root, "Es necesario introducir un " + binding.inputParametro.hint, Snackbar.LENGTH_INDEFINITE)
                                .setAction("Acceptar", View.OnClickListener {} )
                                .setBackgroundTint(Color.rgb(118,0,0))
                                .setActionTextColor(Color.WHITE)
                                .show()
                        }
                        true
                    }
                    false
                })

                when(seleccionado){
                    0 ->{
                        opcionSelecionada = Gets.TorneoPorDisponibles
                        cargarDatos(opcionSelecionada)
                    }

                    1 ->{
                        opcionSelecionada = Gets.TorneoPorFechaFinAsc
                        cargarDatos(opcionSelecionada)
                    }

                    2 ->{
                        opcionSelecionada = Gets.TorneoPorFechaFinDesc
                        cargarDatos(opcionSelecionada)
                    }


                    3 ->{
                        opcionSelecionada = Gets.TorneoPorMenoresTrue
                        cargarDatos(opcionSelecionada)
                    }

                    4 -> {
                        opcionSelecionada = Gets.TorneoPorMenoresFalse
                        cargarDatos(opcionSelecionada)
                    }

                    5 ->{
                        opcionSelecionada = Gets.TorneoPorNivelAsc
                        cargarDatos(opcionSelecionada)
                    }

                    6 ->{
                        opcionSelecionada = Gets.TorneoPorNivelDesc
                        cargarDatos(opcionSelecionada)
                    }


                    7 ->{
                        opcionSelecionada = Gets.TorneoPorNombreGestor
                        binding.inputParametro.setVisibility(View.VISIBLE)
                        binding.inputParametro.hint = opcionesSpiner.get(seleccionado)
                        binding.inputParametro.inputType = InputType.TYPE_CLASS_TEXT
                    }


                    8 ->{
                        opcionSelecionada = Gets.TorneoPorNivel
                        binding.inputParametro.setVisibility(View.VISIBLE)
                        binding.inputParametro.hint = opcionesSpiner.get(seleccionado)
                        binding.inputParametro.inputType = InputType.TYPE_CLASS_NUMBER
                    }
                    else -> {
                        opcionSelecionada = Gets.TorneoPorDisponibles
                        cargarDatos(opcionSelecionada)
                    }
                }

            }

        }

        return binding.root
    }


    fun cargarDatos(gets: Gets) {
        CoroutineScope(Dispatchers.Main).async {
            binding.progressBar.visibility = View.VISIBLE
            binding.lista.visibility = View.GONE

            when(gets){
                Gets.TorneoPorDisponibles ->  torneos = ApiRestAdapter.seleccionarTorneoDisponibles().await()
                Gets.TorneoPorFechaFinAsc -> torneos = ApiRestAdapter.seleccionarTorneoPorFechaFinalizacionAsc().await()
                Gets.TorneoPorFechaFinDesc -> torneos = ApiRestAdapter.seleccionarTorneoPorFechaFinalizacionDesc().await()
                Gets.TorneoPorMenoresTrue ->  torneos = ApiRestAdapter.seleccionarTorneoPorMenoresTrue().await()
                Gets.TorneoPorMenoresFalse ->  torneos = ApiRestAdapter.seleccionarTorneoPorMenoresFalse().await()
                Gets.TorneoPorNivelAsc  -> torneos = ApiRestAdapter.seleccionarTorneoPorNivelAsc().await()
                Gets.TorneoPorNivelDesc  -> torneos = ApiRestAdapter.seleccionarTorneoPorNivelDesc().await()
                Gets.TorneoPorNombreGestor  -> torneos = ApiRestAdapter.seleccionarTorneoPorNombreGestor(nombreGestor).await()
                Gets.TorneoPorNivel  -> torneos = ApiRestAdapter.seleccionarTorneoPorNivel(nivelTorneoIndividual).await()
            }
            cargarAdaptador()
            binding.progressBar.visibility = View.GONE
            binding.lista.visibility = View.VISIBLE

        }
    }


    fun cargarAdaptador(){
        adaptador = Adaptador(torneos!!)
        binding.lista.adapter = adaptador
        binding.lista.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adaptador!!.onClick(View.OnClickListener{ view ->

            model.setItem(torneos!![binding.lista.getChildAdapterPosition(view)])
            NavHostFragment.findNavController(this).navigate(R.id.PrincipalToInfoTorneo)

        })
    }

    override fun onStart() {
        super.onStart()
        cargarDatos(opcionSelecionada)
    }
}