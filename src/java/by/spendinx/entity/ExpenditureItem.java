package by.spendinx.entity;

public class ExpenditureItem extends Entity {
    private int id;
    private String name;

    public ExpenditureItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExpenditureItem { " + '\n' +
                "id = " + id + '\n' +
                "name = " + name + '\n' +
                "}";
    }
}
