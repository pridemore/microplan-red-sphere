package com.example.microplanredsphereandroid;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.microplanredsphereandroid.adapter.ProductsAdapter;
import com.example.microplanredsphereandroid.models.LoanApplicationModel;
import com.example.microplanredsphereandroid.models.Product;
import com.example.microplanredsphereandroid.models.ProductEntry;
import com.example.microplanredsphereandroid.utils.Utils;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Products extends Fragment {
    private RecyclerView recycler;
    private LoanApplicationModel model;
    private View view;
    TextView title;
    private EditText editTextTopup;
    Button btn_next, btn_prev;
    private static final String TAG = "Products";

    public static Products newInstance() {
        return new Products();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_products, container, false);
        model = Utils.getApplicationModel(requireContext());

        //instantiating views
        editTextTopup = view.findViewById(R.id.editTextTopup);
        btn_next = view.findViewById(R.id.btn_nxt);
        btn_prev = view.findViewById(R.id.btn_previous);
        recycler = view.findViewById(R.id.recycler);
        title = view.findViewById(R.id.title);

        //setting static text
        title.setText("Product");
        ArrayList<ProductEntry> products = new ArrayList<>(Arrays.asList(
                new ProductEntry(new Product("ITEL LAPTOP", 120600.00)),
                new ProductEntry(new Product("UNIVESAL 4 PLATE STOVE", 64800.00)),
                new ProductEntry(new Product("2 P/GAS STOVE (5kg tank)", 22860.00)),
                new ProductEntry(new Product("2 P/GAS STOVE (3KG TANK)", 21060.00)),
                new ProductEntry(new Product("2 P/GAS STOVE ONLY", 13860.00)),
                new ProductEntry(new Product("5KG GAS TANK ONLY", 9000.00)),
                new ProductEntry(new Product("19KG TANK", 25200.00)),
                new ProductEntry(new Product("9KG TANK", 18000.00)),
                new ProductEntry(new Product("3KG TANK", 7200.00)),
                new ProductEntry(new Product("OPEN VIEW DECODER", 17280.00)),
                new ProductEntry(new Product("SOUND SYSTEMS", 43200.00)),
                new ProductEntry(new Product("ITEL A16 PLUS", 14220.00)),
                new ProductEntry(new Product("ITEL P37", 28550.00)),
                new ProductEntry(new Product("SAMSUNG M02", 46800.00)),
                new ProductEntry(new Product("ITEL A56", 21960.00)),
                new ProductEntry(new Product("ITEL A14", 11880.00)),
                new ProductEntry(new Product("ITEL A56 PRO", 25670.00)),
                new ProductEntry(new Product("BOOM", 14400.00)),
                new ProductEntry(new Product("D LIGHT 200", 8280.00)),
                new ProductEntry(new Product("DLIGHT S3 X 3", 9940.00)),
                new ProductEntry(new Product("DLIGHT S3 X 1", 3315.00)),
                new ProductEntry(new Product("DLIGHT D100", 27000.00)),
                new ProductEntry(new Product("ITEL A37", 19440.00)),
                new ProductEntry(new Product("POWER BANK P52", 4680.00)),
                new ProductEntry(new Product("ITEL S16", 24660.00)),
                new ProductEntry(new Product("TOSHIBA 32'' TV", 68400.00)),
                new ProductEntry(new Product("CREATIVE 42'' TV", 97200.00)),
                new ProductEntry(new Product("ITEL SPEAKER", 5400.00)),
                new ProductEntry(new Product("ORAIMO SPEAKER", 10800.00)),
                new ProductEntry(new Product("WIRELESS EARPHONES", 6480.00)),
                new ProductEntry(new Product("LENOVO TAB 7", 50400.00)),
                new ProductEntry(new Product("LENOVO LAPTOP", 135000.00)),
                new ProductEntry(new Product("ITEL KIDS TABLET", 25920.00)),
                new ProductEntry(new Product("SOLAR PANEL 265 WATTS", 28800.00)),
                new ProductEntry(new Product("SOLAR PANEL 330 WATTS", 48600.00)),
                new ProductEntry(new Product("S-PANEL 265W + DELIVERY", 31680.00)),
                new ProductEntry(new Product("S-PANEL 330W + DELIVERY", 51480.00)),
                new ProductEntry(new Product("KDV KING BED", 154980.00)),
                new ProductEntry(new Product("KDV QUEEN BED", 129600.00)),
                new ProductEntry(new Product("KDV DOUBLE BED", 85320.00)),
                new ProductEntry(new Product("FLORIDA DOUBLE BED", 85320.00)),
                new ProductEntry(new Product("FLORIDA QUEEN BED", 96480.00)),
                new ProductEntry(new Product("FLORIDA KING BED", 123230.00)),
                new ProductEntry(new Product("PICO 100 X3", 10800.00)),
                new ProductEntry(new Product("PICO PLUS X3", 8640.00)),
                new ProductEntry(new Product("SUNKING CHARGE", 7200.00)),
                new ProductEntry(new Product("SUNKING FAN", 30600.00)),
                new ProductEntry(new Product("SUNKING PRO 200", 7200.00)),
                new ProductEntry(new Product("SUNKING PRO 300", 10800.00)),
                new ProductEntry(new Product("SUNKING PRO 400", 11520.00)),
                new ProductEntry(new Product("TECNO POP 2F", 23400.00)),
                new ProductEntry(new Product("TECNO POP 3", 23400.00)),
                new ProductEntry(new Product("TECNO POP 5 16 GB", 24750.00)),
                new ProductEntry(new Product("TECNO POP 5 32 GB", 30960.00)),
                new ProductEntry(new Product("TECNIO POP 5PRO", 45720.00)),
                new ProductEntry(new Product("TECNO POP 4 PRO", 32400.00)),
                new ProductEntry(new Product("TECNO SPARK 7", 45720.00)),
                new ProductEntry(new Product("TECNO SPARK 7 PRO", 48600.00)),
                new ProductEntry(new Product("TECNO CANOM 17", 61920.00)),
                new ProductEntry(new Product("TECNO CANOM 17 PRO", 75600.00)),
                new ProductEntry(new Product("CANOM 15", 57600.00)),
                new ProductEntry(new Product("TECNO POVOIUR", 66600.00))
        ));

        if (model.products == null || model.products.isEmpty()) {
            model.products = products;
        }
        Collections.sort(model.products);
        recycler.setAdapter(new ProductsAdapter(model.products));

        //Buttons logic
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificationError verificationError = productFormValidation();
                if (verificationError == null) {
                    editTextTopup.setText("" + model.topUp);
                    Log.d(TAG, "Top up value-------------" + model.topUp);
                    ((NewApplicationActivity) getActivity()).replaceFragment(new LoanDetailsFragment());
                } else {
                    String errorMessage = verificationError.getErrorMessage();
                    Toast.makeText((NewApplicationActivity) getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NewApplicationActivity) getActivity()).replaceFragment(new NewApplicationFragment());
            }
        });



        return view;
    }

    private VerificationError productFormValidation() {
        try {
            boolean isProductsSelected = model.products != null && !model.products.isEmpty();
            if (isProductsSelected) {
                isProductsSelected = false;
                for (ProductEntry entry : model.products) {
                    if (entry.getQuantity() > 0) {
                        isProductsSelected = true;
                        break;
                    }
                }
            }
            boolean isTopupSelected = editTextTopup.getText().length() != 0 && Double.parseDouble(editTextTopup.getText().toString()) != 0;
            if (!isProductsSelected && !isTopupSelected) {
                return new VerificationError("Please select at least one product");
            } else if (isTopupSelected && isProductsSelected) {
                return new VerificationError("You cant select both top-up and products");
            }
            model.topUp = Double.parseDouble(editTextTopup.getText().toString());
            model.agent_id = Utils.getUserModel(getContext()).getId();
            Utils.saveApplicationModel(requireContext(), model);
        } catch (Exception e) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error")
                    .setMessage(e.getMessage())
                    .show();
        }
        return null;
    }
}