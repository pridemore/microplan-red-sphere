package com.example.microplanredsphereandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microplanredsphereandroid.R;
import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.models.ProductEntry;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<LoanApplicationModel> applicationsArrayList;

    public RecyclerAdapter(ArrayList<LoanApplicationModel> applicationsArrayList) {
        this.applicationsArrayList = applicationsArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewApplicantName,textViewProducts,textViewApplicationDate,textViewInstallment;

        public MyViewHolder(final View view) {
            super(view);
            textViewApplicantName = view.findViewById(R.id.textViewApplicantName);
            textViewProducts=view.findViewById(R.id.textViewProducts);
            textViewApplicationDate=view.findViewById(R.id.textViewApplicationDate);
            textViewInstallment=view.findViewById(R.id.textViewInstallment);
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
        String name = applicationsArrayList.get(position).firstName;
        String surname=applicationsArrayList.get(position).lastName;
        String date=applicationsArrayList.get(position).dateSignBorrower;
        List<ProductEntry> productEntryList=applicationsArrayList.get(position).products;
        String product = null;
        if(productEntryList.size()>0) {
            for (ProductEntry productEntry : productEntryList
            ) {
                product += productEntry.getProduct().getName().toString() + ",";
            }
        }else{
            double topUp = applicationsArrayList.get(position).topUp;
            product=String.valueOf(topUp);
        }

        String installment=applicationsArrayList.get(position).newLoanAmount.toString();

        holder.textViewApplicantName.setText(new StringBuilder().append(name).append(" ").append(surname).toString());
        holder.textViewProducts.setText(product);
        holder.textViewApplicationDate.setText(date);
        holder.textViewInstallment.setText("$"+installment);
    }

    @Override
    public int getItemCount() {
        return applicationsArrayList.size();
    }
}
