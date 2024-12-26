package com.LibraryMangementSystemCore.Main;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class BooksImplementation {

    public void addBook() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Book ID:");
        int book_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Title:");
        String title = sc.nextLine();
        System.out.println("Enter Author Name:");
        String author = sc.nextLine();
        System.out.println("Enter Publisher Name:");
        String publisher = sc.nextLine();
        System.out.println("Enter Year of Publication:");
        int year = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Books Type ID:");
        int storeId = sc.nextInt();

        BooksStore bs = null;
        Transaction transaction = null;

        try (Session session = ConnectionToDataBases.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Fetch BooksStore by storeId
            bs = session.get(BooksStore.class, storeId);

            if (bs != null) {
                Books b = new Books();
                b.setBook_id(book_id);
                b.setTitle(title);
                b.setAuthor(author);
                b.setPublisher(publisher);
                b.setPublication_year(year);
                b.setBookStore(bs); 

                session.save(b);
                transaction.commit();

                System.out.println("Book is Added");
            } else {
                System.out.println("Book Store ID not found.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateBook() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Book ID to Update:");
        int book_id = sc.nextInt();
        sc.nextLine();

        Transaction transaction = null;

        try (Session session = ConnectionToDataBases.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Fetch Book by book_id
            Books b = session.get(Books.class, book_id);

            if (b != null) {
                System.out.println("Enter New Title: (Optional):");
                String title = sc.nextLine();
                if (!title.isEmpty()) {
                    b.setTitle(title);
                }

                System.out.println("Enter New Author Name: (Optional):");
                String author = sc.nextLine();
                if (!author.isEmpty()) {
                    b.setAuthor(author);
                }

                System.out.println("Enter New Publisher Name: (Optional)");
                String publisher = sc.nextLine();
                if (!publisher.isEmpty()) {
                    b.setPublisher(publisher);
                }

                System.out.println("Enter New Year of Publication (0->current):");
                int year = sc.nextInt();
                if (year != 0) {
                    b.setPublication_year(year);
                }

                session.update(b);
                transaction.commit();

                System.out.println("Book Updated Successfully");

            } else {
                System.out.println("Book ID not found.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteBook() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Book ID to Delete:");
        int book_id = sc.nextInt();

        Transaction transaction = null;

        try (Session session = ConnectionToDataBases.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Fetch Book by book_id
            Books b = session.get(Books.class, book_id);

            if (b != null) {
                session.delete(b);
                transaction.commit();

                System.out.println("Book Deleted Successfully");

            } else {
                System.out.println("Book ID not found.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void viewAllBooks() {
        try (Session session = ConnectionToDataBases.getSessionFactory().openSession()) {
            List<Books> booksList = session.createQuery("from Books", Books.class).list();

            if (booksList.isEmpty()) {
                System.out.println("No books found.");
            } else {
                for (Books b : booksList) {
                    System.out.println("Book ID: " + b.getBook_id());
                    System.out.println("Title: " + b.getTitle());
                    System.out.println("Author: " + b.getAuthor());
                    System.out.println("Publisher: " + b.getPublisher());
                    System.out.println("Year of Publication: " + b.getPublication_year());
                    System.out.println("Books Store ID: " + b.getBookStore().getId());
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------");
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBook() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Book ID to View:");
        int book_id = sc.nextInt();

        try (Session session = ConnectionToDataBases.getSessionFactory().openSession()) {
            Books b = session.get(Books.class, book_id);

            if (b != null) {
            	System.out.println("----------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Book ID: " + b.getBook_id());
                System.out.println("Title: " + b.getTitle());
                System.out.println("Author: " + b.getAuthor());
                System.out.println("Publisher: " + b.getPublisher());
                System.out.println("Year of Publication: " + b.getPublication_year());
                System.out.println("Books Type ID: " + b.getBookStore().getId());
                System.out.println("----------------------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Book ID not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
