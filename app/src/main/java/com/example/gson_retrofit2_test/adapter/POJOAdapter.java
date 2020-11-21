package com.example.gson_retrofit2_test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gson_retrofit2_test.POJO;
import com.example.gson_retrofit2_test.R;

import java.util.ArrayList;
import java.util.List;

public class POJOAdapter extends RecyclerView.Adapter<POJOAdapter.POJOViewHolder> {
    public List<POJO> getPojos() {
        return pojos;
    }

    private List<POJO> pojos = new ArrayList<>();


    public void setPojos(List<POJO> pojos) {
        this.pojos = pojos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public POJOViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pojo_item, parent, false);
        return new POJOViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull POJOViewHolder holder, int position) {
        POJO pojo = pojos.get(position);
        holder.textViewBody.setText(pojo.getBody());
        holder.textViewTitle.setText(pojo.getTitle());
        holder.textViewUserId.setText("" + pojo.getUserId());
        holder.textViewId.setText("" + pojo.getId());
    }

    @Override
    public int getItemCount() {
        return pojos.size();
    }

    class POJOViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewUserId;
        private TextView textViewId;
        private TextView textViewTitle;
        private TextView textViewBody;

        public POJOViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserId = itemView.findViewById(R.id.text_view_user_id);
            textViewId = itemView.findViewById(R.id.text_view_id);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewBody = itemView.findViewById(R.id.text_view_body);
        }


    }
}
