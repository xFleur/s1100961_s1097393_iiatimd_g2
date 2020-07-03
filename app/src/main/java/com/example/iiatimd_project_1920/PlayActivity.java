package com.example.iiatimd_project_1920;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PlayActivity extends AppCompatActivity {

    private String firstName = "Mark";

    private static final String TAG = "PlayActivity";

    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        TextView tv_name = findViewById(R.id.home_Welcome);
        tv_name.setText("Welkom, " + firstName);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            TextView tv = findViewById(R.id.home_Goal);
            tv.setText("To-do: " + bundle.get("leerdoel").toString());
        } else{
            TextView tv = findViewById(R.id.home_Goal);
            tv.setText("To-do: geen to-do");
        }

        Button buttonPlay = findViewById(R.id.bt_play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayActivity.this, QuizActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }


    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){

            new AlertDialog.Builder(this)
                    .setTitle("Do you want to Exit?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setResult(RESULT_OK, new Intent().putExtra("Exit", true));
                            finish();
                        }
                    }).create().show();
        }else{
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("DATATA", "onStop() in QuizActivity");
        finish();
    }


}
