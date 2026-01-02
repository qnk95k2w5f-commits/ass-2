package Cinema.Ticket;

public class Movie {
    private String id, title, genre;
    private int duration;
    private double rating;

    public Movie(String id, String title, String genre, int duration, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return "Movie[" + id + ", " + title + ", " + genre + ", " + duration + "min, ★" + rating + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id.equals(((Movie) o).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}