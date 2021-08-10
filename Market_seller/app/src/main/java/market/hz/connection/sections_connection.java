package market.hz.connection;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import market.hz.R;
import market.hz.function.main_adapter_data;
import market.hz.function.main_adapter;




public class sections_connection {

    private String PHP_MYSQL_SITE_URL;
    private final Context c;
    private main_adapter adapter ;
    private GridView gridView;
    private GridView gridView2;

    private TextView textView1,textView2;

    private String selected_group;

    public sections_connection(Context c, GridView gridView, GridView gridView2, TextView textView1, TextView textView2) {
        this.c = c;
        this.gridView = gridView;
        this.gridView2 = gridView2;
        this.textView1 = textView1;
        this.textView2 = textView2;
        PHP_MYSQL_SITE_URL=c.getString(R.string.url)+"/app/api.php";
        selected_group="0";
    }

    public String getSelected_group() {
        selected_group=adapter.getSelected_group();
        return selected_group;
    }

    /*
        RETRIEVE/SELECT/REFRESH
         */
    public void retrieve(final GridView gv, final ProgressBar myProgressBar)
    {
        final ArrayList<main_adapter_data> all_offers = new ArrayList<>();

        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        // AndroidNetworking



        AndroidNetworking.get(PHP_MYSQL_SITE_URL)
                .setPriority(Priority.MEDIUM)
                .addHeaders("task","sections")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;


                        main_adapter_data main_adapter_data1;

                        try
                        {
                            for(int i=0;i<response.length();i++)
                            {
                                jo=response.getJSONObject(i);

                                int id=jo.getInt("id");
                                int rank=jo.getInt("rank");
                                String description=jo.getString("description");
                                String imageURL=jo.getString("image_url");

                                main_adapter_data1=new main_adapter_data(c.getString(R.string.url)+"/app/all_image/"+id+"/"+imageURL,description, id+"",rank+"");
                                all_offers.add(main_adapter_data1);


                            }

                            //SET TO SPINNER

                            adapter =new main_adapter(c,all_offers, myProgressBar, gridView, gridView2, textView1, textView2);
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
                            Toast.makeText(c, "مستعرض الأقسام فارغ !", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(c, "تحقق من الإتصال بالإنترنت", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


}


