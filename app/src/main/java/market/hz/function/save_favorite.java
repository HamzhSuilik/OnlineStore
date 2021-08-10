package market.hz.function;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;


public class save_favorite {

    private int folder;
    private int row;
    private Context context;

    public save_favorite(int folder, int row, Context context) {
        this.folder = folder;
        this.row = row;
        this.context = context;
    }

    public void save_new(){

        int save_check;
        SharedPreferences gg = context.getSharedPreferences("save_file", Context.MODE_PRIVATE);

        int key=1;

        for (int i = 1; key !=0 ; i++) {
            save_check=gg.getInt("save_"+i,0);

            if(save_check==0){

                SharedPreferences.Editor copy = gg.edit();
                copy.putInt("save_"+i,1);
                copy.putInt("folder_"+i,folder);
                copy.putInt("row_"+i,row);
                copy.apply();
                key=0;
            }

        }
    }

    public void delete(){
        int index=check();
        SharedPreferences gg = context.getSharedPreferences("save_file", Context.MODE_PRIVATE);

        SharedPreferences.Editor copy = gg.edit();
        copy.putInt("save_"+index,2);
        copy.putInt("folder_"+index,0);
        copy.putInt("row_"+index,0);
        copy.apply();
    }

    public int check(){

        int save_check;
        int folder_check;
        int row_check;

        SharedPreferences gg = context.getSharedPreferences("save_file", Context.MODE_PRIVATE);

        int key=1;

        for (int i = 1; key !=0 ; i++) {
            save_check=gg.getInt("save_"+i,0);


            if(save_check==0){
                key=0;
            }

            if(save_check==1){

                folder_check=gg.getInt("folder_"+i,0);
                row_check=gg.getInt("row_"+i,0);

                if(folder_check==folder && row_check==row){
                    key=0;
                    return i;
                }
            }


        }


        return 0;
    }

    public ArrayList<favorite_data> select_all(){
        ArrayList<favorite_data> arrayList=new ArrayList<>();
        SharedPreferences gg = context.getSharedPreferences("save_file", Context.MODE_PRIVATE);

        int key=1;

        for (int i = 1; key==1; i++) {
            if(gg.getInt("save_"+i,0)==0){
                key=0;
            }else {
                if(gg.getInt("save_"+i,0)==1) {
                    arrayList.add(new favorite_data(gg.getInt("folder_" + i, 0) + "", gg.getInt("row_" + i, 0) + ""));
                }
            }
        }

        return arrayList;
    }
}
