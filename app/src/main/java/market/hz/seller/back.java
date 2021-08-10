package market.hz.seller;

import androidx.appcompat.app.AppCompatActivity;

import market.hz.MainActivity;
import market.hz.R;

import android.content.Intent;
import android.os.Bundle;

public class back extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);


        Intent intent=new Intent();
        intent.putExtra("value1","**");
        setResult(99,intent);
        finish();


/*
        Thread g=new Thread(){
            @Override
            public void run() {

                try {
                    sleep(100);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        g.start();


 */


    }






}