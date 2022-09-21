package com.example.singleton_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    private lateinit var m2_txtTitle :TextView
    private lateinit var m2_edtName:EditText
    private lateinit var m2_edtDescribe:EditText
    private lateinit var m2_edtPrice:EditText
    private lateinit var m2_btnAdd:Button
    private lateinit var m2_btnCancle:Button
    public var instance = Database(this)
    private fun AnhXa(){
        m2_txtTitle=findViewById(R.id.m2_txtTitle)
        m2_edtName=findViewById(R.id.m2_edtName)
        m2_edtDescribe=findViewById(R.id.m2_edtDescribe)
        m2_edtPrice=findViewById(R.id.m2_edtPrice)
        m2_btnAdd=findViewById(R.id.m2_btnAdd)
        m2_btnCancle=findViewById(R.id.m2_btnCancle)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        AnhXa()
        m2_btnCancle.setOnClickListener(){
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        m2_btnAdd.setOnClickListener(){
            var name = m2_edtName.text.toString()
            var describe = m2_edtDescribe.text.toString()
            var price= m2_edtPrice.text.toString()
            instance.QueryData("INSERT INTO Food VALUES(null,'"+name+"','"+describe+"','"+price+"')")
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}