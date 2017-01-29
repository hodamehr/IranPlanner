package tools;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.adapter.AttractionsListAdapter;

/**
 * Created by Hoda on 10/01/2017.
 */
public class Util {

    public static Typeface getFontAwesome(Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");
        return font;
    }

    public static void convertMinuteToHour(int totalMinute, TextView textView) {
        if (totalMinute >= 60) {
            int hours = (int) Math.floor(totalMinute / 60);
            totalMinute %= 60;
            if (totalMinute == 0) {
                textView.setText("طول بازدید: " + Util.persianNumbers(String.valueOf( hours)) + " ساعت ");
            } else {
                textView.setText("طول بازدید: " +  Util.persianNumbers(String.valueOf( hours))+ " ساعت و " + totalMinute + " دقیقه ");
            }
        } else {
            textView.setText("طول بازدید: " + Util.persianNumbers(String.valueOf( totalMinute))  + " دقیقه ");
        }
    }

    public static String convertToHexString(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static byte[] convertToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";

    public static String englishNumbers(String number) {
        char[] chars = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }

    public static String persianNumbers(String str) {
        char[] arabicChars = {'٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'};
        String englishNumbers = "0123456789";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (englishNumbers.contains(String.valueOf(str.charAt(i)))) {
                builder.append(arabicChars[(int) (str.charAt(i)) - 48]);
            } else {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }


}
