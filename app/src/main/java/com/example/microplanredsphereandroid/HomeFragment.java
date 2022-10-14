package com.example.microplanredsphereandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microplanredsphereandroid.adapter.RecyclerAdapter;
import com.example.microplanredsphereandroid.models.LoanApplications;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private static final String TAG = "Home Fragment";
    private ArrayList<LoanApplications> applicationsList;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "ON HOME");
        recyclerView=view.findViewById(R.id.recyclerView);
        applicationsList = new ArrayList<>();
        setUserInfo();
        setAdapter();
        FloatingActionButton newApplicationBtn=view.findViewById(R.id.btn_new_application);

        newApplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),NewApplicationActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    private void setAdapter() {
        RecyclerAdapter adapter=new RecyclerAdapter(applicationsList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }
    private void setUserInfo() {
        applicationsList.add(new LoanApplications("Belinda","Viriri",276,"2 P/GAS STOVE","28/01/22"));
        applicationsList.add(new LoanApplications("Anotida","Magara",5476,"UNIVESAL 4 PLATE STOVE","04/03/22"));
        applicationsList.add(new LoanApplications("Pridemore","Rugara",576,"LENOVO LAPTOP","05/05/22"));
        applicationsList.add(new LoanApplications("Taonga","Tore",476,"SOLAR PANEL 265 WATTS","13/06/22"));
        applicationsList.add(new LoanApplications("Takudzwa","Magada",2767,"ITEL KIDS TABLET","06/06/22"));
        applicationsList.add(new LoanApplications("Tinashe","Meki",2785,"POWER BANK P52","05/03/22"));
        applicationsList.add(new LoanApplications("Anna","Mabari",4752,"DLIGHT D100","06/03/22"));
        applicationsList.add(new LoanApplications("Dereck","Marambanyika",236,"LENOVO TAB 7","07/04/22"));
        applicationsList.add(new LoanApplications("Tafadzwa","Tereki",5746,"ITEL A56","06/04/22"));
        applicationsList.add(new LoanApplications("Terence","Rugare",677,"ITEL A14","08/03/22"));
        applicationsList.add(new LoanApplications("Evah","Tinarwe",756,"SOLAR PANEL 265 WATTS","05/03/22"));
        applicationsList.add(new LoanApplications("Brenda","Ticharwa",2347,"2 P/GAS STOVE ONLY","23/04/22"));

    }
}