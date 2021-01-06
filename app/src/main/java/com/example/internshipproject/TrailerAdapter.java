package com.example.internshipproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.CustomViewHolder> {
    private List<TrailerResponse.Trailer> dataList;
    private OnNoteListener mOnNoteListener;

    public TrailerAdapter(List<TrailerResponse.Trailer> dataList, OnNoteListener onNoteListener) {
        this.dataList = dataList;
        this.mOnNoteListener = onNoteListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View myView;

        TextView textUser;
        OnNoteListener onNoteListener;
        CustomViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            myView = itemView;
            textUser = myView.findViewById(R.id.user);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.OnNoteClick(getAdapterPosition());
        }
    }

    @Override

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.trailer_layout, parent, false);
        return new CustomViewHolder(view, mOnNoteListener);
    }

    @Override

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textUser.setText(dataList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface  OnNoteListener{
        void OnNoteClick(int position);
    }
}
