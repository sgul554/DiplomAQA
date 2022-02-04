package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class MainPayment {
    private SelenideElement headingPage = $$ ("h2").find(Condition.text("Путешествие дня"));
    private SelenideElement buttonBuy = $$ ("button").find(Condition.exactText("Купить"));
    private SelenideElement buttonCredit = $$ ("button").find(Condition.exactText("Купить в кредит"));

    public MainPayment(){
        headingPage.shouldBe(Condition.visible);
    }

    public BuyPage clickButtonBuy(){
        buttonBuy.click();
        return new BuyPage();
    }

    public CreditPage clickButtonCredit(){
        buttonCredit.click();
        return new CreditPage();
    }
}
