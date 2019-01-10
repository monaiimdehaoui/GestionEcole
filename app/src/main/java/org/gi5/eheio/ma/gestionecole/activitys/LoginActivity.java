package org.gi5.eheio.ma.gestionecole.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.gi5.eheio.ma.gestionecole.R;

import java.io.Console;

public class LoginActivity extends AppCompatActivity {

    private SQLiteDatabase mDataBase ;
    private Button mSbmitButton ;
    private EditText musername;
    private EditText mpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSbmitButton = findViewById(R.id.submit);
        musername = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);


        mDataBase  = openOrCreateDatabase("GestionEcole",MODE_PRIVATE,null);



        mSbmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String query = "Select * from etudiant ";
                Cursor resultSet = mDataBase.rawQuery(query,null);

                int count = resultSet.getCount();
                resultSet.close();
                Log.d("kkk","gggg");

                if(resultSet.getCount() == 1){
                    Log.d("kkk","gggg");
                    String name = resultSet.getString(4);
                    String felier = resultSet.getString(6);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("NAME", name);
                    intent.putExtra("FILIER", felier);
                    startActivity(intent);
                }

            }
        });

    }
}
