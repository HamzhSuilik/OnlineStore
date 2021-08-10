package market.hz;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import market.hz.connection.products_connection;
import market.hz.connection.sections_connection;
import market.hz.function.products_adapter;
import market.hz.function.products_data;
import market.hz.seller.Add_new_group;
import market.hz.seller.Add_new_product_data;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter adapter;
    private ViewPager mViewPager;
    private Fragment fragment_1;
    private Fragment fragment_2;
    private Fragment fragment_3;


    private static final int new_product_requestCode=1;

    // ---------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(2, true);

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new SectionsPageAdapter(getSupportFragmentManager());

        fragment_1=new Tab1Fragment();
        fragment_2=new Tab2Fragment();
        fragment_3=new Tab3Fragment();

        adapter.addFragment(fragment_1, "المفضلة");
        adapter.addFragment(fragment_2, "الأقسام");
        adapter.addFragment(fragment_3, "العروض المميزة");
        viewPager.setAdapter(adapter);
    }


    public void gooo(View view) {

        OnCreate_tab2();

    }


    @Override
    public void onBackPressed() {

        int i= mViewPager.getCurrentItem();
        GridView myGridView=findViewById(R.id.grid2);
        GridView myGridView1=findViewById(R.id.grid);
        TextView text1=findViewById(R.id.bt1_tab2);
        TextView text2=findViewById(R.id.bt2_tab2);

        if(i==1 && myGridView.getVisibility()==View.VISIBLE){
            myGridView.setVisibility(View.INVISIBLE);
            text1.setVisibility(View.VISIBLE);
            text2.setVisibility(View.GONE);

            Animation anmi = AnimationUtils.loadAnimation(this, R.anim.bounce);
            myGridView1.setAnimation(anmi);

            anmi=AnimationUtils.loadAnimation(this, R.anim.lefttoright);
            text1.setAnimation(anmi);


        }else {
            super.onBackPressed();
        }

    }

    public void goo_new_group(View view) {
        Intent go = new Intent(this, Add_new_group.class);
        startActivityForResult(go,88);
    }


    public void goo_new_product(View view){
        Context c= fragment_2.getContext();
        Activity fragment2=(Activity)c;
        TextView group_number =fragment2.findViewById(R.id.group_number);

        Intent intent=new Intent(this, Add_new_product_data.class);
        intent.putExtra("folder",group_number.getText().toString());
        startActivityForResult(intent,new_product_requestCode);
    }

    public void OnCreate_tab2(){


        Context c= fragment_2.getContext();
        Activity view=(Activity)c;

        // ***************************************************

        TextView textView1=view.findViewById(R.id.bt1_tab2);
        TextView textView2=view.findViewById(R.id.bt2_tab2);
        TextView folder_text=view.findViewById(R.id.group_number);
        GridView gridView=view.findViewById(R.id.grid);
        GridView gridView2=view.findViewById(R.id.grid2);
        ProgressBar progressBar=view.findViewById(R.id.progressBar_tab_2);


        String folder=folder_text.getText().toString();


        ArrayList<products_data> all_products = new ArrayList<>();
        products_adapter adapter =new products_adapter(c,all_products,folder,progressBar,gridView,gridView2,textView1,textView2);
        gridView2.setAdapter(adapter);

        products_connection products_connection1=new products_connection(c, folder,progressBar,gridView,gridView2,textView1,textView2);
        products_connection1.retrieve(gridView2,progressBar);

    }


    public void re_start_group_section(){

        Context c= fragment_2.getContext();
        Activity view=(Activity)c;

        // ***************************************************

        TextView textView1=view.findViewById(R.id.bt1_tab2);
        TextView textView2=view.findViewById(R.id.bt2_tab2);
        GridView gridView=view.findViewById(R.id.grid);
        GridView gridView2=view.findViewById(R.id.grid2);
        ProgressBar progressBar=view.findViewById(R.id.progressBar_tab_2);

        sections_connection sections_connection1=new sections_connection(c, gridView, gridView2, textView1, textView2);
        sections_connection1.retrieve(gridView,progressBar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==88){
            int i=data.getIntExtra("check",0);

            if(i==1){
                re_start_group_section();
            }

        }



        if(requestCode==new_product_requestCode){
            if(data.getIntExtra("success",0)==1){
                OnCreate_tab2();
            }
        }

        if(requestCode==3){
            if(data.getIntExtra("success",0)==1){
                re_start_group_section();
            }
        }


    }

}
