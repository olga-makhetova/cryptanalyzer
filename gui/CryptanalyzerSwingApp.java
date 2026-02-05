package gui;

import ciphercore.ProcessType;

import javax.swing.*;
import java.io.File;

public class CryptanalyzerSwingApp {

    private final JFrame frame = new JFrame("cryptanalyzer");

    private final FilePanel srcFilePanel = new FilePanel(this);
    private final FilePanel dstFilePanel = new FilePanel(this);

    private final JTextField tfKey = new JTextField();

    private final ProcessButton cipherButton = new ProcessButton(this, ProcessType.CIPHER);
    private final ProcessButton encipherButton = new ProcessButton(this, ProcessType.ENCIPHER);
    private final ProcessButton bruteforceButton = new ProcessButton(this, ProcessType.BRUTEFORCE);

    public JFrame getFrame() {
        return frame;
    }

    public int getKey() {
        try {
            return Integer.parseInt(tfKey.getText());
        } catch (NumberFormatException ex) {
            showMsgErr("Заполните корректно поле Ключ");
        }
        return -1;
    }

    public boolean validateFiles() {
        String resultSrc = FileValidator.validateSrcFile(srcFilePanel.getFile());
        String resultDst = FileValidator.validateDstFile(dstFilePanel.getFile());
        if (resultSrc.isEmpty() && resultDst.isEmpty()) {
            return true;
        } else {
            showMsgErr(resultSrc + resultDst);
            return false;
        }
    }

    public File getSrcFile() {
        return srcFilePanel.getFile();
    }

    public File getDstFile() {
        return dstFilePanel.getFile();
    }

    public void showMsgInfo(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Информация", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMsgErr(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public void run() {
        // окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Dimensions.MAIN_WINDOW.getDimension());

        // панель с вертикальным расположением
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // две панели открытия файла
        JLabel lbSrcFile = new JLabel("Исходный файл");
        mainPanel.add(lbSrcFile);
        mainPanel.add(srcFilePanel);

        JLabel lbDstFile = new JLabel("Файл результата");
        mainPanel.add(lbDstFile);
        mainPanel.add(dstFilePanel);

        // панель с полем ключа
        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.X_AXIS));
        JLabel lKey = new JLabel("Ключ: ");
        keyPanel.add(lKey);
        tfKey.setMaximumSize(Dimensions.SMALL_TEXT_FIELD.getDimension());
        keyPanel.add(tfKey);
        mainPanel.add(keyPanel);

        // горизонтальная панель с кнопками
        JPanel processPanel = new JPanel();
        processPanel.setLayout(new BoxLayout(processPanel, BoxLayout.X_AXIS));
        processPanel.add(cipherButton);
        processPanel.add(encipherButton);
        processPanel.add(bruteforceButton);
        mainPanel.add(processPanel);

        // панель на окно
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}