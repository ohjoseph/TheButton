package com.example.josephoh.thebutton;

import java.util.ArrayList;

/**
 * Created by josephoh on 2/25/17.
 */

public class Group {

    private String mName;
    private ArrayList<String> mMembers;
    private ArrayList<TheButton> mButtons;

    public Group(String name, ArrayList<String> members, ArrayList<TheButton> buttons) {
        mName = name;
        mMembers = members;
        mButtons = buttons;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public ArrayList<String> getMembers() {
        return mMembers;
    }

    public void setMembers(ArrayList<String> members) {
        mMembers = members;
    }

    public ArrayList<TheButton> getButtons() {
        return mButtons;
    }

    public void setButtons(ArrayList<TheButton> buttons) {
        mButtons = buttons;
    }
}
