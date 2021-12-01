package com.example.mvga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
    private Button ViewProfile,HireTrainers;


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

        db= new DBHelper(this);
        ViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=getIntent().getStringExtra("key_user");


                Cursor res= db.getAllData(username);
                /*String mail=res.getString(2);*/

                int sum=sum(username);
                /*String decry_mail=decrypt(mail,sum);*/




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

                }

                showMsg("Profile",buffer.toString());


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
}