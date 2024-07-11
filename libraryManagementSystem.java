import java.util.Scanner;

interface Item {
  String Id();

  boolean isAvailable();

  void borrow();

  void returnItem();
}

class Book implements Item {
  private String title;
  private String author;
  private boolean available;

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
    this.available = true;
  }

  public String Id() {
    return title;
  }

  public boolean isAvailable() {
    return available;
  }

  public void borrow() {
    if (available) {
      available = false;
      System.out.println("Borrowed book: " + title);
    } else {
      System.out.println("Book not available: " + title);
    }
  }

  public void returnItem() {
    available = true;
    System.out.println("Returned book: " + title);
  }
}

public class libraryManagementSystem {
  private Item[] items;

  public libraryManagementSystem(int capacity) {
    this.items = new Item[capacity];
  }

  public void addItem() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter book title: ");
    String title = scanner.nextLine();

    System.out.print("Enter book author: ");
    String author = scanner.nextLine();

    for (int i = 0; i < items.length; i++) {
      if (items[i] == null) {
        items[i] = new Book(title, author);
        System.out.println("Added book: " + title);
        return;
      }
    }
    System.out.println("Library at capacity.");
  }

  public void borrowItem(String title) {
    for (Item item : items) {
      if (item != null && item.Id().equals(title)) {
        item.borrow();
        return;
      }
    }
    System.out.println("Book not found: " + title);
  }

  public void returnItem(String title) {
    for (Item item : items) {
      if (item != null && item.Id().equals(title)) {
        item.returnItem();
        return;
      }
    }
    System.out.println("Book not found: " + title);
  }

  public static void main(String[] args) {
    libraryManagementSystem library = new libraryManagementSystem(5);

    library.addItem();

    library.borrowItem("The Lord of the Rings");
    library.borrowItem("Atomic habits");

    library.returnItem("Atomic habit");
    library.returnItem("The Lord of the Rings");
  }
}
