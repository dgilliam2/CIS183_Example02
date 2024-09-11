package com.example.cis183_example02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter
{
    Context context;
    ArrayList<User> user_list;

    public UserListAdapter(Context c, ArrayList<User> ls)
    {
        context = c;
        user_list = ls;
    }
    @Override
    public int getCount()
    {
        return user_list.size();
    }

    @Override
    public Object getItem(int i)
    {
        return user_list.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService((MainActivity.LAYOUT_INFLATER_SERVICE));
            view = mInflater.inflate(R.layout.custom_cell, null);
        }
        TextView username = view.findViewById(R.id.txt_v_cc_usr);
        TextView full_name = view.findViewById(R.id.txt_v_cc_flname);

        User usr = user_list.get(i);
        username.setText(usr.getUsername());
        full_name.setText(usr.getLname() + ", " + usr.getFname());
        return view;
    }
}
