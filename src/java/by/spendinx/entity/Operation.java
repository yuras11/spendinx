package by.spendinx.entity;

public class Operation extends Entity {
    private int id;
    private User user;
    private Income income;
    private Expenditure expenditure;
    private double volume;
    private Currency currency;
    private String dateOfOperation;

    public Operation(int id, User user, Income income, Expenditure expenditure, double volume, Currency currency, String dateOfOperation) {
        this.id = id;
        this.user = user;
        this.income = income;
        this.expenditure = expenditure;
        this.volume = volume;
        this.currency = currency;
        this.dateOfOperation = dateOfOperation;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Income getIncome() {
        return income;
    }

    public Expenditure getExpenditure() {
        return expenditure;
    }

    public double getVolume() {
        return volume;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getDateOfOperation() {
        return dateOfOperation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public void setExpenditure(Expenditure expenditure) {
        this.expenditure = expenditure;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setDateOfOperation(String dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    @Override
    public String toString() {
        return "Operation { " + '\n' +
                "id = " + id + '\n' +
                "user = " + '\n' +
                user + '\n' +
                "income = " + '\n' +
                income + '\n' +
                "expenditure = " + '\n' +
                expenditure + '\n' +
                "volume = " + '\n' +
                volume + '\n' +
                "currency = " + '\n' +
                currency + '\n' +
                "dateOfOperation = " + '\n' +
                dateOfOperation + '\n' +
                "}";
    }
}
