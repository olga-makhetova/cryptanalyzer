package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;


// панель открытия файла
public class FilePanel extends JPanel {
    private File startDirectory = new File(System.getProperty("user.dir"));
    private File file;
    private final CryptanalyzerSwingApp parentApp;
    JTextField tfFileName = new JTextField();
    JButton btOpenFile = new JButton("…");

    public File getFile() {
        return file;
    }

    public FilePanel(CryptanalyzerSwingApp parentApp) {
        this.parentApp = parentApp;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // текстовое поле
        tfFileName.setMaximumSize(Dimensions.TEXT_FIELD.getDimension());
        tfFileName.setEditable(false);
        add(tfFileName);

        // кнопка
        btOpenFile.setMaximumSize(Dimensions.SMALL_BUTTON.getDimension());
        btOpenFile.addActionListener(_ -> openFileDialog());
        add(btOpenFile);
    }

    private void openFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(startDirectory);

        if (fileChooser.showOpenDialog(parentApp.getFrame()) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            startDirectory = fileChooser.getCurrentDirectory();
            tfFileName.setText(file.getAbsolutePath());
        }
    }
}