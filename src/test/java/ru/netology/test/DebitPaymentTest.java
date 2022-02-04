package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.page.MainPayment;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitPaymentTest {
    public static String url = System.getProperty("sut.url");

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openPage() {
        open(url);
    }

    @AfterEach
    public void cleanData() {
        SqlHelper.cleanData();
    }

    @Test
    void shouldPositivePaymentApprovedCard() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitSuccessPayment();
        assertEquals("APPROVED", SqlHelper.getStatusDebitCard());
    }

    @Test
    void shouldPositivePaymentDeclinedCard() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getDeclinedCard());
        payment.waitFailedPayment();
        assertEquals("DECLINED", SqlHelper.getStatusDebitCard());
    }

    @Test
    void shouldPaymentEmptyCard() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getEmptyCard());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentOtherCard() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getOtherCard());
        payment.waitFailedPayment();
    }

    @Test
    void shouldPaymentInvalidCard15symbol() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidCard15symbol());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentInvalidCard17symbol() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidCard17symbol());
        payment.waitFailedPayment();
    }

    @Test
    void shouldPaymentInvalidMonth1symbol() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidMonth1symbol());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentInvalidMonth00() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidMonth00());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentInvalidMonth13symbol() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidMonth13symbol());
        payment.waitErrorDate();
    }

    @Test
    void shouldPaymentInvalidYearExpired() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidYearExpired());
        payment.waitErrorExpired();
    }

    @Test
    void shouldPaymentInvalidYearOver5() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidYearOver5());
        payment.waitErrorDate();
    }

    @Test
    void shouldPaymentInvalidYear1symbol() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidYear1symbol());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentInvalidHolder1word() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidHolder1word());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentInvalidHolderRus() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidHolderRus());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentInvalidHolderNum() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidHolderNum());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentInvalidHolderSpecialSymbol() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidHolderSpecialSymbol());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentInvalidCvv1symbol() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidCvv1symbol());
        payment.waitErrorFormat();
    }

    @Test
    void shouldPaymentInvalidHolderEmpty() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidHolderEmpty());
        payment.waitErrorNecessary();
    }

    @Test
    void shouldPaymentInvalidCvvThenValidCvv() {
        val mainPage = new MainPayment();
        val payment = mainPage.clickButtonBuy();
        payment.inputData(DataHelper.getInvalidCvv1symbol());
        payment.waitErrorFormat();
        payment.cleanFieldCvv();
        payment.inputData(DataHelper.getApprovedCard());
        payment.waitErrorFormatNotVisible();
    }

}
