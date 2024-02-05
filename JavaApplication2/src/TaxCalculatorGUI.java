import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxCalculatorGUI extends JFrame {

    private JTextField ageTextField;
    private JTextField taxYearTextField;
    private JTextField incomeTextField;
    private JTextArea resultTextArea;

    public TaxCalculatorGUI() {
        setTitle("Bug Slayers Tax Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        ageTextField = new JTextField();
        taxYearTextField = new JTextField();
        incomeTextField = new JTextField();
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        inputPanel.add(new JLabel("Enter your age: "));
        inputPanel.add(ageTextField);
        inputPanel.add(new JLabel("Enter the tax year: "));
        inputPanel.add(taxYearTextField);
        inputPanel.add(new JLabel("Enter your annual salary: "));
        inputPanel.add(incomeTextField);

        JButton calculateButton = new JButton("Calculate Tax");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTax();
            }
        });

        inputPanel.add(new JLabel());
        inputPanel.add(calculateButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calculateTax() {
        try {
            int age = Integer.parseInt(ageTextField.getText());
            int taxYear = Integer.parseInt(taxYearTextField.getText());
            double income = Double.parseDouble(incomeTextField.getText());
            double taxedAmount = calculateTax2024(income);
            double remainingSalary = income - taxedAmount;

            resultTextArea.setText(String.format("Your tax for the year %d is: R%.2f%n", taxYear, taxedAmount) +
                    String.format("Your remaining annual salary after tax is: R%.2f%n", remainingSalary));
        } catch (NumberFormatException ex) {
            resultTextArea.setText("Invalid input. Please enter valid numbers for age, tax year, and income.");
        }
    }

    public static double calculateTax2024(double income) {
        double taxedAmount = 0;

        if (income > 1 && income <= 237100) {
            taxedAmount = income * 0.18;
        } else if (income > 237100 && income <= 370500) {
            taxedAmount = 42678 + ((income - 237100) * 0.26);
        } else if (income > 370500 && income <= 512800) {
            taxedAmount = 77362 + ((income - 370500) * 0.31);
        } else if (income > 512800 && income <= 673000) {
            taxedAmount = 121475 + ((income - 512800) * 0.36);
        } else if (income > 673000 && income <= 857900) {
            taxedAmount = 179147 + ((income - 673000) * 0.39);
        } else if (income > 857900 && income <= 1817000) {
            taxedAmount = 251258 + ((income - 857900) * 0.41);
        } else if (income > 1817000) {
            taxedAmount = 644489 + ((income - 1817000) * 0.45);
        } else {
            System.out.println("Tax calculation not implemented for the specified year.");
        }
        return taxedAmount;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaxCalculatorGUI());
    }
}
