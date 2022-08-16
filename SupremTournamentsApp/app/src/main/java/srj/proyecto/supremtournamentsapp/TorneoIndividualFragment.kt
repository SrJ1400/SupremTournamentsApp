package srj.proyecto.supremtournamentsapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import srj.proyecto.supremtournamentsapp.clases.TorneoIndividual
import srj.proyecto.supremtournamentsapp.databinding.FragmentTorneoIndividualBinding
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*


class TorneoIndividualFragment : Fragment() {

    lateinit var binding: FragmentTorneoIndividualBinding

    private val model:DatoViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTorneoIndividualBinding.inflate(layoutInflater)

        val date:Date = Date.from(Instant.now())

        val nameObserver = Observer<TorneoIndividual>{dato ->
            Picasso.get()
                .load(dato.fotoUrlTorneoIndividual)
                .error(R.mipmap.ic_launcher)
                .into(binding.imageView2)

            binding.nombreTorneoTextView.text = dato.nombreTorneoIndividual

            val formato = SimpleDateFormat("dd/MM/yyyy HH:mm")

            binding.fechaFinalizaTextView.text=  binding.fechaFinalizaTextView.text.toString() + ": " + formato.format(Date(dato.fechaFinalizacion))
            binding.fechaInicioTextView.text= binding.fechaInicioTextView.text.toString() + ": " + formato.format(Date(dato.fechaInicio))
            binding.nivelTextView.text= binding.nivelTextView.text.toString() + ": " + dato.nivel
            binding.menoresEdadCheckBox.isChecked = dato.menoresEdad == true
            binding.nombreGestorTextView.text = binding.nombreGestorTextView.text.toString() + dato.idGestor.nombre
            binding.emailGestorTextView.text = binding.emailGestorTextView.text.toString() + dato.idGestor.email
            binding.descripcionCompletaTextView.text= dato.descripcionCompleta

        }
        model.getItem.observe(requireActivity(), nameObserver)

        binding.floatingActionButton.setOnClickListener { view->
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "message/rfc822"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(model.getItem.value?.idGestor?.email))
            if (emailIntent.resolveActivity(activity?.packageManager!!) != null) {
                startActivity(Intent.createChooser(emailIntent, "Selecciona una aplicación"))
            }else{
                Toast.makeText(binding.root.context,"Tu dipositivo no puede mandar emails", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}