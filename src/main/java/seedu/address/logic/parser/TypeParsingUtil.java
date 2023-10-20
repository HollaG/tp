package seedu.address.logic.parser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Subject;



/**
 * Contains utility methods used for parsing strings into various desirable values, and validating them.
 */
public class TypeParsingUtil {
    /**
     * Parses the time from the input string, which can be in the following formats: hh:mm
     * @param input the input string where the time is to be parsed from
     * @return the time parsed
     * @throws ParseException if the input is not a valid time
     */
    public static LocalTime parseTime(String input) throws ParseException {
        String hourMinPattern = "(\\d{1,2}):(\\d{2})";
        Pattern p = Pattern.compile(hourMinPattern);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            int hour = TypeParsingUtil.parseNum(m.group(1));
            int min = TypeParsingUtil.parseNum(m.group(2));
            if (hour > 23 || min > 59) {
                throw new ParseException(input + " is not a valid time");
            }
            return LocalTime.of(hour, min);
        } else {
            throw new ParseException(input + " is not a valid time");
        }
    }
    /**
     * overloading parseTime to take in flagName and parse the flag from the input string
     */
    public static LocalTime parseTime(String flagName, String input) throws ParseException {
        return parseTime(parseFlag(flagName, input));
    }
    /**
     * overloading to not throw exception if the flag is not found when isOptional is true
     */
    public static LocalTime parseTime(String flagName, String input, boolean isOptional) throws ParseException {
        if (isOptional) {
            try {
                return parseTime(parseFlag(flagName, input));
            } catch (ParseException e) {
                return null;
            }
        }
        return parseTime(parseFlag(flagName, input));
    }

    /**
     * Parses the date from the input string, which can be in the following formats:
     * 1. dd, then the date will be the current month and year
     * 2. mm/dd ,then the date will be the current year
     * 3. yyyy/mm/dd ,then the date will be the given year
     * 4. yy/mm/dd ,then the date will be the given year + 2000
     * @param input the input string where the date is to be parsed from
     * @return the date parsed
     * @throws ParseException if the input is not a valid date
     */
    public static LocalDate parseDate(String input) throws ParseException {
        String dayPattern = "(\\d{1,2})";
        String monthDayPattern = "(\\d{1,2})/(\\d{1,2})";
        String yearMonthDayPattern = "(\\d{2,4})/(\\d{1,2})/(\\d{1,2})";
        Pattern dayP = Pattern.compile(dayPattern);
        Pattern monthDayP = Pattern.compile(monthDayPattern);
        Pattern yearMonthDayP = Pattern.compile(yearMonthDayPattern);
        Matcher dayM = dayP.matcher(input);
        Matcher monthDayM = monthDayP.matcher(input);
        Matcher yearMonthDayM = yearMonthDayP.matcher(input);
        if (yearMonthDayM.matches()) {
            int year = TypeParsingUtil.parseNum(yearMonthDayM.group(1), 0, 9999);
            if (year < 1000) {
                year += 2000;
            }
            int month = TypeParsingUtil.parseNum(yearMonthDayM.group(2), 1, 12);
            int day = TypeParsingUtil.parseNum(yearMonthDayM.group(3), 1, 31);
            return LocalDate.of(year, month, day);
        } else if (monthDayM.matches()) {
            LocalDate now = LocalDate.now();
            int month = TypeParsingUtil.parseNum(monthDayM.group(1), 1, 12);
            int day = TypeParsingUtil.parseNum(monthDayM.group(2), 1, 31);
            return LocalDate.of(now.getYear(), month, day);
        } else if (dayM.matches()) {
            LocalDate now = LocalDate.now();
            int day = TypeParsingUtil.parseNum(dayM.group(1), 1, 31);
            return LocalDate.of(now.getYear(), now.getMonth(), day);
        } else {
            throw new ParseException(input + " is not a valid date");
        }
    }

    /**
     * overloading parseDate to take in flagName and parse the flag from the input string
     */
    public static LocalDate parseDate(String flagName, String input) throws ParseException {
        return parseDate(parseFlag(flagName, input));
    }

    /**
     * overloading to not throw exception if the flag is not found when isOptional is true
     */
    public static LocalDate parseDate(String flagName, String input, boolean isOptional) throws ParseException {
        if (isOptional) {
            try {
                return parseDate(parseFlag(flagName, input));
            } catch (ParseException e) {
                return null;
            }
        }
        return parseDate(parseFlag(flagName, input));
    }

    /**
     * Parses the number from the input string
     * @param input the input string where the flag is to be parsed from
     * @param min the minimum value of the number
     * @param max the maximum value of the number
     * @return the number parsed
     * @throws ParseException if the input is not a valid number
     */
    public static Integer parseNum(String input, int min, int max) throws ParseException {
        try {
            int num = Integer.parseInt(input);
            if (num < min || num > max) {
                throw new ParseException("Number " + input + " of range: " + min + "-" + max);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new ParseException(input + " is not a number");
        }
    }

    /**
     * overloading parseNum assuming no min and max
     */
    public static Integer parseNum(String input) throws ParseException {
        return parseNum(input, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * overloading parseNum to take in flagName and parse the flag from the input string
     */
    public static Integer parseNum(String flagName, String input, int min, int max) throws ParseException {
        return parseNum(parseFlag(flagName, input), min, max);
    }

    /**
     * overloading parseNum to take in flagName and parse the flag from the input string assuming no min and max
     */
    public static Integer parseNum(String flagName, String input) throws ParseException {
        return parseNum(parseFlag(flagName, input));
    }

    /**
     * overloading to not throw exception if the flag is not found when isOptional is true
     */
    public static Integer parseNum(String flagName, String input, boolean isOptional) throws ParseException {
        if (isOptional) {
            try {
                return parseNum(parseFlag(flagName, input));
            } catch (ParseException e) {
                return null;
            }
        }
        return parseNum(parseFlag(flagName, input));
    }
    /**
     * Parses the string from the input string
     */
    public static String parseStr(String input) throws ParseException {
        return input;
    }
    /**
     * overloading parseStr to take in flagName and parse the flag from the input string
     */
    public static String parseStr(String flagName, String input) throws ParseException {
        return parseStr(parseFlag(flagName, input));
    }
    /**
     * overloading to not throw exception if the flag is not found when isOptional is true
     */
    public static String parseStr(String flagName, String input, boolean isOptional) throws ParseException {
        if (isOptional) {
            try {
                return parseStr(parseFlag(flagName, input));
            } catch (ParseException e) {
                return null;
            }
        }
        return parseStr(parseFlag(flagName, input));
    }

    /**
     * Parses the subject from the input string
     * @param input the input string where the flag is to be parsed from
     * @return the subject parsed
     * @throws ParseException if the input is not a valid subject
     */
    public static Subject parseSubject(String input) throws ParseException {
        if (Subject.isValidSubject(input.toUpperCase())) {
            return Subject.parseSubject(input);
        } else {
            throw new ParseException(input + " is not a valid subject");
        }
    }
    /**
     * overloading parseSubject to take in flagName and parse the flag from the input string
     */
    public static Subject parseSubject(String flagName, String input) throws ParseException {
        return parseSubject(parseFlag(flagName, input));
    }
    /**
     * overloading to not throw exception if the flag is not found when isOptional is true
     */
    public static Subject parseSubject(String flagName, String input, boolean isOptional) throws ParseException {
        if (isOptional) {
            try {
                return parseSubject(parseFlag(flagName, input));
            } catch (ParseException e) {
                return null;
            }
        }
        return parseSubject(parseFlag(flagName, input));
    }

    /**
     * Parses the day of week from the input string
     * @param input the input string where the flag is to be parsed from
     * @return the day of week parsed
     * @throws ParseException if the flag is not found
     */
    public static DayOfWeek parseDayOfWeek(String input) throws ParseException {
        try {
            return DayOfWeek.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(input + " is not a valid day of week");
        }
    }

    /**
     * overloading parseDayOfWeek to take in flagName and parse the flag from the input string
     */
    public static DayOfWeek parseDayOfWeek(String flagName, String input) throws ParseException {
        return parseDayOfWeek(parseFlag(flagName, input));
    }

    /**
     * overloading to not throw exception if the flag is not found when isOptional is true
     */
    public static DayOfWeek parseDayOfWeek(String flagName, String input, boolean isOptional) throws ParseException {
        if (isOptional) {
            try {
                return parseDayOfWeek(parseFlag(flagName, input));
            } catch (ParseException e) {
                return null;
            }
        }
        return parseDayOfWeek(parseFlag(flagName, input));
    }
    /**
     * Parses the flag from the input string
     * @param flag the flag to parse
     * @param input the input string where the flag is to be parsed from
     * @return the string after the flag
     * @throws ParseException if the flag is not found
     */
    public static String parseFlag(String flag, String input) throws ParseException {
        Pattern p = Pattern.compile("-" + flag + "\\s*([\\w:,/]+)");
        Matcher m = p.matcher(input);
        if (m.find()) {
            return m.group(1);
        } else {
            throw new ParseException("Flag " + flag + " not found");
        }
    }

    /**
     * Validates the pattern from the input string
     * @param pattern the pattern to validate
     * @param input the input string where the flag is to be parsed from
     * @return true if the pattern matches the input string
     */
    public static boolean validate(String pattern, String input) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input.toLowerCase());
        return m.matches();
    }
}
