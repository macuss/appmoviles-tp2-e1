package com.example.appmoviles_tp2_e1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.appmoviles_tp2_e1.UserApplication.Companion.pref
import java.util.concurrent.ThreadLocalRandom
import java.util.Random
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    // INICIO DE VARIABLES

    private lateinit var btnCalcular: Button
    private lateinit var btnSalir: Button
    private lateinit var tvRestantes: TextView
    private lateinit var tvRandom_num: TextView
    private lateinit var tvBest_score: TextView
    private lateinit var tvActual_score: TextView
    private lateinit var etNumIngresado: EditText


    var random_num: Int = 0
    var intentos_restantes: Int = 5
    var range: IntRange = 1..5
    var score: Int = 0
    var scoreMaximo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUIComponents()
        initListeners()
    }

    private fun initListeners() {

        btnCalcular.setOnClickListener {

            // control que sea <5
            if (intentos_restantes == 0) {
                compararPuntajes()
                score = 0
                tvActual_score.text = score.toString()
                actualizarAleatorio()


            } else {

                // comparar  random_num con EditText
                val num_ingresado = etNumIngresado.text.toString()
                if (num_ingresado == "$random_num") {
                    score += 10
                    tvActual_score.text = score.toString()
                    actualizarAleatorio()
                    Log.i(
                        "acertaste!",
                        "numero $random_num adivinado!!.. actualizando aleatorio .. "
                    )
                    compararPuntajes()
                } else {
                    //etNumIngresado.setText("")
                    intentos_restantes -= 1
                    tvRestantes.text = intentos_restantes.toString()
                    Log.i(
                        "atento!",
                        "no es ese!!.. le quedan: $intentos_restantes intentos restantes.. "
                    )
                }
            }

            // ADIVINAR NUMERO ALEATORIO **************
        }


        btnSalir.setOnClickListener {
            finish()
        }

    }

    private fun compararPuntajes() {
        if (score > scoreMaximo) {
            scoreMaximo = score
            tvBest_score.text = scoreMaximo.toString()
            actualizarVarSharedPref()
        }
    }

    private fun actualizarVarSharedPref() {
        pref.saveTopScore(scoreMaximo.toString())
    }


    private fun initUIComponents() {

        // hacer tareas de onCreate anotadas  **************
        random_num = randomInt()

        tvRandom_num = findViewById(R.id.RANDOM_NUM)
        tvRandom_num.text = random_num.toString()

        btnCalcular = findViewById(R.id.btnCalculate)
        btnSalir = findViewById(R.id.btnExit)
        etNumIngresado = findViewById(R.id.etNumIngresado)

        tvRestantes = findViewById(R.id.tvIntentos)
        tvRestantes.text = intentos_restantes.toString()

        tvBest_score = findViewById(R.id.tvBEST_SCORE)
        // cargar desde shared Preferences el dato con el mejor score

        if (pref.getTopScore().isNotEmpty()) {


            scoreMaximo = pref.getTopScore().toInt()
            Log.i("score maximo", "$scoreMaximo")
            tvBest_score.text = scoreMaximo.toString()
        }

        tvActual_score = findViewById(R.id.tvACTUAL_SCORE)


    }

    private fun randomInt(): Int {
        val nAleatorio = Random()
        return nAleatorio.nextInt(5) + 1
    }

    private fun actualizarAleatorio() {
        random_num = randomInt()
        tvRandom_num.text = random_num.toString()
        intentos_restantes = 5
        tvRestantes.text = intentos_restantes.toString()

    }

}

