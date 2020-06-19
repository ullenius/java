package dev.personnummer;
import java.text.*;
import java.util.function.IntUnaryOperator;
import java.util.regex.*;

/**
 * Class used to validate Swedish identity numbers (Samordningsnummer and Personnummer)
 *
 * @author Johannes Tegnér
 */
abstract class Personnummer implements IdentityNumber {
    private static final Pattern regexPattern;
    private final String number;

    static {
        regexPattern = Pattern.compile("^(\\d{2})?(\\d{2})(\\d{2})(\\d{2})([-|+]?)?(\\d{3})(\\d?)$");
    }
    
   public Personnummer(CharSequence number, IntUnaryOperator numberType) {
	   if (!valid(number, numberType)) 
		   throw new IllegalArgumentException("Invalid personal number");
	   
	   this.number = number.toString();
   }

    /**
     * Validate a Swedish social security number.
     *
     * @param value Social security number to validate, as string.
     * @return True if valid.
     */
    private static boolean valid(CharSequence value, IntUnaryOperator numberType) {
        if (value == null) {
            return false;
        }

        Matcher matches = regexPattern.matcher(value);
        if (!matches.find()) {
            return false;
        }

        int year, month, day, control, number;
        try {
            String y = matches.group(2);
            year     = Integer.parseInt((y.length() == 4 ? y.substring(2) : y));
            month    = Integer.parseInt(matches.group(3));
            day      = Integer.parseInt(matches.group(4));
            control  = Integer.parseInt(matches.group(7));
            number   = Integer.parseInt(matches.group(6));
        } catch (NumberFormatException e) {
            return false;
        }

        // The format passed to Luhn method is supposed to be YYmmDDNNN
        // Hence all numbers that are less than 10 (or in last case 100) will have leading 0's added.
        int luhn = luhn(String.format("%02d%02d%02d%03d0", year, month, day, number));
        day = numberType.applyAsInt(day);
        return (luhn == control) && (testDate(year, month, day));
    }

    private static int luhn(String value) {
        // Luhn/mod10 algorithm. Used to calculate a checksum from the
        // passed value. The checksum is returned and tested against the control number
        // in the social security number to make sure that it is a valid number.

        int temp;
        int sum = 0;

        for (int i = 0; i < value.length(); i++) {
            temp = Character.getNumericValue(value.charAt(i));
            temp *= 2 - (i % 2);
            if (temp > 9)
                temp -= 9;

            sum += temp;
        }

        return (int)(Math.ceil((double)sum / 10.0) * 10.0 - (double)sum);
    }

    private static boolean testDate(int year, int month, int day) {
        try {
            DateFormat df = new SimpleDateFormat("yy-MM-dd");
            df.setLenient(false);
            df.parse(String.format("%02d-%02d-%02d", year, month, day));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
	public String toString() {
		return longFormat();
	}
	
	public String longFormat() {
		return number;
	}
	
	public String shortFormat() {
		return (hasLongFormat()) ? longFormat().substring(2) : longFormat();
		
	}
	
	public boolean hasLongFormat() {
		return (longFormat().length() == 13); // 10 digits number, 2 for century. 1 char for +/-
	}
	
}
