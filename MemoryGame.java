import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemoryGame extends JFrame implements ActionListener {
    private JButton[] buttons;
    private int[] values;
    private int selectedIndex;
    private int matches;

    public MemoryGame() {
        super("Memory Game");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        buttons = new JButton[16];
        values = new int[16];

        for (int i = 0; i < 8; i++) {
            int value = i + 1;

            JButton button1 = new JButton("" + value);
            button1.setActionCommand("" + i);
            button1.addActionListener(this);

            JButton button2 = new JButton("" + value);
            button2.setActionCommand("" + i);
            button2.addActionListener(this);

            buttons[i] = button1;
            buttons[i + 8] = button2;
            values[i] = value;
            values[i + 8] = value;
        }

        for (int i = 0; i < 16; i++) {
            int j = (int)(Math.random() * 16);
            JButton tempButton = buttons[i];
            buttons[i] = buttons[j];
            buttons[j] = tempButton;
            int tempValue = values[i];
            values[i] = values[j];
            values[j] = tempValue;
        }

        for (int i = 0; i < 16; i++) {
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);

        selectedIndex = -1;
        matches = 0;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        int index = Integer.parseInt(button.getActionCommand());

        if (selectedIndex == -1) {
            button.setEnabled(false);
            selectedIndex = index;
        } else {
            if (values[selectedIndex] == values[index]) {
                matches++;
                buttons[selectedIndex].setVisible(false);
                buttons[index].setVisible(false);

                if (matches == 8) {
                    JOptionPane.showMessageDialog(this, "Vous avez gagné !");
                    System.exit(0);
                }
            } else {
                buttons[selectedIndex].setEnabled(true);
            }

            selectedIndex = -1;
        }
    }

    public static void main(String[] args) {
        new MemoryGame();
    }
}
