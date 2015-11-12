package com.asc.coy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by songmho on 15. 11. 10..
 */
public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);
        Button login=(Button)findViewById(R.id.login);
        Button signup=(Button)findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(""+email.getText(), ""+password.getText(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if (parseUser!=null) {
                            Toast.makeText(Login_Activity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login_Activity.this,MainActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(Login_Activity.this, "아이디와 비밀번호를 다시 확인 하십시오.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this,Signup_Activity.class));
                finish();
            }
        });


    }
}
