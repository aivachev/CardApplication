package com.example.andrew.cardapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.andrew.cardapplication.AsyncTasks.AsyncTask;
import com.example.andrew.cardapplication.Adapter.CustomAdapter;
import com.example.andrew.cardapplication.Models.Common;
import com.example.andrew.cardapplication.Models.ItemObject;
import com.example.andrew.cardapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Andrew on 09.10.2017.
 */

public class CardsFragment extends Fragment {
    CardView mCardView;
    AsyncTask myAsyncTask;

    public static CardsFragment newInstance() {
        CardsFragment fragment = new CardsFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    public CardsFragment() {
        // singleton
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_card, container, false);
        GridView gridview = (GridView)view.findViewById(R.id.gridview);

        try {
            List<ItemObject> allItems = getAllItemObject();
            CustomAdapter customAdapter = new CustomAdapter(getActivity(), allItems);
            gridview.setAdapter(customAdapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private List<ItemObject> getAllItemObject() throws InterruptedException, ExecutionException{
        List<ItemObject> items = new ArrayList<>();
        LoadAsyncTask();
        Common lst = myAsyncTask.get();
        items.add(new ItemObject("IP - " + lst.getIp().getIp(),null, null));
        items.add(new ItemObject("date - " + lst.getDateTime().getDate(),
                "milliseconds_since_epoch - " + lst.getDateTime().getMilliseconds(),
                "time - " + lst.getDateTime().getTime()));
        items.add(new ItemObject("X-Cloud-Trace-Context - " + lst.getHeaders().getCloud(),
                "Host -  " + lst.getHeaders().getHost(), "User-Agent - " + lst.getHeaders().getAgent()));
        items.add(new ItemObject(null,null,null));
        items.add(new ItemObject(null,null,null));
        return items;
    }

    private void LoadAsyncTask() {
        myAsyncTask = (AsyncTask) getActivity().getLastNonConfigurationInstance();
        if (myAsyncTask == null) {
            myAsyncTask = new AsyncTask(getActivity());
        } else {
            myAsyncTask.setContext(getActivity());
        }
        myAsyncTask.execute();
    }
}
