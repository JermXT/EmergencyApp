package avgdc.emergencyapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Photo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }


    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void onClick(View v){
        dispatchTakePictureIntent();
    }

    //Camera
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ((ImageView)findViewById(R.id.photo)).setImageBitmap(imageBitmap);
        }
    }

    //Bring From Album
    public void Sorry(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sorry").setMessage("I didn't finsih\n -Jae Choi-").setNeutralButton("Close",null).show();
    }


    // Back Privious Activity
    public void onBack(View v){
        finish();
    }
}
