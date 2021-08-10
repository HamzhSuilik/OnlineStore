package market.hz.seller_connection;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import market.hz.MainActivity;
import market.hz.R;
import market.hz.seller.Update_group;

public class Update_group_connection {

    private Uri filePath;
    private ImageView imageView;
    private ContentResolver contentResolver;
    private Context c;
    private String DATA_UPLOAD_URL;
    private ProgressBar uploadProgressBar;
    public Intent intent;
    private Button button;
    private String id;



    public Update_group_connection(String id ,Uri filePath, ImageView imageView, ContentResolver contentResolver, Context c, ProgressBar uploadProgressBar, Button button) {
        this.filePath = filePath;
        this.imageView = imageView;
        this.contentResolver = contentResolver;
        this.c = c;
        this.uploadProgressBar = uploadProgressBar;
        this.button = button;
        this.id=id;
        DATA_UPLOAD_URL=c.getString(R.string.url)+"/app/api_seller.php";
    }


    public Update_group_connection(String id , ImageView imageView, Context c, ProgressBar uploadProgressBar, Button button) {

        this.imageView = imageView;
        this.c = c;
        this.uploadProgressBar = uploadProgressBar;
        this.button = button;
        this.id=id;
        DATA_UPLOAD_URL=c.getString(R.string.url)+"/app/api_seller.php";

    }


    public void update (String description)
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
                .addMultipartParameter("id",id)
                .addMultipartParameter("new_image","yes")
                .addMultipartParameter("update_group","go")
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
                                    button.setVisibility(View.VISIBLE);

                                    Toast.makeText(c, "تم تحديث البيانات بنجاح", Toast.LENGTH_LONG).show();

                                    ((Activity)c).setResult(1,new Intent().putExtra("success",1));
                                    ((Activity)c).finish();



                                    // #####################

                                } else {
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                    button.setVisibility(View.VISIBLE);
                                }
                            }catch(Exception e)
                            {
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                button.setVisibility(View.VISIBLE);
                            }
                        }else{
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            button.setVisibility(View.VISIBLE);
                        }
                        uploadProgressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        button.setVisibility(View.VISIBLE);
                    }
                });

    }


    public void update2 (String description)
    {


        uploadProgressBar.setVisibility(View.VISIBLE);

        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("id",id)
                .addMultipartParameter("new_image","no")
                .addMultipartParameter("update_group","go")
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
                                Toast.makeText(c, "تم تحديث البيانات بنجاح", Toast.LENGTH_LONG).show();

                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    button.setVisibility(View.VISIBLE);

                                    ((Activity)c).setResult(1,new Intent().putExtra("success",1));
                                    ((Activity)c).finish();


                                    // #####################

                                } else {
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                    button.setVisibility(View.VISIBLE);
                                }
                            }catch(Exception e)
                            {
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                button.setVisibility(View.VISIBLE);
                            }
                        }else{
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            button.setVisibility(View.VISIBLE);
                        }
                        uploadProgressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        button.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void set_image(){
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
