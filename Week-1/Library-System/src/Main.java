import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);

        System.out.println("------- Library System -------");
        System.out.println("Enter Name of Book : ");
        String nameBook=input.nextLine();

        System.out.println("Enter Number of Pages : ");
        String numberOfPages=input.nextLine();

        System.out.println("Enter Price Book : ");
        String price=input.nextLine();

        System.out.println("Enter Name of Author : ");
        String nameAuthor=input.nextLine();

        System.out.println("Enter Email of Author : ");
        String email=input.nextLine();

        Author author=new Author(nameAuthor,email);
        Book book=new Book(nameBook, numberOfPages,price,author);

        System.out.println("\n------ Book Details ------");
        System.out.println(book);
    }
}