package com.qt.aplicaciondispositivosmoviles.model

class Car (nombre : String,marca : String,color : String,cilindraje : Int, modelo : Int,puerta : Int, url: String){
    var nombre: String
    var marca: String
    var color : String
    var modelo : Int
    var puerta : Int
    var cilindraje : Int
    var url: String

  init {
    this.nombre = nombre
    this.marca = marca
    this.modelo = modelo
    this.color = color
    this.puerta = puerta
    this.cilindraje = cilindraje
    this.url = url
  }

}