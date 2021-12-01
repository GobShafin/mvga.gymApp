package com.example.mvga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText username, Password, ConfirmPassword, EmailAddress, PersonName;
    Button Register;
    RadioGroup radio_group_Package;
    RadioButton radioButton;

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
        setContentView(R.layout.activity_main2);

        username=(EditText) findViewById(R.id.editTextUsername);
        Password=(EditText) findViewById(R.id.editTextPassword);
        ConfirmPassword= (EditText) findViewById(R.id.editTextConfirmPassword);
        EmailAddress=(EditText) findViewById(R.id.editTextEmailAddress);
        PersonName= (EditText) findViewById(R.id.editTextPersonName);
        radio_group_Package= findViewById(R.id.radio_group_Package);

        Register= (Button) findViewById(R.id.Register);

        db= new DBHelper(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_name= username.getText().toString();
                String pass= Password.getText().toString();
                String c_pass=ConfirmPassword.getText().toString();
                String mail=EmailAddress.getText().toString();
                String name =PersonName.getText().toString();

                int selectedId = radio_group_Package.getCheckedRadioButtonId();
                radioButton= findViewById(selectedId);
                String pack=radioButton.getText().toString();

                if(u_name.equals("") || pass.equals("") || c_pass.equals("") || mail.equals("") || name.equals("") || pack.equals(""))
                    Toast.makeText(MainActivity2.this, "Please enter all the information", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(c_pass)){
                        Boolean checkuser= db.checkusername(u_name);
                        if(checkuser==false){

                            //pass mail encryption
                            String password= u_name;
                            String input= pass;
                            int sum=sum(password);
                            String encry_pass=encrypt(input,sum);
                            String encry_mail=encrypt(mail,sum);
                            //pass mail encryption

                            Boolean insert = db.insertuserdata(u_name,encry_pass,encry_mail,name,pack);

                            if(insert=true){
                                Toast.makeText(MainActivity2.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity2.this, "Registration failed...", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity2.this, "Username already exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity2.this, "Password didn't match!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}