import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SI2026Lab2Test {

    @Test
    void searchBookEveryStatementTest() {
        Library library = new Library();
        library.addBook(new Book("Clean Code Test", "Jovan Mitrev", "Programming"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            library.searchBookByTitle("");
        });
        assertEquals("Invalid title", ex.getMessage());

        List<Book> foundBooks = library.searchBookByTitle("Clean Code");
        assertNotNull(foundBooks);
        assertEquals(1, foundBooks.size());
        assertEquals("Clean Code", foundBooks.get(0).getTitle());

        List<Book> noBooks = library.searchBookByTitle("Non Existent Book");
        assertNull(noBooks);
    }
    @Test
    void borrowBookEveryBranchTest() {
        Library library = new Library();
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));

        library.borrowBook("1984", "George Orwell");

        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "J.R.R. Tolkien");
        });

        RuntimeException notFoundEx = assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Harry Potter", "J.K. Rowling");
        });
        assertEquals("Book not found", notFoundEx.getMessage());

        library.borrowBook("The Hobbit", "J.R.R. Tolkien");

        RuntimeException alreadyBorrowedEx = assertThrows(RuntimeException.class, () -> {
            library.borrowBook("1984", "George Orwell");
        });
        assertEquals("Book is already borrowed.", alreadyBorrowedEx.getMessage());
    }
    @Test
    void borrowBookMultipleConditionTest() {
        Library library = new Library();

        // 1. T || X 
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("", "Author"));

        // 2. F || T 
        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("Title", ""));

        // 3. F || F 
        library.addBook(new Book("Title", "Author", "Genre"));
        assertDoesNotThrow(() -> library.borrowBook("Title", "Author"));
    }

    @Test
    void searchBookMultipleConditionTest() {
        Library library = new Library();

        // 1. T && T
        library.addBook(new Book("Clean Code", "Robert", "Dev"));
        assertNotNull(library.searchBookByTitle("Clean Code"));

        // 2. T && F
        library.borrowBook("Clean Code", "Robert");
        assertNull(library.searchBookByTitle("Clean Code"));

        // 3. F && X 
        assertNull(library.searchBookByTitle("Other Book"));
    }
}
