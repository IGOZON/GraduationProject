package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import static java.lang.String.format;

public class DataHelper {

    private DataHelper() {
    }

    public static final Faker faker = new Faker(new Locale("en"));

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String holder;
        String codCvcCvv;
    }


    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }


    public static CardInfo getCardNumberZero() {
        return new CardInfo("0000000000000000", getValidMonth(), getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getCardNumberLess() {
        return new CardInfo("1111 2222 3333", getValidMonth(), getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getCardNumberSymbol() {
        return new CardInfo("1111 2222 333! 4444", getValidMonth(), getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getCardNumberCyrillic() {
        return new CardInfo("1111 2222 33им 4444", getValidMonth(), getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getCardNumberLatin() {
        return new CardInfo("1111 2222 3333 f222", getValidMonth(), getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getCardNumberEmpty() {
        return new CardInfo("", getValidMonth(), getValidYear(), generateValidName(), getValidCVC());
    }

    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return format("%02d", localDate.getMonthValue());
    }

    public static CardInfo getMonth13() {
        return new CardInfo(getApprovedCardNumber(), "13", getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getMonthZero() {
        return new CardInfo(getApprovedCardNumber(), "0", getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getMonthSymbol() {
        return new CardInfo(getApprovedCardNumber(), "1@", getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getMonthLatin() {
        return new CardInfo(getApprovedCardNumber(), "T2", getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getMonthCyrillic() {
        return new CardInfo(getApprovedCardNumber(), "Е2", getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getMonthOneDigit() {
        return new CardInfo(getApprovedCardNumber(), "6", getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getMonthTwoZeros() {
        return new CardInfo(getApprovedCardNumber(), "00", getValidYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getMonthEmpty() {
        return new CardInfo(getApprovedCardNumber(), "", getValidYear(), generateValidName(), getValidCVC());
    }

    public static String getValidYear() {
        Random random = new Random();
        int year = random.nextInt(3);
        return LocalDate.now().plusYears(year).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPastYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.minusYears(1));
    }

    public static String getYearMore() {
        LocalDate localDate = LocalDate.now();
        return String.format("%ty", localDate.plusYears(10));
    }

    public static CardInfo getYearLessThanCurrent() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getPastYear(), generateValidName(), getValidCVC());
    }

    public static CardInfo getYearMoreThanTheCurrentOne() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getYearMore(), generateValidName(), getValidCVC());
    }

    public static CardInfo getYearSymbol() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "+3", generateValidName(), getValidCVC());
    }

    public static CardInfo getYearLatin() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "hh", generateValidName(), getValidCVC());
    }

    public static CardInfo getYearOneDigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "1", generateValidName(), getValidCVC());
    }

    public static CardInfo getYearEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "", generateValidName(), getValidCVC());
    }

    public static String generateValidName() {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static CardInfo getHolderCyrillic() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "Игорь", getValidCVC());
    }

    public static CardInfo getHolderSymbol() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "Igor!", getValidCVC());
    }

    public static CardInfo getHolderDigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "Igo4", getValidCVC());
    }

    public static CardInfo getHolderOneLetter() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "H", getValidCVC());
    }

    public static CardInfo getHolderMoreThan100() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "JonathanChristopherAlexander" +
                "BenjaminNathanielNicholasWilliamBartholomewTheodoreMatthewHarrisonAndersonJosephMichaelEdwardFrederick" +
                "JamesAndrewAlexanderTheThird", getValidCVC());
    }

    public static CardInfo getHolderEmpty() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "", getValidCVC());
    }

    public static String getValidCVC() {
        return ("111");
    }

    public static CardInfo getCVCOneSymbol() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateValidName(), "2!2");
    }

    public static CardInfo getCVCOneLetter() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateValidName(), "2A2");
    }

    public static CardInfo getCVCOneDigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateValidName(), "8");
    }

    public static CardInfo getCVCTwoDigit() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateValidName(), "11");
    }

    public static CardInfo getCVCZero() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateValidName(), "000");
    }

    public static CardInfo getCVCBlank() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateValidName(), "");
    }
}

