package com.example.microplanredsphereandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microplanredsphereandroid.R;
import com.example.microplanredsphereandroid.models.LoanApplications;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<LoanApplications> applicationsArrayList;

    public RecyclerAdapter(ArrayList<LoanApplications> applicationsArrayList) {
        this.applicationsArrayList = applicationsArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText,surnameText,dateText;

        public MyViewHolder(final View view) {
            super(view);
            nameText = view.findViewById(R.id.textView4);
            surnameText=view.findViewById(R.id.lastName);
            dateText=view.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = applicationsArrayList.get(position).getName();
        String surname=applicationsArrayList.get(position).getSurname();
        String date=applicationsArrayList.get(position).getApplicationDate();
        holder.nameText.setText(name);
        holder.surnameText.setText(surname);
        holder.dateText.setText(date);
    }

    @Override
    public int getItemCount() {
        return applicationsArrayList.size();
    }
}
