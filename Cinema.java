package Cinema.Ticket;

import java.util.ArrayList;
import java.util.List;

abstract class Ticket {
    private String id, seat, time;
    private Movie movie;
    private Customer customer;

    public Ticket(String id, Movie m, Customer c, String time, String seat) {
        this.id = id;
        this.movie = m;
        this.customer = c;
        this.time = time;
        this.seat = seat;
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public abstract double getPrice();
    public abstract String getType();

    @Override
    public String toString() {
        return "Ticket[" + id + ", " + movie.getTitle() + ", " + customer.getName() +
                ", " + time + ", " + seat + ", " + getType() + ", $" + getPrice() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

class Regular extends Ticket {
    public Regular(String id, Movie m, Customer c, String time, String seat) {
        super(id, m, c, time, seat);
    }
    public double getPrice() { return 12.0; }
    public String getType() { return "Regular"; }
}

class VIP extends Ticket {
    private boolean snacks;
    public VIP(String id, Movie m, Customer c, String time, String seat, boolean snacks) {
        super(id, m, c, time, seat);
        this.snacks = snacks;
    }
    public double getPrice() { return snacks ? 33.0 : 25.0; }
    public String getType() { return "VIP" + (snacks ? "+Snacks" : ""); }
}

class Student extends Ticket {
    public Student(String id, Movie m, Customer c, String time, String seat) {
        super(id, m, c, time, seat);
    }
    public double getPrice() { return 8.0; }
    public String getType() { return "Student"; }
}

class CinemaSystem {
    private List<Movie> movies = new ArrayList<Movie>();
    private List<Ticket> tickets = new ArrayList<Ticket>();
    private List<Customer> customers = new ArrayList<Customer>();

    public void add(Movie m) { movies.add(m); }
    public void add(Customer c) { customers.add(c); }
    public void add(Ticket t) {
        tickets.add(t);
        t.getCustomer().addPoints(10);
    }

    public List<Movie> filterByGenre(String g) {
        List<Movie> result = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if (m.getGenre().equalsIgnoreCase(g)) {
                result.add(m);
            }
        }
        return result;
    }

    public List<Movie> filterByRating(double min) {
        List<Movie> result = new ArrayList<Movie>();
        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if (m.getRating() >= min) {
                result.add(m);
            }
        }
        return result;
    }

    public List<Ticket> filterByCustomer(String id) {
        List<Ticket> result = new ArrayList<Ticket>();
        for (int i = 0; i < tickets.size(); i++) {
            Ticket t = tickets.get(i);
            if (t.getCustomer().getId().equals(id)) {
                result.add(t);
            }
        }
        return result;
    }

    public Movie findMovie(String id) {
        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public Movie findByTitle(String title) {
        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }
        return null;
    }

    public List<Movie> sortByTitle() {
        List<Movie> result = new ArrayList<Movie>(movies);
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = 0; j < result.size() - i - 1; j++) {
                if (result.get(j).getTitle().compareTo(result.get(j + 1).getTitle()) > 0) {
                    Movie temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    public List<Movie> sortByRating() {
        List<Movie> result = new ArrayList<Movie>(movies);
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = 0; j < result.size() - i - 1; j++) {
                if (result.get(j).getRating() < result.get(j + 1).getRating()) {
                    Movie temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    public List<Customer> sortByPoints() {
        List<Customer> result = new ArrayList<Customer>(customers);
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = 0; j < result.size() - i - 1; j++) {
                if (result.get(j).getPoints() < result.get(j + 1).getPoints()) {
                    Customer temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    public List<Ticket> sortByPrice() {
        List<Ticket> result = new ArrayList<Ticket>(tickets);
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = 0; j < result.size() - i - 1; j++) {
                if (result.get(j).getPrice() < result.get(j + 1).getPrice()) {
                    Ticket temp = result.get(j);
                    result.set(j, result.get(j + 1));
                    result.set(j + 1, temp);
                }
            }
        }
        return result;
    }

    public double revenue() {
        double total = 0;
        for (int i = 0; i < tickets.size(); i++) {
            total += tickets.get(i).getPrice();
        }
        return total;
    }

    public void showMovies() {
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
        }
    }

    public void showTickets() {
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println(tickets.get(i));
        }
    }

    public void showCustomers() {
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i));
        }
    }
}