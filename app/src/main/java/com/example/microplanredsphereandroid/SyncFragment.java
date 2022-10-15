package com.example.microplanredsphereandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microplanredsphereandroid.adapter.RecyclerAdapter;
import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SyncFragment extends Fragment {
    private ImageView backIcon, menu;
    private TextView title;
    private Button btn_previous,btn_sync;
    private RecyclerView recyclerView;
    private ArrayList<LoanApplicationModel> applicationsList;
    private List<LoanApplicationModel> modelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sync, container, false);
        modelList = Utils.getSavedLoans(getContext());

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        btn_sync=view.findViewById(R.id.btn_sync);
        recyclerView = view.findViewById(R.id.recyclerView);
        applicationsList = new ArrayList<>();


        //setting static text
        title.setText("Sync Applications");

        //Buttons logic
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Activity.class.equals(HomeActivity.class)){
                    ((HomeActivity) getActivity()).replaceFragment(new HomeFragment());
                }else {
                    ((NewApplicationActivity) getActivity()).replaceFragment(new HomeFragment());
                }
            }
        });
        btn_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //loading loan applications to our list
        setSyncLoanApplicationsList();

        //load the list to adapter
        setAdapter();

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

        for (LoanApplicationModel loanApplication:modelList
        ) {
            applicationsList.add(loanApplication);
        }
//        applicationsList.add(new LoanApplications("Anna", "Mabari",200,"LENOVO LAPTOP" ,"06/03/22"));
//        applicationsList.add(new LoanApplications("Dereck", "Marambanyika", 4000,"SOLAR PANEL 265 WATTS","07/04/22"));
//        applicationsList.add(new LoanApplications("Tafadzwa", "Tereki",700,"ITEL KIDS TABLET", "06/04/22"));
//        applicationsList.add(new LoanApplications("Terence", "Rugare", 500,"ITEL S16","08/03/22"));
//        applicationsList.add(new LoanApplications("Evah", "Tinarwe", 75,"BOOM","05/03/22"));
//        applicationsList.add(new LoanApplications("Brenda", "Ticharwa", 9000,"DLIGHT S3 X 3","23/04/22"));

    }
}