package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement buy = $(byText("Купить"));
    private final SelenideElement buyInCredit = $(byText("Купить в кредит"));
    private final SelenideElement paymentCard = $(byText("Оплата по карте"));
    private final SelenideElement creditCard = $(byText("Кредит по данным карты"));

    public DashboardPage payBuyCard() {
        buy.click();
        paymentCard.shouldBe(visible);
        return new DashboardPage();
    }

    public DashboardPage payCreditCard() {
        buyInCredit.click();
        creditCard.shouldBe(visible);
        return new DashboardPage();
    }
}
