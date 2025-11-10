package com.example.practicaltest01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import android.content.Intent
import android.app.Activity
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class PracticalTest01_Secondary : AppCompatActivity() {

    private lateinit var input1: TextView
    private lateinit var input2: TextView
    private lateinit var input3: TextView
    private lateinit var input4: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val in1 = intent.getIntExtra("input1", 0)
        val in2 = intent.getIntExtra("input2", 0)
        val in3 = intent.getIntExtra("input3", 0)
        val in4 = intent.getIntExtra("input4", 0)


        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        input3 = findViewById(R.id.input3)
        input4 = findViewById(R.id.input4)

        input1.text = in1.toString()
        input2.text = in2.toString()
        input3.text = in3.toString()
        input4.text = in4.toString()

        val sum = findViewById<Button>(R.id.sum)
        sum.setOnClickListener {
            val result = in1 + in2 + in3 + in4
            val intent = Intent()
            intent.putExtra("result", result)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        val prod = findViewById<Button>(R.id.prod)
        prod.setOnClickListener {
            val result = in1 * in2 * in3 * in4
            val intent = Intent()
            intent.putExtra("result", result)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}