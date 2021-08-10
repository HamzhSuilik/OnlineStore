package market.hz.connection;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;

import market.hz.R;
import market.hz.function.offer_data;
import market.hz.function.offer_adapter;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class offers_connection {

    private String PHP_MYSQL_SITE_URL;
    private final Context c;
    private offer_adapter adapter ;

    public offers_connection(Context c) {
        this.c = c;
        PHP_MYSQL_SITE_URL=c.getString(R.string.url)+"/app/api.php";
    }
    /*
    RETRIEVE/SELECT/REFRESH
     */
    public void retrieve(final GridView gv, final ProgressBar myProgressBar)
    {
        final ArrayList<offer_data> all_offers = new ArrayList<>();

        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        // AndroidNetworking



        AndroidNetworking.get(PHP_MYSQL_SITE_URL)
                .setPriority(Priority.MEDIUM)
                .addHeaders("task","offers")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;

                        offer_data offer_data1;

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

                                offer_data1=new offer_data(c.getString(R.string.url)+"/app/all_image/offers/"+id+"/"+imageURL,description,price,old_price, id+"");
                                all_offers.add(offer_data1);
                            }

                            //SET TO SPINNER
                            adapter =new offer_adapter(c,all_offers);
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
                            Toast.makeText(c, "قسم العروض فارغ !", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(c, "تحقق من الإتصال بالإنترنت", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


}
