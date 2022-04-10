package com.example.registrationwaitinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, cwid, priority, major;
    Button insert, update, remove, view;

    DB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        cwid= findViewById(R.id.cwid);
        priority= findViewById(R.id.priority);
        major= findViewById(R.id.major);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        remove = findViewById(R.id.btnRemove);
        view = findViewById(R.id.btnView);
        DB = new DB(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String cwidTXT = cwid.getText().toString();
                String priorityTXT = priority.getText().toString();
                String majorTXT = major.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, cwidTXT, priorityTXT,majorTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Has Been Inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New entry Has Not Been Inserted", Toast.LENGTH_SHORT).show();

            }
         });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String cwidTXT = cwid.getText().toString();
                String priorityTXT = priority.getText().toString();
                String majorTXT = major.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, cwidTXT, priorityTXT,majorTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Existing Entry Has Been Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkremovedata = DB.removedata(nameTXT);
                if(checkremovedata==true)
                    Toast.makeText(MainActivity.this, "Existing Entry Has Been Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "There is No Existing Entry", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name:"+ res.getString(0)+ "\n");
                    buffer.append("cwid:"+ res.getString(1)+ "\n");
                    buffer.append("Priority :"+ res.getString(2)+ "\n\n");
                    buffer.append("Major:"+ res.getString(3)+ "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
    }
}