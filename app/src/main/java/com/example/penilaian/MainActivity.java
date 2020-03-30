package com.example.penilaian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,editTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editName = findViewById(R.id.editText_name);
        editSurname =findViewById(R.id.editText_surname);
        editMarks = findViewById(R.id.editText_marks);
        editTextId =findViewById(R.id.editTextId);
    }

    //fungsi menampilkan data
    public void viewAll(View view) {
        String data = "";

        Cursor res = this.myDb.getAllData();
        try{
            while(res.moveToNext()){
                data += "Nama : "+res.getString(1)+"\n"+
                        "Surname : "+res.getString(2)+"\n"+
                        "Marks : "+res.getString(3)+"\n"+
                        "Id : "+res.getString(0)+"\n\n";
            }
        }catch(Exception e){
            Log.d(TAG, "viewAll: "+e.toString());
        }finally{
            res.close();
        }

        this.showMessage("Data", data);
    }

    //fungsi menghapus data
    public void delete(View view) {
        this.myDb.deleteData(editTextId.getText().toString());
    }

    //fungsi menambah data
    public void add(View view) {
        String name = editName.getText().toString();
        String surname = editSurname.getText().toString();
        String marks = editMarks.getText().toString();
        this.myDb.insertData(name,surname,marks);
    }

    //fungsi mengupdate data
    public void edit(View view) {
        String id = editTextId.getText().toString();
        String name = editName.getText().toString();
        String surname = editSurname.getText().toString();
        String marks = editMarks.getText().toString();
        this.myDb.updateData(id,name,surname,marks);
    }

    //membuat alert dialog
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

