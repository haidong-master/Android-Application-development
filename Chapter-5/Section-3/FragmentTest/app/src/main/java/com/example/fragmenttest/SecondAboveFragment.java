package com.example.fragmenttest;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SecondAboveFragment extends Fragment {
	public TextView mTextview1;
	public Button   mBtn;
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View v = inflater.inflate(R.layout.second_above_fragment, container, false); 
    	mTextview1 = (TextView)v.findViewById(R.id.tv1);  
    	return v;
	} 
}
