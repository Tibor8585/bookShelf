import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class exerciseBeadandó2 {
    public static void main(String[] args) {
        List<String> listOfBooks = datesOfBook();
        writeToConsole(listOfBooks);
        searchBook(listOfBooks);
        List<String> actualListOfBooks = booksCounter(listOfBooks);
        writeToFile(actualListOfBooks);
    }

    public static List<String> datesOfBook() {
        enum Genre {
            SCIENCE_FICTION,
            HORROR,
            SCIENCE,
            COMICS
        }
        List<String> booksList = new ArrayList<>();
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Title of the book: ");
            String title = scan.nextLine();
            System.out.println("Author of the book: ");
            String author = scan.nextLine();
            System.out.println("Kiadás dátuma: ");
            LocalDate dateOfPublication = LocalDate.parse(scan.next());
            int publicationYear = dateOfPublication.getYear();
            System.out.println("Genre of the book (1.Science fiction 2. Horror 3. Science 4.comics: ");
            int genre = scan.nextInt();
            String genreOfTheBook = "";
            if (genre == 1) {
                genreOfTheBook = String.valueOf(Genre.SCIENCE_FICTION);
            } else if (genre == 2) {
                genreOfTheBook = String.valueOf(Genre.HORROR);
            } else if (genre == 3) {
                genreOfTheBook = String.valueOf(Genre.SCIENCE);
            } else if (genre == 4) {
                genreOfTheBook = String.valueOf(Genre.COMICS);
            }
            System.out.println("How many there are: ");
            int quantity = scan.nextInt();

            String book = title + " - " + author + " - " + genreOfTheBook + " - " + quantity + " - " + publicationYear;

            booksList.add(book);
        } while (booksList.size() != 3);

        return booksList;
    }

    public static void writeToConsole(List<String> bookList) {
        System.out.println(bookList);
    }

    public static void searchBook(List<String> bookList) {
        Scanner scan2 = new Scanner(System.in);
        System.out.println("Add meg a keresett címet: ");
        String titleSearch = scan2.nextLine();

        for (String book : bookList) {
            String[] books = book.split(" - ");
            String title = books[0];

            if (title.equalsIgnoreCase(titleSearch)) {
                System.out.println("The list contains the following book: " + titleSearch);

            } else {
                System.out.println("Sorry, you can't find this book");

            }
        }
    }

    public static List<String> booksCounter(List<String> bookList) {
        List<String> newBookList = bookList;

        Scanner scan2 = new Scanner(System.in);
        System.out.println("Add meg a címét a könyvnek, amine darabszámát módosítani szeretnéd: ");
        String titleSearch = scan2.nextLine();
        List<String> modifiedBookList = new ArrayList<>();
        for (String book : newBookList) {
            String[] books = book.split(" - ");
            String title = books[0];
            String author = books[1];
            String genreOfTheBook = books[2];
            String quantity = books[3];
            String publicationYear = books[4];
            if (title.equalsIgnoreCase(titleSearch)) {
                System.out.println("Add meg a könyv aktuális daranszámát: ");
                quantity = scan2.nextLine();
                book = title + " - " + author + " - " + genreOfTheBook + " - " + quantity + " - " + publicationYear;
                modifiedBookList.add(book);
            }else  {
                modifiedBookList.add(book);
            }
        }
        return modifiedBookList;
    }

    public static void writeToFile(List<String> newBooklist) {
        String finalContent = "";
        for (String content : newBooklist) {
            finalContent += content + System.lineSeparator();
        }

        try {
            Files.write(Paths.get("savedFiles/library2.txt"), finalContent.getBytes("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
