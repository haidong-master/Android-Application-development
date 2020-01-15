package com.example.bookreader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.example.bookreader.BooksAdapter.OnItemClickLitener;

public class BooksTitleFragment extends Fragment{

	private RecyclerView m_booksRecyclerView;
	private List<Books> bookList;
	private BooksAdapter adapter;
	private boolean flagTwoPane;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		bookList = getBooks();
		adapter = new BooksAdapter((ArrayList<Books>) bookList);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.books_title_frag, container, false);

		m_booksRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

		m_booksRecyclerView.setLayoutManager(layoutManager);
		
		adapter.setOnItemClickLitener(new OnItemClickLitener()  
		{  
		    @Override  
		    public void onItemClick(View view, int position)  
		    {  
		    	Books books = bookList.get(position);
				if (flagTwoPane) {
					BooksContentFragment booksContentFragment = (BooksContentFragment) getFragmentManager()
							.findFragmentById(R.id.books_content_fragment);
					booksContentFragment.showBookContent(books.getTitle(), books.getContent());
				} else {
					StartActivity(getActivity(), books.getTitle(),
							books.getContent());
				}
		    }  
		}); 
		
		m_booksRecyclerView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getActivity().findViewById(R.id.books_content_layout) != null) {
			flagTwoPane = true;
		} else {
			flagTwoPane = false;
		}
	}
	private List<Books> getBooks() {
		List<Books> bookList = new ArrayList<Books>();
		Books book1  = new Books();
		book1.setTitle("<<Android Development>>");
		book1.setContent("Are you interested in trying SmartEyeglass, Sony s binocular, see-through eyewear with camera, GPS and sensors? Or maybe learn about how to test your apps on Sony devices for free through the newly-launched Remote Device Lab (beta)? Find out about all that Sony has to offer developers at this year s Droidcon London");
		bookList.add(book1);
		Books book2  = new Books();
		book2.setTitle("<<Java Development>>");
		book2.setContent("Logs are targeting administrators when they encounter some problem. Therefore logs should describe the problem in an easily understandable form. Avoid using internal terms and abbreviations in log messages. Checking component versions");
		bookList.add(book2);
		return bookList;
	}
	public void StartActivity(Context context, String booksTitle,
			String booksContent) {
		Intent intent = new Intent(context, BooksContentActivity.class);
		intent.putExtra("book_title", booksTitle);
		intent.putExtra("book_content", booksContent);
		context.startActivity(intent);
	}
}
