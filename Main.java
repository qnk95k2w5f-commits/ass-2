package Cinema.Ticket;

public class Main {
    public static void main(String[] args) {
        CinemaSystem s = new CinemaSystem();

        Movie m1 = new Movie("M1", "Inception", "Sci-Fi", 148, 8.8);
        Movie m2 = new Movie("M2", "Dark Knight", "Action", 152, 9.0);
        Movie m3 = new Movie("M3", "Interstellar", "Sci-Fi", 169, 8.6);
        s.add(m1); s.add(m2); s.add(m3);

        Customer c1 = new Customer("C1", "John", "john@mail.com");
        Customer c2 = new Customer("C2", "Jane", "jane@mail.com");
        s.add(c1); s.add(c2);

        s.add(new Regular("T1", m1, c1, "19:00", "A5"));
        s.add(new VIP("T2", m2, c2, "21:00", "V1", true));
        s.add(new Student("T3", m3, c1, "18:00", "B3"));

        System.out.println("=== ВСЕ ФИЛЬМЫ ===");
        s.showMovies();

        System.out.println("\n=== ВСЕ БИЛЕТЫ ===");
        s.showTickets();

        System.out.println("\n=== КЛИЕНТЫ ===");
        s.showCustomers();

        System.out.println("\n=== ФИЛЬТР: Sci-Fi ===");
        s.filterByGenre("Sci-Fi").forEach(System.out::println);

        System.out.println("\n=== ФИЛЬТР: Рейтинг >= 8.7 ===");
        s.filterByRating(8.7).forEach(System.out::println);

        System.out.println("\n=== ФИЛЬТР: Билеты C1 ===");
        s.filterByCustomer("C1").forEach(System.out::println);

        System.out.println("\n=== ПОИСК: M2 ===");
        System.out.println(s.findMovie("M2"));

        System.out.println("\n=== ПОИСК: Inception ===");
        System.out.println(s.findByTitle("Inception"));

        System.out.println("\n=== СОРТИРОВКА: По названию ===");
        s.sortByTitle().forEach(System.out::println);

        System.out.println("\n=== СОРТИРОВКА: По рейтингу ===");
        s.sortByRating().forEach(System.out::println);

        System.out.println("\n=== СОРТИРОВКА: По баллам ===");
        s.sortByPoints().forEach(System.out::println);

        System.out.println("\n=== СОРТИРОВКА: По цене ===");
        s.sortByPrice().forEach(System.out::println);

        System.out.println("\n=== Общая выручка: $" + s.revenue() + " ===");

        System.out.println("\n=== ПОЛИМОРФИЗМ ===");
        System.out.println("Роль клиента: " + c1.getRole());
        Admin a = new Admin("A1", "Alice", "alice@cinema.com", "IT");
        System.out.println("Роль админа: " + a.getRole());

        System.out.println("\n=== EQUALS & HASHCODE ===");
        Movie test = new Movie("M1", "Inception", "Sci-Fi", 148, 8.8);
        System.out.println("m1.equals(test): " + m1.equals(test));
        System.out.println("Одинаковый hashCode: " + (m1.hashCode() == test.hashCode()));
    }
}