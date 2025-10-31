public class Book {
    String name;
    int numberOfPage;
    double price;
    Author author;


    public Book(){}

    public Book(String name,int numberOfPage,double price){
        this.name=name;
        this.numberOfPage=numberOfPage;
        this.price=price;
    }

    public Book(String name,int numberOfPage,double price,Author author){
        this(name,numberOfPage,price);
        this.author=author;
    }
    public Book(String name,String numberOfPage,String price,Author author){
        this(name,Integer.parseInt(numberOfPage),Double.parseDouble(price));
        this.author=author;
    }


    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public int getNumberOfPage(){return numberOfPage;}
    public void setNumberOfPage(int numberOfPage){this.numberOfPage=numberOfPage;}

    public double getPrice(){return price;}
    public void setPrice(double price){this.price=price;}

    public Author getAuthor(){return author;}
    public void setAuthor(Author author){this.author=author;}

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", numberOfPage=" + numberOfPage +
                ", price=" + price +"$"+
                ", author=" + author +
                '}';
    }
}
