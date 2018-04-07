package avgdc.emergencyapp;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

public class Login extends AppCompatActivity {

    public static String PhoneNumber;
    public static boolean loginState = false;
    private EditText numbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick (View v){
        if(loginState == false){
            numbd = (EditText) findViewById(R.id.PhoneNO);
            if(numbd.length() == 10){
                PhoneNumber =numbd.getText().toString();
                loginState = true;
                Intent intent = new Intent(this, Photo.class);
                startActivity(intent);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Not right Number").setMessage("Write your real Phone Number").setNeutralButton("Close",null).show();
            }
        }else{
            Intent intent = new Intent(this, PinMapsActivity.class);
            startActivity(intent);
        }
    }
}
