package pojos;

public class Activity {
    private int id;
    private String title;
    private String dueDate;
    private boolean completed;

    public Activity() {}

    public Activity(int id,String title, String dueDate,boolean completed) {
        this.id=id;
        this.title = title;
        this.dueDate = dueDate;
        this.completed=completed;
    }

    public int getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}

