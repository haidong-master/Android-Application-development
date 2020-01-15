package com.example.bookreader;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BooksContentFragment extends Fragment {

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.books_content_frag, container, false);
		return view;
	}

	public void showBookContent(String newsTitle, String newsContent) {
		View visibilityLayout = view.findViewById(R.id.visibility_layout);
		visibilityLayout.setVisibility(View.VISIBLE);
		TextView booksTitleText = (TextView) view.findViewById(R.id.books_title);
		TextView booksContentText = (TextView) view
				.findViewById(R.id.books_content);
		booksTitleText.setText(newsTitle);
		booksContentText.setText(newsContent);
	}

}
