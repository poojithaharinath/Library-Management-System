package com.LibraryMangementSystemCore.Main;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class BooksStore {
	@Id
	private int Id;
	private String TypeOfBook;

	@OneToMany(mappedBy = "bookStore")
	private List<Books> books;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTypeOfBook() {
		return TypeOfBook;
	}

	public void setTypeOfBook(String typeOfBook) {
		TypeOfBook = typeOfBook;
	}

	public List<Books> getBooks() {
		return books;
	}

	public void setBooks(List<Books> books) {
		this.books = books;
	}
}
