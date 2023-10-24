package com.qt.aplicaciondispositivosmoviles.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.qt.aplicaciondispositivosmoviles.databinding.CarCellBinding
import com.qt.aplicaciondispositivosmoviles.model.Car

class CarAdapter(val c: Context, val datos: ArrayList<Car> ) : BaseAdapter() {

    var context :Context
    var data : ArrayList<Car>

    init {
        context = c
        data = datos
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder : ViewHolder

        if (convertView == null){
            var itemBinding = CarCellBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
            holder = ViewHolder(itemBinding)
            holder.view = itemBinding.root
             holder.view.tag = holder

        }
        else {
                holder = convertView.tag as ViewHolder
        }
        val carro = data.get(position)
        holder.binding.txtNombre.text = carro.nombre
        holder.binding.txtMarca1.text = carro.marca.toString()
        holder.binding.txtColor.text = carro.color.toString()
        holder.binding.txtModelo1.text = carro.modelo.toString()
        holder.binding.txtPuerta1.text = carro.puerta.toString()
        holder.binding.txtCilindraje1.text = carro.cilindraje.toString()
        holder.binding.imgAvatar.load(carro.url){
            transformations(CircleCropTransformation())
        }
        return holder.view

    }

    class ViewHolder(itemBinding: CarCellBinding)  {
        var view :View
        var binding : CarCellBinding

        init {
            view = itemBinding.root
            binding = itemBinding
        }

    }

}