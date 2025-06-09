package utils;

import java.util.UUID;

public abstract  class Util {
    public static String criarUUID(String prefix) {
        return prefix + UUID.randomUUID().toString();
    }
}
