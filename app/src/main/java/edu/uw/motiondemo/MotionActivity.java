package edu.uw.motiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MotionActivity extends AppCompatActivity {

    private static final String TAG = "Motion";

    private TextView txtX, txtY, txtZ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);

        //views for easy access
        txtX = (TextView)findViewById(R.id.txt_x);
        txtY = (TextView)findViewById(R.id.txt_y);
        txtZ = (TextView)findViewById(R.id.txt_z);


    }

}
