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
        private String securityCode;
    }

    public static CardInformation getValidCardInformation() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.shiftYear(5).getYear(), dateGenerator.shiftMonth(2).getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    public static CardInformation getInvalidCardInformation() {
        return new CardInformation(cardNumberGenerator.getDeclinedCardNumber(), dateGenerator.shiftYear(5).getYear(), dateGenerator.shiftMonth(2).getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    public static CardInformation getEmptyCardInformation() {
        return new CardInformation(" ", " ", " ", " ", " ");
    }

    public static CardInformation getCardInformationWithInvalidNumber() {
        return new CardInformation(cardNumberGenerator.getInvalidCardNumber(), dateGenerator.shiftYear(5).getYear(), dateGenerator.shiftMonth(2).getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    public static CardInformation getCardInformationWithExpiredYear() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.shiftYear(-2).getYear(), dateGenerator.shiftMonth(0).getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    public static CardInformation getCardInformationWithExpiredMonth() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.shiftYear(0).getYear(), dateGenerator.shiftMonth(-1).getMonth(), faker.name().fullName(), Integer.toString(faker.number().numberBetween(100, 999)));
    }

    public static CardInformation getWrongCardInformationWithWrongFormat() {
        return new CardInformation(cardNumberGenerator.getApprovedCardNumber(), dateGenerator.wrongYear().getYear(), dateGenerator.wrongMonth().getMonth(), "@", "99");
    }
}
