package market.hz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import java.util.ArrayList;
import java.util.List;
import  market.hz.connection.details_connection;
import market.hz.function.save_favorite;


public class details extends AppCompatActivity {

    private String folder;
    private String row;

    private TextView button_favorite;
    private ImageView favorite_icon;
    Boolean favorite_check;


    ImageSlider imageSlider;
    ViewPager viewPager;
    details_connection detailsConnection;
    LinearLayout pagerDots;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        folder =getIntent().getStringExtra("folder");
        row=getIntent().getStringExtra("row");

        // Favorite check
        button_favorite=findViewById(R.id.favorite_button_id);
        favorite_icon=findViewById(R.id.favorite_icon);

        save_favorite favorite=new save_favorite(Integer.valueOf(folder),Integer.valueOf(row),getApplicationContext());
        if(favorite.check()!=0){
            favorite_check=true;
            favorite_icon.setVisibility(View.VISIBLE);
            button_favorite.setVisibility(View.GONE);
        }else{
            favorite_check=false;
        }


        // infalte

        title=findViewById(R.id.titel_details);
        description=findViewById(R.id.description_details);
        price=findViewById(R.id.price_details);
        old_price=findViewById(R.id.old_price_details);
        size=findViewById(R.id.size_details);
        size1=findViewById(R.id.size_1_details);
        size2=findViewById(R.id.size_2_details);
        size3=findViewById(R.id.size_3_details);
        size4=findViewById(R.id.size_4_details);
        size5=findViewById(R.id.size_5_details);
        // image color
        image_color_1=findViewById(R.id.image_color_1);
        image_color_2=findViewById(R.id.image_color_2);
        image_color_3=findViewById(R.id.image_color_3);
        image_color_4=findViewById(R.id.image_color_4);
        image_color_5=findViewById(R.id.image_color_5);

        // color selector

        color_selector=findViewById(R.id.color_selector);
        all_size=findViewById(R.id.all_sizes_linearLayout);
        color_text_1=findViewById(R.id.color_text_details);
        color_text_2=findViewById(R.id.color_text2_details);

        ProgressBar myDataLoaderProgressBar=findViewById(R.id.progressBar_details);

        pagerDots = findViewById(R.id.pager_dots);

        // connection
        // $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

        ArrayList text_array=new ArrayList<TextView>();

        text_array.add(title);
        text_array.add(description);
        text_array.add(price);
        text_array.add(old_price);
        text_array.add(size);
        text_array.add(size1);
        text_array.add(size2);
        text_array.add(size3);
        text_array.add(size4);
        text_array.add(size5);
        text_array.add(color_text_1);
        text_array.add(color_text_2);

        ArrayList image_array=new ArrayList<ImageView>();

        image_array.add(image_color_1);
        image_array.add(image_color_2);
        image_array.add(image_color_3);
        image_array.add(image_color_4);
        image_array.add(image_color_5);

        ArrayList layout_array=new ArrayList<LinearLayout>();

        layout_array.add(color_selector);
        layout_array.add(all_size);

        viewPager = findViewById(R.id.view_pager);
        imageSlider=findViewById(R.id.image_slider);

        detailsConnection =new details_connection(this,folder,row,text_array,image_array,layout_array,imageSlider,viewPager);
        detailsConnection.retrieve(myDataLoaderProgressBar);



    }



    public void color1(View view) {
        image_color_1.setBackgroundResource(R.drawable.image_background);
        image_color_2.setBackgroundResource(R.color.invisible);
        image_color_3.setBackgroundResource(R.color.invisible);
        image_color_4.setBackgroundResource(R.color.invisible);
        image_color_5.setBackgroundResource(R.color.invisible);


        detailsConnection.select_image_group(1,pagerDots);

    }

    public void color2(View view) {
        image_color_1.setBackgroundResource(R.color.invisible);
        image_color_2.setBackgroundResource(R.drawable.image_background);
        image_color_3.setBackgroundResource(R.color.invisible);
        image_color_4.setBackgroundResource(R.color.invisible);
        image_color_5.setBackgroundResource(R.color.invisible);

        detailsConnection.select_image_group(2,pagerDots);
    }

    public void color3(View view) {
        image_color_1.setBackgroundResource(R.color.invisible);
        image_color_2.setBackgroundResource(R.color.invisible);
        image_color_3.setBackgroundResource(R.drawable.image_background);
        image_color_4.setBackgroundResource(R.color.invisible);
        image_color_5.setBackgroundResource(R.color.invisible);

        detailsConnection.select_image_group(3,pagerDots);
    }

    public void color4(View view) {
        image_color_1.setBackgroundResource(R.color.invisible);
        image_color_2.setBackgroundResource(R.color.invisible);
        image_color_3.setBackgroundResource(R.color.invisible);
        image_color_4.setBackgroundResource(R.drawable.image_background);
        image_color_5.setBackgroundResource(R.color.invisible);

        detailsConnection.select_image_group(4,pagerDots);
    }

    public void color5(View view) {
        image_color_1.setBackgroundResource(R.color.invisible);
        image_color_2.setBackgroundResource(R.color.invisible);
        image_color_3.setBackgroundResource(R.color.invisible);
        image_color_4.setBackgroundResource(R.color.invisible);
        image_color_5.setBackgroundResource(R.drawable.image_background);

        detailsConnection.select_image_group(5,pagerDots);
    }

    public void favorite(View view){
        save_favorite favorite=new save_favorite(Integer.valueOf(folder),Integer.valueOf(row),getApplicationContext());
        favorite.save_new();
        favorite_icon.setVisibility(View.VISIBLE);
        button_favorite.setVisibility(View.GONE);

        Toast.makeText(this, ""+description.getText().toString().length(), Toast.LENGTH_SHORT).show();


        SharedPreferences gg = getSharedPreferences("save_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor copy = gg.edit();
        if(gg.getInt("change",0)==0) {
            copy.putInt("change", 1);
            copy.apply();
        }else{
            copy.putInt("change", 0);
            copy.apply();
        }

    }

    public void favorite2(View view) {
        save_favorite favorite=new save_favorite(Integer.valueOf(folder),Integer.valueOf(row),getApplicationContext());
        favorite.delete();
        favorite_icon.setVisibility(View.GONE);
        button_favorite.setVisibility(View.VISIBLE);


        SharedPreferences gg = getSharedPreferences("save_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor copy = gg.edit();
        if(gg.getInt("change",0)==0) {
            copy.putInt("change", 1);
            copy.apply();
        }else{
            copy.putInt("change", 0);
            copy.apply();
        }
    }
}