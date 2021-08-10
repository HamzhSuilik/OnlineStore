package market.hz.seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import market.hz.R;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import market.hz.seller_connection.Add_new_group_connection;

public class Add_new_group extends AppCompatActivity {

    public Uri filePath;
    public ImageView imageView;
    public ProgressBar progressBar;
    private Button button_select_image,button_save;
    Add_new_group_connection add_new_group_connection;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_group);

        progressBar=findViewById(R.id.progressBar);
        imageView=findViewById(R.id.imageView);
        button_select_image=findViewById(R.id.button);
        button_save=findViewById(R.id.button2);
        editText=findViewById(R.id.editText);

        add_new_group_connection =new Add_new_group_connection();

    }

    public void select(View view) {

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Add_new_group.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        }
        else {
            showFileChooser();
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


    public void showFileChooser(){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            add_new_group_connection =new Add_new_group_connection(filePath,imageView,getContentResolver(), Add_new_group.this, progressBar);
            add_new_group_connection.set_image();

            button_select_image.setText("تغيير الصورة");
            button_save.setVisibility(View.VISIBLE);

        }
    }

    public void save(View view) {

        button_save.setVisibility(View.GONE);
        add_new_group_connection.upload(editText.getText().toString(),editText,imageView,button_select_image,button_save);

    }



    @Override
    public void onBackPressed() {

        setResult(1,new Intent().putExtra("check",0));
        super.onBackPressed();

    }
}