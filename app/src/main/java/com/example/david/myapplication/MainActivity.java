package com.example.david.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_target);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("Item number " + i);
        }

        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private static class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<String> data;

        public MyAdapter(List<String> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 0) {
                return new HeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false));
            } else {
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ItemViewHolder) {
                ((ItemViewHolder) holder).txtvItem.setText(getData(position));
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        @Override
        public int getItemCount() {
            return data.size() + 1;
        }

        private String getData(int position) {
            return position > 0 && position < getItemCount() ? data.get(position - 1) : null;
        }

        static class ItemViewHolder extends RecyclerView.ViewHolder {
            private View row;
            private TextView txtvItem;

            ItemViewHolder(View row) {
                super(row);
                this.row = row;
                txtvItem = (TextView) row.findViewById(R.id.txtv_item);
            }
        }

        static class HeaderHolder extends RecyclerView.ViewHolder {

            public HeaderHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
