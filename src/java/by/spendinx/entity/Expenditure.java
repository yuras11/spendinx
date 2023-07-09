package by.spendinx.entity;

public class Expenditure extends Entity {
    private int id;
    private String name;
    private ExpenditureItem expenditureItem;

    public Expenditure(int id, String name, ExpenditureItem expenditureItem) {
        this.id = id;
        this.name = name;
        this.expenditureItem = expenditureItem;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ExpenditureItem getExpenditureItem() {
        return expenditureItem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpenditureItem(ExpenditureItem expenditureItem) {
        this.expenditureItem = expenditureItem;
    }

    @Override
    public String toString() {
        return "Expenditure { " + '\n' +
                "id = " + id + '\n' +
                "name = " + name + '\n' +
                "expenditureItem = " + '\n' +
                expenditureItem + '\n' +
                "}";
    }
}
