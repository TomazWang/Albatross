package me.tomazwang.app.albatross.data;

import android.provider.BaseColumns;

/**
 * Created by TomazWang on 2016/9/28.
 */

public class DBContract {

    public static final class TodoListEntry implements BaseColumns{
        public static final String TABLE_NAME = "TABLE_TODO_LIST";

        public static final String COL_LIST_NAME = "COL_LIST_NAME";

        public static final String COL_CREATE_DATE = "COL_CREATE_DATE";

        public static final String COL_FOLDER_KEY = "COL_FOLDER_KEY";
    }


    public static final class TaskEntry implements BaseColumns{

        public static final String TABLE_NAME = "TABLE_TASK";

        public static final String COL_LIST_KEY = "COL_LIST_KEY";

        public static final String COL_TASK_NAME = "COL_TASK_NAME";

        public static final String COL_CREATE_DATE = "COL_CREATE_DATE";

        public static final String COL_IS_CHECKED = "COL_IS_CHECKED";

    }


    public static final class NoteEntry implements BaseColumns{

        public static final String TABLE_NAME = "TABLE_NOTE";

        public static final String COL_TASK_KEY = "COL_TASK_KEY";

        public static final String COL_NOTE_CONTEXT = "COL_NOTE_CONTEXT";
        public static final String COL_LAST_EDIT_DATE = "COL_LAST_EDIT_DATE";
    }


}
