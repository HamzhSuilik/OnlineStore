package market.hz.seller_connection;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.ArrayList;

import market.hz.R;
import market.hz.connection.products_connection;
import market.hz.connection.sections_connection;
import market.hz.function.products_adapter;
import market.hz.function.products_data;

public class Drop_product_connection {

    private String DATA_UPLOAD_URL;
    private String row;
    private String folder;

    private ProgressBar uploadProgressBar;
    private Context c;
    private GridView gridView;
    private GridView gridView2;

    private TextView textView1,textView2;

    public Drop_product_connection(String folder,String row, Context c, ProgressBar uploadProgressBar,
                                   GridView gridView, GridView gridView2, TextView textView1, TextView textView2) {
        this.folder=folder;
        this.row=row;
        this.uploadProgressBar = uploadProgressBar;
        this.c = c;
        this.gridView = gridView;
        this.gridView2 = gridView2;
        this.textView1 = textView1;
        this.textView2 = textView2;
        DATA_UPLOAD_URL=c.getString(R.string.url)+"/app/api_seller.php";
    }

    public void drop()
    {
        uploadProgressBar.setVisibility(View.VISIBLE);

        AndroidNetworking.upload(DATA_UPLOAD_URL)
                .addMultipartParameter("drop_product","go")
                .addMultipartParameter("folder",folder)
                .addMultipartParameter("row",row)
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
                                Toast.makeText(c, "تم حذف منتج بنجاح", Toast.LENGTH_LONG).show();

                                if (responseString.equalsIgnoreCase("Success")) {

                                    // #######################
                                    // response

                                    sections_connection sections_connection1=new sections_connection(c, gridView, gridView2, textView1, textView2);
                                    sections_connection1.retrieve(gridView,uploadProgressBar);

                                    ArrayList<products_data> all_products = new ArrayList<>();
                                    products_adapter adapter =new products_adapter(c,all_products,folder,uploadProgressBar,gridView,gridView2,textView1,textView2);
                                    gridView2.setAdapter(adapter);

                                    products_connection products_connection1=new products_connection(c, folder,uploadProgressBar,gridView,gridView2,textView1,textView2);
                                    products_connection1.retrieve(gridView2,uploadProgressBar);


                                    // #####################

                                } else {
                                    Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e)
                            {
                                e.printStackTrace();
                                Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                        uploadProgressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(ANError error) {
                        error.printStackTrace();
                        uploadProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                    }
                });

    }



    public void setC(Context c) {
        this.c = c;
    }
}



