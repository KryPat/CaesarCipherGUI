import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CaesarCipher extends JFrame {
    private JTextField txtKey;
    private JTextArea txtIn;
    private JTextArea txtOut;
    private JSlider slider;
    private JButton btnMoveUp;
    public String encode( String message, int keyVal ){
        String output = "";
        char key = (char) keyVal;
        for ( int x = 0; x < message.length(); x++ ) {
            char input = message.charAt(x);
            if (input >= 'A' && input <= 'Z')
            {
                input += key;
                if (input > 'Z')
                    input -= 26;
                if (input < 'A')
                    input += 26;
            }
            else if (input >= 'a' && input <= 'z')
            {
                input += key;
                if (input > 'z')
                    input -= 26;
                if (input < 'a')
                    input += 26;
            }
            else if (input >= '0' && input <= '9')
            {
                input += (keyVal % 10);
                if (input > '9')
                    input -= 10;
                if (input < '0')
                    input += 10;
            }
            output += input;
        }
        return output;
    }
    public CaesarCipher() {
        getContentPane().setBackground(new Color(248, 248, 255));
        setTitle("Secret Messenger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        txtIn = new JTextArea();
        txtIn.setCaretColor(new Color(0, 0, 0));
        txtIn.setBounds(new Rectangle(1, 1, 1, 1));
        txtIn.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
        txtIn.setWrapStyleWord(true);
        txtIn.setLineWrap(true);
        txtIn.setFont(new Font("Lucida Console", Font.PLAIN, 18));
        txtIn.setBounds(10, 11, 564, 140);
        getContentPane().add(txtIn);

        txtOut = new JTextArea();
        txtOut.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
        txtOut.setWrapStyleWord(true);
        txtOut.setLineWrap(true);
        txtOut.setFont(new Font("Lucida Console", Font.PLAIN, 18));
        txtOut.setBounds(10, 210, 564, 140);
        getContentPane().add(txtOut);

        txtKey = new JTextField();
        txtKey.setHorizontalAlignment(SwingConstants.CENTER);
        txtKey.setText("3");
        txtKey.setBounds(258, 173, 44, 20);
        getContentPane().add(txtKey);
        txtKey.setColumns(10);

        JLabel lblKey = new JLabel("Key:");
        lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
        lblKey.setBounds(202, 176, 46, 14);
        getContentPane().add(lblKey);

        JButton btnAction = new JButton("Send");
        btnAction.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        btnAction.setBackground(new Color(255, 255, 255));
        btnAction.addActionListener(arg0 -> {
            try {
                String message = txtIn.getText();
                int key = Integer.parseInt( txtKey.getText() );
                String output = encode( message, key );
                txtOut.setText( output );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Please enter a whole number value for the encryption key.");
                txtKey.requestFocus();
                txtKey.selectAll();
            }
        });
        btnAction.setBounds(312, 168, 144, 29);
        getContentPane().add(btnAction);

        slider = new JSlider();
        slider.addChangeListener(arg0 -> {
            txtKey.setText( "" + slider.getValue() );
            String message = txtIn.getText();
            int key = slider.getValue();
            String output = encode( message, key );
            txtOut.setText( output );
        });
        slider.setValue(0);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(13);
        slider.setMinorTickSpacing(1);
        slider.setMinimum(-26);
        slider.setMaximum(26);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(248, 248, 255));
        slider.setBounds(10, 162, 200, 45);
        getContentPane().add(slider);

        btnMoveUp = new JButton("Move UP ⮝");
        btnMoveUp.addActionListener(e -> {
            String temp = txtIn.getText();
            txtIn.setText(txtOut.getText());
            slider.setValue(-slider.getValue());
        });
        btnMoveUp.setBounds(460, 168, 105, 28);
        getContentPane().add(btnMoveUp);
    }

    public static void main(String[] args) {
        CaesarCipher theApp = new CaesarCipher();
        theApp.setSize(new Dimension(600,400));
        theApp.setVisible(true);

    }
}