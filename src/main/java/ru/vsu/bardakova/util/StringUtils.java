package ru.vsu.bardakova.util;

public class StringUtils {

    private StringUtils() {

    }

    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }
}
