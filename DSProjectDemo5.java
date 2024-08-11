import java.util.Scanner;

class Ticket {
    String movieName;
    double price;

    Ticket(String movieName, double price) {
        this.movieName = movieName;
        this.price = price;
    }

    String getMovieName() {
        return movieName;
    }

    double getPrice() {
        return price;
    }

    public String toString() {
        return movieName + " ---> " + price;
    }
}

class Node {
    Ticket data;
    Node next;
    Node prev;

    Node(Ticket ticket) {
        this.data = ticket;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList {
    Node head;

    void addTicket(Ticket ticket) {
        Node n = new Node(ticket);
        if (head == null) {
            head = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
    }

    void removeTicket(String ticketName) {
        Node current = head;
        while (current != null) {
            if (current.data.getMovieName().equals(ticketName)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                if (current == head) {
                    head = current.next;
                }
                return; // Ticket found and removed
            }
            current = current.next;
        }
    }

    void displayTickets() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    boolean isEmpty() {
        return head == null;
    }
}

class DSProjectDemo5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoublyLinkedList ticketList = new DoublyLinkedList();

        System.out.println("Select User Type:");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("3. Exit");
        System.out.print("Enter User Type: ");
        int user = sc.nextInt();
        sc.nextLine();

        switch (user) {
            case 1: // Admin
                int admin_Option;
                do {
                    System.out.println("Admin Options:");
                    System.out.println("1. Add Ticket");
                    System.out.println("2. Remove Ticket");
                    System.out.println("3. Display Tickets");
                    System.out.println("4. Back to Main Menu");
                    System.out.print("Select Admin Option: ");
                    admin_Option = sc.nextInt();
                    sc.nextLine();

                    switch (admin_Option) {
                        case 1: // Add Ticket
                            System.out.print("How many tickets do you want to add: ");
                            int n = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter your tickets one by one: ");
                            for (int i = 0; i < n; i++) {
                                System.out.println("enter ticket name: ");
                                String ticketName = sc.nextLine();
                                System.out.println("enter ticket price: ");
                                double ticketPrice = sc.nextDouble();
                                sc.nextLine();
                                Ticket ticket = new Ticket(ticketName, ticketPrice);
                                ticketList.addTicket(ticket);
                            }
                            System.out.println("Tickets added successfully!");
                            break;

                        case 2: // Remove Ticket
                            if (!ticketList.isEmpty()) {
                                System.out.print("Enter ticket name to remove: ");
                                String ticketToRemove = sc.nextLine();
                                ticketList.removeTicket(ticketToRemove);
                                System.out.println("Ticket removed successfully!");
                            } else {
                                System.out.println("No tickets available to remove.");
                            }
                            break;

                        case 3: // Display Tickets
                            System.out.println("----- Ticket List -----");
                            ticketList.displayTickets();
                            break;

                        case 4: // Back to Main Menu
                            break;

                        default:
                            System.out.println("Invalid Admin Option");
                    }
                } while (admin_Option != 4);
                break;
        }
    }
}