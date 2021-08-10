package market.hz.connection;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import market.hz.R;
import market.hz.function.products_data;
import market.hz.function.products_adapter;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class products_connection {

    private String PHP_MYSQL_SITE_URL;
    private final Context c;
    private products_adapter adapter ;
    private String folder;

    private GridView gridView;
    private GridView gridView2;
    private ProgressBar progressBar;
    private TextView textView1,textView2;

    public products_connection(Context c, String folder,ProgressBar progressBar, GridView gridView, GridView gridView2,
                               TextView textView1, TextView textView2) {
        this.c = c;
        this.folder = folder;
        PHP_MYSQL_SITE_URL=c.getString(R.string.url)+"/app/api.php";

        this.gridView = gridView;
        this.gridView2 = gridView2;
        this.textView1 = textView1;
        this.textView2 = textView2;
        this.progressBar = progressBar;
    }
    /*
    RETRIEVE/SELECT/REFRESH
     */
    public void retrieve(final GridView gv, final ProgressBar myProgressBar)
    {
        final ArrayList<products_data> all_products = new ArrayList<>();

         myProgressBar.setIndeterminate(true);
         myProgressBar.setVisibility(View.VISIBLE);

        // AndroidNetworking

        AndroidNetworking.get(PHP_MYSQL_SITE_URL)
                .setPriority(Priority.MEDIUM)
                .addHeaders("task","products")
                .addHeaders("folder",folder)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;

                        products_data products_data1;

                        try
                        {
                            for(int i=0;i<response.length();i++)
                            {
                                jo=response.getJSONObject(i);

                                int id=jo.getInt("id");
                                String description=jo.getString("description");
                                String price=jo.getString("price");
                                String old_price=jo.getString("old_price");
                                String imageURL=jo.getString("image_url");

                                products_data1=new products_data(c.getString(R.string.url)+"/app/all_image/"+folder+"/"+id+"/"+imageURL,description,price,old_price, id+"");
                                all_products.add(products_data1);
                            }


                            //SET TO SPINNER


                            adapter =new products_adapter(c,all_products,folder,progressBar,gridView,gridView2,textView1,textView2);
                            gv.setAdapter(adapter);
                            myProgressBar.setVisibility(View.GONE);

                        }catch (JSONException e)
                        {
                            myProgressBar.setVisibility(View.GONE);
                            Toast.makeText(c, "تحقق من الإتصال بالإنترنت", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        myProgressBar.setVisibility(View.GONE);

                        if(anError.getMessage().length()==55){
                            Toast.makeText(c, "هذا القسم فارغ !", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(c, "تحقق من الإتصال بالإنترنت", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


}


