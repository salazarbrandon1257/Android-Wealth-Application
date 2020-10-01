package com.example.divcal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.divcal.portfolioActivity.addList;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class StockAdapter extends
        RecyclerView.Adapter<StockAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView priceTextView;
        public TextView shareTextView;
        public ImageButton deleteButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.stock_name);
            priceTextView = (TextView) itemView.findViewById(R.id.stock_price);
            shareTextView = (TextView) itemView.findViewById(R.id.stock_shares);

            deleteButton = (ImageButton) itemView.findViewById(R.id.delete_button);
        }
    }

        private List<portfolioActivity.Stocks> mContacts;

        // Pass in the contact array into the constructor
        public StockAdapter(ArrayList<portfolioActivity.Stocks> contacts) {
            mContacts = contacts;
        }
    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_stock, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final StockAdapter.ViewHolder holder, final int position) {
        // Get the data model based on position
        final portfolioActivity.Stocks contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(contact.getName());
        TextView textView2 = holder.priceTextView;
        textView2.setText(contact.getPrice());
        TextView textView3 = holder.shareTextView;
        textView3.setText(contact.getShares().toString());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContacts.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,mContacts.size());
                }
            });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public void add(portfolioActivity.Stocks tsla) {
        mContacts.add(tsla);
    }
    //public void remove(portfolioActivity.Stocks tsla) {
        //mContacts.remove(holder.getAdapterPosition());notifyDataSetChanged();

    //}
}





