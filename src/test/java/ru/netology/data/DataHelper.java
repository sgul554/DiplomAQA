package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class Card {
        private String cardNumber;
        private String month;
        private String year;
        private String holder;
        private String cvv;
    }

    private static
    Faker faker = new Faker(new Locale("en"));
    String ApprovedCard = "4444 4444 4444 4441";

    public static String getRandomCard() {
        return faker.business().creditCardNumber();
    }

    public static String getCountMonth() {
        int countMonth = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(countMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getCountYear() {
        return LocalDate.now().plusYears(faker.number().numberBetween(0, 5)).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getRandomName() {
        return faker.name().fullName();
    }

    public static String getRandomCvv() {
        return faker.number().digits(3);
    }

    public static String getApprovedCardNumber(){
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber(){
        return "4444 4444 4444 4442";
    }

    public static Card getApprovedCard() {
        return new Card(getApprovedCardNumber(), getCountMonth(), getCountYear(), getRandomName(), getRandomCvv());
    }

    public static Card getDeclinedCard() {
        return new Card(getDeclinedCardNumber(), getCountMonth(), getCountYear(), getRandomName(), getRandomCvv());
    }

    public static Card getEmptyCard() {
        return new Card("", "", "", "", "");
    }

    public static Card getOtherCard() {
        return new Card(getRandomCard(), getCountMonth(), getCountYear(), getRandomName(), getRandomCvv());
    }

    public static Card getInvalidCard15symbol() {
        return new Card("1234 5678 9101 234", getCountMonth(), getCountYear(), getRandomName(), getRandomCvv());
    }

    public static Card getInvalidCard17symbol() {
        return new Card("1234 5678 9101 2345 6", getCountMonth(), getCountYear(), getRandomName(), getRandomCvv());
    }

    public static Card getInvalidMonth1symbol(){
        return new Card(getApprovedCardNumber(), "1", getCountYear(), getRandomName(), getRandomCvv());
    }

    public static Card getInvalidMonth00(){
        return new Card(getApprovedCardNumber(), "00", getCountYear(), getRandomName(), getRandomCvv());
    }

    public static Card getInvalidMonth13symbol(){
        return new Card(getApprovedCardNumber(), "13", getCountYear(), getRandomName(), getRandomCvv());
    }

    public static Card getInvalidYearExpired() {
        return new Card(getApprovedCardNumber(), getCountMonth(), "15", getRandomName(), getRandomCvv());
    }

    public static Card getInvalidYearOver5() {
        return new Card(getApprovedCardNumber(), getCountMonth(), "30", getRandomName(), getRandomCvv());
    }

    public static Card getInvalidYear1symbol() {
        return new Card(getApprovedCardNumber(), getCountMonth(), "5", getRandomName(), getRandomCvv());
    }

    public static Card getInvalidHolder1word() {
        return new Card(getApprovedCardNumber(), getCountMonth(), getCountYear(), "Fedot", getRandomCvv());
    }

    public static Card getInvalidHolderRus() {
        return new Card(getApprovedCardNumber(), getCountMonth(), getCountYear(), "Борис Некрасов", getRandomCvv());
    }

    public static Card getInvalidHolderNum() {
        return new Card(getApprovedCardNumber(), getCountMonth(), getCountYear(), "12345", getRandomCvv());
    }

    public static Card getInvalidHolderSpecialSymbol() {
        return new Card(getApprovedCardNumber(), getCountMonth(), getCountYear(), "&&&*%", getRandomCvv());
    }

    public static Card getInvalidCvv1symbol() {
        return new Card(getApprovedCardNumber(), getCountMonth(), getCountYear(), getRandomName(), "7");
    }

}
