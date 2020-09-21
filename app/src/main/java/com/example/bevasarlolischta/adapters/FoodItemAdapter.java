package com.example.bevasarlolischta.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bevasarlolischta.ListActivity;
import com.example.bevasarlolischta.ListItemsActivity;
import com.example.bevasarlolischta.R;

import java.util.ArrayList;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {

    private ArrayList<String> names =  new ArrayList<>();
    private ArrayList<String> numbers =  new ArrayList<>();
    private ArrayList<String> units =  new ArrayList<>();
    private ArrayList<String> deletes =  new ArrayList<>();
    private Context mcontext;
    private LayoutInflater mInflater;
    private OnListListener mOnNoteListener;

    public FoodItemAdapter(ArrayList<String> names, ArrayList<String> numbers, ArrayList<String> units, Context mcontext) {
        this.names = names;
        this.numbers = numbers;
        this.units = units;
        this.mInflater = LayoutInflater.from(mcontext);
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_food_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nitem = names.get(position);
        holder.finame.setText(nitem);

        String nuitem = numbers.get(position);
        holder.finumber.setText(nuitem);

        String uitem = units.get(position);
        holder.fiunit.setText(uitem);


    }

    @Override
    public int getItemCount()
    {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView finame;
        TextView finumber;
        TextView fiunit;
        LinearLayout filayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            finame = itemView.findViewById(R.id.item_name_display);
            finumber = itemView.findViewById(R.id.numpick_list_display);
            fiunit = itemView.findViewById(R.id.spinner_list_display);
            filayout = itemView.findViewById(R.id.single_food_item);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(mOnNoteListener != null) mOnNoteListener.onListClick(v, getAdapterPosition());
        }
    }

    public void setClickListener(FoodItemAdapter.OnListListener onListListener){
        this.mOnNoteListener = onListListener;
    }

    public interface OnListListener {
        void onListClick(View view, int position);

    }

}
