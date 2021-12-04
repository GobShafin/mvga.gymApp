package com.example.mvga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button sign_up,login;
    EditText editTextUsername, editTextPassword;
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

    //encry
    public String encrypt(String input,int sum){
        //String input=textPane.getText();
        int i;
        char[] in=input.toCharArray();
        char[] out=input.toCharArray();
        int Cc=4;
        int a=7,c=4,m=9,n=(sum/Cc)%10;

        for(i=0;i<in.length;i++){
            int ascii = (int) in[i]+n;
            out[i]=(char)ascii;
            n=(a*n+c)%m;
        }
        String output=new String(out);
        return output;

    }
    //encry


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_up= (Button) findViewById(R.id.sign_up);
        login=(Button) findViewById(R.id.login);
        editTextUsername=(EditText) findViewById(R.id.editTextUsername);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);

        db= new DBHelper(this);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=editTextUsername.getText().toString();
                String pass=editTextPassword.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the information", Toast.LENGTH_SHORT).show();
                else{

                    //pass decryption
                    String password= user;
                    String input= pass;
                    int sum=sum(password);
                    String decry_pass=encrypt(input,sum);
                    //pass decryption


                    Boolean checkuserpass = db.checkusernamepassword(user,decry_pass);
                    if(checkuserpass==true){

                        Toast.makeText(MainActivity.this, "Welcome back!", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(getApplicationContext(), Dashboard.class);
                        intent.putExtra("key_user",user);
                        startActivity(intent);




                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
    public void openActivity2(){
        Intent intent= new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

}