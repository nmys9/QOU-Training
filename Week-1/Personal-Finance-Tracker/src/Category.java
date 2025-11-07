import java.util.Objects;

public class Category {
    private final int id;
    private String name;
    private String description;
    private static int count=0;


    public  Category(String name,String description){
        count++;
        this.id=count;
        this.name=name;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return String.format("Category is %s ( %s )",this.name,this.description);
    }
}
