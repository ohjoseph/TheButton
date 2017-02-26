package com.example.josephoh.thebutton;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by josephoh on 2/25/17.
 */

public class Database {

    private static Database sDatabase;
    private Context mContext;
    private ArrayList<Group> mGroups;

    public static Database get(Context context) {
        if (sDatabase == null) {
            sDatabase = new Database(context);
        }

        return sDatabase;
    }

    private Database(Context context) {
        mContext = context;

        mGroups = new ArrayList<>();
        for (int i=1; i<4; i++) {
            ArrayList<TheButton> buttons = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            for (int j=1; j<6; j++) {
                buttons.add(new TheButton("Button " + j));
                names.add("Member " + j);
            }

            Group group = new Group("Group " + i, names, buttons);
            group.setButtons(buttons);
            group.setMembers(names);
            mGroups.add(group);
        }
    }

    public ArrayList<Group> getGroups() {
        return mGroups;
    }
}
