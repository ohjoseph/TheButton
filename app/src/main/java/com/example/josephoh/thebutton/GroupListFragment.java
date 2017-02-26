package com.example.josephoh.thebutton;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GroupListFragment extends Fragment {

    private RecyclerView mGroupRecyclerView;
    private GroupAdapter mGroupAdapter;
    private TextView mEmptyView;

    public GroupListFragment() {
        // Required empty public constructor
    }

    public static GroupListFragment newInstance(String param1, String param2) {
        GroupListFragment fragment = new GroupListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Set argument parameters
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_group_list, container, false);
        mEmptyView = (TextView) v.findViewById(R.id.group_list_emptyView);

        // Initialize RecyclerView
        mGroupRecyclerView = (RecyclerView) v.findViewById(R.id.group_list_recyclerView);
        mGroupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return v;
    }

    /*******
     * RecyclerView Classes for Group List
     ********/
    private class GroupAdapter extends RecyclerView.Adapter<GroupHolder> {
        private List<Group> mGroupList;
        private int num;

        public GroupAdapter(List<Group> groups) {
            mGroupList = groups;
        }

        @Override
        public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.list_item_group, parent, false);
            num++;
            return new GroupHolder(v);
        }

        @Override
        public void onBindViewHolder(GroupHolder holder, int position) {
            Group group = mGroupList.get(position);
            holder.bindTimeHolder(group);
        }

        @Override
        public int getItemCount() {
            return mGroupList.size();
        }

        public void setGroups(List<Group> groups) {
            mGroupList = groups;
            notifyDataSetChanged();
        }
    }

    private class GroupHolder extends RecyclerView.ViewHolder {
        private Group mGroup;
        private TextView mGroupNameTextView;
        private Button mAddToGroupButton;
        private RecyclerView mGroupButtonsRecyclerView;
        private GroupButtonAdapter mGroupButtonAdapter;

        public GroupHolder(View itemView) {
            super(itemView);

            // Find views in the group holder
            mGroupNameTextView =
                    (TextView) itemView.findViewById(R.id.group_list_name);
            mAddToGroupButton =
                    (Button) itemView.findViewById(R.id.group_list_new_button);
            mGroupButtonsRecyclerView =
                    (RecyclerView) itemView.findViewById(R.id.group_button_recyclerView);
        }

        public void bindTimeHolder(Group group) {
            mGroup = group;
            mGroupNameTextView.setText(group.getName());
            mAddToGroupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle clicks
                    Toast.makeText(getActivity(),
                            "Add Button to " + mGroup.getName(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });

            // Initialize recyclerview of buttons for each group
            mGroupButtonsRecyclerView.setLayoutManager(new LinearLayoutManager(
                    getActivity(), LinearLayoutManager.HORIZONTAL, false));
            if (mGroupButtonAdapter == null) {
                mGroupButtonAdapter = new GroupButtonAdapter(group.getButtons());
            } else {
                mGroupButtonAdapter.setTheButtons(group.getButtons());
                mGroupButtonAdapter.notifyDataSetChanged();
            }
            mGroupButtonsRecyclerView.setAdapter(mGroupButtonAdapter);
        }
    }

    /************
     * RecyclerView classes for Button List for each Group
     *************/

    private class GroupButtonAdapter extends RecyclerView.Adapter<GroupButtonHolder> {
        private ArrayList<TheButton> mTheButtons;

        public GroupButtonAdapter(ArrayList<TheButton> theButtons) {
            mTheButtons = theButtons;
        }

        @Override
        public GroupButtonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.list_item_group_button, parent, false);
            return new GroupButtonHolder(v);
        }

        @Override
        public void onBindViewHolder(GroupButtonHolder holder, int position) {
            TheButton theButton = mTheButtons.get(position);
            holder.bindGroupHolder(theButton);
        }

        @Override
        public int getItemCount() {
            return mTheButtons.size();
        }

        public void setTheButtons(ArrayList<TheButton> buttons) {
            mTheButtons = buttons;
        }
    }

    private class GroupButtonHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TheButton mTheButton;
        private TextView mTitleTextView;
        private TextView mSubtitleTextView;

        public GroupButtonHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            // Store references to each view
            mTitleTextView =
                    (TextView) itemView.findViewById(R.id.group_list_button_title_textView);
            mSubtitleTextView =
                    (TextView) itemView.findViewById(R.id.group_list_button_subtitle_textView);
        }

        @Override
        public void onClick(View v) {
            // Handle clicks on the holder
            Toast.makeText(
                    getActivity(), mTheButton.getName() + " clicked!", Toast.LENGTH_SHORT).show();
        }

        public void bindGroupHolder(TheButton button) {
            // Set the textviews with specific button's information
            mTheButton = button;
            mTitleTextView.setText(button.getName());
        }
    }

    /**
     * Helper Classes
     **/

    public void updateUI() {
        mEmptyView.setVisibility(View.INVISIBLE);

        // Populate list of groups
        List<Group> groups = Database.get(getActivity()).getGroups();
        if (mGroupAdapter == null) {
            mGroupAdapter = new GroupAdapter(groups);
        } else {
            mGroupAdapter.setGroups(groups);
        }

        mGroupRecyclerView.setAdapter(mGroupAdapter);
    }
}
