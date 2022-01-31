package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyPage {
    private SelenideElement heading = $$("h3").find(Condition.text("Оплата по карте"));
    private SelenideElement fieldCardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement fieldMonth = $("[placeholder='08']");
    private SelenideElement fieldYear = $("[placeholder='22']");
    private SelenideElement fieldHolder = $$("[class='input__control']").get(3);
    private SelenideElement fieldCvv = $("[placeholder='999']");
    private SelenideElement buttonContinue = $$("button").find(Condition.exactText("Продолжить"));

    private SelenideElement notificationSuccess = $(withText("Операция одобрена Банком."));
    private SelenideElement notificationFail = $(withText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement errorFormat = $(withText("Неверный формат"));
    private SelenideElement errorDate = $(withText("Неверно указан срок действия карты"));
    private SelenideElement errorExpired = $(withText("Истёк срок действия карты"));
    private SelenideElement errorNecessary = $(withText("Поле обязательно для заполнения"));

    public BuyPage() {
        heading.shouldBe(Condition.visible);
    }

    public void inputData(DataHelper.Card card) {
        fieldCardNumber.setValue(card.getCardNumber());
        fieldMonth.setValue(card.getMonth());
        fieldYear.setValue(card.getYear());
        fieldHolder.setValue(card.getHolder());
        fieldCvv.setValue(card.getCvv());
        buttonContinue.click();
    }

    public void waitSuccessPayment() {
        notificationSuccess.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void waitFailedPayment() {
        notificationFail.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void waitErrorFormat() {
        errorFormat.shouldBe(Condition.visible);
    }

    public void waitErrorDate() {
        errorDate.shouldBe(Condition.visible);
    }

    public void waitErrorExpired() {
        errorExpired.shouldBe(Condition.visible);
    }

    public void waitErrorNecessary() {
        errorNecessary.shouldBe(Condition.visible);
    }
}