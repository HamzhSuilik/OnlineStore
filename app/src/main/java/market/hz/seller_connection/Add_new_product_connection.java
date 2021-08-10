package market.hz.seller_connection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import org.json.JSONObject;
import java.io.File;
import java.util.ArrayList;
import market.hz.R;


public class Add_new_product_connection {

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    private ImageView image7;
    private ImageView image8;
    private ImageView image9;
    private ImageView image10;
    private EditText editText;
    private TextView title_text;
    private TextView get_id;

    private String DATA_UPLOAD_URL;
    private ProgressBar uploadProgressBar;
    private GridView gridView;
    private Context c;
    private Button bt_save, bt_select;

    private String title1;
    private String title2;
    private String description;
    private String size;
    private String size1;
    private String size2;
    private String size3;
    private String size4;
    private String size5;
    private String price;
    private String old_price;
    private String folder;
    private int color_count;
    private int current_group;
    private File main_image;


    private String color_text;
    private String number_of_image;
    private ArrayList<File> image_files;





    public Add_new_product_connection(Context c,ProgressBar uploadProgressBar,Button bt_save,Button bt_select,Intent intent,
                                      File main_image, ArrayList<File> image_files,String color_text) {

        DATA_UPLOAD_URL=c.getString(R.string.url)+"/app/api_seller.php";
        this.uploadProgressBar=uploadProgressBar;
        this.bt_save=bt_save;
        this.bt_select=bt_select;
        this.c=c;

        title1 = intent.getStringExtra("title1");
        title2= intent.getStringExtra("title2");
        description= intent.getStringExtra("description");
        size= intent.getStringExtra("size");
        size1= intent.getStringExtra("size1");
        size2= intent.getStringExtra("size2");
        size3= intent.getStringExtra("size3");
        size4= intent.getStringExtra("size4");
        size5= intent.getStringExtra("size5");
        price= intent.getStringExtra("price");
        old_price= intent.getStringExtra("old_price");
        folder= intent.getStringExtra("folder");
        color_count= intent.getIntExtra("color_count",1);

        current_group=intent.getIntExtra("current_group",1);
        this.main_image=main_image;


        this.color_text=color_text;
        this.image_files=image_files;
        this.number_of_image=image_files.size()+"";

    }


    public Add_new_product_connection(Context c,ProgressBar uploadProgressBar,Button bt_save,Button bt_select,
                                      File main_image, ArrayList<File> image_files,String color_text,String folder,int current_group,int color_count) {

        DATA_UPLOAD_URL=c.getString(R.string.url)+"/app/api_seller.php";
        this.uploadProgressBar=uploadProgressBar;
        this.bt_save=bt_save;
        this.bt_select=bt_select;
        this.c=c;


        this.folder= folder;
        this.current_group=current_group;
        this.main_image=main_image;


        this.color_text=color_text;
        this.image_files=image_files;
        this.number_of_image=image_files.size()+"";
        this.color_count=color_count;

    }


    public void all_view(EditText editText, TextView title_text,TextView get_id,ImageView image1,ImageView image2,ImageView image3,ImageView image4
            ,ImageView image5,ImageView image6,ImageView image7,ImageView image8,ImageView image9,ImageView image10){

        this.editText=editText;
        this.title_text=title_text;
        this.image1=image1;
        this.image2=image2;
        this.image3=image3;
        this.image4=image4;
        this.image5=image5;
        this.image6=image6;
        this.image7=image7;
        this.image8=image8;
        this.image9=image9;
        this.image10=image10;
        this.get_id=get_id;
    }






