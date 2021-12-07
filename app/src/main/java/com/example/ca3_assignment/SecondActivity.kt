package com.example.ca3_assignment


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.util.zip.Inflater

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val listView=findViewById<ListView>(R.id.listView)
        val languages = arrayOf("C","C++","JAVA","PYTHON","KOTLIN","RUBY","JAVA SCRIPT")

        val arrayAdapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,languages)

        listView.adapter=arrayAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,"Selected Language Is : "+languages[position],Toast.LENGTH_LONG).show()}

        val btn = findViewById<Button>(R.id.search)
        val btn1=findViewById<Button>(R.id.name)

        btn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Proceeding to Google ")
                .setMessage("Do You Want To Search More Programming Languages")
                .setPositiveButton("Yes"){dialog,which ->
                    Toast.makeText(applicationContext,"Opening Google",Toast.LENGTH_SHORT).show()
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.setData(Uri.parse("https://www.google.com/")) //implicit intent
                    startActivity(intent)
                }
                .setNegativeButton("No"){dialog, which ->
                    Toast.makeText(applicationContext,"NO ",Toast.LENGTH_SHORT).show()
                }
                .setCancelable(false)
                .show()
        }
        btn1.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Enter Your Name Please")
            val input = EditText(this)
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
            input.layoutParams = lp
            builder.setView(input)
            builder.setPositiveButton("Done") { dialogInterface, i ->
                Toast.makeText(applicationContext,"Thank You "+ input.text.toString() + " & Have A Nice Day " , Toast.LENGTH_LONG).show() }
            builder.show()
        }
        registerForContextMenu(listView)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?,menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater=menuInflater
        inflater.inflate(R.menu.context_menu,menu)
        menu!!.setHeaderTitle("Select The Action")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            Toast.makeText(applicationContext,"Searching On Google",Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.google.com/"))
            startActivity(intent)
        }
        else if(item.itemId == R.id.youtube){
            Toast.makeText(applicationContext,"Searching On Youtube",Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://www.youtube.com/"))
            startActivity(intent)
        }
        else{
            return false
        }
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                Toast.makeText(applicationContext, "click on setting", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.previous ->{
                Toast.makeText(applicationContext, "Previous Activity", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.exit -> {
                System.exit(0)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}