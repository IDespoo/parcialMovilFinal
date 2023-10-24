package com.qt.aplicaciondispositivosmoviles
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.qt.aplicaciondispositivosmoviles.adapters.CarAdapter
import com.qt.aplicaciondispositivosmoviles.databinding.ActivityMainBinding
import com.qt.aplicaciondispositivosmoviles.model.Car

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityMainBinding
    lateinit var listaCarros : ArrayList<Car>
    lateinit var adapter : CarAdapter
     var validar : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listaCarros = ArrayList()
        adapter = CarAdapter(this,listaCarros)
        binding.btnAcept.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        validar = false
        if (v?.id == R.id.btnAcept){
            binding.txtError.text = ""
            if (isnull()){
                limpiar()
                return
            }
            else if (modeloNoMayor2000()){
                limpiar()
                return
            }
            cilindrajeEsMenor1500()
            valorDescuento()

            var carro :Car= Car(binding.txtName.text.toString(),
            binding.txtMarca1.text.toString(),
            binding.txtColor1.text.toString(),
            binding.txtCilindraje1.text.toString().toInt(),
            binding.txtModelo1.text.toString().toInt(),
            binding.txtPuerta1.text.toString().toInt(),
            binding.txtUrlImage.text.toString())
            listaCarros.add(carro)
            binding.lstListPersonas.adapter = adapter
            adapter.notifyDataSetChanged()
            limpiar()

        }
    }

    fun isnull(): Boolean {
        if (binding.txtModelo1.text.toString().toIntOrNull() == null ||
            binding.txtPuerta1.text.toString().toIntOrNull() == null ||
            binding.txtCilindraje1.text.toString().toIntOrNull() == null ||
            binding.txtName.text.toString().isNullOrEmpty() ||
            binding.txtMarca1.text.toString().isNullOrEmpty() ||
            binding.txtColor1.text.toString().isNullOrEmpty() ||
            binding.txtUrlImage.text.toString().isNullOrEmpty()
        ) {
            binding.txtError.text = "Error, no se pueden ingresar datos vacíos"
            validar = true
        }

        return validar
    }


    fun modeloNoMayor2000(): Boolean {

        if(binding.txtCilindraje1.text.toString().toInt() > 2000){
            if(binding.txtModelo1.text.toString().toInt() < 2000){
                binding.txtError.text = "El Cilindraje es mayor a 2000 y el modelo es menor al año 2000"
                validar = true
            }
        }
        return validar
    }

    fun cilindrajeEsMenor1500(){
        if(binding.txtCilindraje1.text.toString().toInt() < 1500){
            if(binding.txtColor1.text.toString()=="blanco"||binding.txtColor1.text.toString()=="Blanco"){
                binding.txtError.text = "El carro es publico"
            }
        }
    }

    fun valorDescuento(){
        var valorDescuento : Int = 0
        if(binding.txtModelo1.text.toString().toInt() >= 2019
            && binding.txtModelo1.text.toString().toInt() <= 2023) {
            if (binding.txtCilindraje1.text.toString().toInt() >= 1000
                && binding.txtCilindraje1.text.toString().toInt() <= 1200
            ) {

                binding.txtError.text = "El valor del carro tiene un descuento del 5%"
                valorDescuento = (binding.txtPuerta1.text.toString().toInt()*0.95).toInt()
                binding.txtPuerta1.setText(valorDescuento.toString())
            } else if (binding.txtCilindraje1.text.toString().toInt() >= 1201
                && binding.txtCilindraje1.text.toString().toInt() <= 1500
            ) {
                binding.txtError.text = "El valor del carro tiene un descuento del 10%"
                valorDescuento = ((binding.txtPuerta1.text.toString().toInt()*0.90).toInt())
                binding.txtPuerta1.setText(valorDescuento.toString())

            } else if (binding.txtCilindraje1.text.toString().toInt() >= 2000) {
                binding.txtError.text = "El valor del carro tiene un descuento del 15%"
                valorDescuento = (binding.txtPuerta1.text.toString().toInt()*0.85).toInt()
                binding.txtPuerta1.setText(valorDescuento.toString())
            }
        }
    }

    fun limpiar(){
        binding.txtName.text.clear()
        binding.txtMarca1.text.clear()
        binding.txtColor1.text.clear()
        binding.txtCilindraje1.text.clear()
        binding.txtModelo1.text.clear()
        binding.txtPuerta1.text.clear()
        binding.txtUrlImage.text.clear()
    }

}