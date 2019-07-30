import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class SimpleCalculator extends JPanel implements ActionListener {

    private JTextField display = new JTextField("0");
    private String operator = "=";
    private boolean calculating = true;
    private double[] temporary = {0, 0};

    public SimpleCalculator() {
        setLayout(new BorderLayout());
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, "North");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4));

        String[] buttonLabels = {"7", "8", "9", ",","4", "5", "6", "*","1","2","3","-","0",".","=","+","/" ,"<-", "C","Pow", "+/-"};
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton b = new JButton(buttonLabels[i]);
            panel.add(b);
            b.addActionListener(this);
        }
        add(panel, "Center");
    }

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        if ('0' <= cmd.charAt(0) && cmd.charAt(0) <= '9' || cmd.equals(".")) {
            if (display.getText().length()-1 == 0 && display.getText().charAt(0) == '0' && !cmd.equals("."))  {
                display.setText(cmd);
            } else {
                display.setText(display.getText() + cmd);
            }
        } else if (cmd.equals("+/-")) {
            if (display.getText().charAt(0) == '-') {
                display.setText(display.getText().replace("-",""));
            } else {
                display.setText("-" + display.getText());
            }
        } else if (cmd.equals("<-")) {
            if (display.getText().length()-1 > 0){
                display.setText(display.getText().substring(0,display.getText().length()-1));
            } else {
                display.setText("0");
            }
        } else if (cmd.equals("C")) {
            display.setText("0");
            temporary[0] = 0;
            temporary[1] = 0;
        } else {
            if (calculating && !cmd.equals("=")) {
                temporary[0] = Double.parseDouble(display.getText());
                calculating = false;
                operator = cmd;
                display.setText("0");
            } else {
                if (!calculating) {
                    temporary[1] =  Double.parseDouble(display.getText());
                }
                temporary[0] = calculate(temporary[0],temporary[1]);
                calculating = true;
            }

        }
    }

    private double calculate(double firstNumber, double secondNumber) {
        double result = 0;
        if (operator.equals("+"))
            result = firstNumber + secondNumber;
        else if (operator.equals("-"))
            result = firstNumber - secondNumber;
        else if (operator.equals("*"))
            result = firstNumber * secondNumber;
        else if (operator.equals("/"))
            result = firstNumber / secondNumber;
        else if (operator.equals("Pow")) {
            result = Math.pow(firstNumber,secondNumber);
        }
        display.setText("" + result);
        return result;

    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.add(new SimpleCalculator());
        frame.setVisible(true);
    }
}