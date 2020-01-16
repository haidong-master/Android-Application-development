package com.example.fragmenttest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AboveFragment extends Fragment {
	private static final String TAG = "AboveFragment";
	public TextView mTextview1;
	public Button   mBtn;
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView called.");
		View v = inflater.inflate(R.layout.above_fragment, container, false); 
    	mTextview1 = (TextView)v.findViewById(R.id.tv1);
    
    	return v;
	} 
	@Override
	public void onAttach(Activity activity) {
    	super.onAttach(activity);
    	Log.d(TAG, "onAttach called.");
    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate called.");
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d(TAG, "onActivityCreated called.");
	}
    @Override
	public void onStart() {
    	super.onStart();
    	Log.d(TAG, "onStart called.");
    }
   
    
    @Override
    public void onResume() {
    	super.onResume();
    	Log.d(TAG, "onResume called.");
    }

    @Override
    public void onPause() {
    	super.onPause();
    	Log.d(TAG, "onPause called.");
    }
    
    @Override
    public void onStop() {
    	super.onStop();
    	Log.d(TAG, "onStop called.");	
    }
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	Log.d(TAG, "onDestory called.");
    }
    @Override
    public void onDestroyView() {
    	super.onDestroyView();
    	Log.d(TAG, "onDestroyView called.");
    }
    @Override
    public void onDetach() {
    	super.onDetach();
    	Log.d(TAG, "onDetach called.");
    }
}
