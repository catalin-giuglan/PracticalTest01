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
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import androidx.activity.result.contract.ActivityResultContracts

class PracticalTest01MainActivity : AppCompatActivity() {

    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var input3: EditText
    private lateinit var input4: EditText


    private val messageBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null && intent.action == "Processing Thread"){
                val receivedInput1 = intent.getIntExtra("input1", 0)
                val receivedInput2 = intent.getIntExtra("input2", 0)
                val receivedInput3 = intent.getIntExtra("input3", 0)
                val receivedInput4 = intent.getIntExtra("input4", 0)

                input1.setText(receivedInput1.toString())
                input2.setText(receivedInput2.toString())
                input3.setText(receivedInput3.toString())
                input4.setText(receivedInput4.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test_01)

        val serviceIntent = Intent(this, Practical_Service::class.java)
        startService(serviceIntent)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        input3 = findViewById(R.id.input3)
        input4 = findViewById(R.id.input4)

        val activityResultsLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val message = result.data?.getIntExtra("result", 0)
                    if (message != null) {
                        Toast.makeText(this, "The result is $message", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        val navigateToSecondaryActivityButton = findViewById<Button>(R.id.set)
        navigateToSecondaryActivityButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01_Secondary::class.java)
            intent.putExtra("input1", Integer.valueOf(input1.text.toString()))
            intent.putExtra("input2", Integer.valueOf(input2.text.toString()))
            intent.putExtra("input3", Integer.valueOf(input3.text.toString()))
            intent.putExtra("input4", Integer.valueOf(input4.text.toString()))
            activityResultsLauncher.launch(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        registerReceiver(messageBroadcastReceiver, IntentFilter("Processing Thread"), Context.RECEIVER_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(messageBroadcastReceiver)
    }

    override fun onDestroy() {
        val intent = Intent(applicationContext, Practical_Service::class.java)
        applicationContext.stopService(intent)
        super.onDestroy()
    }
}
