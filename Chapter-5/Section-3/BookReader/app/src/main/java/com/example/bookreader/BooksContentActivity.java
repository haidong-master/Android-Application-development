package com.example.bookreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class BooksContentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.books_content);
		String booksTitle = getIntent().getStringExtra("book_title");
		String booksContent = getIntent().getStringExtra("book_content");
		BooksContentFragment booksContentFragment = (BooksContentFragment) getFragmentManager()
				.findFragmentById(R.id.books_content_fragment);
		booksContentFragment.showBookContent(booksTitle, booksContent);
	}
}
