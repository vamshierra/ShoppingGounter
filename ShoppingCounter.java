import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShoppingCounter extends JFrame implements ActionListener {
    JComboBox<String> productBox;
    JTextField qtyField;
    JTextArea cartArea;
    JLabel totalLabel;
    JButton addBtn, clearBtn, checkoutBtn;

    int[] prices = {0, 15000, 50000, 2000, 3000};
    int grandTotal = 0;

    ShoppingCounter() {
        setTitle("Shopping Counter - VAMSHI ERRA");
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("SHOPPING CART SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(50, 10, 400, 30);
        add(title);

        JLabel pLabel = new JLabel("Select Product:");
        pLabel.setBounds(30, 60, 120, 25);
        add(pLabel);

        String[] products = {"Select Product", "Mobile - Rs. 15000", "Laptop - Rs. 50000", "Headphones - Rs. 2000", "Shoes - Rs. 3000"};
        productBox = new JComboBox<>(products);
        productBox.setBounds(160, 60, 200, 25);
        add(productBox);

        JLabel qLabel = new JLabel("Quantity:");
        qLabel.setBounds(30, 100, 120, 25);
        add(qLabel);

        qtyField = new JTextField();
        qtyField.setBounds(160, 100, 200, 25);
        add(qtyField);

        addBtn = new JButton("Add to Cart");
        addBtn.setBounds(30, 140, 120, 35);
        addBtn.setBackground(new Color(0, 102, 204));
        addBtn.setForeground(Color.WHITE);
        addBtn.addActionListener(this);
        add(addBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(180, 140, 100, 35);
        clearBtn.addActionListener(this);
        add(clearBtn);

        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBounds(300, 140, 120, 35);
        checkoutBtn.setBackground(new Color(0, 153, 51));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.addActionListener(this);
        add(checkoutBtn);

        cartArea = new JTextArea();
        cartArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(cartArea);
        scroll.setBounds(30, 190, 420, 200);
        add(scroll);

        totalLabel = new JLabel("Grand Total: Rs. 0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setBounds(30, 410, 300, 30);
        add(totalLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            try {
                int index = productBox.getSelectedIndex();
                if (index == 0) {
                    JOptionPane.showMessageDialog(this, "Please select a product");
                    return;
                }
                int qty = Integer.parseInt(qtyField.getText());
                int total = prices[index] * qty;
                grandTotal += total;

                String item = productBox.getSelectedItem() + " x " + qty + " = Rs. " + total + "\n";
                cartArea.append(item);
                totalLabel.setText("Grand Total: Rs. " + grandTotal);
                qtyField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid quantity");
            }
        }
        if (e.getSource() == clearBtn) {
            cartArea.setText("");
            grandTotal = 0;
            totalLabel.setText("Grand Total: Rs. 0");
            qtyField.setText("");
        }
        if (e.getSource() == checkoutBtn) {
            if (grandTotal == 0) {
                JOptionPane.showMessageDialog(this, "Cart is empty!");
            } else {
                JOptionPane.showMessageDialog(this, "Order Placed!\nGrand Total: Rs. " + grandTotal + "\nThank you VAMSHI!");
                cartArea.setText("");
                grandTotal = 0;
                totalLabel.setText("Grand Total: Rs. 0");
            }
        }
    }

    public static void main(String[] args) {
        new ShoppingCounter();
    }
}