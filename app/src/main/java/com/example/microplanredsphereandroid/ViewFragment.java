package com.example.microplanredsphereandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
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


public class ViewFragment extends Fragment {
    private ImageView backIcon, menu;
    private TextView title;
    private Button btn_previous,btn_refresh;
    private ArrayList<LoanApplicationModel> applicationsList;
    private RecyclerView recyclerView;
    private List<LoanApplicationModel> modelList;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        //modelList = Utils.getSavedLoans(getContext());
        modelList=Utils.getSavedLoansFromDb(getContext());

        //instantiating views
        backIcon = view.findViewById(R.id.left_icon);
        menu = view.findViewById(R.id.right_icon);
        title = view.findViewById(R.id.title);
        btn_previous = view.findViewById(R.id.btn_previous);
        recyclerView = view.findViewById(R.id.recyclerView);
        btn_refresh=view.findViewById(R.id.btn_refresh);
        applicationsList = new ArrayList<>();

        //setting static text
        title.setText("View Applications");

        //Buttons Logic
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

        btn_refresh.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               //dialog for showing processing
                                               ProgressDialog progressDialog = new ProgressDialog(requireActivity());
                                               progressDialog.setMessage("Refreshing Applications. Please wait...");
                                               progressDialog.show();
                                               Thread thread = new Thread(() -> {
                                                   Utils.loadLoanHistoryFromBackend(getContext());
                                               });
                                               thread.start();

                                               new Handler().postDelayed(() -> {
                                                   getActivity().getSupportFragmentManager().beginTransaction().detach(ViewFragment.this).attach(ViewFragment.this).commit();
                                                   progressDialog.dismiss();

                                               }, 3000);

                                           }
                                       });

        //load data to our arraylist
        setViewLoanApplicationsList();

        //load recycler view with applications list
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

    private void setViewLoanApplicationsList() {

        for (LoanApplicationModel loanApplication:modelList
        ) {
            applicationsList.add(loanApplication);
        }
//        applicationsList.add(new LoanApplications("Belinda", "Viriri", 800, "ITEL LAPTOP", "28/01/22"));
//        applicationsList.add(new LoanApplications("Anotida", "Magara", 400, "SAMSUNG M02", "04/03/22"));
//        applicationsList.add(new LoanApplications("Pridemore", "Rugara", 500, "ITEL P37", "05/05/22"));
//        applicationsList.add(new LoanApplications("Taonga", "Tore", 600, "D LIGHT 200", "13/06/22"));
//        applicationsList.add(new LoanApplications("Takudzwa", "Magada", 8300, "DLIGHT D100", "06/06/22"));
//        applicationsList.add(new LoanApplications("Tinashe", "Meki", 8040, "ITEL SPEAKER", "05/03/22"));
//        applicationsList.add(new LoanApplications("Anna", "Mabari", 600, "CREATIVE 42'' TV", "06/03/22"));
//        applicationsList.add(new LoanApplications("Dereck", "Marambanyika", 100, "ORAIMO SPEAKER", "07/04/22"));
//        applicationsList.add(new LoanApplications("Tafadzwa", "Tereki", 900, "LENOVO TAB 7", "06/04/22"));
//        applicationsList.add(new LoanApplications("Terence", "Rugare", 2000, "ITEL KIDS TABLE", "08/03/22"));
//        applicationsList.add(new LoanApplications("Evah", "Tinarwe", 1000, "ITEL KIDS TABLET", "05/03/22"));
//        applicationsList.add(new LoanApplications("Brenda", "Ticharwa", 400, "SOUND SYSTEMS", "23/04/22"));

    }
}