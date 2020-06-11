package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    private SelenideElement cardNumber = $("input[#root > div > form > fieldset > div:nth-child(1) > span > span > span.input__top]");
    //private SelenideElement cardNumber = $(byText("Номер карты"));
    private SelenideElement expirationMonth = $("input[#root > div > form > fieldset > div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__top]");
    //private SelenideElement expirationMonth = $(byText("Месяц"));
    private SelenideElement expirationYear = $("input[#root > div > form > fieldset > div:nth-child(2) > span > span:nth-child(2) > span > span > span.input__top]");
    //private SelenideElement expirationYear = $(byText("Год"));
    private SelenideElement cardHolderName = $("input[#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__top]");
    //private SelenideElement cardHolderName = $(byText("Владелец"));
    private SelenideElement cardSecurityCode = $("input[#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__top]");
    //private SelenideElement cardSecurityCode = $(byText("CVC/CVV"));
    private SelenideElement successNotification = $("div.notification.notification_status_ok.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white > div.notification__content");
    private SelenideElement errorNotification = $(".notification_status_error.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white > div.notification__content");
    private SelenideElement continueButton = $("div:nth-child(4) > button > span > span");
    private SelenideElement invalidFormatNotification = $(".input__sub");

    public void enterCardData(DataHelper.CardInformation cardInformation) {
        cardNumber.setValue(cardInformation.getCardNumber());
        expirationMonth.setValue(cardInformation.getMonth());
        expirationYear.setValue(cardInformation.getYear());
        cardHolderName.setValue(cardInformation.getHolder());
        cardSecurityCode.setValue(cardInformation.getSecurityCode());
        continueButton.click();
    }
    public void successfulPayment() {
        successNotification.waitUntil(Condition.visible, 15000);
    }

    public void notSuccessfulPayment() {
        errorNotification.waitUntil(Condition.visible, 15000);
    }

    public void invalidFormat() {
        invalidFormatNotification.shouldBe(Condition.visible);
    }
}
