package expenseTrackerApp;

public class Expense {
    private int id;
    private int userId;
    private double amount;
    private String date;
    private String category;
    private String description;

    // Constructors, getters, and setters
    public Expense() {}

    public Expense(int id, int userId, double amount, String date, String category, String description) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
