package com.example.lab_intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.lab_intents.viewmodels.ResultViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var txtResult: TextView
    private lateinit var btnExplicit: Button
    private lateinit var btnImplicit: Button
    private lateinit var btnSend: Button
    private lateinit var btnImage: Button

    private lateinit var resultViewModel: ResultViewModel

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            resultViewModel.setResultValue(data?.getStringExtra("result")?:"N/A")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtResult=findViewById(R.id.txtResult)
        btnExplicit=findViewById(R.id.btnExplicit)
        btnImplicit=findViewById(R.id.btnImplicit)
        btnSend=findViewById(R.id.btnSend)
        btnImage=findViewById(R.id.btnImage)

        resultViewModel = ViewModelProvider(this)[ResultViewModel::class.java]

        resultViewModel.getResult().observe(this){
            txtResult.text=resultViewModel.getResultValue()
        }

        btnExplicit.setOnClickListener{
            Intent(this,ExplicitActivity::class.java).let { i->
                i.putExtra("result",resultViewModel.getResultValue())
                resultLauncher.launch(i)
            }
        }

        btnSend.setOnClickListener {
            Intent(Intent.ACTION_SEND).let{sendIntent->
                sendIntent.type="text/plain"
                sendIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("viktor.meglenovski@students.finki.ukim.mk"))
                sendIntent.putExtra(Intent.EXTRA_TITLE, "MPiP Send Title")
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
                startActivity(sendIntent)
            }
        }

        btnImplicit.setOnClickListener {
            Intent().apply{
                action="mk.ukim.finki.mpip.IMPLICIT_ACTION"
                type="text/plain"
            }.let{ intent ->
               startActivity(intent)
            }
        }

        btnImage.setOnClickListener{
            Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).let { i->
                startActivity(i)
            }
        }
    }
}