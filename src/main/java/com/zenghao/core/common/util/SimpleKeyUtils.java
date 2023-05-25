package com.zenghao.core.common.util;

import java.util.Random;
import java.util.UUID;


public abstract class SimpleKeyUtils {

    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    public static char[] hexChars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    public static String genShortUuid() {
        return genShortUuid(8);
    }

    public static String genShortUuid(int bit) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < bit; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static String genHexKey(int bit) {
        StringBuilder sb = new StringBuilder(bit);
        Random random = new Random((int) Math.random() * 9999);

        for (int i = 0; i < bit; i++) {
            sb.append(hexChars[Math.abs(random.nextInt()) % 16]);
        }
        return sb.toString();
    }

    public static String genUUID() {
        String uuid = UUID.randomUUID().toString();  //转化为String对象
        return uuid.replace("-", "");
    }
}
