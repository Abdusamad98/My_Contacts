package com.example.my_contacts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_contacts.R;
import com.example.my_contacts.models.ContactData;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    private ArrayList<ContactData> data;
    private OnClickListener listener;
    private   OnClickListener itemListener;

    public ContactAdapter(ArrayList<ContactData> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ContactData getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setDeleteListener(OnClickListener listener){
        this.listener=listener;
    }

    public void setItemListener(OnClickListener listener){
        this.itemListener=listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bind(position);
        return convertView;
    }


    class ViewHolder {
        private int position;
        //
        private TextView textName;
        private TextView delete;
        private TextView textNumber;
        private ImageView imageView;
        private ViewGroup item;

        public ViewHolder(View view) {
            textName = view.findViewById(R.id.textName);
            textNumber = view.findViewById(R.id.textNumber);
            imageView = view.findViewById(R.id.image);
            delete=view.findViewById(R.id.delete);
            item=view.findViewById(R.id.item);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) listener.onClick(position);
                }
            });
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemListener != null) itemListener.onClick(position);
                }
            });

        }

        public void bind(int position) {
            this.position = position;
            ContactData d = getItem(position);
            textName.setText(d.getName());
            textNumber.setText(d.getNumber());
            imageView.setImageResource(d.getImageUrl());
        }
    }

    public  interface OnClickListener{
        void onClick( int posittion);
    }
}
