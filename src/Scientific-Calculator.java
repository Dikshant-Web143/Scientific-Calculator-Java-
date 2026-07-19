import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends JFrame {

    private JTextField display;
    private JTextArea historyArea;
    private JLabel modeLabel;

    private boolean degreeMode = true;
    private double memory = 0;
    private String lastAnswer = "0";

    public ScientificCalculator() {

        setTitle("Scientific Calculator");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createGUI();
    }

    // ================= CREATE GUI =================

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBackground(new Color(235, 235, 235));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // ================= DISPLAY =================

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(new Color(235, 235, 235));

        display = new JTextField("0");

        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);

        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setCaretColor(Color.BLACK);

        display.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        2
                )
        );

        modeLabel = new JLabel("DEG");

        modeLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        modeLabel.setForeground(Color.BLACK);
        modeLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        modeLabel.setBackground(Color.WHITE);
        modeLabel.setOpaque(true);

        modeLabel.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        2
                )
        );

        modeLabel.setPreferredSize(
                new Dimension(75, 60)
        );

        topPanel.add(
                display,
                BorderLayout.CENTER
        );

        topPanel.add(
                modeLabel,
                BorderLayout.EAST
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        // ================= BUTTON PANEL =================

        JPanel buttonPanel = new JPanel(
                new GridLayout(8, 7, 7, 7)
        );

        buttonPanel.setBackground(
                new Color(235, 235, 235)
        );

        String[] buttons = {

                "MC", "MR", "M+", "M-", "MS", "DEG", "RAD",

                "sin", "cos", "tan", "asin", "acos", "atan", "π",

                "sinh", "cosh", "tanh", "ln", "log", "√", "e",

                "x²", "x³", "xʸ", "1/x", "|x|", "n!", "%",

                "(", ")", "7", "8", "9", "÷", "C",

                "Ans", "4", "5", "6", "×", "⌫", "CE",

                "2nd", "1", "2", "3", "-", "=", "History",

                "±", "0", ".", "+", "EXP", "Exit",
                "Clear History"
        };

        for (String text : buttons) {

            JButton button =
                    createButton(text);

            buttonPanel.add(button);
        }

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // ================= HISTORY =================

        historyArea = new JTextArea();

        historyArea.setEditable(false);

        historyArea.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        14
                )
        );

        historyArea.setBackground(Color.WHITE);
        historyArea.setForeground(Color.BLACK);

        JScrollPane scrollPane =
                new JScrollPane(historyArea);

        scrollPane.setPreferredSize(
                new Dimension(270, 0)
        );

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(
                                Color.BLACK
                        ),
                        "Calculation History",
                        0,
                        0,
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        ),
                        Color.BLACK
                )
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.EAST
        );

        add(mainPanel);

        // ================= KEYBOARD SUPPORT =================

        display.addKeyListener(
                new KeyAdapter() {

                    @Override
                    public void keyPressed(
                            KeyEvent e
                    ) {

                        if (
                                e.getKeyCode()
                                        ==
                                        KeyEvent.VK_ENTER
                        ) {

                            calculate();
                        }

                        if (
                                e.getKeyCode()
                                        ==
                                        KeyEvent.VK_ESCAPE
                        ) {

                            display.setText("0");
                        }
                    }
                }
        );
    }

    // ================= CREATE BUTTON =================

    private JButton createButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        // BUTTON BACKGROUND

        button.setBackground(Color.WHITE);

        // ALL BUTTON TEXT BLACK

        button.setForeground(Color.BLACK);

        button.setFocusPainted(false);

        // BLACK BORDER

        button.setBorder(
                BorderFactory.createLineBorder(
                        Color.BLACK,
                        1
                )
        );

        button.addActionListener(
                e -> handleButton(text)
        );

        return button;
    }

    // ================= BUTTON HANDLER =================

    private void handleButton(
            String command
    ) {

        switch (command) {

            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
            case "(":
            case ")":

                appendText(command);

                break;

            case "+":
            case "-":
            case "×":
            case "÷":
            case "xʸ":

                appendText(
                        " " + command + " "
                );

                break;

            case "C":
            case "CE":

                display.setText("0");

                break;

            case "⌫":

                backspace();

                break;

            case "=":

                calculate();

                break;

            case "π":

                appendText(
                        String.valueOf(Math.PI)
                );

                break;

            case "e":

                appendText(
                        String.valueOf(Math.E)
                );

                break;

            case "sin":

                applyFunction("sin");

                break;

            case "cos":

                applyFunction("cos");

                break;

            case "tan":

                applyFunction("tan");

                break;

            case "asin":

                applyFunction("asin");

                break;

            case "acos":

                applyFunction("acos");

                break;

            case "atan":

                applyFunction("atan");

                break;

            case "sinh":

                applyFunction("sinh");

                break;

            case "cosh":

                applyFunction("cosh");

                break;

            case "tanh":

                applyFunction("tanh");

                break;

            case "ln":

                applyFunction("ln");

                break;

            case "log":

                applyFunction("log");

                break;

            case "√":

                applyFunction("sqrt");

                break;

            case "x²":

                applyFunction("square");

                break;

            case "x³":

                applyFunction("cube");

                break;

            case "1/x":

                applyFunction("inverse");

                break;

            case "|x|":

                applyFunction("abs");

                break;

            case "n!":

                applyFunction("factorial");

                break;

            case "%":

                applyFunction("percent");

                break;

            case "±":

                changeSign();

                break;

            case "Ans":

                appendText(lastAnswer);

                break;

            case "DEG":

                degreeMode = true;

                modeLabel.setText("DEG");

                break;

            case "RAD":

                degreeMode = false;

                modeLabel.setText("RAD");

                break;

            case "MC":

                memory = 0;

                break;

            case "MR":

                appendText(
                        String.valueOf(memory)
                );

                break;

            case "M+":

                memory += getCurrentValue();

                break;

            case "M-":

                memory -= getCurrentValue();

                break;

            case "MS":

                memory = getCurrentValue();

                break;

            case "EXP":

                appendText("E");

                break;

            case "History":

                JOptionPane.showMessageDialog(
                        this,
                        historyArea.getText(),
                        "Calculation History",
                        JOptionPane.INFORMATION_MESSAGE
                );

                break;

            case "Clear History":

                historyArea.setText("");

                break;

            case "Exit":

                System.exit(0);

                break;
        }
    }

    // ================= APPEND TEXT =================

    private void appendText(
            String text
    ) {

        if (
                display.getText()
                        .equals("0")
        ) {

            display.setText(text);
        }
        else {

            display.setText(
                    display.getText() + text
            );
        }
    }

    // ================= BACKSPACE =================

    private void backspace() {

        String text =
                display.getText();

        if (
                text.length() > 1
        ) {

            display.setText(
                    text.substring(
                            0,
                            text.length() - 1
                    )
            );
        }
        else {

            display.setText("0");
        }
    }

    // ================= CHANGE SIGN =================

    private void changeSign() {

        try {

            double value =
                    Double.parseDouble(
                            display.getText()
                    );

            display.setText(
                    format(-value)
            );
        }
        catch (Exception e) {

            showError();
        }
    }

    // ================= SCIENTIFIC FUNCTIONS =================

    private void applyFunction(
            String function
    ) {

        try {

            double value =
                    getCurrentValue();

            double result = 0;

            switch (function) {

                case "sin":

                    result =
                            degreeMode
                                    ?
                                    Math.sin(
                                            Math.toRadians(
                                                    value
                                            )
                                    )
                                    :
                                    Math.sin(value);

                    break;

                case "cos":

                    result =
                            degreeMode
                                    ?
                                    Math.cos(
                                            Math.toRadians(
                                                    value
                                            )
                                    )
                                    :
                                    Math.cos(value);

                    break;

                case "tan":

                    result =
                            degreeMode
                                    ?
                                    Math.tan(
                                            Math.toRadians(
                                                    value
                                            )
                                    )
                                    :
                                    Math.tan(value);

                    break;

                case "asin":

                    result =
                            Math.asin(value);

                    if (degreeMode) {

                        result =
                                Math.toDegrees(
                                        result
                                );
                    }

                    break;

                case "acos":

                    result =
                            Math.acos(value);

                    if (degreeMode) {

                        result =
                                Math.toDegrees(
                                        result
                                );
                    }

                    break;

                case "atan":

                    result =
                            Math.atan(value);

                    if (degreeMode) {

                        result =
                                Math.toDegrees(
                                        result
                                );
                    }

                    break;

                case "sinh":

                    result =
                            Math.sinh(value);

                    break;

                case "cosh":

                    result =
                            Math.cosh(value);

                    break;

                case "tanh":

                    result =
                            Math.tanh(value);

                    break;

                case "ln":

                    result =
                            Math.log(value);

                    break;

                case "log":

                    result =
                            Math.log10(value);

                    break;

                case "sqrt":

                    result =
                            Math.sqrt(value);

                    break;

                case "square":

                    result =
                            Math.pow(
                                    value,
                                    2
                            );

                    break;

                case "cube":

                    result =
                            Math.pow(
                                    value,
                                    3
                            );

                    break;

                case "inverse":

                    result =
                            1 / value;

                    break;

                case "abs":

                    result =
                            Math.abs(value);

                    break;

                case "percent":

                    result =
                            value / 100;

                    break;

                case "factorial":

                    result =
                            factorial(
                                    (int) value
                            );

                    break;
            }

            display.setText(
                    format(result)
            );

            lastAnswer =
                    String.valueOf(result);
        }
        catch (Exception e) {

            showError();
        }
    }

    // ================= FACTORIAL =================

    private long factorial(
            int number
    ) {

        if (
                number < 0
        ) {

            throw new IllegalArgumentException();
        }

        long result = 1;

        for (
                int i = 1;
                i <= number;
                i++
        ) {

            result *= i;
        }

        return result;
    }

    // ================= CALCULATE =================

    private void calculate() {

        try {

            String expression =
                    display.getText();

            double result =
                    evaluateExpression(
                            expression
                    );

            String answer =
                    format(result);

            historyArea.append(
                    expression
                            + " = "
                            + answer
                            + "\n"
            );

            display.setText(answer);

            lastAnswer =
                    String.valueOf(result);
        }
        catch (Exception e) {

            showError();
        }
    }

    // ================= CURRENT VALUE =================

    private double getCurrentValue() {

        String text =
                display.getText();

        try {

            return Double.parseDouble(text);
        }
        catch (Exception e) {

            return evaluateExpression(text);
        }
    }

    // ================= FORMAT =================

    private String format(
            double value
    ) {

        if (
                value == (long) value
        ) {

            return String.valueOf(
                    (long) value
            );
        }

        return String.format(
                "%.12f",
                value
        )
                .replaceAll(
                        "0+$",
                        ""
                )
                .replaceAll(
                        "\\.$",
                        ""
                );
    }

    // ================= EVALUATE EXPRESSION =================

    private double evaluateExpression(
            String expression
    ) {

        expression =
                expression
                        .replace(
                                "×",
                                "*"
                        )
                        .replace(
                                "÷",
                                "/"
                        )
                        .replace(
                                "xʸ",
                                "^"
                        )
                        .replace(
                                " ",
                                ""
                        );

        return new ExpressionParser(
                expression
        ).parse();
    }

    // ================= ERROR =================

    private void showError() {

        display.setText("Error");

        Timer timer =
                new Timer(
                        1200,
                        e -> display.setText("0")
                );

        timer.setRepeats(false);

        timer.start();
    }

    // ================= EXPRESSION PARSER =================

    private static class ExpressionParser {

        private final String expression;

        private int position = -1;

        private int character;

        ExpressionParser(
                String expression
        ) {

            this.expression =
                    expression;
        }

        void nextCharacter() {

            position++;

            if (
                    position
                            <
                            expression.length()
            ) {

                character =
                        expression.charAt(
                                position
                        );
            }
            else {

                character = -1;
            }
        }

        boolean eat(
                int charToEat
        ) {

            while (
                    character == ' '
            ) {

                nextCharacter();
            }

            if (
                    character
                            ==
                            charToEat
            ) {

                nextCharacter();

                return true;
            }

            return false;
        }

        double parse() {

            nextCharacter();

            double value =
                    parseExpression();

            if (
                    position
                            <
                            expression.length()
            ) {

                throw new RuntimeException(
                        "Unexpected character"
                );
            }

            return value;
        }

        double parseExpression() {

            double value =
                    parseTerm();

            while (true) {

                if (
                        eat('+')
                ) {

                    value += parseTerm();
                }
                else if (
                        eat('-')
                ) {

                    value -= parseTerm();
                }
                else {

                    return value;
                }
            }
        }

        double parseTerm() {

            double value =
                    parsePower();

            while (true) {

                if (
                        eat('*')
                ) {

                    value *= parsePower();
                }
                else if (
                        eat('/')
                ) {

                    value /= parsePower();
                }
                else {

                    return value;
                }
            }
        }

        double parsePower() {

            double value =
                    parseFactor();

            if (
                    eat('^')
            ) {

                value =
                        Math.pow(
                                value,
                                parsePower()
                        );
            }

            return value;
        }

        double parseFactor() {

            if (
                    eat('+')
            ) {

                return parseFactor();
            }

            if (
                    eat('-')
            ) {

                return -parseFactor();
            }

            double value;

            int startPosition =
                    position;

            if (
                    eat('(')
            ) {

                value =
                        parseExpression();

                if (
                        !eat(')')
                ) {

                    throw new RuntimeException(
                            "Missing bracket"
                    );
                }
            }
            else {

                while (
                        (
                                character
                                        >=
                                        '0'
                                        &&
                                        character
                                                <=
                                                '9'
                        )
                                ||
                                character
                                        ==
                                        '.'
                                ||
                                character
                                        ==
                                        'E'
                                ||
                                character
                                        ==
                                        'e'
                ) {

                    nextCharacter();
                }

                if (
                        startPosition
                                ==
                                position
                ) {

                    throw new RuntimeException(
                            "Unexpected character"
                    );
                }

                value =
                        Double.parseDouble(
                                expression.substring(
                                        startPosition,
                                        position
                                )
                        );
            }

            return value;
        }
    }

    // ================= MAIN METHOD =================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    try {

                        UIManager.setLookAndFeel(
                                UIManager
                                        .getSystemLookAndFeelClassName()
                        );
                    }
                    catch (
                            Exception ignored
                    ) {
                    }

                    ScientificCalculator calculator =
                            new ScientificCalculator();

                    calculator.setVisible(true);
                }
        );
    }
}
