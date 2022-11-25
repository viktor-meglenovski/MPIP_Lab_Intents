package com.example.lab_intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.lab_intents.viewmodels.ResultViewModel

class ExplicitActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var btnOk:Button
    private lateinit var btnCancel:Button

    private lateinit var resultViewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        editText = findViewById(R.id.editText)
        btnOk = findViewById(R.id.btnOk)
        btnCancel = findViewById(R.id.btnCancel)

        resultViewModel = ViewModelProvider(this)[ResultViewModel::class.java]

        val bundle: Bundle? = intent.extras

        resultViewModel.getResult().observe(this){
            editText.setText(resultViewModel.getResultValue())
        }

        resultViewModel.setResultValue(bundle?.get("result").toString())

        btnOk.setOnClickListener{ _ ->
            Intent().let{intent ->
                intent.putExtra("result",editText.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }
        btnCancel.setOnClickListener { _ ->
            Intent().let{intent ->
                setResult(Activity.RESULT_CANCELED,intent)
                finish()
            }
        }
    }
}