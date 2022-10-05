package com.example.microplanredsphereandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.microplanredsphereandroid.adapter.RecyclerAdapter;
import com.example.microplanredsphereandroid.models.LoanApplications;

import java.util.ArrayList;

public class SyncFragment extends Fragment {
    private ImageView backIcon, menu;
    private TextView title;
    private RecyclerView recyclerView;
    private ArrayList<LoanApplications> applicationsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sync, container, false);
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        recyclerView = view.findViewById(R.id.recyclerView);
        applicationsList = new ArrayList<>();
        title.setText("Sync Applications");

        setSyncLoanApplicationsList();//loading loan applications to our list
        setAdapter();//load the list to adapter


        return view;
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(applicationsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void setSyncLoanApplicationsList() {
        applicationsList.add(new LoanApplications("Anna", "Mabari", "06/03/22"));
        applicationsList.add(new LoanApplications("Dereck", "Marambanyika", "07/04/22"));
        applicationsList.add(new LoanApplications("Tafadzwa", "Tereki", "06/04/22"));
        applicationsList.add(new LoanApplications("Terence", "Rugare", "08/03/22"));
        applicationsList.add(new LoanApplications("Evah", "Tinarwe", "05/03/22"));
        applicationsList.add(new LoanApplications("Brenda", "Ticharwa", "23/04/22"));

    }
}