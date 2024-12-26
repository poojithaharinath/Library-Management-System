package com.LibraryMangementSystemCore.Main;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Books {
	@Id
	private int book_id;
	private String title;
	private String author;
	private String publisher;
	private int publication_year;
	
	@ManyToOne
	@JoinColumn(name="book_store_id") 
	private BooksStore bookStore;
	
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public int getPublication_year() {
		return publication_year;
	}
	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}

	public BooksStore getBookStore() {
		return bookStore;
	}
	public void setBookStore(BooksStore bookStore) {
		this.bookStore = bookStore;
	}
}
