import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CollegeAlertApp {
    private List<Event> events;
    private Scanner scanner;

    public CollegeAlertApp() {
        events = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("1. Set Reminder");
            System.out.println("2. View Events");
            System.out.println("3. Delete Event");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    setReminder();
                    break;
                case 2:
                    viewEvents();
                    break;
                case 3:
                    deleteEvent();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private void setReminder() {
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter event date (yyyy-MM-dd): ");
        String eventDate = scanner.nextLine();
        System.out.print("Enter event time (HH:mm): ");
        String eventTime = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(eventDate, formatter);
        LocalTime time = LocalTime.parse(eventTime);

        Event event = new Event(eventName, date, time);
        events.add(event);
        System.out.println("Reminder set successfully!");
    }

    private void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No upcoming events.");
        } else {
            System.out.println("Upcoming Events:");
            for (Event event : events) {
                System.out.println(event.toString());
            }
        }
    }

    private void deleteEvent() {
        System.out.print("Enter event name to delete: ");
        String eventName = scanner.nextLine();

        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                events.remove(event);
                System.out.println("Event deleted successfully!");
                return;
            }
        }
        System.out.println("Event not found.");
    }
      public static void main(String[] args) {
        CollegeAlertApp app = new CollegeAlertApp();
        app.run();
    }
}

class Event {
    private String name;
    private LocalDate date;
    private LocalTime time;

    public Event(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - " + date + " " + time;
    }
}