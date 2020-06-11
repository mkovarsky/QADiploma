package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    static Faker faker = new Faker(new Locale("en"));
    static DateGenerator dateGenerator = new DateGenerator();
    static CardNumberGenerator cardNumberGenerator = new CardNumberGenerator();

    @Value
    public static class CardInformation {
        private String cardNumber;
        private String year;
        private String month;
        private String holder;
        private String cvv;
    }

    //Позитивный 1
    public static CardInformation getValidCardInformation() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.futureYear().getYear(), dateGenerator.futureMonth().getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    //Позитивный 2
    public static CardInformation getInvalidCardInformation() {
        return new CardInformation(cardNumberGenerator.getDeclinedCardNumber(), dateGenerator.futureYear().getYear(), dateGenerator.futureMonth().getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    //Негативный 1
    public static CardInformation getEmptyCardInformation() {
        return new CardInformation(" ", " ", " ", " ", " ");
    }

    //Негативный 2
    public static CardInformation getCardInformationWithInvalidNumber() {
        return new CardInformation(cardNumberGenerator.getInvalidCardNumber(), dateGenerator.futureYear().getYear(), dateGenerator.futureMonth().getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    //Негативный 3
    public static CardInformation getCardInformationWithPastYear() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.pastYear().getYear(), dateGenerator.currentMonth().getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    //Негативный 4
    public static CardInformation getCardInformationWithPastMonth() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.currentYear().getYear(), dateGenerator.pastMonth().getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    //Негативный 5
    public static CardInformation getWrongFormatCardInformation() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.wrongYear().getYear(), dateGenerator.wrongMonth().getMonth(), "@", "99");
    }
}
