package com.example.sidebar.ui.zadanie3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.sidebar.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Zadanie3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Zadanie3Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Zadanie3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Zadanie3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Zadanie3Fragment newInstance(String param1, String param2) {
        Zadanie3Fragment fragment = new Zadanie3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ListView listView;
    private ArrayList<String> itemList;
    private CustomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_zadanie3, container, false);

        Button addButton = rootView.findViewById(R.id.listAddButton);
        EditText editText = rootView.findViewById(R.id.editTextList);
        ListView listView = rootView.findViewById(R.id.listView);

        itemList = new ArrayList<>();
        adapter = new CustomAdapter(itemList);
        listView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 1) {
                    addButton.setEnabled(true);
                    addButton.setAlpha(1f);
                }
                else {
                    addButton.setEnabled(false);
                    addButton.setAlpha(0.5f);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                if (!input.isEmpty()) {
                    itemList.add(input);
                    adapter.notifyDataSetChanged();
                    editText.setText(""); // Clear input field
                }

            }
        });


        return rootView;


    }
    private class CustomAdapter extends BaseAdapter {
        private ArrayList<String> items;

        public CustomAdapter(ArrayList<String> items) {
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(requireContext()).inflate(R.layout.list_element, parent, false);
            }

            TextView itemText = convertView.findViewById(R.id.itemText);
            TextView removeItem = convertView.findViewById(R.id.removeItem);

            itemText.setText(items.get(position));

            removeItem.setOnClickListener(v -> {
                items.remove(position);
                notifyDataSetChanged();
            });

            return convertView;
        }
    }
}