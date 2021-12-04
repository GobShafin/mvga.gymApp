/*  calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month,
                                                    int dayOfMonth){

                        curDate = String.valueOf(dayOfMonth);
                        Year = String.valueOf(year);
                        Month = String.valueOf(month);

                        d_m_year(curDate,Year,Month);

                        //this.pDay=curDate;

*//*

                        Intent intent1= new Intent(getApplicationContext(), Schedule.class);
                        intent1.putE
                        xtra("curDate11",curDate);
                        intent1.putExtra("Year11",Year);
                        intent1.putExtra("Month11",Month);
                        startActivity(intent1);

*//*

                    }
                });*/




package com.example.mvga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class Schedule extends AppCompatActivity {





    Button s1,s2;
    CalendarView calendarView;
    String time,pDay="", pMonth="" , pYear="";
    EditText curDate,Year,Month;
    RadioGroup Radio_Group_schedule;
    RadioButton radioButton;

    DBHelper db;


   /* public void d_m_year(String d, String m, String year){
        pDay=d;
        pMonth=m;
        pYear=year;
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);








        s1=(Button) findViewById(R.id.button_schedule1hr);
        s2=(Button) findViewById(R.id.button_schedule2hr);
        Radio_Group_schedule= findViewById(R.id.Radio_Group_schedule);

        db= new DBHelper(this);



        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                int selectId=Radio_Group_schedule.getCheckedRadioButtonId();
                radioButton=findViewById(selectId);
                curDate=(EditText) findViewById(R.id.editTextDate);
                Month=(EditText) findViewById(R.id.editTextMonth);
                Year=(EditText) findViewById(R.id.editTextYear);

                 //TIME
                 time=radioButton.getText().toString();
                //username and trainer, about to pass to the database
                String uname=getIntent().getStringExtra("key_user");
                String trainer=getIntent().getStringExtra("key_trainer");






                String duration1="one";
                String uname1=uname.toString();
                String trainer1=trainer.toString();
                String time1=time.toString();
                String curDate1=curDate.getText().toString();
                String Month1=Month.getText().toString();
                String Year1=Year.getText().toString();



                int curdate=Integer.parseInt(curDate1);
                int month=Integer.parseInt(Month1);
                int year=Integer.parseInt(Year1);

                if(year<2021 || Year1.length()!=4 ){
                    Toast.makeText(Schedule.this, "Invalid year input!", Toast.LENGTH_SHORT).show();
                }
                else if(month<1 || month>12 ){
                    Toast.makeText(Schedule.this, "Invalid month!", Toast.LENGTH_SHORT).show();
                }
                else if(month==2){
                    if(curdate>28){
                        Toast.makeText(Schedule.this, "Please choose another date!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(curdate<1 || curdate>30 ){
                    Toast.makeText(Schedule.this, "Please choose another date!", Toast.LENGTH_SHORT).show();
                }


                else {

                    System.out.println(duration1);
                    System.out.println(uname1);
                    System.out.println(trainer1);
                    System.out.println(time1);
                    System.out.println(curDate1);
                    System.out.println(Month1);
                    System.out.println(Year1);


                    Boolean insert = db.insert_trainer_schedule(uname1, trainer1, curDate1, Month1, Year1, time1, duration1);

                    if (insert = true) {
                        Toast.makeText(Schedule.this, "Schedule successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Schedule.this, "Schedule failed...", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });




        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                int selectId=Radio_Group_schedule.getCheckedRadioButtonId();
                radioButton=findViewById(selectId);
                curDate=(EditText) findViewById(R.id.editTextDate);
                Month=(EditText) findViewById(R.id.editTextMonth);
                Year=(EditText) findViewById(R.id.editTextYear);

                //TIME
                time=radioButton.getText().toString();
                //username and trainer, about to pass to the database
                String uname=getIntent().getStringExtra("key_user");
                String trainer=getIntent().getStringExtra("key_trainer");






                String duration2="two";
                String uname2=uname.toString();
                String trainer2=trainer.toString();
                String time2=time.toString();
                String curDate2=curDate.getText().toString();
                String Month2=Month.getText().toString();
                String Year2=Year.getText().toString();

                int curdate_2=Integer.parseInt(curDate2);
                int month_2=Integer.parseInt(Month2);
                int year_2=Integer.parseInt(Year2);

                if(year_2<2021){
                    Toast.makeText(Schedule.this, "Invalid year input!", Toast.LENGTH_SHORT).show();
                }
                else if(month_2<1 || month_2>12){
                    Toast.makeText(Schedule.this, "Invalid month!", Toast.LENGTH_SHORT).show();
                }
                else if(month_2==2){
                    if(curdate_2>28){
                        Toast.makeText(Schedule.this, "Please choose another date!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(curdate_2<1 || curdate_2>30){
                    Toast.makeText(Schedule.this, "Please choose another date!", Toast.LENGTH_SHORT).show();
                }

                else {

                    System.out.println(duration2);
                    System.out.println(uname2);
                    System.out.println(trainer2);
                    System.out.println(time2);


                    Boolean insert = db.insert_trainer_schedule(uname2, trainer2, curDate2, Month2, Year2, time2, duration2);

                    if (insert = true) {
                        Toast.makeText(Schedule.this, "Schedule successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Schedule.this, "Schedule failed...", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });













    }




}