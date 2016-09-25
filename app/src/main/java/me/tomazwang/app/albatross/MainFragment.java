package me.tomazwang.app.albatross;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by TomazWang on 2016/9/24.
 */

public class MainFragment extends Fragment{

    private ArrayList<String> mTodoListlist = new ArrayList<>();


    // widgets
    private RecyclerView mRecyclerView;
    private TodoListAdapter mListAdapter;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupData();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        // TODO: get widget.
        mRecyclerView = (RecyclerView)view.findViewById(R.id.list_lists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mListAdapter = new TodoListAdapter();
        mRecyclerView.setAdapter(mListAdapter);

        return view;
    }




    private void setupData() {

        // TODO: replace dummy data
        String[] dummyTodolists = new String[]{
                "Indox",
                "House Keeping",
                "Homework",
                "Office work",
                "Things to do",
                "Wish Bucket",
                "List 1",
                "List 2",
                "List 3",
                "List 4",
                "List 5",
                "List 6"

        };

        mTodoListlist.addAll(Arrays.asList(dummyTodolists));


    }

}
