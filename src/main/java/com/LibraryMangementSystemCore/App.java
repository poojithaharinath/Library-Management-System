package com.LibraryMangementSystemCore;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.LibraryMangementSystemCore.Main.BooksImplementation;
import com.LibraryMangementSystemCore.Main.BooksStore;
import com.LibraryMangementSystemCore.Main.ConnectionToDataBases;

public class App {
	static BooksImplementation bi = new BooksImplementation();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------Welcome To Library---------");

		while (true) {
			System.out.println("1. Add Type of Book");
			System.out.println("2. Update Type of Books");
			System.out.println("3. Delete Type of Books");
			System.out.println("4. Show Books Of Particular Type");
			System.out.println("5. Add Book");
			System.out.println("6. Update Book");
			System.out.println("7. Delete Book");
			System.out.println("8. View Book");
			System.out.println("9. View All Books");

			System.out.println("10. Exit");
			System.out.print("Enter your choice: ");

			int choice = sc.nextInt();
			sc.nextLine(); // Consume newline
			switch (choice) {
			case 1:
				addType();
				break;
			case 2:
				updateType();
				break;
			case 3:
				deleteType();
				break;
			case 4:
				showTypes();
				break;
			case 5:
				bi.addBook();
				break;
			case 6:
				bi.updateBook();
				break;
			case 7:
				bi.deleteBook();
				break;
			case 8:
				bi.viewBook();
				break;
			case 9:
				bi.viewAllBooks();
				break;
			case 10:
				System.out.println();
				System.out.println("------------Library Closed---------------");
				System.exit(0);
			default:
				System.out.println("Invalid choice, please try again.");
			}
		}
	}

	private static void addType() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nEnter Book Type ID:");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Type of Book:");
		String type = sc.nextLine();

		BooksStore bs = new BooksStore();
		bs.setId(id);
		bs.setTypeOfBook(type);

		Transaction transaction = null;
		try (Session session = ConnectionToDataBases.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(bs);
			transaction.commit();
			System.out.println("Book type added successfully.");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			System.out.println("Failed to add book type.");
		}

	}

	private static void updateType() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nEnter Book Type ID to update:");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Updated Type of Book:");
		String type = sc.nextLine();

		Transaction transaction = null;
		try (Session session = ConnectionToDataBases.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			BooksStore bs = session.get(BooksStore.class, id);
			if (bs != null) {
				bs.setTypeOfBook(type);
				session.update(bs);
				transaction.commit();
				System.out.println("Book type updated successfully.");
			} else {
				System.out.println("Book Type ID not found.");
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			System.out.println("Failed to update book type.");
		}
	}

	private static void deleteType() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nEnter Book Type ID to delete:");
		int id = sc.nextInt();

		Transaction transaction = null;
		try (Session session = ConnectionToDataBases.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			BooksStore bs = session.get(BooksStore.class, id);
			if (bs != null) {
				session.delete(bs);
				transaction.commit();
				System.out.println("Book type deleted successfully.");
			} else {
				System.out.println("Book Type ID not found.");
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			System.out.println("Failed to delete book type.");
		}
	}

	private static void showTypes() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nEnter Book Type ID to show:");
		int id = sc.nextInt();

		Transaction transaction = null;
		try (Session session = ConnectionToDataBases.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			BooksStore bs = session.get(BooksStore.class, id);
			if (bs != null) {
				System.out.println("Book Store ID: " + bs.getId());
				System.out.println("Type of Book: " + bs.getTypeOfBook());
				System.out.println("------Books in this Store------");
				if(bs != null) {
				bs.getBooks().forEach(book -> {
					System.out.println("\tBook ID: " + book.getBook_id());
					System.out.println("\tTitle: " + book.getTitle());
					System.out.println("\tAuthor: " + book.getAuthor());
					System.out.println("\tPublisher: " + book.getPublisher());
					System.out.println("\tYear: " + book.getPublication_year());

					System.out.println("----------------------------------------------------------------------------------------------------------------------------");
					System.out.println();
				});
				}
				
			} else {
				System.out.println("No Books On This Type Of ID :(");
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			System.out.println("Failed to retrieve book types.");
		}
	}
}
