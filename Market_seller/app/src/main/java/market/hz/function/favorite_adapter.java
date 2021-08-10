package market.hz.function;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import market.hz.connection.favorite_connection;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import market.hz.R;
import market.hz.details;

public class favorite_adapter extends BaseAdapter {


    Context context;
    ArrayList<favorite_data> data;

    public favorite_adapter(Context context, ArrayList<favorite_data> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {




        // **************************


        if(view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.favorite_form,viewGroup,false);
        }

        TextView description = view.findViewById(R.id.description_favorite);
        ImageView ImageView = view.findViewById(R.id.image_favorite);
        TextView price = view.findViewById(R.id.text_price_favorite);
        TextView old_price = view.findViewById(R.id.text_old_price_favorite);
        LinearLayout old_price_place = view.findViewById(R.id.old_price_place_favorite);
        CardView discount=view.findViewById(R.id.discount_favorite);


        favorite_connection favorite_connection1=new favorite_connection(context,data.get(i).getFolder(),data.get(i).getRow(),description,price,old_price,ImageView);
        favorite_connection1.retrieve();





        if (old_price.getText().toString() == "") {
            old_price_place.setVisibility(View.GONE);
            discount.setVisibility(View.GONE);
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent go = new Intent(context, details.class);
                go.putExtra("folder",data.get(i).getFolder());
                go.putExtra("row",data.get(i).getRow());
                context.startActivity(go);

            }
        });

        return view;


        // ***************************

    }
}
