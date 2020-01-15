package com.example.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BelowFragment extends Fragment {
	public TextView mTextview1;
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View v = inflater.inflate(R.layout.below_fragment, container, false); 
    	mTextview1 = (TextView)v.findViewById(R.id.tv1);
    	return v;
	} 
}
