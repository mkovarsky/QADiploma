package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.TourOfferPage;
import sqlUtils.SQLUtils;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UITest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("Позитивный сценарий. Успешная покупка по карте.")
    @Test
    public void shouldConfirmPaymentWithApprovedCard() throws SQLException {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val approvedCardInformation = DataHelper.getValidCardInformation();
        paymentPage.enterCardData(approvedCardInformation);
        paymentPage.successfulPayment();
        val paymentId = SQLUtils.getPaymentId();
        val statusForPayment = SQLUtils.getStatusForPayment(paymentId);
        Assertions.assertEquals("APPROVED", statusForPayment);
    }

    @DisplayName("Позитивный сценарий. Успешная покупка в кредит.")
    @Test
    public void shouldConfirmBuyingOnCreditWithApprovedCard() throws SQLException {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val approvedCardInformation = DataHelper.getValidCardInformation();
        paymentPage.enterCardData(approvedCardInformation);
        paymentPage.successfulPayment();
        val paymentId = SQLUtils.getPaymentId();
        val statusForCredit = SQLUtils.getStatusForCredit(paymentId);
        assertEquals("APPROVED", statusForCredit);
    }

    @DisplayName("Позитивный сценарий. Покупка по отклоняемой карте.")
    @Test
    public void shouldNotConfirmPaymentWithDeclinedCard() throws SQLException {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val declinedCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardData(declinedCardInformation);
        paymentPage.notSuccessfulPayment();
        val paymentId = SQLUtils.getPaymentId();
        val statusForPayment = SQLUtils.getStatusForPayment(paymentId);
        assertThat(statusForPayment, equalTo("DECLINED"));
    }
    @DisplayName("Позитивный сценарий. Покупка в кредит по данным отклоняемой карты.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithDeclinedCard() throws SQLException {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val declinedCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardData(declinedCardInformation);
        paymentPage.notSuccessfulPayment();
        val paymentId = SQLUtils.getPaymentId();
        val statusFortCredit = SQLUtils.getStatusForCredit(paymentId);
        assertThat(statusFortCredit, equalTo("DECLINED"));
    }
    @DisplayName("Негативный сценарий. Покупка с незаполненными данными карты.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyForm(){
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.invalidFormat();
    }

    @DisplayName("Негативный сценарий. Покупка в кредит с незаполненными данными карты.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyForm(){
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.invalidFormat();
    }
    @DisplayName("Негативный сценарий. Покупка по карте с невалидным номером.")
    @Test
    public void shouldNotConfirmPaymentWithInvalidCardNumber() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getCardInformationWithInvalidNumber();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.notSuccessfulPayment();
    }
    @DisplayName("Негативный сценарий. Покупка в кредит по данным карты с невалидным номером.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithInvalidCardNumber() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getCardInformationWithInvalidNumber();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.notSuccessfulPayment();
    }

    @DisplayName("Негативный сценарий. Покупка по карте с истекшим сроком действия(в прошлых годах).")
    @Test
    public void shouldNotConfirmPaymentWithExpiredYearCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getCardInformationWithExpiredYear();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.expiredYear();
    }
    @DisplayName("Негативный сценарий. Покупка в кредит по по данным карты с истекшим сроком действия(в прошлых годах).")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredYearCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getCardInformationWithExpiredYear();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.expiredYear();
    }
    @DisplayName("Негативный сценарий. Покупка по карте с истекшим сроком действия(в прошлых месяце).")
    @Test
    public void shouldNotConfirmPaymentWithExpiredMonthCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getCardInformationWithExpiredMonth();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.expiredMonth();
    }
    @DisplayName("Негативный сценарий. Покупка в кредит по по данным карты с истекшим сроком действия(в прошлых месяце).")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredMonthCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getCardInformationWithExpiredMonth();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.expiredMonth();
    }
    @DisplayName("Негативный сценарий. Покупка по карте с неверным форматом полей.")
    @Test
    public void shouldNotConfirmPaymentWithWrongFormatFieldsCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.payByCard();
        val invalidCardInformation = DataHelper.getWrongCardInformationWithWrongFormat();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.invalidFormat();
    }
    @DisplayName("Негативный сценарий. Покупка в кредит по по данным карты с неверным форматом полей.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithWrongFormatFieldsCard() {
        val tourOfferPage = new TourOfferPage();
        val paymentPage = tourOfferPage.buyOnCredit();
        val invalidCardInformation = DataHelper.getWrongCardInformationWithWrongFormat();
        paymentPage.enterCardData(invalidCardInformation);
        paymentPage.invalidFormat();
    }
}