    public void upload_1()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)


                .addMultipartFile("image_1",image_files.get(0))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();

                                if (responseString.equalsIgnoreCase("Success")) {


                                    //
                                    // #######################
                                    // response
                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);

                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }

    public void upload_2()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response
                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();

                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }

    public void upload_3()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();

                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }


    public void upload_4()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }


    public void upload_5()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);

                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();


                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }


    public void upload_6()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);

                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();


                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }


    public void upload_7()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))
                .addMultipartFile("image_7",image_files.get(6))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);

                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();

                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }


    public void upload_8()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))
                .addMultipartFile("image_7",image_files.get(6))
                .addMultipartFile("image_8",image_files.get(7))


                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);

                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();

                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }


    public void upload_9()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))
                .addMultipartFile("image_7",image_files.get(6))
                .addMultipartFile("image_8",image_files.get(7))
                .addMultipartFile("image_9",image_files.get(8))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);

                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();

                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }


    public void upload_10()
    {


        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("new_product","go")
                .addMultipartFile("main_image",main_image)

                .addMultipartParameter("title1",title1)
                .addMultipartParameter("title2",title2)
                .addMultipartParameter("description",description)
                .addMultipartParameter("size",size)
                .addMultipartParameter("size1",size1)
                .addMultipartParameter("size2",size2)
                .addMultipartParameter("size3",size3)
                .addMultipartParameter("size4",size4)
                .addMultipartParameter("size5",size5)
                .addMultipartParameter("price",price)
                .addMultipartParameter("old_price",old_price)

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))
                .addMultipartFile("image_7",image_files.get(6))
                .addMultipartFile("image_8",image_files.get(7))
                .addMultipartFile("image_9",image_files.get(8))
                .addMultipartFile("image_10",image_files.get(9))


                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    String response_id = response.get("id").toString();
                                    get_id.setText(response_id);

                                    if(color_count==1){
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }else {
                                        Toast.makeText(c, "تم حفظ المجموعة 1", Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }

                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);

                        Toast.makeText(c, "لا يمكن حفظ هذه الرموز ! قم بحذف الرموز الخاصة من النصوص", Toast.LENGTH_LONG).show();
                        ((Activity)c).setResult(1,new Intent().putExtra("success",0));
                        ((Activity)c).finish();

                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }





    // *****************************************************




    public void upload_image_1()  {



            uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())


                .addMultipartFile("image_1",image_files.get(0))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void upload_image_2()  {



        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void upload_image_3()  {



        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void upload_image_4()  {



        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void upload_image_5()  {



        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void upload_image_6()  {



        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void upload_image_7()  {



        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))
                .addMultipartFile("image_7",image_files.get(6))


                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void upload_image_8()  {



        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))
                .addMultipartFile("image_7",image_files.get(6))
                .addMultipartFile("image_8",image_files.get(7))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void upload_image_9()  {



        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))
                .addMultipartFile("image_7",image_files.get(6))
                .addMultipartFile("image_8",image_files.get(7))
                .addMultipartFile("image_9",image_files.get(8))



                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    public void upload_image_10()  {



        uploadProgressBar.setVisibility(View.VISIBLE);


        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("upload_product_images","go")

                .addMultipartParameter("folder",folder)
                .addMultipartParameter("color_count",current_group+"")
                .addMultipartParameter("color_text",color_text)
                .addMultipartParameter("number_of_image",number_of_image)
                .addMultipartParameter("id",get_id.getText().toString())

                .addMultipartFile("image_1",image_files.get(0))
                .addMultipartFile("image_2",image_files.get(1))
                .addMultipartFile("image_3",image_files.get(2))
                .addMultipartFile("image_4",image_files.get(3))
                .addMultipartFile("image_5",image_files.get(4))
                .addMultipartFile("image_6",image_files.get(5))
                .addMultipartFile("image_7",image_files.get(6))
                .addMultipartFile("image_8",image_files.get(7))
                .addMultipartFile("image_9",image_files.get(8))
                .addMultipartFile("image_10",image_files.get(9))


                .setTag("MYSQL_UPLOAD")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null) {
                            uploadProgressBar.setVisibility(View.GONE);
                            try{
                                //SHOW RESPONSE FROM SERVER
                                String responseString = response.get("message").toString();


                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response


                                    if(current_group<color_count){
                                        Toast.makeText(c, "تم حفظ المجموعة "+current_group, Toast.LENGTH_LONG).show();
                                        current_group++;
                                        clear_data();
                                        new_title();
                                    }else{
                                        Toast.makeText(c, "تم حفظ المنتج الجديد", Toast.LENGTH_LONG).show();
                                        Finish();
                                    }


                                    // #####################

                                } else {
                                    bt_save.setVisibility(View.VISIBLE);
                                    bt_select.setVisibility(View.VISIBLE);
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                bt_save.setVisibility(View.VISIBLE);
                                bt_select.setVisibility(View.VISIBLE);
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            bt_save.setVisibility(View.VISIBLE);
                            bt_select.setVisibility(View.VISIBLE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        bt_save.setVisibility(View.VISIBLE);
                        bt_select.setVisibility(View.VISIBLE);
                    }
                });

    }



    private void Finish(){

        ((Activity)c).setResult(1,new Intent().putExtra("success",1));
        ((Activity)c).finish();


    }

    private void clear_data(){

        image1.setImageResource(R.drawable.edit_bg);
        image2.setImageResource(R.drawable.edit_bg);
        image3.setImageResource(R.drawable.edit_bg);
        image4.setImageResource(R.drawable.edit_bg);
        image5.setImageResource(R.drawable.edit_bg);
        image6.setImageResource(R.drawable.edit_bg);
        image7.setImageResource(R.drawable.edit_bg);
        image8.setImageResource(R.drawable.edit_bg);
        image9.setImageResource(R.drawable.edit_bg);
        image10.setImageResource(R.drawable.edit_bg);

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

        editText.setText("");
        bt_select.setText("اختر صور المنتج (1-10)");
        bt_select.setVisibility(View.VISIBLE);
    }

    private void new_title(){
        switch (current_group){
            case 1:
                title_text.setText("المجموعة الأولى");
                break;
            case 2:
                title_text.setText("المجموعة الثانية");
                break;

            case 3:
                title_text.setText("المجموعة الثالثة");
                break;

            case 4:
                title_text.setText("المجموعة الرابعة");
                break;

            case 5:
                title_text.setText("المجموعة الخامسة");
                break;

        }
    }



}
