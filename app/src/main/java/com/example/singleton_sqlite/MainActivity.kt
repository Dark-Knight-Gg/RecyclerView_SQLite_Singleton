package com.example.singleton_sqlite

import android.app.Dialog
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var m1_txtTitle :TextView
    private  lateinit var  m1_btnAdd:Button
    private lateinit var m1_recyclerview:RecyclerView
    public var listFood = ArrayList<Food>()
    private lateinit var adapter: FoodAdapter
    public var instance= Database(this)

    public fun AnhXa(){
        m1_txtTitle= findViewById(R.id.m1_txtTitle)
        m1_btnAdd=findViewById(R.id.m1_btnAdd)
        m1_recyclerview=findViewById(R.id.m1_recyclerview)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AnhXa()
        adapter = FoodAdapter(this,listFood)
        //Tạo bảng Food
        instance.QueryData("CREATE TABLE IF NOT EXISTS Food(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name NVARCHAR(200), Describe NVARCHAR(500),Price NVARCHAR(50))")
        m1_recyclerview.layoutManager= LinearLayoutManager(this)
        m1_recyclerview.adapter=adapter
        m1_btnAdd.setOnClickListener(){
            var intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        GETDATA()
    }
    public fun GETDATA(){
        var cursor: Cursor = instance.GetData("SELECT * FROM Food")
        listFood.clear()
        while(cursor.moveToNext()){
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var describe = cursor.getString(2)
            var price = cursor.getString(3)
            listFood.add(Food(id,name,describe,price))
        }
        adapter.notifyDataSetChanged()
    }
    public fun DELETE(id :Int){
        var dialog :Dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.delete_dialog)
        ///ánh xạ
        var dialog_txtTitle : TextView = dialog.findViewById(R.id.dialog_txtTitle)
        var dialog_btnyes : Button =dialog.findViewById(R.id.dialog_btnYes)
        var dialog_btnNo :Button = dialog.findViewById(R.id.dialog_btnNo)
        ///Sử lsy sự kiện Yes-No
        dialog_btnNo.setOnClickListener(){
            dialog.dismiss()
        }
        dialog_btnyes.setOnClickListener(){
            instance.QueryData("DELETE FROM Food WHERE ID='"+id+"'")
            GETDATA()
            dialog.dismiss()

        }
        dialog.show()
    }
    public fun  EDIT(id : Int,name:String,describe:String,price:String){
        var dialog:Dialog= Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.edit_dialog)
        //ánh xạ
        var dialogEdit_txtTitle :TextView= dialog.findViewById(R.id.dialogEdit_txtTitle)
        var dialogEdit_edtName :EditText =dialog.findViewById(R.id.dialogEdit_edtName)
        var dialogEdit_edtDescribe :EditText=dialog.findViewById(R.id.dialogEdit_edtDescribe)
        var dialogEdit_edtPrice :EditText =dialog.findViewById(R.id.dialogEdit_edtPrice)
        var dialogEdit_btnOk :Button = dialog.findViewById(R.id.dialogEdit_btnOk)
        var dialogEdit_btnNo :Button = dialog.findViewById(R.id.dialogEdit_btnNo)
        //--------
        dialogEdit_edtName.setText(name)
        dialogEdit_edtDescribe.setText(describe)
        dialogEdit_edtPrice.setText(price)
        //Xử lý sự kiện Ok-No
        dialogEdit_btnNo.setOnClickListener(){
            dialog.dismiss()
        }
        dialogEdit_btnOk.setOnClickListener(){
            instance.QueryData("UPDATE Food SET Name='"+dialogEdit_edtName.text+"',Describe='"+dialogEdit_edtDescribe.text+"',Price='"+dialogEdit_edtPrice.text+"' WHERE Id='"+id+"'")
            GETDATA()
            dialog.dismiss()
        }
        dialog.show()
    }
}