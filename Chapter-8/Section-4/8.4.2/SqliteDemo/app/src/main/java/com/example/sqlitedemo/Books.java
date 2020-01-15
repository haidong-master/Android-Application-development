package com.example.sqlitedemo;

public class Books {

	public static final String TABLENAME = "book";
	public int  _id;
	public String name;
	public String author;
	public int reserve;

	public Books() {
	}

	public Books(String name, String author, int reserve) {
		this.name = name;
		this.author = author;
		this.reserve = reserve;
	}
}
