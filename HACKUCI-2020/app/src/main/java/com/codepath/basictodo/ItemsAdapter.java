package com.codepath.basictodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClicklistener{
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClicklistener longClicklistener;
    public ItemsAdapter(List<String> items, OnLongClicklistener longClicklistener) {
        this.items=items;
        this.longClicklistener=longClicklistener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflator to inflate a view

      View todoView=  LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap it isnide a viewHolder and return it

        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //grab the item at the position
        String item=items.get(position);
        //bind the item into the specified viewholder

        holder.bind(item);

    }
    // tells how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // container to provide easy access to view that represent each row of the list

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem= itemView.findViewById(android.R.id.text1);

        }
        //update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //remove the item from the recyclerview
                    longClicklistener.onItemLongClicked(getAdapterPosition());

                    return true;
                }
            });
        }
    }
}
