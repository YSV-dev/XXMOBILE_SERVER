package com.swlibs.common.system;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

public class Numbers {

    public static String asString(Integer value) {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }
    }

    public static String asString(Long value) {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }
    }

    public static String asString(Float value) {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }
    }

    public static String intToStr(Integer v) {
        if (v == null) {
            return null;
        }
        return String.format(Locale.FRANCE, "%d", v).replace('.', ','); //TODO: replace?
    }

    public static String intToStr(Integer v, String defaultValue) {
        if (v == null) {
            return defaultValue;
        }
        return String.format(Locale.FRANCE, "%d", v);
    }

    public static String floatToStr(Float v) {
        if (v == null) {
            return null;
        }
        return String.format(Locale.FRANCE, "%1.2f", v).replace('.', ',');
    }

    public static String floatToStr(Float v, String s) {
        return floatToStr(v, s, 2);
    }

    public static String floatToStr(Float v, String s, int l) {
        if (v == null) {
            return null;
        }
        if(s.equals(".")) {
            return String.format(Locale.FRANCE, "%1."+ l +"f", v).replace(',', '.');
        }else{
            return String.format(Locale.FRANCE, "%1."+ l +"f", v).replace('.', ',');
        }
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    public static Float parseFloat(String value){
        if(value != null){
            if(value.equals("")){
                return null;
            }
            String result  = value.replace(",",".");
            return Float.parseFloat(result);
        }
        return null;
        //return value == null || value.isEmpty() ? 0 : Float.parseFloat(value.replace(',', '.'));
    }

    public static short toShort(byte[] bytes) {
        if(bytes.length < 2) {
            throw new RuntimeException();
        }
        int temp = bytes[0] & 0xFF;
        temp = (temp << 8) | (bytes[1] & 0xFF);
        return (short)temp;
    }

    public static short toShort(byte byte1, byte byte2) {
        int temp = byte1 & 0xFF;
        temp = (temp << 8) | (byte2 & 0xFF);
        return (short)temp;
    }

    public static int toInt(byte byte1, byte byte2, byte byte3, byte byte4) {
        return ( ( (byte1 & 0xFF) << 24) | ( (byte2 & 0xFF) << 16) | ( (byte3 & 0xFF) << 8) | (byte4 & 0xFF) );
    }

    public static byte[] toArrayBe(short value){
        return ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN).putShort(value).array();
    }

    public static byte[] toArrayLe(short value){
        return ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort(value).array();
    }

    public static byte[] toArrayBe(int value){
        return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(value).array();
    }

    public static byte[] toArrayLe(int value){
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }

    public static double round2(double value) {
        return (double) Math.round(value * 100f) / 100f;
    }

    public static String getRuLiteral(int num, String a, String b, String c)
    {
        int preLastDigit = num % 100 / 10;
        if (preLastDigit == 1)
        {
            return c;
        }
        switch (num % 10)
        {
            case 1:
                return a;
            case 2:
            case 3:
            case 4:
                return b;
            default:
                return c;
        }
    }

    public static String numericToString(int num, String a, String b, String c){
        return num + " " + getRuLiteral(num, a, b, c);
    }

    public static String diffDateToString(Long milliseconds){
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long months = days / 30;
        long years = months / 12;
        if(years > 0){
            return numericToString(safeLongToInt(years), "год","года","лет");
        }else if(months > 0){
            return numericToString(safeLongToInt(months), "месяц","месяца","месяцев");
        }else if(days > 0){
            return numericToString(safeLongToInt(days), "день","дня","дней");
        }else if(hours > 0){
            return numericToString(safeLongToInt(hours), "час","часа","часов");
        }else if(minutes > 0){
            return numericToString(safeLongToInt(minutes), "минуту","минуты","минут");
        }else if(seconds > 0){
            return numericToString(safeLongToInt(seconds), "секунду","секунды","секунд");
        }else{
            return numericToString(safeLongToInt(milliseconds), "миллисекунду","миллисекунды","миллисекунд");
        }
    }

    /*public static String humanReadableByteCountBin(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }*/

    public static String byteCountToString(final Long size) {
        String displaySize;
        Long KB = 1024L;
        Long MB = KB * KB;
        Long GB = MB * KB;
        Long TB = GB * KB;

        if (size / (TB * MB)  > 0) {
            displaySize = (size / (TB * MB)) + " EB";
        } else if (size / (TB * KB) > 0) {
            displaySize = (size / (TB * KB)) + " PB";
        } else if (size / TB > 0) {
            displaySize = (size / TB) + " TB";
        } else if (size / GB > 0) {
            displaySize = (size / GB) + " GB";
        } else if (size / MB  > 0) {
            displaySize = (size / MB) + " MB";
        } else if (size / KB > 0) {
            displaySize = (size / KB) + " KB";
        } else {
            displaySize = size + " bytes";
        }
        return displaySize;
    }
    
    public static Integer parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch(Exception ignore) {
            return null;
        }
    }

    public static Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch(Exception ignore) {
            return null;
        }
    }

}
