package com.example.jccla.rat.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jccla.rat.Model.Model;
import com.example.jccla.rat.Model.SightingDataItem;
import com.example.jccla.rat.R;

import java.util.List;

public class SightingActivity extends AppCompatActivity{
    //This class will place sighting reports into a recycler?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighting);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dataitem_list);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(Model.getInstance().getItems()));
    }


    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<SightingDataItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<SightingDataItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dataitem_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText("" + mValues.get(position).getKey());
            holder.mContentView.setText(mValues.get(position).getAddress());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DataItemDetailActivity.class);
                    //Log.e("sighting activity - problem with item #", holder.mItem.getIndex() + "");
                    intent.putExtra("index", holder.mItem.getIndex());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public SightingDataItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.key);
                mContentView = (TextView) view.findViewById(R.id.address);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }

    }


}
