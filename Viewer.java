package Cinema.Ticket;

abstract class Person {
    private String id, name, email;

    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + "[" + id + ", " + name + ", " + email + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

class Customer extends Person {
    private int points = 0;

    public Customer(String id, String name, String email) {
        super(id, name, email);
    }

    public int getPoints() { return points; }
    public void addPoints(int p) { points += p; }

    @Override
    public String getRole() { return "Customer"; }

    @Override
    public String toString() {
        return super.toString() + ", Points=" + points;
    }
}

class Admin extends Person {
    private String dept;

    public Admin(String id, String name, String email, String dept) {
        super(id, name, email);
        this.dept = dept;
    }

    @Override
    public String getRole() { return "Admin"; }
}