package market.hz.function;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;


public class save_favorite_offers {


    private int row;
    private Context context;

    public save_favorite_offers(int row, Context context) {
        this.row = row;
        this.context = context;
    }

    public void save_new(){

        int save_check;
        SharedPreferences gg = context.getSharedPreferences("save_file", Context.MODE_PRIVATE);

        int key=1;

        for (int i = 1; key !=0 ; i++) {
            save_check=gg.getInt("save_offers_"+i,0);

            if(save_check==0){

                SharedPreferences.Editor copy = gg.edit();
                copy.putInt("save_offers_"+i,1);
                copy.putInt("row_offers_"+i,row);
                copy.apply();
                key=0;
            }

        }
    }

    public void delete(){
        int index=check();
        SharedPreferences gg = context.getSharedPreferences("save_file", Context.MODE_PRIVATE);

        SharedPreferences.Editor copy = gg.edit();
        copy.putInt("save_offers_"+index,2);
        copy.putInt("row_offers_"+index,0);
        copy.apply();
    }

    public int check(){

        int save_check;
        int row_check;

        SharedPreferences gg = context.getSharedPreferences("save_file", Context.MODE_PRIVATE);

        int key=1;

        for (int i = 1; key !=0 ; i++) {
            save_check=gg.getInt("save_offers_"+i,0);


            if(save_check==0){
                key=0;
            }

            if(save_check==1){

                row_check=gg.getInt("row_offers_"+i,0);

                if(row_check==row){
                    key=0;
                    return i;
                }
            }


        }


        return 0;
    }

    public ArrayList<String> select_all(){
        ArrayList<String> arrayList=new ArrayList<>();
        SharedPreferences gg = context.getSharedPreferences("save_file", Context.MODE_PRIVATE);

        int key=1;

        for (int i = 1; key==1; i++) {
            if(gg.getInt("save_offers_"+i,0)==0){
                key=0;
            }else {
                if(gg.getInt("save_offers_"+i,0)==1) {
                    arrayList.add(gg.getInt("row_" + i, 0) + "");
                }
            }
        }

        return arrayList;
    }
}
