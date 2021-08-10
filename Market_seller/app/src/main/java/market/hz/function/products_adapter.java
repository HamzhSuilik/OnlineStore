package market.hz.function;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import market.hz.connection.products_connection;
import market.hz.details;
import androidx.cardview.widget.CardView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import market.hz.R;
import market.hz.seller.Update_group;
import market.hz.seller_connection.Drop_product_connection;

public class products_adapter extends BaseAdapter {


    Context context;
    ArrayList<products_data> data;
    private String folder;

    private GridView gridView;
    private GridView gridView2;
    private ProgressBar progressBar;
    private TextView textView1,textView2;

    public products_adapter(Context context, ArrayList<products_data> data,String folder, ProgressBar progressBar,
                            GridView gridView, GridView gridView2, TextView textView1, TextView textView2) {
        this.context = context;
        this.data = data;
        this.folder=folder;

        this.gridView = gridView;
        this.gridView2 = gridView2;
        this.textView1 = textView1;
        this.textView2 = textView2;
        this.progressBar = progressBar;
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
            view= LayoutInflater.from(context).inflate(R.layout.products_form,viewGroup,false);
        }

        TextView description = view.findViewById(R.id.description_product);
        ImageView ImageView = view.findViewById(R.id.image_product);
        TextView price = view.findViewById(R.id.text_price);
        TextView old_price = view.findViewById(R.id.text_old_price);
        LinearLayout old_price_place = view.findViewById(R.id.old_price_place);
        CardView discount=view.findViewById(R.id.discount);



        final products_data data= (products_data) this.getItem(i);

        description.setText(data.getText());
        price.setText(data.getPrice());
        old_price.setText(data.getOld_price());

        if (data.getOld_price().length()==0 || data.getOld_price() == null) {
            old_price_place.setVisibility(View.GONE);
            discount.setVisibility(View.GONE);
        }



        if(data.getImage() != null && data.getImage().length() > 0)
        {
            Picasso.get().load(data.getImage()).placeholder(R.drawable.invesible_image).into(ImageView);
        }else {
            Toast.makeText(context, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.invesible_image).into(ImageView);
        }


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // *******************


                AlertDialog.Builder sms = new AlertDialog.Builder(context);

                sms.setMessage(data.getText()+"\n"+"حدد العملية المطلوبة :")
                        .setPositiveButton("تعديل", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        })


                        .setNegativeButton("حذف", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AlertDialog.Builder sms2 = new AlertDialog.Builder(context);

                                String text1 ="تحذير !";
                                String text2="\n";
                                String text3 ="سيتم حذف هذا المنتج ( ";
                                String text4=data.getText();
                                String text5 =" ):";

                                String text=text1+text2+text3+text4+text5;

                                sms2.setMessage(text)
                                        .setPositiveButton("حذف", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Drop_product_connection drop1 =new Drop_product_connection(folder,data.getId(),context,progressBar, gridView, gridView2, textView1, textView2);
                                                drop1.drop();

                                            }
                                        })


                                        .setNegativeButton("تراجع", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .show();

                            }
                        })

                        .setNeutralButton("إستعراض", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent go = new Intent(context,details.class);
                                go.putExtra("folder",folder);
                                go.putExtra("row",data.getId());
                                context.startActivity(go);

                            }
                        })
                        .show();


                // ********************


            }
        });

        return view;


        // ***************************

    }
}
