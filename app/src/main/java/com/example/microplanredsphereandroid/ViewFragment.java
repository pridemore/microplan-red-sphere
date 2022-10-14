package com.example.microplanredsphereandroid;

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
import com.example.microplanredsphereandroid.models.LoanApplications;

import java.util.ArrayList;


public class ViewFragment extends Fragment {
    private ImageView backIcon, menu;
    private TextView title;
    private Button btn_previous;
    private ArrayList<LoanApplications> applicationsList;
    private RecyclerView recyclerView;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous=view.findViewById(R.id.btn_previous);
        recyclerView = view.findViewById(R.id.recyclerView);
        applicationsList = new ArrayList<>();
        title.setText("View Applications");

        setViewLoanApplicationsList();//load data to our arraylist
        setAdapter();//load recycler view with applications list

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).replaceFragment(new HomeFragment());
            }
        });

        return view;
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(applicationsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
    private void setViewLoanApplicationsList() {
        applicationsList.add(new LoanApplications("Belinda", "Viriri", 800,"ITEL LAPTOP","28/01/22"));
        applicationsList.add(new LoanApplications("Anotida", "Magara", 400,"SAMSUNG M02","04/03/22"));
        applicationsList.add(new LoanApplications("Pridemore", "Rugara",500,"ITEL P37", "05/05/22"));
        applicationsList.add(new LoanApplications("Taonga", "Tore", 600,"D LIGHT 200","13/06/22"));
        applicationsList.add(new LoanApplications("Takudzwa", "Magada",8300,"DLIGHT D100", "06/06/22"));
        applicationsList.add(new LoanApplications("Tinashe", "Meki", 8040,"ITEL SPEAKER","05/03/22"));
        applicationsList.add(new LoanApplications("Anna", "Mabari", 600,"CREATIVE 42'' TV","06/03/22"));
        applicationsList.add(new LoanApplications("Dereck", "Marambanyika",100,"ORAIMO SPEAKER", "07/04/22"));
        applicationsList.add(new LoanApplications("Tafadzwa", "Tereki", 900,"LENOVO TAB 7","06/04/22"));
        applicationsList.add(new LoanApplications("Terence", "Rugare", 2000,"ITEL KIDS TABLE","08/03/22"));
        applicationsList.add(new LoanApplications("Evah", "Tinarwe", 1000,"ITEL KIDS TABLET","05/03/22"));
        applicationsList.add(new LoanApplications("Brenda", "Ticharwa",400,"SOUND SYSTEMS", "23/04/22"));

    }
}