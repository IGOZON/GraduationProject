package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PurchasePage {

    // Поля
    private final SelenideElement cardnumber = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private final SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private final SelenideElement owner = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvccvv = $(byText("CVC/CVV")).parent().$(".input__control");
    private final SelenideElement continuebutton = $(byText("Продолжить"));

    // Ошибки
    private final SelenideElement formatCardError = $(byText("Неверный формат")).parent().$(".input__sub");
    private final SelenideElement cardNumberError = $(byText("Номер карты")).parent().$(".input__sub");
    private final SelenideElement monthError = $(byText("Месяц")).parent().$(".input__sub");
    private final SelenideElement yearError = $(byText("Год")).parent().$(".input__sub");
    private final SelenideElement expiredCardError = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement termCardError = $(byText("Истёк срок действия карты"));
    private final SelenideElement ownerError = $(byText("Владелец")).parent().$(".input__sub");
    private final SelenideElement cvcError = $(byText("CVC/CVV")).parent().$(".input__sub");

    public void fillingOutTheForm(DataHelper.CardInfo cardInfo) {
        cardnumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getHolder());
        cvccvv.setValue(cardInfo.getCodCvcCvv());
        continuebutton.click();
    }

    public void emptyForm() {
        continuebutton.click();
        cardNumberError.shouldBe(visible);
        monthError.shouldBe(visible);
        yearError.shouldBe(visible);
        ownerError.shouldBe(visible);
        cvcError.shouldBe(visible);
    }

    public void paymentSuccessfull() {
        $(".notification_status_ok").shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public void declinedPayment() {
        $(byCssSelector("div.notification.notification_status_error.notification_has-closer.notification_stick-to_right.notification_theme_alfa-on-white")).shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public void invalidCardFormat() {
        formatCardError.shouldBe(visible);
    }

    public void invalidCardExpirationDate() {
        expiredCardError.shouldBe(visible);
    }

    public void theСardExpired() {
        termCardError.shouldBe(visible);
    }

    public void monthNotValid() {
        monthError.shouldBe(visible);
    }

    public void yearNotValid() {
        yearError.shouldBe(visible);
    }

    public void ownerNotValid() {
        ownerError.shouldBe(visible);
    }

    public void cvcNotValid() {
        cvcError.shouldBe(visible);
    }
}
