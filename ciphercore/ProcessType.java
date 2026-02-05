package ciphercore;

public enum ProcessType {
    CIPHER("Зашифровать"),
    ENCIPHER("Расшифровать"),
    BRUTEFORCE("Взломать!");

    private final String description;

    ProcessType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}