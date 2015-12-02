package com.asc.coy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by songmho on 15. 11. 12..
 */
public class Signup_Activity extends AppCompatActivity {


    Toolbar toolbar;
    EditText user_name;
    EditText mail;
    EditText pass;
    EditText pass_check;
    Button signup_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("회원가입");

        user_name = (EditText) findViewById(R.id.user_name);
        mail = (EditText) findViewById(R.id.mail);
        pass = (EditText) findViewById(R.id.pass);
        pass_check = (EditText) findViewById(R.id.pass_check);


        signup_btn = (Button) findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user=new ParseUser();
                user.setUsername(""+mail.getText());
                user.setPassword("" + pass.getText());
                user.setEmail("" + mail.getText());
                user.put("name", "" + user_name.getText());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup_Activity.this, My_edit_Activity.class);

                            intent.putExtra("name", user_name.getText().toString());
                            intent.putExtra("gender", "");
                            intent.putExtra("department", "");
                            intent.putExtra("stu_num", "");
                            startActivity(intent);
                            finish();
                        } else {
//                                    Log.d("error", String.valueOf(e.getCode()));
                            if (e.getCode() == 202) {
                                Toast.makeText(getApplicationContext(), "이미 존재하는 mail 주소입니다.", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "문제가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                            }//end else

                        }
                    }
                });
            }
        });

    }
}
