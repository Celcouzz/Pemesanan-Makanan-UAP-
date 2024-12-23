package Gambar;
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
        private String orderType;
        private int orderNumber;
        private Menu menuItem;
        private int quantity;

        public Order(String customerName, String orderType, int orderNumber, Menu menuItem, int quantity) {
            this.customerName = customerName;
            this.orderType = orderType;
            this.orderNumber = orderNumber;
            this.menuItem = menuItem;
            this.quantity = quantity;
        }

        public double getTotalPrice() {
            return menuItem.getPrice() * quantity;
        }

        public String getOrderDetails() {
            return customerName + " (Order #" + orderNumber + ") ordered " + quantity + " x " + menuItem.getName() + " (" + orderType + ") for $" + getTotalPrice();
        }
    }

    // GUI untuk Halaman Restoran
    static class RestaurantPage {
        private JFrame frame;

        public RestaurantPage() {
            frame = new JFrame("Restoran");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Label judul
            JLabel titleLabel = new JLabel("Restoran", JLabel.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            frame.add(titleLabel, BorderLayout.NORTH);

            // Tempat gambar
            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabel.setIcon(new ImageIcon("UAP PROGRAM LANJUT/src/Gambar/Restoran.jpeg")); // Ganti dengan path gambar Anda
            frame.add(imageLabel, BorderLayout.CENTER);

            // Tombol "Pesan"
            JButton orderButton = new JButton("Pesan");
            orderButton.setFont(new Font("Arial", Font.PLAIN, 18));
            orderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); // Tutup halaman restoran
                    new CustomerInfoPage(); // Buka halaman identitas pemesan
                }
            });
            frame.add(orderButton, BorderLayout.SOUTH);

            frame.setVisible(true);
        }
    }

    // GUI untuk Halaman Identitas Pemesan
    static class CustomerInfoPage {
        private JFrame frame;
        private JTextField nameField;
        private JTextField orderNumberField;
        private JComboBox<String> orderTypeComboBox;

        public CustomerInfoPage() {
            frame = new JFrame("Customer Information");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridBagLayout());
            frame.setLocationRelativeTo(null);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            // Input Nama Pemesan
            gbc.gridx = 0;
            gbc.gridy = 0;
            frame.add(new JLabel("Customer Name:"), gbc);

            gbc.gridx = 1;
            nameField = new JTextField(20);
            frame.add(nameField, gbc);

            // Input Nomor Pesanan
            gbc.gridx = 0;
            gbc.gridy = 1;
            frame.add(new JLabel("Order Number:"), gbc);

            gbc.gridx = 1;
            orderNumberField = new JTextField(20);
            frame.add(orderNumberField, gbc);

            // Pilihan Dine In atau Take Away
            gbc.gridx = 0;
            gbc.gridy = 2;
            frame.add(new JLabel("Order Type:"), gbc);

            gbc.gridx = 1;
            orderTypeComboBox = new JComboBox<>(new String[]{"Dine In", "Take Away"});
            frame.add(orderTypeComboBox, gbc);

            // Tombol Lanjut
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.EAST;
            JButton nextButton = new JButton("Next");
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String name = nameField.getText().trim();
                        int orderNumber = Integer.parseInt(orderNumberField.getText().trim());
                        String orderType = (String) orderTypeComboBox.getSelectedItem();

                        if (name.isEmpty() || orderNumber <= 0) {
                            throw new IllegalArgumentException("Name cannot be empty and order number must be greater than 0.");
                        }

                        frame.dispose(); // Tutup halaman identitas pemesan
                        new MenuSelectionPage(name, orderType, orderNumber); // Buka halaman pemilihan menu

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid order number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            frame.add(nextButton, gbc);

            frame.setVisible(true);
        }
    }

    // GUI untuk Halaman Pemilihan Menu
    static class MenuSelectionPage {
        private JFrame frame;
        private JComboBox<String> categoryComboBox;
        private JComboBox<Menu> menuComboBox;
        private JTextField quantityField;
        private JButton submitButton;
        private JTextArea orderDisplay;
        private List<Order> orders = new ArrayList<>();

        private String customerName;
        private String orderType;
        private int orderNumber;

        private Menu[] appetizers = {
                new Menu("Spring Rolls", 3.99, "Crispy rolls with vegetables"),
                new Menu("Garlic Bread", 2.99, "Buttery garlic bread")
        };
        private Menu[] mainCourses = {
                new Menu("Burger", 5.99, "Delicious beef burger"),
                new Menu("Pizza", 7.99, "Cheesy and tasty pizza"),
                new Menu("Pasta", 6.99, "Italian pasta with tomato sauce")
        };

        public MenuSelectionPage(String customerName, String orderType, int orderNumber) {
            this.customerName = customerName;
            this.orderType = orderType;
            this.orderNumber = orderNumber;

            frame = new JFrame("Menu Selection");
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            // Pilihan Kategori
            gbc.gridx = 0;
            gbc.gridy = 0;
            frame.add(new JLabel("Select Category:"), gbc);

            gbc.gridx = 1;
            categoryComboBox = new JComboBox<>(new String[]{"Appetizers", "Main Courses"});
            categoryComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateMenuItems();
                }
            });
            frame.add(categoryComboBox, gbc);

            // Pilihan Menu
            gbc.gridx = 0;
            gbc.gridy = 1;
            frame.add(new JLabel("Select Menu:"), gbc);

            gbc.gridx = 1;
            menuComboBox = new JComboBox<>(appetizers);
            frame.add(menuComboBox, gbc);

            // Input Quantity
            gbc.gridx = 0;
            gbc.gridy = 2;
            frame.add(new JLabel("Quantity:"), gbc);

            gbc.gridx = 1;
            quantityField = new JTextField(5);
            frame.add(quantityField, gbc);

            // Tombol Submit Pesanan
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.EAST;
            submitButton = new JButton("Place Order");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Menu selectedMenu = (Menu) menuComboBox.getSelectedItem();
                        int quantity = Integer.parseInt(quantityField.getText());

                        if (quantity <= 0) {
                            throw new IllegalArgumentException("Quantity must be greater than 0.");
                        }

                        // Create and store the order
                        Order order = new Order(customerName, orderType, orderNumber, selectedMenu, quantity);
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
            frame.add(submitButton, gbc);

            // Display area for orders
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            orderDisplay = new JTextArea(10, 40);
            orderDisplay.setEditable(false);
            frame.add(new JScrollPane(orderDisplay), gbc);

            frame.setVisible(true);
        }

        private void updateMenuItems() {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            menuComboBox.removeAllItems();
            Menu[] items = selectedCategory.equals("Appetizers") ? appetizers : mainCourses;
            for (Menu item : items) {
                menuComboBox.addItem(item);
            }
        }

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
                new RestaurantPage(); // Memulai dengan halaman restoran
            }
        });
    }
}
