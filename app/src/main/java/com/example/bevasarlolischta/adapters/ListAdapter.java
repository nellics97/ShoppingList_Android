package com.example.bevasarlolischta.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bevasarlolischta.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private ArrayList<String> mtext= new ArrayList<>();
    private LayoutInflater mInflater;
    private OnListListener mOnNoteListener;


    public ListAdapter(ArrayList<String> text, Context context) {
        this.mtext = text;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String item = mtext.get(position);
        holder.text.setText(item);

    }

    @Override
    public int getItemCount() {
        return mtext.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;
        RelativeLayout item_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.name_of_list);
            item_layout = itemView.findViewById(R.id.itemrl);



            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnNoteListener != null) mOnNoteListener.onListClick(v, getAdapterPosition());
        }
    }

    public void setClickListener(OnListListener onListListener){
        this.mOnNoteListener = onListListener;
    }

    public interface OnListListener {
        void onListClick(View view, int position);

    }
}
