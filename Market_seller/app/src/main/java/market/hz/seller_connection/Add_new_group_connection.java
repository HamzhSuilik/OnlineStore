package market.hz.seller_connection;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import java.io.IOException;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import org.json.JSONObject;
import java.io.File;

import market.hz.MainActivity;
import market.hz.R;
import market.hz.seller.Add_new_group;

import android.database.Cursor;





public class Add_new_group_connection {

    private Uri filePath;
    private ImageView imageView;
    private ContentResolver contentResolver;
    private Context c;
    private String DATA_UPLOAD_URL;
    private ProgressBar uploadProgressBar;
    public Intent intent;

    public Add_new_group_connection(Uri filePath, ImageView imageView, ContentResolver contentResolver, Context c, ProgressBar uploadProgressBar) {
        this.filePath = filePath;
        this.imageView = imageView;
        this.contentResolver = contentResolver;
        this.c = c;
        this.uploadProgressBar = uploadProgressBar;
        intent=new Intent();
        DATA_UPLOAD_URL=c.getString(R.string.url)+"/app/api_seller.php";
    }

    public Add_new_group_connection( ) {
        intent=new Intent();
    }

    public void set_image(){
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upload(String description, final EditText editText, final ImageView imageView, final Button bt_select, final Button bt_save)
    {


        // GET EMAGE ADDRESS
            File imageFile;
            try {
                imageFile = new File(getImagePath(filePath));

            }catch (Exception e){
                Toast.makeText(c, "Please pick an Image From Right Place, maybe Gallery or File Explorer so that we can get its path."+e.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }

            uploadProgressBar.setVisibility(View.VISIBLE);

            AndroidNetworking.upload(DATA_UPLOAD_URL)
                    .addMultipartFile("image",imageFile)
                    .addMultipartParameter("new_group","go")
                    .addMultipartParameter("description",description)
                    .setTag("MYSQL_UPLOAD")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if(response != null) {
                                try{
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get("message").toString();


                                    if (responseString.equalsIgnoreCase("Success")) {

                                        // #######################
                                        // response
                                        editText.setText("");
                                        imageView.setImageResource(R.color.invisible);
                                        bt_save.setVisibility(View.GONE);
                                        bt_select.setText("حدد صورة");

                                        intent.putExtra("check",1);

                                        Toast.makeText(c, "تمت عملية إنشاء قسم جديد بنجاح", Toast.LENGTH_LONG).show();

                                        ((Activity)c).setResult(99,intent);
                                        ((Activity)c).finish();
                                        // #####################

                                    } else {
                                        bt_save.setVisibility(View.VISIBLE);
                                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                    }
                                }catch(Exception e)
                                {
                                    bt_save.setVisibility(View.VISIBLE);
                                    e.printStackTrace();
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }else{
                                bt_save.setVisibility(View.VISIBLE);
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                            uploadProgressBar.setVisibility(View.GONE);
                        }
                        @Override
                        public void onError(ANError error) {
                            error.printStackTrace();
                            uploadProgressBar.setVisibility(View.GONE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            bt_save.setVisibility(View.VISIBLE);
                        }
                    });

    }


    public String getImagePath(Uri uri)
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
