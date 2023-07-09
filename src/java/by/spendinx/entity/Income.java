package by.spendinx.entity;

public class Income extends Entity {
    private int id;
    private String name;
    private IncomeSource incomeSource;

    public Income(int id, String name, IncomeSource incomeSource) {
        this.id = id;
        this.name = name;
        this.incomeSource = incomeSource;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IncomeSource getIncomeSource() {
        return incomeSource;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIncomeSource(IncomeSource incomeSource) {
        this.incomeSource = incomeSource;
    }

    @Override
    public String toString() {
        return "Income { " + '\n' +
                "id = " + id + '\n' +
                "name = " + name + '\n' +
                "incomeSource = " + '\n' +
                incomeSource + '\n' +
                "}";
    }
}
