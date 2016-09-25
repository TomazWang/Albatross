package me.tomazwang.app.albatross;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TomazWang on 2016/9/24.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

    private final static int LIST_ICON_ID = R.drawable.list;

    private ArrayList<String> mTodoLists = new ArrayList<>();




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position > getRealItemCount()){
            // add Create new list button
            holder.listIcon().setVisibility(View.INVISIBLE);
            holder.listName().setText(R.string.creat_new_list);
        }


        String listName = getItem(position);
        holder.listIcon().setVisibility(View.VISIBLE);
        holder.listName().setText(listName);


    }

    public int getRealItemCount(){
        return mTodoLists.size();
    }

    public void removeItem(int index){
        mTodoLists.remove(index);
    }

    public void removeItem(String item){
        mTodoLists.remove(item);
    }

    public void addItem(String listName){
        this.mTodoLists.add(listName);
    }

    public String getItem(int position){
        return mTodoLists.get(position);
    }


    @Override
    public int getItemCount() {
        return mTodoLists.size() + 1; // +1 for "Create new list button"
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mListIcon;
        private TextView mListName;


        public ViewHolder(View itemView) {
            super(itemView);

            mListIcon = (ImageView) itemView.findViewById(R.id.list_icon);
            mListName = (TextView) itemView.findViewById(R.id.list_name);

        }


        public ImageView listIcon() {
            return mListIcon;
        }


        public TextView listName() {
            return mListName;
        }
    }
}
