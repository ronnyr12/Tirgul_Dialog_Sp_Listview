package com.example.tirgul_dialog_sp_listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDialog;
    TextView tv;
    EditText etName;
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        pref = getApplicationContext().getSharedPreferences("MyPref",
                0); // 0 - for private mode
        editor = pref.edit();

        tv = findViewById(R.id.tv);
        btnDialog = findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Build an AlertDialog
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Save user data");
                builder.setMessage("Are you sure you want to save?");
                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        saveUserData();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(),
                                "Saving canceled by user",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }
        });
    }

    private void saveUserData() {
        String name = etName.getText().toString();
        if(name.trim().length()>0){
            editor.putString("key_name", name); // Storing string
            editor.commit();
        }else
            Toast.makeText(MainActivity.this,
                    "enter name to continue",
                    Toast.LENGTH_SHORT).show();
    }
}