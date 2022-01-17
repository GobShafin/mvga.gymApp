package com.example.mvga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    private Button ViewProfile,HireTrainers,ViewSchedule, CancelSchedule;


    DBHelper db;

    //sum
    public int sum(String password){
        int i,sum=0;
        //String password=passwordField.getText();
        char[] arr=password.toCharArray();

        for(i=0;i<arr.length;i++){
            sum=sum+arr[i];
        }
        sum=sum+arr[0]*arr[1]+arr[arr.length-1];

        return sum;
    }
    //sum

    //decry
    public String decrypt(String input,int sum){
        int i;
        char[] in=input.toCharArray();
        char[] out=input.toCharArray();
        int Cc=4;
        int a=7,c=4,m=9,n=(sum/Cc)%10;

        for(i=0;i<in.length;i++){
            int ascii = (int) in[i]-n;
            out[i]=(char)ascii;
            n=(a*n+c)%m;
        }
        String output=new String(out);
        return output;
    }
    //decry



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ViewProfile= (Button) findViewById(R.id.ViewProfile);
        HireTrainers= (Button) findViewById(R.id.HireTrainers);
        ViewSchedule= (Button) findViewById(R.id.ViewSchedule);
        CancelSchedule=(Button) findViewById(R.id.Cancel_schedule);

        db= new DBHelper(this);
        HireTrainers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=getIntent().getStringExtra("key_user");
                Cursor res= db.getAllData(username);

                while(res.moveToNext()){
                    String pack= res.getString(4);
                    if(pack.equals("Yearly") || pack.equals("Monthly")){

                        //passing username to the hire trainer activity
                        Intent intent= new Intent(getApplicationContext(), hire_trainer.class);
                        intent.putExtra("key_user",username);
                        startActivity(intent);

                        //open hire trainer acitvity
                        /*openActivity();*/
                    }
                    else{
                        Toast.makeText(Dashboard.this, "Pay per view package members can't book trainers!", Toast.LENGTH_SHORT).show();
                    }
                }




                /*openActivity();*/
            }
        });

        ViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=getIntent().getStringExtra("key_user");
                Cursor res= db.getAllData(username);

                int sum=sum(username);




                if(res.getCount()==0){
                    //show msg
                    return;
                }
                StringBuffer buffer=new StringBuffer();


                while(res.moveToNext()){
                    buffer.append("Username: "+ res.getString(0)+"\n" );

                    String mail=res.getString(2);
                    String decry_mail=decrypt(mail,sum);
                    buffer.append("Mail: "+decry_mail+"\n" );
                    buffer.append("Name: "+ res.getString(3)+"\n" );

                    buffer.append("Current package: "+ res.getString(4)+"\n" );
                    String pack=res.getString(4);

                    String cardnumber=res.getString(5);
                    String decry_card=decrypt(cardnumber,sum);
                    buffer.append("Card number: "+decry_card+"\n");
                }

                showMsg("Profile",buffer.toString());


            }
        });

        ViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=getIntent().getStringExtra("key_user");
                Cursor res= db.getSchedule(username);

                if(res.getCount()==0){
                    Toast.makeText(Dashboard.this, "You don't have any schedule at this moment!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();


                while(res.moveToNext()){
                    buffer.append("Username: "+ res.getString(0)+"\n\n" );
                    buffer.append("Trainer name: "+res.getString(1)+"\n" );
                    buffer.append("Date: "+ res.getString(2)+ "/" +res.getString(3) + "/" + res.getString(4) + "\n" );

                    buffer.append("Time: "+res.getString(5)+"\n" );
                    buffer.append("Duration: "+res.getString(6)+ "-hour" +"\n" );

                }

                showMsg("Schedule",buffer.toString());



            }
        });

        CancelSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=getIntent().getStringExtra("key_user");
                Boolean CancelSchedule=db.CancelSchedule(username);
                if(CancelSchedule==true){
                    Toast.makeText(Dashboard.this, "Schedule Canceled! You can make a new schedule.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Dashboard.this, "You don't have any schedule to cancel!", Toast.LENGTH_SHORT).show();
                }


            }
        });







    }




    public void showMsg(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void openActivity(){
        Intent intent1= new Intent(this,hire_trainer.class);
        startActivity(intent1);
    }





}