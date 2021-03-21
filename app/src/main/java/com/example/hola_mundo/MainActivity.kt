package com.example.hola_mundo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var TextToSpeechVar:TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TextToSpeechVar = TextToSpeech(this, this)

        //On click del boton.
        findViewById<Button>(R.id.BtnPlay).setOnClickListener {
            var Mensaje:String = findViewById<TextView>(R.id.TxtTexto).text.toString()
            if(Mensaje.isEmpty()){
                TextToSpeechVar!!.speak("Para hablar, primero tienes que introducir un texto",TextToSpeech.QUEUE_FLUSH,null,"")
            }
            else{
                TextToSpeechVar!!.speak(Mensaje,TextToSpeech.QUEUE_FLUSH,null,"")
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            TextToSpeechVar!!.setLanguage(Locale("ES"))
        }
        else{
            findViewById<TextView>(R.id.TxtHola).text = "No disponible"
        }
    }

    override fun onDestroy() {
        if(TextToSpeechVar!=null){
            TextToSpeechVar!!.stop()
            TextToSpeechVar!!.shutdown()
        }
        super.onDestroy()
    }
}