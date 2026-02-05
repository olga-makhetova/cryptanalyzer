package ciphercore;

/* Для брутфорса работаем с одной строкой без буфера */

public class CaesarCipherBruteforce {
    private final String cipheredString;
    private String encipheredString;

    public int getKey() {
        return key;
    }

    private int key;

    public CaesarCipherBruteforce(String cipheredString) {
        this.cipheredString = cipheredString;
    }

    // запуск брутфорса
    public String process() {
        for (key = -1; key < CaesarCipher.ALPHABET.length(); ++key) {
            CaesarCipher cipher = new CaesarCipher(key);
            encipheredString = cipher.encipherString(cipheredString);

            if (makeSense()) return encipheredString;
        }
        return null;
    }

    private boolean makeSense() {
        return checkPunctuation() && checkCountSpace();
    }

    // проверка, что пробелы составляют 10-25% всех символов.
    private boolean checkCountSpace() {
        int countSpace = 0;
        for (char c : encipheredString.toCharArray()) {
            if (c == ' ') countSpace++;
        }

        float prc = 100 * ((float) countSpace / encipheredString.length());
        System.out.println("countSpace=" + countSpace + ", prc: " + prc);
        return prc >= 10 && prc <= 25;
    }

    // проверка, что после определённых знаков пунктуации стоит пробел
    private boolean checkPunctuation() {
        int countPunctuations = 0;
        int countRightPunctuations = 0;
        for (int i = 0; i < encipheredString.length(); i++) {
            char c = encipheredString.charAt(i);
            if (CaesarCipher.PUNCTUATIONS.indexOf(c) == -1) {//проверяем только для определённых знаков пунктуации
                continue;
            }

            countPunctuations++;
            if (i == encipheredString.length() - 1 || encipheredString.charAt(i + 1) == ' ') {
                countRightPunctuations++;
            }
        }

        return countPunctuations == countRightPunctuations;
    }
}
