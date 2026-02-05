package gui;

import java.io.File;

public class FileValidator {
    public static String validateDstFile(File file) {
        if (file == null) {
            return "Файл назначения не указан";
        }

        if (file.exists() && file.isDirectory()) {
            return "Файл назначения является директорией";
        }

        if (file.exists() && !file.canWrite()) {
            return "Нет прав на запись в файл назначения";
        }

        // Проверка возможности создания файла (если не существует)
        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                return "Родительская директория для файла назначения не существует";
            }
            if (parentDir != null && !parentDir.canWrite()) {
                return "Нет прав на запись в родительскую директорию";
            }
        }

        return "";
    }

    public static String validateSrcFile(File file) {
        if (file == null) {
            return "Исходный файл не указан";
        }

        if (!file.exists()) {
            return "Исходный файл не существует";
        }

        if (file.isDirectory()) {
            return "Исходный файл является директорией";
        }

        if (!file.canRead()) {
            return "Нет прав на чтение исходного файла";
        }

        return "";
    }
}
