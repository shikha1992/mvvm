package com.kemistree.mvvmdemo.view.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.kemistree.mvvmdemo.R;
import com.kemistree.mvvmdemo.model.People;
import com.kemistree.mvvmdemo.view.adapter.PeopleAdapter;
import com.kemistree.mvvmdemo.view.ui.notifications.NotificationsViewModel;

import java.util.List;


public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
    private PeopleAdapter peopleAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager  layoutManager;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 3;


    public View onCreateView( @NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState ) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        homeViewModel.getText().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged( @Nullable List<People> s ) {
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                peopleAdapter = new PeopleAdapter();
                peopleAdapter.setPeopleList(s);
                recyclerView.setAdapter(peopleAdapter);
                recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

            }
        });

        return root;
    }
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 30) {
                    homeViewModel.api_setdata();
                }
            }
        }
    };
}