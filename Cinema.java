package Cinema.Ticket;

import java.util.*;
import java.util.stream.Collectors;

// Абстрактный класс для билетов
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

// Типы билетов (Полиморфизм)
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

// Система управления кинотеатром
class CinemaSystem {
    private List<Movie> movies = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void add(Movie m) { movies.add(m); }
    public void add(Customer c) { customers.add(c); }
    public void add(Ticket t) {
        tickets.add(t);
        t.getCustomer().addPoints(10);
    }

    // Фильтрация
    public List<Movie> filterByGenre(String g) {
        return movies.stream().filter(m -> m.getGenre().equalsIgnoreCase(g)).collect(Collectors.toList());
    }

    public List<Movie> filterByRating(double min) {
        return movies.stream().filter(m -> m.getRating() >= min).collect(Collectors.toList());
    }

    public List<Ticket> filterByCustomer(String id) {
        return tickets.stream().filter(t -> t.getCustomer().getId().equals(id)).collect(Collectors.toList());
    }

    // Поиск
    public Movie findMovie(String id) {
        return movies.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    public Movie findByTitle(String title) {
        return movies.stream().filter(m -> m.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }

    // Сортировка
    public List<Movie> sortByTitle() {
        return movies.stream().sorted(Comparator.comparing(Movie::getTitle)).collect(Collectors.toList());
    }

    public List<Movie> sortByRating() {
        return movies.stream().sorted(Comparator.comparing(Movie::getRating).reversed()).collect(Collectors.toList());
    }

    public List<Customer> sortByPoints() {
        return customers.stream().sorted(Comparator.comparing(Customer::getPoints).reversed()).collect(Collectors.toList());
    }

    public List<Ticket> sortByPrice() {
        return tickets.stream().sorted(Comparator.comparing(Ticket::getPrice).reversed()).collect(Collectors.toList());
    }

    public double revenue() {
        return tickets.stream().mapToDouble(Ticket::getPrice).sum();
    }

    public void showMovies() { movies.forEach(System.out::println); }
    public void showTickets() { tickets.forEach(System.out::println); }
    public void showCustomers() { customers.forEach(System.out::println); }
}