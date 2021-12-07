package com.example.ca3_assignment

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgV = findViewById<ImageView>(R.id.imgV)
        val btnP = findViewById<Button>(R.id.btnP)
        val cls = resources.getStringArray(R.array.Classes)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val button = findViewById<Button>(R.id.submit)
        val pop = findViewById<Button>(R.id.popup)


        val getI = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imgV.setImageURI(it)
            })

        btnP.setOnClickListener {
            getI.launch("image/*")
        }

//Alert Dialog Box
        button.setOnClickListener()
        {

            val builder = AlertDialog.Builder(this) //alert dialog box
            builder.setTitle("Confirmation ")
                .setMessage("Are You Sure, You Want To Submit And Proceed To Next Page?")
                .setCancelable(true)
                .setIcon(android.R.drawable.ic_dialog_alert)

            builder.setPositiveButton("Yes") { dialogInterface, which ->
                Toast.makeText(applicationContext, "Next Page", Toast.LENGTH_LONG).show()
                val intent = Intent(this, SecondActivity::class.java) //explicit intent
                startActivity(intent)
            }
            builder.setNeutralButton("Cancel") { dialogInterface, which ->
                Toast.makeText(
                    applicationContext,
                    "clicked cancel\n operation cancel",
                    Toast.LENGTH_LONG
                ).show()
            }
            builder.setNegativeButton("No") { dialogInterface, which ->
                Toast.makeText(applicationContext, "clicked No", Toast.LENGTH_LONG).show()
            }

            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }

//Spinner
            if (spinner != null) {
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,cls)
                spinner.adapter = adapter

                spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                        Toast.makeText(this@MainActivity, getString(R.string.Selected_Class) + " " + "" + cls[position], Toast.LENGTH_SHORT).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {}
                }
            }
//Pop Up Menu
        pop.setOnClickListener{
            val popup = PopupMenu(this,pop)
            popup.menuInflater.inflate(R.menu.popup,popup.menu)

            popup.setOnMenuItemClickListener {
                Toast.makeText(applicationContext,"You Clicked : " + it.title,Toast.LENGTH_LONG).show()
                if (it.title=="Same Page") {
                    Toast.makeText(applicationContext,"Same Page",Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else if (it.title=="Search")
                {
                    val url = "http://www.google.com"
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                }
                else if(it.title=="Exit"){
                    System.exit(0)
                }
                true
            }
            popup.show()
        }
    }
}