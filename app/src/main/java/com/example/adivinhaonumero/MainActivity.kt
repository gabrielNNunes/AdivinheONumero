package com.example.adivinhaonumero

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.awt.font.NumericShaper
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var numeroParaAdivinhar=0
    var numeroDetentativas=0
    var acerto = false

    fun NovoJogo(view: View){
        entradaNumero.text.clear()
        listaNumeros.text = " "
        numeroDetentativas=0
        acerto = false
        tentativas.text = "0"
        falaMaquina.text = "Pensando!..."
        numeroParaAdivinhar = GerarNumeroAleatorio()
        falaMaquina.text = "Tente adivinhar o número!"
        bloquearDesbloquearBotao(true)


    }

    fun Verificar(view: View){
        numeroDetentativas++
        tentativas.text = numeroDetentativas.toString()

        if(entradaNumero.text.isNotEmpty()){
            var entradaNumero2 = entradaNumero.text.toString().toInt()
            entradaNumero.text.clear()
            if(entradaNumero2>0 && entradaNumero2<=100){
                if(numeroDetentativas ==1){
                    listaNumeros.text = entradaNumero2.toString()
                }else if(numeroDetentativas > 1 && numeroDetentativas < 11){
                    listaNumeros.text = entradaNumero2.toString() + ", " + listaNumeros.text
                }


                if(numeroDetentativas<10 && acerto == false){
                    if(entradaNumero2 == numeroParaAdivinhar){
                        falaMaquina.text = "Parabéns, vc ganhou! :)\nO número é: $numeroParaAdivinhar"
                        acerto = true
                        bloquearDesbloquearBotao(false)

                    }else{
                        falaMaquina.text = GerarDicas(entradaNumero2)
                    }
                }else{
                    falaMaquina.text = "Que pena, Você perdeu! :(\nO número era: $numeroParaAdivinhar"
                    bloquearDesbloquearBotao(false)
                }

            }else{
                falaMaquina.text = "Esse número não serve! Tente de novo :(\nO número deve ser entre 1 e 100"
                numeroDetentativas--
                tentativas.text = numeroDetentativas.toString()
                entradaNumero.text.clear()
            }
        }else{
            falaMaquina.text = "Digite algo!"
            numeroDetentativas--
            tentativas.text = numeroDetentativas.toString()
            entradaNumero.text.clear()
        }




    }

    private fun GerarNumeroAleatorio(): Int {
        val numeroAleatorio = Random.nextInt(1,100)
        return numeroAleatorio

    }
    private fun GerarDicas(entradaNumero: Int) :String {
        var dica = ""
        if (entradaNumero > numeroParaAdivinhar) {
            dica =  "O número que pensei é MENOR!"
        }else if (entradaNumero < numeroParaAdivinhar) {
            dica =  "O número que pensei é MAIOR!"
        }
        return dica
    }
    private fun bloquearDesbloquearBotao(boolean: Boolean){
        if(boolean == true){
            botaoVerificar.isEnabled = true
            botaoVerificar.setBackgroundColor(Color.rgb(45,97,31))
        }else if(boolean == false){
            botaoVerificar.isEnabled = false
            botaoVerificar.setBackgroundColor(Color.rgb(109,113,108))
        }
    }


}


