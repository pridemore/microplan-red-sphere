package com.example.microplanredsphereandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microplanredsphereandroid.adapter.RecyclerAdapter;
import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private static final String TAG = "Home Fragment";
    private ArrayList<LoanApplicationModel> applicationsList;
    private RecyclerView recyclerView;
    TextView textTotalApplications, textSyncedToServer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "ON HOME");

        //instantiating views
        recyclerView = view.findViewById(R.id.recyclerView);
        textSyncedToServer = view.findViewById(R.id.textSyncedToServer);
        textTotalApplications = view.findViewById(R.id.textTotalApplications);

        applicationsList = new ArrayList<>();
        setLoanApplicationList();
        setAdapter();
        FloatingActionButton newApplicationBtn = view.findViewById(R.id.btn_new_application);

        newApplicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewApplicationActivity.class);
                startActivity(intent);
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

    private void setLoanApplicationList() {
        final List<LoanApplicationModel> toBeSyncedModelList = Utils.getSavedLoans(getContext());
        final List<LoanApplicationModel> yourHistoryModellList = Utils.getSavedLoansFromDb(getContext());

        for (LoanApplicationModel loanApplication : yourHistoryModellList
        ) {
            applicationsList.add(loanApplication);
        }

        textTotalApplications.setText(String.valueOf(yourHistoryModellList.size()));
        textSyncedToServer.setText(String.valueOf(toBeSyncedModelList.size()));
//        applicationsList.add(new LoanApplications("Belinda","Viriri",276,"2 P/GAS STOVE","28/01/22"));
//        applicationsList.add(new LoanApplications("Anotida","Magara",5476,"UNIVESAL 4 PLATE STOVE","04/03/22"));
//        applicationsList.add(new LoanApplications("Pridemore","Rugara",576,"LENOVO LAPTOP","05/05/22"));
//        applicationsList.add(new LoanApplications("Taonga","Tore",476,"SOLAR PANEL 265 WATTS","13/06/22"));
//        applicationsList.add(new LoanApplications("Takudzwa","Magada",2767,"ITEL KIDS TABLET","06/06/22"));
//        applicationsList.add(new LoanApplications("Tinashe","Meki",2785,"POWER BANK P52","05/03/22"));
//        applicationsList.add(new LoanApplications("Anna","Mabari",4752,"DLIGHT D100","06/03/22"));
//        applicationsList.add(new LoanApplications("Dereck","Marambanyika",236,"LENOVO TAB 7","07/04/22"));
//        applicationsList.add(new LoanApplications("Tafadzwa","Tereki",5746,"ITEL A56","06/04/22"));
//        applicationsList.add(new LoanApplications("Terence","Rugare",677,"ITEL A14","08/03/22"));
//        applicationsList.add(new LoanApplications("Evah","Tinarwe",756,"SOLAR PANEL 265 WATTS","05/03/22"));
//        applicationsList.add(new LoanApplications("Brenda","Ticharwa",2347,"2 P/GAS STOVE ONLY","23/04/22"));

    }
}