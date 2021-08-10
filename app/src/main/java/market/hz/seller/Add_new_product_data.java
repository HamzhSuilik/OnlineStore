package market.hz.seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import market.hz.MainActivity;
import market.hz.R;
import market.hz.seller_connection.Add_new_group_connection;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Add_new_product_data extends AppCompatActivity {

    private EditText title1,title2,description,size,size1,size2,size3,size4,size5,price,old_price;

    private TextView color1,color2,color3,color4,color5;

    private ImageView imageView;
    private Button button,next_button;
    public Uri filePath;
    private File imageFile;
    private String image_file_text;

    private String title1_text,title2_text,description_text,size_text,size1_text,size2_text,size3_text,size4_text,size5_text,price_text,old_price_text;

    private int color_count;
    private String folder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product_data);

        color_count=1;
        color1=findViewById(R.id.color1_new_product);
        color2=findViewById(R.id.color2_new_product);
        color3=findViewById(R.id.color3_new_product);
        color4=findViewById(R.id.color4_new_product);
        color5=findViewById(R.id.color5_new_product);

        title1=findViewById(R.id.title1_new_product);
        title2=findViewById(R.id.title2_new_product);
        description=findViewById(R.id.description_new_product);
        size=findViewById(R.id.size_new_product);
        size1=findViewById(R.id.size1_new_product);
        size2=findViewById(R.id.size2_new_product);
        size3=findViewById(R.id.size3_new_product);
        size4=findViewById(R.id.size4_new_product);
        size5=findViewById(R.id.size5_new_product);
        price=findViewById(R.id.price_new_product);
        old_price=findViewById(R.id.old_price_new_product);

        imageView=findViewById(R.id.main_image);
        button=findViewById(R.id.select_image_bt);
        next_button=findViewById(R.id.next_button);

        folder=getIntent().getStringExtra("folder");

    }

    public void next_goo(View view) {

        title1_text=title1.getText().toString();
        title2_text=title2.getText().toString();
        description_text=description.getText().toString();
        size_text=size.getText().toString();
        size1_text=size1.getText().toString();
        size2_text=size2.getText().toString();
        size3_text=size3.getText().toString();
        size4_text=size4.getText().toString();
        size5_text=size5.getText().toString();
        price_text=price.getText().toString();
        old_price_text=old_price.getText().toString();


        Intent intent =new Intent(Add_new_product_data.this,Add_new_product_image.class);
        intent.putExtra("title1",title1_text);
        intent.putExtra("title2",title2_text);
        intent.putExtra("description",description_text);
        intent.putExtra("size",size_text);
        intent.putExtra("size1",size1_text);
        intent.putExtra("size2",size2_text);
        intent.putExtra("size3",size3_text);
        intent.putExtra("size4",size4_text);
        intent.putExtra("size5",size5_text);
        intent.putExtra("price",price_text);
        intent.putExtra("old_price",old_price_text);
        intent.putExtra("folder",folder);
        intent.putExtra("color_count",color_count);
        intent.putExtra("image_file",image_file_text);


        startActivityForResult(intent,2);

    }

    public void color_count_1(View view) {
        color_count=1;
        color1.setBackgroundResource(R.drawable.select_image_count);
        color2.setBackgroundResource(R.drawable.edit_bg);
        color3.setBackgroundResource(R.drawable.edit_bg);
        color4.setBackgroundResource(R.drawable.edit_bg);
        color5.setBackgroundResource(R.drawable.edit_bg);
    }

    public void color_count_2(View view) {
        color_count=2;
        color2.setBackgroundResource(R.drawable.select_image_count);

        color1.setBackgroundResource(R.drawable.edit_bg);
        color3.setBackgroundResource(R.drawable.edit_bg);
        color4.setBackgroundResource(R.drawable.edit_bg);
        color5.setBackgroundResource(R.drawable.edit_bg);
    }

    public void color_count_3(View view) {
        color_count=3;
        color3.setBackgroundResource(R.drawable.select_image_count);

        color2.setBackgroundResource(R.drawable.edit_bg);
        color1.setBackgroundResource(R.drawable.edit_bg);
        color4.setBackgroundResource(R.drawable.edit_bg);
        color5.setBackgroundResource(R.drawable.edit_bg);
    }

    public void color_count_4(View view) {
        color_count=4;
        color4.setBackgroundResource(R.drawable.select_image_count);

        color2.setBackgroundResource(R.drawable.edit_bg);
        color3.setBackgroundResource(R.drawable.edit_bg);
        color1.setBackgroundResource(R.drawable.edit_bg);
        color5.setBackgroundResource(R.drawable.edit_bg);
    }

    public void color_count_5(View view) {
        color_count=5;
        color5.setBackgroundResource(R.drawable.select_image_count);

        color2.setBackgroundResource(R.drawable.edit_bg);
        color3.setBackgroundResource(R.drawable.edit_bg);
        color4.setBackgroundResource(R.drawable.edit_bg);
        color1.setBackgroundResource(R.drawable.edit_bg);
    }

    public void select_goo(View view) {

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Add_new_product_data.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        }
        else {
            showFileChooser();
        }

    }


    private void showFileChooser(){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image_file_text=getImagePath(filePath,getContentResolver());
                imageFile = new File(getImagePath(filePath,getContentResolver()));
                imageView.setImageBitmap(bitmap);
                button.setText("تغيير الصورة");
                next_button.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "مجلد الصورة غير معروف", Toast.LENGTH_SHORT).show();
            }

        }

        if(requestCode==2){
            if(data.getIntExtra("success",0)==1){
                setResult(1,new Intent().putExtra("success",1));
                finish();
            }
        }


    }

    @Override
    public void onBackPressed() {
        setResult(1,new Intent().putExtra("success",0));
        super.onBackPressed();
    }

    public String getImagePath(Uri uri, ContentResolver contentResolver)
    {
        String[] projection={MediaStore.Images.Media.DATA};
        Cursor cursor=contentResolver.query(uri,projection,null,null,null);
        if(cursor == null){
            return null;
        }
        int columnIndex= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(columnIndex);
        cursor.close();
        return s;
    }

}