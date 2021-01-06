package com.example.internshipproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ReviewAdapter extends BaseAdapter {
    List<ReviewResponse.Review> Reviews;
    private Context context;
    private LayoutInflater layoutInflater;

    public ReviewAdapter(List<ReviewResponse.Review> Reviews, Context reviewActivity) {
        this.Reviews = Reviews;
        this.context = reviewActivity;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.review_list_layout, parent, false);
        }


        TextView textView = convertView.findViewById(R.id.Reviewcontent);
        textView.setText(Reviews.get(position).getContent());
        textView = convertView.findViewById(R.id.user);
        textView.setText(Reviews.get(position).getAuthor());
        System.out.println(Reviews.size());
        return convertView;
    }

}
