package market.hz;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import market.hz.function.offer_data;
import market.hz.function.offer_adapter;
import market.hz.connection.offers_connection;


public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Fragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);


        GridView myGridView=view.findViewById(R.id.offer_grid);
        ProgressBar myDataLoaderProgressBar=view.findViewById(R.id.myDataLoaderProgressBar);


        offers_connection offers_connection1= new offers_connection(getContext());
        offers_connection1.retrieve(myGridView,myDataLoaderProgressBar);

        return view;
    }
}
