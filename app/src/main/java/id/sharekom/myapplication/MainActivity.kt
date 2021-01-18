package id.sharekom.myapplication

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.sharekom.myapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener, View.OnClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tts = TextToSpeech(this, this)
        binding.btnTts.setOnClickListener(this)
    }

    override fun onInit(status: Int) {
        if (status != TextToSpeech.ERROR) {
            tts.language = Locale.ENGLISH
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_tts -> {
                val toSpeak = binding.tiMessage.editText?.text.toString().trim()

                if (toSpeak == "") {
                    Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show()
                } else {
                    tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        }
    }

    override fun onPause() {
        if (tts.isSpeaking) {
            tts.stop()
        }
        super.onPause()
    }
}