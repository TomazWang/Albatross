package me.tomazwang.app.albatross;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by TomazWang on 2016/9/24.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

    private final static int LIST_ICON_ID = R.drawable.list;

    @IntDef({
            ViewType.NORMAL_LIST,
            ViewType.CREATE_NEW_LIST
    })


    public @interface ViewType {
        int NORMAL_LIST = 1;
        int CREATE_NEW_LIST = 2;
    }


    private ArrayList<String> mTodoLists = new ArrayList<>();
    private ListFunction mListFunction;

    public TodoListAdapter(ArrayList<String> mTodoLists, ListFunction listFunction) {
        this.mTodoLists = mTodoLists;
        this.mListFunction = listFunction;
    }

    @Override
    public int getItemViewType(int position) {

        if (position >= getRealItemCount()) {
            // last item
            return ViewType.CREATE_NEW_LIST;
        }

        return ViewType.NORMAL_LIST;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ViewType.CREATE_NEW_LIST) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_new_list, parent, false);
            TodoListAdapter.ViewHolder holder = new TodoListAdapter.ViewHolder(view, new ViewHolder.TodoListOnItemClick() {
                @Override
                public void onClick(View v, int position) {
                    mListFunction.createNewList();
                }

                @Override
                public void onIconClick(View v, View iconView, int position) {
                    mListFunction.createNewList();
                }

                @Override
                public void onLongClick(View v, int layoutPosition) {
                    onClick(v, layoutPosition);
                }

            });
            return holder;

        } else {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            TodoListAdapter.ViewHolder holder = new TodoListAdapter.ViewHolder(view, new ViewHolder.TodoListOnItemClick() {
                @Override
                public void onClick(View v, int position) {
                    // TODO: replace dummy list id.
                    mListFunction.enterList(0);
                }

                @Override
                public void onIconClick(View v, View iconView, int position) {
                    // TODO: replace dummy list id.
                    mListFunction.enterList(0);
                }

                @Override
                public void onLongClick(View v, int layoutPosition) {
                    // TODO: replace dummy list id.
                    mListFunction.editList(0);
                }
            });

            return holder;
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: position = " + position + ", item count = " + getRealItemCount());

        if (position >= getRealItemCount()) {
            return;
        }


        String listName = mTodoLists.get(position);
        holder.icon().setVisibility(View.VISIBLE);
        holder.listName().setText(listName);
    }

    public int getRealItemCount() {
        return mTodoLists.size();
    }


    @Override
    public int getItemCount() {
        return mTodoLists.size() + 1; // +1 for "Create new list button"
    }

    interface ListFunction {
        void enterList(int listId);

        void createNewList();

        void editList(int listId);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TodoListOnItemClick mListener;
        private ImageView mIcon;
        private TextView mListName;


        public ViewHolder(View itemView, TodoListOnItemClick listener) {
            super(itemView);

            mListener = listener;
            mIcon = (ImageView) itemView.findViewById(R.id.list_icon);
            mListName = (TextView) itemView.findViewById(R.id.list_name);

            itemView.setOnClickListener(v -> mListener.onClick(v, getLayoutPosition()));
            itemView.setOnLongClickListener(v -> {
                mListener.onLongClick(v, getLayoutPosition());
                return true;
            });
            mIcon.setOnClickListener(v -> mListener.onIconClick(itemView, v, getLayoutPosition()));


        }

        public View view() {
            return super.itemView;
        }

        public ImageView icon() {
            return mIcon;
        }

        public TextView listName() {
            return mListName;
        }


        interface TodoListOnItemClick {

            void onClick(View v, int position);

            void onIconClick(View v, View iconView, int position);

            void onLongClick(View v, int layoutPosition);
        }

    }


}
