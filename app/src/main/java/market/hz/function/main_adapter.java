package market.hz.function;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import market.hz.MainActivity;
import market.hz.R;
import market.hz.connection.products_connection;
import market.hz.seller_connection.Drop_group_connection;
import market.hz.seller.Update_group;

public class main_adapter extends BaseAdapter {


    Context context;
    ArrayList<main_adapter_data> data=new ArrayList<>();
    private ProgressBar progressBar;
    private GridView gridView;
    private GridView gridView2;

    private TextView textView1;
    private TextView textView2;

    // ********************
    int save_higher_rank=-1;
    int size;
    int max_index=0;
    //  ******************

    private String selected_group;

    public main_adapter(Context context, ArrayList<main_adapter_data> data, ProgressBar progressBar, GridView gridView, GridView gridView2, TextView textView1, TextView textView2) {
        this.context = context;

        // ******************************************

        size = data.size();
        this.progressBar = progressBar;
        this.gridView = gridView;
        this.gridView2 = gridView2;
        this.textView1 = textView1;
        this.textView2 = textView2;

        selected_group="0";

        for (int i = 0; i <size ; i++) {
           this.data.add(data.get(get_higher_rank(data)));
        }
       // ******************************************

    }

    public String getSelected_group() {
        return selected_group;
    }

    private int get_higher_rank(ArrayList<main_adapter_data> data){


        int rank;
        int max=0;

        if(save_higher_rank==-1) {

            for (int i = 0; i < size ; i++) {

                rank = Integer.valueOf(data.get(i).getRank());

                if (rank > max) {
                    max = rank;
                    max_index = i;
                }

            }
            save_higher_rank=max;


            return max_index;

        }else{


            for (int i = 0; i < size ; i++) {
                rank = Integer.valueOf(data.get(i).getRank());
                if (rank > max && rank<save_higher_rank) {
                    max = rank;
                    max_index = i;
                }
            }
            save_higher_rank=max;

            return max_index;
        }


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
            view= LayoutInflater.from(context).inflate(R.layout.main_section_form,viewGroup,false);
        }

        TextView txtName = view.findViewById(R.id.text_view);
        ImageView ImageView = view.findViewById(R.id.imageView);

        final main_adapter_data data= (main_adapter_data) this.getItem(i);

        txtName.setText(data.getText());


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

                                Activity activity=(Activity)context;

                                Intent go = new Intent(context,Update_group.class);
                                go.putExtra("text",data.getText());
                                go.putExtra("image",data.getImage());
                                go.putExtra("id",data.getId());

                                activity.startActivityForResult(go,3);


                            }
                        })


                        .setNegativeButton("حذف", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AlertDialog.Builder sms2 = new AlertDialog.Builder(context);

                                String text1 ="تحذير !";
                                String text2="\n";
                                String text3 ="سيتم حذف قسم ال ( ";
                                String text4=data.getText();
                                String text5 =" )و كافة العناصر التي يحتويها :";

                                String text=text1+text2+text3+text4+text5;

                                sms2.setMessage(text)
                                        .setPositiveButton("حذف", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                Drop_group_connection drop1 =new Drop_group_connection(data.getId(),context,progressBar, gridView, gridView2, textView1, textView2);
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

                                String folder=data.getId();
                                selected_group=folder;

                                textView1.setVisibility(View.GONE);
                                textView2.setVisibility(View.VISIBLE);


                                gridView2.setVisibility(View.VISIBLE);


                                ArrayList<products_data> all_products = new ArrayList<>();
                                products_adapter adapter =new products_adapter(context,all_products,folder,progressBar,gridView,gridView2,textView1,textView2);
                                gridView2.setAdapter(adapter);

                                products_connection products_connection1=new products_connection(context, folder,progressBar,gridView,gridView2,textView1,textView2);
                                products_connection1.retrieve(gridView2,progressBar);

                                Activity page2=((Activity)context);
                                TextView text=page2.findViewById(R.id.group_number);
                                text.setText(data.getId());

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
