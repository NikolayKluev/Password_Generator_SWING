import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.util.Random;

public class VisualForm extends JFrame {

    JTextField password = new JTextField(SwingConstants.LEFT);
    JButton getPassword = new JButton("Generate");
    JButton copyButton = new JButton("Copy");

    public VisualForm() {
        super("Password");

        JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT));
        content.setLayout(null);
        setSize(300, 200);
        setLocation(900, 400);
        setVisible(true);

        password.setBounds(70, 20, 150, 30);
        getPassword.setBounds(70, 70, 150, 30);
        copyButton.setBounds(85, 120, 120, 30);

        content.add(password);
        content.add(getPassword);
        content.add(copyButton);

        getPassword.addActionListener(new GenerateButtonAction());
        copyButton.addActionListener(new CopyButtonListener());

        setContentPane(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class GenerateButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            password.setText(getPassword().toString());
        }

        public ByteArrayOutputStream getPassword() {
            int[] result = new int[8];
            int[] random = new int[8];
            boolean isNumber = false;
            boolean isUpCase = false;
            boolean isDCase = false;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int i = 0; i < random.length; i++) {
                if ((i == random.length - 2 || i == random.length - 1) &&
                        (isNumber == false || isDCase == false || isUpCase == false)) {
                    if (isNumber == false) {
                        random[i] = 0;
                        isNumber = true;
                    }
                    if (isUpCase == false) {
                        random[i] = 1;
                        isUpCase = true;
                    }
                    if (isDCase == false) {
                        random[i] = 2;
                        isDCase = true;
                    }
                } else
                    random[i] = new Random().nextInt(3);
                switch (random[i]) {
                    case 0:
                        result[i] = new Random().nextInt(48, 58);
                        isNumber = true;
                        break;
                    case 1:
                        result[i] =  new Random().nextInt(65, 91);
                        isUpCase = true;
                        break;
                    case 2:
                        result[i] =  new Random().nextInt(97, 123);
                        isDCase = true;
                        break;
                }
                baos.write(result[i]);
            }
            return baos;
        }
    }

    private class CopyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection copyString = new StringSelection(password.getText());
            clip.setContents(copyString, copyString);
        }
    }
}
