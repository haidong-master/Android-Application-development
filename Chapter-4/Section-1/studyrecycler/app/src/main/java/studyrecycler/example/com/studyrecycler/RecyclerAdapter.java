package studyrecycler.example.com.studyrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
	private String[] mDataset;

	public RecyclerAdapter(String[] dataset) {
		mDataset = dataset;
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
		return mDataset.length;
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
		holder.mTextView.setText(mDataset[position]);
		
		//��������˻ص��������õ���¼�  
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