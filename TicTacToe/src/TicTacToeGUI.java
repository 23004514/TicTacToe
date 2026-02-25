import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private TicTacToe model;
    private JButton[][] buttons;
    private JButton resetButton;

    public TicTacToeGUI() {
        model = new TicTacToe();
        buttons = new JButton[3][3];
        initUI();
    }

    private void initUI() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel grid = new JPanel(new GridLayout(3, 3));
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 48);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton b = new JButton(String.valueOf('-'));
                b.setFont(font);
                b.setFocusPainted(false);
                final int r = i, c = j;
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onCellClicked(r, c);
                    }
                });
                buttons[i][j] = b;
                grid.add(b);
            }
        }

        resetButton = new JButton("New Game");
        resetButton.addActionListener(e -> resetGame());

        add(grid, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onCellClicked(int row, int col) {
        if (model.makeMove(row, col)) {
            buttons[row][col].setText(String.valueOf(model.getBoard()[row][col]));
            buttons[row][col].setEnabled(false);

            if (model.checkWinner()) {
                JOptionPane.showMessageDialog(this, "Player " + model.getCurrentPlayer() + " wins!");
                disableBoard();
                return;
            }

            if (model.checkDraw()) {
                JOptionPane.showMessageDialog(this, "Draw!");
                disableBoard();
                return;
            }

            model.switchPlayer();
        }
    }

    private void disableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        model.reset();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf('-'));
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        // Ensure GUI created on EDT
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
