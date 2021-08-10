package market.hz;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import market.hz.connection.sections_connection;
import market.hz.seller.Add_new_product_data;


public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    sections_connection sections_connection1;
    GridView myGridView;
    ProgressBar myDataLoaderProgressBar;
    TextView text1,text2,group_number_text;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        text1=view.findViewById(R.id.bt1_tab2);
        text2=view.findViewById(R.id.bt2_tab2);
        group_number_text=view.findViewById(R.id.group_number);

        myGridView=view.findViewById(R.id.grid);
        myDataLoaderProgressBar=view.findViewById(R.id.progressBar_tab_2);
        GridView myGridView2=view.findViewById(R.id.grid2);



        sections_connection1=new sections_connection(view.getContext(), myGridView, myGridView2, text1, text2);
        sections_connection1.retrieve(myGridView,myDataLoaderProgressBar);

        return view;
    }




}
