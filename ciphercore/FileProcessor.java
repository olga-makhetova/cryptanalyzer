package ciphercore;

import java.io.*;

public class FileProcessor {
    private final File srcFile;
    private final File dstFile;
    private CaesarCipher cipher;

    public FileProcessor(File srcFile, File dstFile, int key) {
        this.srcFile = srcFile;
        this.dstFile = dstFile;
        cipher = new CaesarCipher(key);
    }


    public FileProcessor(File srcFile, File dstFile) {
        this.srcFile = srcFile;
        this.dstFile = dstFile;
    }

    // Зашифровка/расшифровка в зависимости от типа
    public void process(ProcessType processType) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(srcFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(dstFile))
        ) {
            String bufferLine;

            while ((bufferLine = reader.readLine()) != null) {
                switch (processType) {
                    case ProcessType.CIPHER -> writer.write(cipher.cipherString(bufferLine));
                    case ProcessType.ENCIPHER -> writer.write(cipher.encipherString(bufferLine));
                }
                writer.newLine();
            }
        }
    }

    // Брутфорс, возвращает найденный ключ
    public int bruteForce() throws IOException {
        try (var reader = new FileReader(srcFile);
             var writer = new FileWriter(dstFile)
        ) {
            CaesarCipherBruteforce bf = new CaesarCipherBruteforce(reader.readAllAsString());
            String result = bf.process();
            if (result != null) {
                writer.write(result);
                return bf.getKey();
            } else {
                return -1;
            }
        }
    }
}
