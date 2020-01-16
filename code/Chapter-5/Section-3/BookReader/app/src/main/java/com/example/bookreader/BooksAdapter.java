package com.example.bookreader;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//public class BooksAdapter extends ArrayAdapter<Books> {

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder>{

	//out items array
    private ArrayList<Books> mDataset; 

    //Ensure that ids are unique
    private int itemCounter = 1;

	public  BooksAdapter(ArrayList<Books> dataset) {
		mDataset  = new ArrayList<Books>(dataset);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public TextView mTextView;

		public ViewHolder(View itemView) {
			super(itemView);
			mTextView = (TextView) itemView;
		}
	}

	@Override
	public int getItemCount() {
		return mDataset.size();
	}
	
	public interface OnItemClickLitener  
    {  
        void onItemClick(View view, int position);  
    }  
  
    private OnItemClickLitener mOnItemClickLitener;  
  
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)  
    {  
        this.mOnItemClickLitener = mOnItemClickLitener;  
    }   
	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		holder.mTextView.setText(mDataset.get(position).getTitle());

        if (mOnItemClickLitener != null)  
        {  
        	holder.itemView.setOnClickListener(new OnClickListener()  
            {  
                @Override  
                public void onClick(View v)  
                {  
                    mOnItemClickLitener.onItemClick(holder.itemView, position);  
                }  
            });  
        }  	
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = View.inflate(parent.getContext(),
				android.R.layout.simple_list_item_1, null);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}
}
