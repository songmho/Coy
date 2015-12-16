package com.asc.coy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by songmho on 15. 11. 25..
 */
public class My_edit_Activity extends AppCompatActivity {
    EditText name;
    EditText stu_num;
    EditText department;
    RadioGroup fieldgroup;
    RadioButton field1;
    RadioButton field2;
    Button yes;

    int CAMERA_REQUEST=1000;
    int SELECT_FILE=2000;

    ParseFile profile_parse;
    ImageView my_image;
    CharSequence[] item={"카메라","갤러리에서 사진 가져오기","삭제"};
    File file_up_path=new File("data/data/com.asc.coy/files/");
    ParseUser user=ParseUser.getCurrentUser();

    Bitmap thum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        name=(EditText)findViewById(R.id.name);
        stu_num=(EditText)findViewById(R.id.stu_num);
        department=(EditText)findViewById(R.id.department);
        fieldgroup=(RadioGroup)findViewById(R.id.fieldgroup);
        field1 =(RadioButton)findViewById(R.id.field1);
        field2 =(RadioButton)findViewById(R.id.field2);
        yes=(Button)findViewById(R.id.yes);
        my_image=(ImageView) findViewById(R.id.my_image);

        if(intent.getBooleanExtra("gender",true))
            field1.setChecked(true);
        else
            field2.setChecked(true);

        name.setText(intent.getStringExtra("name"));
        stu_num.setText(intent.getStringExtra("stu_num"));
        department.setText(intent.getStringExtra("department"));

        String tempPath="data/data/com.example.photori.photoribook/files/profile.jpg";

        Bitmap bm = BitmapFactory.decodeFile(tempPath);
        if(bm!=null){
            Glide.with(getApplicationContext()).load(bitmapTobyte(bm)).
                    bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(my_image);
        }
        else{
            //Glide.with(getApplicationContext()).load(R.drawable.ic_mypage_camera).
              //  bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(my_image);

        }


        my_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakingAlertDialog();
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stu_num.getText()!=null && department!=null && (field1.isChecked() || field2.isChecked())) {
                    if(thum!=null){
                        File file=new File("profile.jpg");
                        FileOutputStream fos= null;
                        try {
                            fos = openFileOutput("profile.jpg",0);
                            thum.compress(Bitmap.CompressFormat.JPEG,50,fos);
                            fos.flush();
                            fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    ParseUser user = ParseUser.getCurrentUser();
                    user.put("name", name.getText().toString());
                    user.put("department", department.getText().toString());
                    user.put("stu_num", stu_num.getText().toString());
                    if (fieldgroup.getCheckedRadioButtonId() == R.id.field1)
                        user.put("ismale", true);
                    else if (fieldgroup.getCheckedRadioButtonId() == R.id.field2)
                        user.put("ismale", false);
                    user.saveInBackground();
                    Toast.makeText(My_edit_Activity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                    if(getIntent().getAction()=="android.intent.action.SIGN_UP")
                        startActivity(new Intent(My_edit_Activity.this,MainActivity.class));
                    finish();
                }
                else
                    Toast.makeText(My_edit_Activity.this, "정보를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    
    }

    private void MakingAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(My_edit_Activity.this, R.style.dialog);
        builder.setTitle("프로필 사진 추가하기");
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (item[position].equals("카메라")) {
                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (camera.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(camera, CAMERA_REQUEST);
                } else if (item[position].equals("갤러리에서 사진 가져오기")) {
                    Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    gallery.addCategory(Intent.CATEGORY_OPENABLE);
                    gallery.setType("image/*");
                    startActivityForResult(Intent.createChooser(gallery, "갤러리 선택"), SELECT_FILE);
                } else if (item[position].equals("삭제")) {
                    File[] files = file_up_path.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        String fname = files[i].getName();
                        if (fname.equals("profile.jpg"))
                            files[i].delete();
                    }
                    ParseUser.getCurrentUser().remove("profile");
                    Toast.makeText(getApplicationContext(), "삭제하였습니다.", Toast.LENGTH_SHORT).show();
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mypage_camera);
                    my_image.setImageBitmap(b);
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        thum = null;
        if(resultCode==RESULT_OK && data!=null){
            if(requestCode==CAMERA_REQUEST){
                thum=(Bitmap)data.getExtras().get("data");
                //    profile.setImageBitmap(thum);

                Glide.with(getApplicationContext()).load(bitmapTobyte(thum)).
                        bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(my_image);

                imgSendParse(thum);
            }
            else if(requestCode==SELECT_FILE){
                Uri uri=data.getData();
                try {
                    thum = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    Glide.with(getApplicationContext()).load(bitmapTobyte(thum)).
                            bitmapTransform(new CropCircleTransformation(getApplicationContext())).into(my_image);
                    imgSendParse(thum);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    private void imgSendParse(Bitmap thum) {
        profile_parse=new ParseFile("profile.jpg",bitmapTobyte(thum));
        if(user.get("profile")!=null)
            user.remove("profile");
        user.put("profile", profile_parse);
        user.saveInBackground();
    }

    private byte[] bitmapTobyte(Bitmap bm) {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] bytes=stream.toByteArray();
        return bytes;
    }
}
