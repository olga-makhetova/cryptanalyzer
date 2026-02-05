package gui;

import ciphercore.FileProcessor;
import ciphercore.ProcessType;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ProcessButton extends JButton {
    private final CryptanalyzerSwingApp parentApp;
    private final ProcessType processType;

    public ProcessButton(CryptanalyzerSwingApp parentApp, ProcessType processType) {
        this.parentApp = parentApp;
        this.processType = processType;
        super(processType.getDescription());
        initComponents();
    }

    private void initComponents() {
        setMaximumSize(Dimensions.BUTTON.getDimension());
        addActionListener(_ -> process());
    }

    // для листенера
    private void process() {
        switch (processType) {
            case BRUTEFORCE -> bruteforce();
            case CIPHER, ENCIPHER -> cipherEncipher();
        }
    }

    // шифровка/расшифровка
    private void cipherEncipher() {
        if (!parentApp.validateFiles()) {
            return;
        }

        int key = parentApp.getKey();
        if (key == -1) {
            return;
        }

        try {
            FileProcessor processor = new FileProcessor(parentApp.getSrcFile(), parentApp.getDstFile(), key);
            processor.process(processType);
            parentApp.showMsgInfo("Готово!");
        } catch (IOException e) {
            parentApp.showMsgErr(e.getMessage());
        }
    }

    // брутфорс
    private void bruteforce() {
        if (!parentApp.validateFiles()) {
            return;
        }

        try {
            FileProcessor processor = new FileProcessor(parentApp.getSrcFile(), parentApp.getDstFile());
            int result = processor.bruteForce();
            parentApp.showMsgInfo(result == -1 ? "К сожалению, не удалось взломать шифр" : "Получилось! Ключ = " + result);
        } catch (IOException e) {
            parentApp.showMsgErr(e.getMessage());
        }
    }
}