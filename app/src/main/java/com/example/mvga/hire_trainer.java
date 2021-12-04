package com.example.mvga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class hire_trainer extends AppCompatActivity {
    Button next;
    RadioGroup Radio_Group_trainer;
    RadioButton radioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_trainer);







        next=(Button) findViewById(R.id.Next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Radio_Group_trainer= findViewById(R.id.Radio_Group_trainer);
                int SelectId= Radio_Group_trainer.getCheckedRadioButtonId();
                radioButton=findViewById(SelectId);


                //Taking intent value
                String username=getIntent().getStringExtra("key_user");
                //passing username and trainer details to the schedule activity
                String Trainer=radioButton.getText().toString();
                Intent intent= new Intent(getApplicationContext(), Schedule.class);
                //Intent intent1=new Intent(getApplicationContext(),Schedule.class);



                intent.putExtra("key_user",username);
                intent.putExtra("key_trainer",Trainer);
                startActivity(intent);
                /*openActivity();*/


            }
        });


    }

    public void openActivity(){
        Intent intent= new Intent(this,Schedule.class);
        startActivity(intent);
    }
}