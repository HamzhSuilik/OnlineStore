package market.hz.seller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import market.hz.MainActivity;
import market.hz.R;
import market.hz.connection.products_connection;
import market.hz.function.products_adapter;
import market.hz.function.products_data;
import market.hz.seller_connection.Add_new_product_connection;
import market.hz.seller_connection.Drop_group_connection;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.audiofx.DynamicsProcessing;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import static com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker.*;

public class Add_new_product_image extends AppCompatActivity {

    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9,image10;
    private EditText editText;
    private Button select_bt,next_bt;
    private TextView title_text;
    private TextView get_id;
    private ProgressBar progressBar;

    private File main_image;
    private String title1_text,title2_text,description_text,size_text,size1_text,size2_text,size3_text,size4_text,size5_text,price_text,old_price_text;
    private int color_count;
    private String folder;

    private String color_text;
    private int current_page;
    private int group_num;
    private ArrayList<File> image_files;

    private Boolean exit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product_image);

        current_page=1;
        exit=false;

        title1_text=getIntent().getStringExtra("title1");
        title2_text=getIntent().getStringExtra("title2");
        description_text=getIntent().getStringExtra("description");
        size_text=getIntent().getStringExtra("size");
        size1_text=getIntent().getStringExtra("size1");
        size2_text=getIntent().getStringExtra("size2");
        size3_text=getIntent().getStringExtra("size3");
        size4_text=getIntent().getStringExtra("size4");
        size5_text=getIntent().getStringExtra("size5");
        price_text=getIntent().getStringExtra("price");
        old_price_text=getIntent().getStringExtra("old_price");
        folder=getIntent().getStringExtra("folder");
        color_count=getIntent().getIntExtra("color_count",1);

        String pathname=getIntent().getStringExtra("image_file");
        main_image=new File(pathname);


        title2_text=getIntent().getStringExtra("title2");
        title2_text=getIntent().getStringExtra("title2");
        title2_text=getIntent().getStringExtra("title2");
        // ************************************


        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);
        image4=findViewById(R.id.image4);
        image5=findViewById(R.id.image5);
        image6=findViewById(R.id.image6);
        image7=findViewById(R.id.image7);
        image8=findViewById(R.id.image8);
        image9=findViewById(R.id.image9);
        image10=findViewById(R.id.image10);

        title_text=findViewById(R.id.add_new_product_image_title);
        get_id=findViewById(R.id.get_id);
        editText=findViewById(R.id.title1_new_product);
        progressBar=findViewById(R.id.progressBar_add_new_product);

        select_bt=findViewById(R.id.select_image_bt);
        next_bt=findViewById(R.id.next_button);

        image_files=new ArrayList<>();


        current_page=1;
        group_num=getIntent().getIntExtra("color_count",1);

       }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (shouldHandleResult(requestCode, resultCode, data, 100)) {

                ArrayList<Image> image_data = getImages(data);
                ArrayList<Uri>all_filePath=new ArrayList<>();
                ArrayList<File> files=new ArrayList<>();
                ArrayList<Bitmap> image_bitmap=new ArrayList<>();

                try {

                    for (int i = 0; i < image_data.size(); i++) {
                        all_filePath.add(image_data.get(i).getUri());
                        files.add(new File(getImagePath(all_filePath.get(i), getContentResolver())));
                        image_bitmap.add(MediaStore.Images.Media.getBitmap(getContentResolver(),all_filePath.get(i) ));
                        set_image(image_bitmap);

                        if(current_page==group_num){
                            next_bt.setText("حفظ");
                        }

                        image_files=files;

                        next_bt.setVisibility(View.VISIBLE);
                        select_bt.setText("تغيير الصور");
                    }

                }catch (IOException e) {
                    e.printStackTrace();
                }



            }

            if(requestCode==99){

                Toast.makeText(this, "*******", Toast.LENGTH_SHORT).show();
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
    protected void onStart() {


        if(exit){
            finish();
        }
        super.onStart();

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

    public void showFileChooser(){
        with(this)
                .setFolderMode(true)
                .setFolderTitle("Album")
                .setDirectoryName("Image Picker")
                .setMultipleMode(true)
                .setShowNumberIndicator(true)
                .setMaxSize(10)
                .setLimitMessage("You can select up to 10 images")
                .setRequestCode(100)
                .start();
    }


    public void select_image_group(View view) {
        exit=false;

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Add_new_product_image.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        }
        else {
            showFileChooser();
        }

    }

    public void next_image_group(View view) {

        exit=true;

        color_text=editText.getText().toString();
        select_bt.setVisibility(View.GONE);
        next_bt.setVisibility(View.GONE);


        switch (title_text.getText().toString()){
            case "المجموعة الأولى" :
                current_page=1;
                break;

            case "المجموعة الثانية" :
                current_page=2;
                break;

            case "المجموعة الثالثة" :
                current_page=3;
                break;

            case "المجموعة الرابعة" :
                current_page=4;
                break;

            case "المجموعة الخامسة" :
                current_page=5;
                break;
        }


        if(current_page==1){
            save1();
        }else{
            save2();
        }



    }

    private void set_image(ArrayList<Bitmap> image_bitmap){

        int size=image_bitmap.size();

        image1.setVisibility(View.GONE);
        image2.setVisibility(View.GONE);
        image3.setVisibility(View.GONE);
        image4.setVisibility(View.GONE);
        image5.setVisibility(View.GONE);
        image6.setVisibility(View.GONE);
        image7.setVisibility(View.GONE);
        image8.setVisibility(View.GONE);
        image9.setVisibility(View.GONE);
        image10.setVisibility(View.GONE);

        if(size>=1){
            image1.setVisibility(View.VISIBLE);
            image1.setImageBitmap(image_bitmap.get(0));
        }

        if(size>=2){
            image2.setVisibility(View.VISIBLE);
            image2.setImageBitmap(image_bitmap.get(1));
        }

        if(size>=3){
            image3.setVisibility(View.VISIBLE);
            image3.setImageBitmap(image_bitmap.get(2));
        }

        if(size>=4){
            image4.setVisibility(View.VISIBLE);
            image4.setImageBitmap(image_bitmap.get(3));
        }

        if(size>=5){
            image5.setVisibility(View.VISIBLE);
            image5.setImageBitmap(image_bitmap.get(4));
        }

        if(size>=6){
            image6.setVisibility(View.VISIBLE);
            image6.setImageBitmap(image_bitmap.get(5));
        }

        if(size>=7){
            image7.setVisibility(View.VISIBLE);
            image7.setImageBitmap(image_bitmap.get(6));
        }

        if(size>=8){
            image8.setVisibility(View.VISIBLE);
            image8.setImageBitmap(image_bitmap.get(7));
        }

        if(size>=9){
            image9.setVisibility(View.VISIBLE);
            image9.setImageBitmap(image_bitmap.get(8));
        }

        if(size>=10){
            image10.setVisibility(View.VISIBLE);
            image10.setImageBitmap(image_bitmap.get(9));
        }

    }

    private void save1(){
        next_bt.setVisibility(View.GONE);

        Intent intent=new Intent();

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
        intent.putExtra("current_group",current_page);
        intent.putExtra("color_count",color_count);

        Add_new_product_connection connection =new Add_new_product_connection(this,progressBar,next_bt,select_bt,intent,main_image,image_files,color_text);
        connection.all_view(editText,title_text,get_id,image1,image2,image3,image4,image5,image6,image7,image8,image9,image10);



        switch (image_files.size()){

            case 1:
                connection.upload_1();
                break;

            case 2:
                connection.upload_2();
                break;

            case 3:
                connection.upload_3();
                break;

            case 4:
                connection.upload_4();
                break;

            case 5:
                connection.upload_5();
                break;

            case 6:
                connection.upload_6();
                break;

            case 7:
                connection.upload_7();
                break;

            case 8:
                connection.upload_8();
                break;

            case 9:
                connection.upload_9();
                break;

            case 10:
                connection.upload_10();
                break;

        }


    }

    private void save2(){

        next_bt.setVisibility(View.GONE);

        Add_new_product_connection connection =new Add_new_product_connection(this,progressBar,next_bt,select_bt,main_image,image_files,color_text,folder,current_page,color_count);
        connection.all_view(editText,title_text,get_id,image1,image2,image3,image4,image5,image6,image7,image8,image9,image10);


        switch (image_files.size()){

            case 1:
                connection.upload_image_1();
                break;
            case 2:
                connection.upload_image_2();
                break;
            case 3:
                connection.upload_image_3();
                break;
            case 4:
                connection.upload_image_4();
                break;
            case 5:
                connection.upload_image_5();
                break;
            case 6:
                connection.upload_image_6();
                break;
            case 7:
                connection.upload_image_7();
                break;
            case 8:
                connection.upload_image_8();
                break;
            case 9:
                connection.upload_image_9();
                break;
            case 10:
                connection.upload_image_10();
                break;
        }


    }

    @Override
    public void onBackPressed() {

        switch (title_text.getText().toString()){
            case "المجموعة الأولى" :
                current_page=1;
                break;

            case "المجموعة الثانية" :
                current_page=2;
                break;

            case "المجموعة الثالثة" :
                current_page=3;
                break;

            case "المجموعة الرابعة" :
                current_page=4;
                break;

            case "المجموعة الخامسة" :
                current_page=5;
                break;
        }


        AlertDialog.Builder sms = new AlertDialog.Builder(Add_new_product_image.this);

        if(current_page==1){

            sms.setMessage("لم يتم حفظ هذا المنتج !"+"\n"+"هل تريد الخروج ")
                    .setPositiveButton("تراجع", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("خروج", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setResult(1,new Intent().putExtra("success",0));
                            finish();
                        }
                    })
                    .show();


            // ********************


        }else{


            sms.setMessage("لم يتم حفظ كل المجموعات !"+"\n"+"هل تريد الخروج")
                    .setPositiveButton("تراجع", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("خروج", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            setResult(1,new Intent().putExtra("success",1));
                            finish();
                        }
                    })
                    .show();


            //super.onBackPressed();
        }
    }


}