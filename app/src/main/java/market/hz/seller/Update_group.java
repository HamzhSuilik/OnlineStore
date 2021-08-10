package market.hz.seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import market.hz.R;
import market.hz.seller_connection.Update_group_connection;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Update_group extends AppCompatActivity {

    ProgressBar progressBar;
    EditText editText;
    ImageView imageView;
    Button button;
    public Uri filePath;
    Update_group_connection connection;
    String id;
    private Boolean change_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_group);

        progressBar=findViewById(R.id.progressBar_update_group);
        editText=findViewById(R.id.editText_update_group);
        imageView=findViewById(R.id.imageView_update_group);
        button=findViewById(R.id.button2_update_group);

        editText.setText(getIntent().getStringExtra("text"));
        String image_url =getIntent().getStringExtra("image");
        id=getIntent().getStringExtra("id");
        change_image=false;

        connection=new Update_group_connection(id,imageView,Update_group.this,progressBar,button);


        if(image_url != null && image_url.length() > 0)
        {
            Picasso.get().load(image_url).placeholder(R.drawable.invesible_image).into(imageView);
        }else {
            Toast.makeText(this, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.invesible_image).into(imageView);
        }

    }

    public void select_image(View view) {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Update_group.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        }
        else {
            showFileChooser();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            connection=new Update_group_connection(id,filePath,imageView,getContentResolver(), Update_group.this, progressBar, button);
            change_image=true;
            connection.set_image();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showFileChooser();
            } else {
                Toast.makeText(this, "لا يمكن الوصول إلى الاستوديو - لم يتم منح الصلاحية", Toast.LENGTH_SHORT).show();
            }

        }
    }



    @Override
    public void onBackPressed() {
        setResult(1,new Intent().putExtra("success",0));
        super.onBackPressed();
    }


    public void showFileChooser(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    public void update(View view) {

        button.setVisibility(View.INVISIBLE);

        if(change_image){
            connection.update(editText.getText().toString());
        }else{
            connection=new Update_group_connection(id,imageView,Update_group.this,progressBar,button);
            connection.update2(editText.getText().toString());
        }


    }

}