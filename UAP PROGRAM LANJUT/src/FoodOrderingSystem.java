import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class FoodOrderingSystem {

     // Model untuk Menu Makanan
    static class Menu {
        private String name;
        private double price;
        private String description;

        public Menu(String name, double price, String description) {
            this.name = name;
            this.price = price;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return name + " - $" + price;
        }
    }

    // Model untuk Pesanan
    static class Order {
        private String customerName;
        private Menu menuItem;
        private int quantity;

        public Order(String customerName, Menu menuItem, int quantity) {
            this.customerName = customerName;
            this.menuItem = menuItem;
            this.quantity = quantity;
        }

        public double getTotalPrice() {
            return menuItem.getPrice() * quantity;
        }

        public String getOrderDetails() {
            return customerName + " ordered " + quantity + " x " + menuItem.getName() + " for $" + getTotalPrice();
        }
    }

    // GUI untuk Pemesanan
    static class OrderGUI {
        private JFrame frame;
        private JTextField nameField;
        private JComboBox<Menu> menuComboBox;
        private JTextField quantityField;
        private JButton submitButton;
        private JTextArea orderDisplay;

        private List<Order> orders = new ArrayList<>();

        // Menu Makanan
        private Menu[] menuItems = {
                new Menu("Burger", 5.99, "Delicious beef burger"),
                new Menu("Pizza", 7.99, "Cheesy and tasty pizza"),
                new Menu("Pasta", 6.99, "Italian pasta with tomato sauce")
        };

        public OrderGUI() {
            frame = new JFrame("Food Ordering System");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            // Input Nama Pemesan
            frame.add(new JLabel("Customer Name:"));
            nameField = new JTextField(20);
            frame.add(nameField);

            // Pilih Menu Makanan
            frame.add(new JLabel("Select Menu:"));
            menuComboBox = new JComboBox<>(menuItems);
            frame.add(menuComboBox);

            // Input Quantity
            frame.add(new JLabel("Quantity:"));
            quantityField = new JTextField(5);
            frame.add(quantityField);

            // Tombol untuk Submit Pemesanan
            submitButton = new JButton("Place Order");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String name = nameField.getText();
                        Menu selectedMenu = (Menu) menuComboBox.getSelectedItem();
                        int quantity = Integer.parseInt(quantityField.getText());

                        if (name.isEmpty() || quantity <= 0) {
                            throw new IllegalArgumentException("Name cannot be empty and quantity must be greater than 0.");
                        }

                        // Create and store the order
                        Order order = new Order(name, selectedMenu, quantity);
                        orders.add(order);

                        // Display the orders in the text area
                        displayOrders();

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid quantity.");
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage());
                    }
                }
            });
            frame.add(submitButton);

            // Display area for orders
            orderDisplay = new JTextArea(10, 40);
            orderDisplay.setEditable(false);
            frame.add(new JScrollPane(orderDisplay));

            frame.setVisible(true);
        }

        // Menampilkan Pesanan
        private void displayOrders() {
            StringBuilder orderDetails = new StringBuilder();
            for (Order order : orders) {
                orderDetails.append(order.getOrderDetails()).append("\n");
            }
            orderDisplay.setText(orderDetails.toString());
        }
    }

    // Main Program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OrderGUI();
            }
        });
    }
}
