package market.hz.connection;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

import market.hz.R;
import market.hz.function.products_adapter;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





public class details_connection {

    private String url_http;
    private String PHP_MYSQL_SITE_URL;
    private final Context c;
    private products_adapter adapter ;
    private String folder;
    private String row;
    private int num_color;


    // image color
    ImageView image_color_1;
    ImageView image_color_2;
    ImageView image_color_3;
    ImageView image_color_4;
    ImageView image_color_5;

    TextView title;
    TextView description;
    TextView price;
    TextView old_price;
    TextView size;
    TextView size1;
    TextView size2;
    TextView size3;
    TextView size4;
    TextView size5;

    LinearLayout color_selector;
    LinearLayout all_size;
    TextView color_text_1;
    TextView color_text_2;

    ImageSlider imageSlider;
    ViewPager viewPager;

    List<SlideModel> slideModels1;
    List<SlideModel> slideModels2;
    List<SlideModel> slideModels3;
    List<SlideModel> slideModels4;
    List<SlideModel> slideModels5;

    private int num_image1;
    private int num_image2;
    private int num_image3;
    private int num_image4;
    private int num_image5;

    private String color1;
    private String color2;
    private String color3;
    private String color4;
    private String color5;



    public details_connection(Context c, String folder, String row, final ArrayList<TextView> arrayList, final ArrayList<ImageView> arrayList_image, final ArrayList<LinearLayout> arrayList_layout
    , ImageSlider imageSlider, ViewPager viewPager) {
        this.c = c;
        this.folder = folder;
        this.row = row;

        this.title=arrayList.get(0);
        this.description=arrayList.get(1);
        this.price=arrayList.get(2);
        this.old_price=arrayList.get(3);
        this.size=arrayList.get(4);
        this.size1=arrayList.get(5);
        this.size2=arrayList.get(6);
        this.size3=arrayList.get(7);
        this.size4=arrayList.get(8);
        this.size5=arrayList.get(9);
        this.color_text_1=arrayList.get(10);
        this.color_text_2=arrayList.get(11);

        this.image_color_1=arrayList_image.get(0);
        this.image_color_2=arrayList_image.get(1);
        this.image_color_3=arrayList_image.get(2);
        this.image_color_4=arrayList_image.get(3);
        this.image_color_5=arrayList_image.get(4);

        this.color_selector=arrayList_layout.get(0);
        this.all_size=arrayList_layout.get(1);

        this.viewPager=viewPager;
        this.imageSlider=imageSlider;


        url_http=c.getString(R.string.url);
        PHP_MYSQL_SITE_URL=c.getString(R.string.url)+"/app/api.php";
    }
    /*
    RETRIEVE/SELECT/REFRESH
     */
    public void retrieve(final ProgressBar myProgressBar)
    {

        num_color=0;
        myProgressBar.setIndeterminate(true);
        myProgressBar.setVisibility(View.VISIBLE);

        // AndroidNetworking

        AndroidNetworking.get(PHP_MYSQL_SITE_URL)
                .setPriority(Priority.MEDIUM)
                .addHeaders("task","details")
                .addHeaders("folder",folder)
                .addHeaders("row",row)

                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;


                        try
                        {

                                jo=response.getJSONObject(0);

                            title.setText(jo.getString("title"));

                            description.setText(jo.getString("description"));
                            price.setText(jo.getString("price"));
                            old_price.setText(jo.getString("old_price"));
                            size.setText(jo.getString("size_text"));
                            size1.setText(jo.getString("size_1"));
                            size2.setText(jo.getString("size_2"));
                            size3.setText(jo.getString("size_3"));
                            size4.setText(jo.getString("size_4"));
                            size5.setText(jo.getString("size_5"));
                            color1=jo.getString("color");
                            color_text_2.setText(jo.getString("color"));

                            num_color=jo.getInt("colors");
                            hide_image_selector(num_color);
                            hide_size_selector();
                            hide_empty_view();


                            ArrayList<String>image=new ArrayList<>();
                            image.add(jo.getString("image_url_1"));
                            image.add(jo.getString("image_url_2"));
                            image.add(jo.getString("image_url_3"));
                            image.add(jo.getString("image_url_4"));
                            image.add(jo.getString("image_url_5"));
                            image.add(jo.getString("image_url_6"));
                            image.add(jo.getString("image_url_7"));
                            image.add(jo.getString("image_url_8"));
                            image.add(jo.getString("image_url_9"));
                            image.add(jo.getString("image_url_10"));

                            image_downloader_1(image);

                            if(num_color>1){
                                ArrayList<String>image2=new ArrayList<>();
                                image2.add(jo.getString("image_2_url_1"));
                                image2.add(jo.getString("image_2_url_2"));
                                image2.add(jo.getString("image_2_url_3"));
                                image2.add(jo.getString("image_2_url_4"));
                                image2.add(jo.getString("image_2_url_5"));
                                image2.add(jo.getString("image_2_url_6"));
                                image2.add(jo.getString("image_2_url_7"));
                                image2.add(jo.getString("image_2_url_8"));
                                image2.add(jo.getString("image_2_url_9"));
                                image2.add(jo.getString("image_2_url_10"));

                                color2=jo.getString("color2");
                                image_downloader_2(image2);
                            }

                            if(num_color>2){
                                ArrayList<String>image3=new ArrayList<>();
                                image3.add(jo.getString("image_3_url_1"));
                                image3.add(jo.getString("image_3_url_2"));
                                image3.add(jo.getString("image_3_url_3"));
                                image3.add(jo.getString("image_3_url_4"));
                                image3.add(jo.getString("image_3_url_5"));
                                image3.add(jo.getString("image_3_url_6"));
                                image3.add(jo.getString("image_3_url_7"));
                                image3.add(jo.getString("image_3_url_8"));
                                image3.add(jo.getString("image_3_url_9"));
                                image3.add(jo.getString("image_3_url_10"));

                                color3=jo.getString("color3");
                                image_downloader_3(image3);
                            }

                            if(num_color>3){
                                ArrayList<String>image4=new ArrayList<>();
                                image4.add(jo.getString("image_4_url_1"));
                                image4.add(jo.getString("image_4_url_2"));
                                image4.add(jo.getString("image_4_url_3"));
                                image4.add(jo.getString("image_4_url_4"));
                                image4.add(jo.getString("image_4_url_5"));
                                image4.add(jo.getString("image_4_url_6"));
                                image4.add(jo.getString("image_4_url_7"));
                                image4.add(jo.getString("image_4_url_8"));
                                image4.add(jo.getString("image_4_url_9"));
                                image4.add(jo.getString("image_4_url_10"));

                                color4=jo.getString("color4");
                                image_downloader_4(image4);
                            }

                            if(num_color>4){
                                ArrayList<String>image5=new ArrayList<>();
                                image5.add(jo.getString("image_5_url_1"));
                                image5.add(jo.getString("image_5_url_2"));
                                image5.add(jo.getString("image_5_url_3"));
                                image5.add(jo.getString("image_5_url_4"));
                                image5.add(jo.getString("image_5_url_5"));
                                image5.add(jo.getString("image_5_url_6"));
                                image5.add(jo.getString("image_5_url_7"));
                                image5.add(jo.getString("image_5_url_8"));
                                image5.add(jo.getString("image_5_url_9"));
                                image5.add(jo.getString("image_5_url_10"));

                                color5=jo.getString("color5");
                                image_downloader_5(image5);
                            }



                        }catch (JSONException e)
                        {

                            myProgressBar.setVisibility(View.GONE);
                            Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();

                        }
                    }
                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        myProgressBar.setVisibility(View.GONE);
                        Toast.makeText(c, "لم تنجح العملية تحقق من الإتصال بالإنترنت", Toast.LENGTH_LONG).show();
                    }
                });

    }


    private void hide_image_selector(int num_color){


        if(num_color<5){
            image_color_5.setVisibility(View.INVISIBLE);
        }

        if(num_color<4){
            image_color_4.setVisibility(View.INVISIBLE);
        }

        if(num_color<3){
            image_color_3.setVisibility(View.INVISIBLE);
        }

        if(num_color<2){
            color_selector.setVisibility(View.GONE);
            color_text_1.setVisibility(View.GONE);
            color_text_2.setVisibility(View.GONE);
        }


    }

    private void hide_size_selector(){

        if(size5.getText().length()==0){
            size5.setVisibility(View.INVISIBLE);
        }

        if(size4.getText().length()==0){
            size4.setVisibility(View.INVISIBLE);
        }

        if(size3.getText().length()==0){
            size3.setVisibility(View.INVISIBLE);
        }

        if(size2.getText().length()==0){
            size2.setVisibility(View.INVISIBLE);
        }

        if(size1.getText().length()==0){
            size1.setVisibility(View.INVISIBLE);
        }

        if(size1.getText().length()==0 && size2.getText().length()==0 && size3.getText().length()==0 && size4.getText().length()==0 && size5.getText().length()==0){
            all_size.setVisibility(View.GONE);
        }

    }

    private void hide_empty_view(){
        if(old_price.getText().length()==0){
            old_price.setVisibility(View.GONE);
        }
        if(title.getText().length()==0){
            title.setVisibility(View.GONE);
        }
        if(description.getText().length()==0){
            description.setVisibility(View.GONE);
        }

        if(old_price.getText().length()==0  && price.getText().length()==0){
            old_price.setVisibility(View.GONE);
            price.setVisibility(View.GONE);
        }

    }

    private void image_downloader_1(ArrayList<String>image){

            num_image1 = 0;
            slideModels1 = new ArrayList<>();

            for (int i = 9; i >= 0; i--) {

                if (image.get(i) != "null") {
                    num_image1++;
                    String url = url_http+"/app/all_image/"+folder+"/"+row+"/"+ image.get(i);
                    slideModels1.add(new SlideModel(url, "", ScaleTypes.FIT));
                }

            }



        String path=url_http+"/app/all_image/"+folder+"/"+row+"/" + image.get(0);

        if(path != null && path.length() > 0)
        {
            Picasso.get().load(path).placeholder(R.drawable.invesible_image).into(image_color_1);
        }else {
            Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.invesible_image).into(image_color_1);
        }

        imageSlider.setImageList(slideModels1, ScaleTypes.FIT);
        viewPager.setCurrentItem(num_image1-1);

    }

    private void image_downloader_2(ArrayList<String>image){

        num_image2 = 0;
        slideModels2 = new ArrayList<>();

        for (int i = 9; i >= 0; i--) {

            if (image.get(i) != "null") {
                num_image2++;
                String url = url_http+"/app/all_image/"+folder+"/"+row+"/" + image.get(i);
                slideModels2.add(new SlideModel(url, "", ScaleTypes.FIT));
            }

        }

        String path=url_http+"/app/all_image/"+folder+"/"+row+"/" + image.get(0);

        if(path != null && path.length() > 0)
        {
            Picasso.get().load(path).placeholder(R.drawable.invesible_image).into(image_color_2);
        }else {
            Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.invesible_image).into(image_color_1);
        }
    }

    private void image_downloader_3(ArrayList<String>image){

        num_image3 = 0;
        slideModels3 = new ArrayList<>();

        for (int i = 9; i >= 0; i--) {

            if (image.get(i) != "null") {
                num_image3++;
                String url = url_http+"/app/all_image/"+folder+"/"+row+"/" + image.get(i);
                slideModels3.add(new SlideModel(url, "", ScaleTypes.FIT));
            }

        }

        String path=url_http+"/app/all_image/"+folder+"/"+row+"/" + image.get(0);

        if(path != null && path.length() > 0)
        {
            Picasso.get().load(path).placeholder(R.drawable.invesible_image).into(image_color_3);
        }else {
            Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.invesible_image).into(image_color_1);
        }
    }

    private void image_downloader_4(ArrayList<String>image){

        num_image4 = 0;
        slideModels4 = new ArrayList<>();

        for (int i = 9; i >= 0; i--) {

            if (image.get(i) != "null") {
                num_image4++;
                String url = url_http+"/app/all_image/"+folder+"/"+row+"/" + image.get(i);
                slideModels4.add(new SlideModel(url, "", ScaleTypes.FIT));
            }

        }

        String path=url_http+"/app/all_image/"+folder+"/"+row+"/" + image.get(0);

        if(path != null && path.length() > 0)
        {
            Picasso.get().load(path).placeholder(R.drawable.invesible_image).into(image_color_4);
        }else {
            Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.invesible_image).into(image_color_1);
        }
    }

    private void image_downloader_5(ArrayList<String>image){

        num_image5 = 0;
        slideModels5 = new ArrayList<>();

        for (int i = 9; i >= 0; i--) {

            if (image.get(i) != "null") {
                num_image5++;
                String url = url_http+"/app/all_image/"+folder+"/"+row+"/" + image.get(i);
                slideModels5.add(new SlideModel(url, "", ScaleTypes.FIT));
            }

        }

        String path=url_http+"/app/all_image/"+folder+"/"+row+"/" + image.get(0);

        if(path != null && path.length() > 0)
        {
            Picasso.get().load(path).placeholder(R.drawable.invesible_image).into(image_color_5);
        }else {
            Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.invesible_image).into(image_color_1);
        }
    }

    public void select_image_group(int i,LinearLayout dot){

        if(i==1){
            color_text_2.setText(color1);
            imageSlider.setImageList(slideModels1, ScaleTypes.FIT);
            viewPager.setCurrentItem(num_image1-1);

            if(slideModels1.size()==1){
                dot.setVisibility(View.GONE);
            }else{
                dot.setVisibility(View.VISIBLE);
            }
        }

        if(i==2){
            color_text_2.setText(color2);
            imageSlider.setImageList(slideModels2, ScaleTypes.FIT);
            viewPager.setCurrentItem(num_image2-1);

            if(slideModels2.size()==1){
                dot.setVisibility(View.GONE);
            }else{
                dot.setVisibility(View.VISIBLE);
            }
        }

        if(i==3){
            color_text_2.setText(color3);
            imageSlider.setImageList(slideModels3, ScaleTypes.FIT);
            viewPager.setCurrentItem(num_image3-1);

            if(slideModels3.size()==1){
                dot.setVisibility(View.GONE);
            }else{
                dot.setVisibility(View.VISIBLE);
            }
        }

        if(i==4){
            color_text_2.setText(color4);
            imageSlider.setImageList(slideModels4, ScaleTypes.FIT);
            viewPager.setCurrentItem(num_image4-1);

            if(slideModels4.size()==1){
                dot.setVisibility(View.GONE);
            }else{
                dot.setVisibility(View.VISIBLE);
            }
        }

        if(i==5){
            color_text_2.setText(color5);
            imageSlider.setImageList(slideModels5, ScaleTypes.FIT);
            viewPager.setCurrentItem(num_image5-1);

            if(slideModels5.size()==1){
                dot.setVisibility(View.GONE);
            }else{
                dot.setVisibility(View.VISIBLE);
            }
        }




    }

}