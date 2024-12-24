package Gambar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        private String imagePath;

        public Menu(String name, double price, String description, String imagePath) {
            this.name = name;
            this.price = price;
            this.description = description;
            this.imagePath = imagePath;
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

        public String getImagePath() {
            return imagePath;
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

        public String getCustomerName() {
            return customerName;
        }

        public String getOrderType() {
            return orderType;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public String getMenuItemName() {
            return menuItem.getName();
        }

        public int getQuantity() {
            return quantity;
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
            imageLabel.setIcon(new ImageIcon("Gambar/Restoran.jpeg")); // Ganti dengan path gambar Anda
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
        private JButton viewTableButton;
        private JTextArea orderDisplay;
        private JLabel foodImageLabel;
        private List<Order> orders = new ArrayList<>();

        private String customerName;
        private String orderType;
        private int orderNumber;

        private Menu[] appetizers = {
                new Menu("Lumpia", 3.99, "Gulungan sayur yang renyah", "Gambar/Lumpia.jpeg"),
                new Menu("Kentang Goreng", 2.99, "Kentang yang dipotong dan terasa renyah dan gurih", "Gambar/Kentang_Goreng.jpeg")
        };
        private Menu[] mainCourses = {
                new Menu("Burger", 5.99, "Burger sapi yang juicy", "Gambar/Burger.jpeg"),
                new Menu("Pizza", 7.99, "Pizza dengan topping keju leleh", "Gambar/Pizza.jpeg"),
                new Menu("Pasta", 6.99, "Pasta dengan saus khas italia", "Gambar/Pasta.jpeg")
        };
        private Menu[] desserts = {
                new Menu("Es Krim", 4.50, "Es krim rasa vanilla", "Gambar/Es_krim.jpeg"),
                new Menu("Kue", 5.00, "Kue rasa coklat", "Gambar/Kue.jpeg")
        };

        public MenuSelectionPage(String customerName, String orderType, int orderNumber) {
            this.customerName = customerName;
            this.orderType = orderType;
            this.orderNumber = orderNumber;

            frame = new JFrame("Menu Selection");
            frame.setSize(600, 600);
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
            categoryComboBox = new JComboBox<>(new String[]{"Appetizers", "Main Courses", "Desserts"});
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
            menuComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateFoodImage();
                }
            });
            frame.add(menuComboBox, gbc);

            // Tempat Gambar
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            foodImageLabel = new JLabel();
            foodImageLabel.setHorizontalAlignment(JLabel.CENTER);
            frame.add(foodImageLabel, gbc);
            updateFoodImage(); // Panggil metode pertama kali untuk menampilkan gambar default

            // Input Quantity
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            frame.add(new JLabel("Quantity:"), gbc);

            gbc.gridx = 1;
            quantityField = new JTextField(5);
            frame.add(quantityField, gbc);

            // Tombol Submit Pesanan
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.EAST;
            submitButton = new JButton("Place Order");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    placeOrder();
                }
            });
            frame.add(submitButton, gbc);

            // Tombol View Table
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            viewTableButton = new JButton("View Table");
            viewTableButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new OrderTablePage(orders);
                }
            });
            frame.add(viewTableButton, gbc);

            // Tampilkan Pesanan
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 2;
            orderDisplay = new JTextArea(10, 40);
            orderDisplay.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(orderDisplay);
            frame.add(scrollPane, gbc);

            frame.setVisible(true);
        }

        private void updateMenuItems() {
            String category = (String) categoryComboBox.getSelectedItem();
            switch (category) {
                case "Appetizers":
                    menuComboBox.setModel(new DefaultComboBoxModel<>(appetizers));
                    break;
                case "Main Courses":
                    menuComboBox.setModel(new DefaultComboBoxModel<>(mainCourses));
                    break;
                case "Desserts":
                    menuComboBox.setModel(new DefaultComboBoxModel<>(desserts));
                    break;
            }
            updateFoodImage();
        }

        private void updateFoodImage() {
            Menu selectedMenu = (Menu) menuComboBox.getSelectedItem();
            foodImageLabel.setIcon(new ImageIcon(selectedMenu.getImagePath()));
        }

        private void placeOrder() {
            try {
                Menu selectedMenu = (Menu) menuComboBox.getSelectedItem();
                int quantity = Integer.parseInt(quantityField.getText().trim());

                if (quantity <= 0) {
                    throw new IllegalArgumentException("Quantity must be greater than 0.");
                }

                for (Order order : orders) {
                    if (order.getMenuItemName().equals(selectedMenu.getName()) && order.getCustomerName().equals(customerName)) {
                        JOptionPane.showMessageDialog(frame, "This order already exists.", "Duplicate Order", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                Order order = new Order(customerName, orderType, orderNumber, selectedMenu, quantity);
                orders.add(order);
                orderDisplay.append(order.getOrderDetails() + "\n");
                quantityField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // GUI untuk Menampilkan Tabel Pesanan
    static class OrderTablePage {
        public OrderTablePage(List<Order> orders) {
            JFrame frame = new JFrame("Order Table");
            frame.setSize(600, 400);

            String[] columnNames = {"Customer Name", "Order Type", "Order Number", "Menu Item", "Quantity", "Total Price"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (Order order : orders) {
                Object[] row = {
                        order.getCustomerName(),
                        order.getOrderType(),
                        order.getOrderNumber(),
                        order.getMenuItemName(),
                        order.getQuantity(),
                        order.getTotalPrice()
                };
                tableModel.addRow(row);
            }

            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);

            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RestaurantPage::new);
    }
}
