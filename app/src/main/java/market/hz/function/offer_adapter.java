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
import com.squareup.picasso.Picasso;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import market.hz.R;
import market.hz.details_offers;

public class offer_adapter extends BaseAdapter {


    Context context;
    ArrayList<offer_data> data;

    public offer_adapter(Context context, ArrayList<offer_data> data) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {




        // **************************


        if(view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.offer_form,viewGroup,false);
        }

        TextView description = view.findViewById(R.id.description_product_offer);
        ImageView ImageView = view.findViewById(R.id.image_product_offer);
        TextView price = view.findViewById(R.id.text_price_offer);
        TextView old_price = view.findViewById(R.id.text_old_price_offer);
        LinearLayout old_price_place = view.findViewById(R.id.old_price_place_offer);
        CardView discount=view.findViewById(R.id.discount_offer);



        final offer_data data= (offer_data) this.getItem(i);

        description.setText(data.getText());
        //ImageView.setImageResource(data.getImage());
        price.setText(data.getPrice());
        old_price.setText(data.getOld_price());

        if (data.getOld_price().length()==0 || data.getOld_price() == null) {
            old_price_place.setVisibility(View.GONE);
            discount.setVisibility(View.GONE);
        }


        if(data.getImage() != null && data.getImage().length() > 0)
        {
            Picasso.get().load(data.getImage()).placeholder(R.drawable.invesible_image2).into(ImageView);
        }else {
            Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.invesible_image2).into(ImageView);
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(context, details_offers.class);
                go.putExtra("row",data.getId());
                context.startActivity(go);
            }
        });

        return view;


        // ***************************

    }
}
