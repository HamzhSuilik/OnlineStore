package market.hz.connection;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import market.hz.R;
import market.hz.function.products_data;
import market.hz.function.favorite_data;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class favorite_connection {

    private String PHP_MYSQL_SITE_URL;
    private String url_http;
    private final Context c;
    private String folder;
    private String row;

    private TextView text;
    private TextView price_text;
    private TextView old_price_text;
    private ImageView imageView;

    public favorite_connection(Context c, String folder, String row, TextView text, TextView price_text, TextView old_price_text, ImageView imageView) {
        this.c = c;
        this.folder = folder;
        this.row = row;
        this.text = text;
        this.price_text = price_text;
        this.old_price_text = old_price_text;
        this.imageView = imageView;

        url_http=c.getString(R.string.url);
        PHP_MYSQL_SITE_URL=c.getString(R.string.url)+"/app/api.php";
    }
    /*
    RETRIEVE/SELECT/REFRESH
     */
    public void retrieve()
    {
        //  final ProgressBar myProgressBar
        final ArrayList<favorite_data> all_products = new ArrayList<>();

        //myProgressBar.setIndeterminate(true);
        //myProgressBar.setVisibility(View.VISIBLE);

        // AndroidNetworking

        AndroidNetworking.get(PHP_MYSQL_SITE_URL)
                .setPriority(Priority.MEDIUM)
                .addHeaders("task","favorite")
                .addHeaders("folder",folder)
                .addHeaders("row",row)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;


                        try
                        {
                            for(int i=0;i<response.length();i++)
                            {
                                jo=response.getJSONObject(i);


                                String description=jo.getString("description");
                                String price=jo.getString("price");
                                String old_price=jo.getString("old_price");
                                String imageURL=jo.getString("image_url");

                                text.setText(description);
                                price_text.setText(price);
                                old_price_text.setText(old_price);


                                String path=url_http+"/app/all_image/"+folder+"/"+row+"/"+ imageURL;

                                if(path != null && path.length() > 0)
                                {
                                    Picasso.get().load(path).placeholder(R.drawable.invesible_image).into(imageView);
                                }else {
                                    Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
                                    Picasso.get().load(R.drawable.invesible_image).into(imageView);
                                }

                            }



                            //SET TO SPINNER



                            //myProgressBar.setVisibility(View.GONE);

                        }catch (JSONException e)
                        {
                            //myProgressBar.setVisibility(View.GONE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                        }
                    }
                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        //myProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                    }
                });
    }


}