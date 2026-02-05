package ciphercore;

public class CaesarCipher {
    public static final String LETTERS = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    public static final String PUNCTUATIONS = ".,:!?";
    public static final String ALPHABET = LETTERS + PUNCTUATIONS + " -”";
    private final int key;

    public CaesarCipher(int key) {
        this.key = key;
    }

    //находит сдвинутый символ
    private static String shiftChar(char ch, int shift) {
        int index = ALPHABET.indexOf(Character.toLowerCase(ch));
        if (index == -1) return "";
        return "" + ALPHABET.charAt((index + shift) % ALPHABET.length());
    }

    // вспомогательная функция - сдвиг каждого символа
    private String processString(String s, int shift) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(shiftChar(s.charAt(i), shift));
        }
        return sb.toString();
    }

    // функция шифровки
    public String cipherString(String s) {
        return processString(s, key);
    }

    // функция расшифровки
    public String encipherString(String s) {
        return processString(s, ALPHABET.length() - key);
    }
}
