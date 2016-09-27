package me.tomazwang.app.albatross;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by TomazWang on 2016/9/27.
 */

public class TaskAdpter extends RecyclerView.Adapter<TaskAdpter.ViewHolder> {

    private final TaskFunciton mListener;
    ArrayList<String> mTaskList = new ArrayList<String>();

    public TaskAdpter(ArrayList<String> mTaskList, TaskFunciton mListener) {
        this.mTaskList = mTaskList;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_task, parent, false);

        // TODO: add on click

        return new ViewHolder(view, new ViewHolder.OnClickListener() {
            @Override
            public void onClick(View itemView, int position) {

                // TODO: replace dummy task id.
                mListener.enterTask(0);
            }

            @Override
            public void onCheck(View itemView, View checkBox, int position) {

                boolean isChecked = ((CheckBox) checkBox).isChecked();


                // TODO: replace dummy task id.
                mListener.checkTask(0, isChecked);


            }

            @Override
            public void onLabelClick(View itemView, View labelIconView, int position) {


                //TODO: replace dummy task id.
                mListener.labelTask(0);

            }
        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String taskName = mTaskList.get(position);

        holder.getTaskNameView().setText(taskName);


    }


    @Override
    public int getItemCount() {
        return 0;
    }


    static interface TaskFunciton {

        void checkTask(int taskId, boolean isChecked);

        void labelTask(int taskId);

        void editTask(int taskId);

        void enterTask(int taskId);

    }


    static public class ViewHolder extends RecyclerView.ViewHolder {


        private final OnClickListener mListener;
        CheckBox mCheckBox;
        ImageView mLabelIcon;
        TextView mTaskNameView;

        public ViewHolder(View itemView, OnClickListener listener) {
            super(itemView);

            mListener = listener;

            mCheckBox = (CheckBox) itemView.findViewById(R.id.task_checkBox);
            mLabelIcon = (ImageView) itemView.findViewById(R.id.task_label);
            mTaskNameView = (TextView) itemView.findViewById(R.id.task_name);


            itemView.setOnClickListener(v -> mListener.onClick(v, getLayoutPosition()));
            mCheckBox.setOnClickListener(v -> mListener.onCheck(itemView, mCheckBox, getLayoutPosition()));
            mLabelIcon.setOnClickListener(v -> mListener.onLabelClick(itemView, mLabelIcon, getLayoutPosition()));

        }

        public View getItemView() {
            return super.itemView;
        }

        public CheckBox getCheckBox() {
            return mCheckBox;
        }

        public ImageView getLabelIcon() {
            return mLabelIcon;
        }

        public TextView getTaskNameView() {
            return mTaskNameView;
        }


        interface OnClickListener {
            void onClick(View itemView, int position);

            void onCheck(View itemView, View checkBox, int position);

            void onLabelClick(View itemView, View labelIconView, int position);
        }

    }
}
