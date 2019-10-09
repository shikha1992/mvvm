package com.kemistree.mvvmdemo.view.ui.dashboard;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kemistree.mvvmdemo.R;
import com.kemistree.mvvmdemo.view.BottomAct;
import com.kemistree.mvvmdemo.view.MainActivity;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView( @NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState ) {

        FragmentActivity frag = this.getActivity();
        this.dashboardViewModel = (DashboardViewModel)ViewModelProviders.of(frag).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final EditText textView = root.findViewById(R.id.edt_txt);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged( CharSequence charSequence, int i, int i1, int i2 ) {

            }

            @Override
            public void onTextChanged( CharSequence charSequence, int i, int i1, int i2 ) {

                dashboardViewModel.setText(textView.getText().toString());
            }

            @Override
            public void afterTextChanged( Editable editable ) {

            }
        });

        return root;
    }
}