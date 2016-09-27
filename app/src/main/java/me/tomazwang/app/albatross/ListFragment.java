package me.tomazwang.app.albatross;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements TaskAdpter.TaskFunciton {

    // the fragment initialization parameters.
    private static final String LIST_ID = "listId";

    private int mListID;

    private OnInteractionListener mListener;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mDataSet;
    private TaskAdpter mTaskAdpter;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(int listId) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(LIST_ID, listId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mListID = getArguments().getInt(LIST_ID);

            initDataSet();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.list_task);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        this.mTaskAdpter = new TaskAdpter(mDataSet, this);





        // TODO: get widgets

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInteractionListener) {
            mListener = (OnInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initDataSet() {
        // TODO: replace dummy data

        String[] dummy_tasks = new String[]{
                "task1",
                "task2",
                "task3",
                "task4",
                "task5",
                "task6",
                "task7",
                "task8",
                "task9",
                "task10",
                "task11",
                "task12"
        };


        this.mDataSet.addAll(Arrays.asList(dummy_tasks));

    }

    @Override
    public void checkTask(int taskId, boolean isChecked) {
        Log.d(TAG, "checkTask: "+taskId);
    }

    @Override
    public void labelTask(int taskId) {
        Log.d(TAG, "labelTask: "+taskId);
    }

    @Override
    public void editTask(int taskId) {
        Log.d(TAG, "editTask: "+taskId);
    }

    @Override
    public void enterTask(int taskId) {
        Log.d(TAG, "enterTask: "+taskId);
        mListener.enterTask(taskId);
    }


    public interface OnInteractionListener {
        void enterTask(int taskId);
    }
}
