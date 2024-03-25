package com.example.sfrfinalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class slashscreen<slashScreen> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       logolauncher logolauncher= new logolauncher();
       logolauncher.start();

        setContentView(R.layout.activity_slashscreen);

    }

private class  logolauncher extends Thread{
        public void run(){

            try {
                sleep(4000);

            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent intent=new Intent(slashscreen.this,MainActivity.class);
            startActivity(intent);
            slashscreen.this.finish();
        }



}

}
