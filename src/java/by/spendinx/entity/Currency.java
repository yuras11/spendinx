package by.spendinx.entity;

public class Currency extends Entity {
    private int id;
    private String name;
    private float course;

    public Currency(int id, String name, float course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getCourse() {
        return course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(float course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Currency { " + '\n' +
                "id = " + id + '\n' +
                "name = " + name + '\n' +
                "course = " + course + '\n' +
                "}";
    }
}
