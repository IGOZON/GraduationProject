package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.PurchasePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class PurchasingTourTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        SQLHelper.deleteTable();
    }

    @Test
    @DisplayName("Покупка картой APPROVED")
    void theCardPaymentMustBeApproved() {
        var cardinfo = new DataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateValidName(), getValidCVC());
        var dashboarpage = new DashboardPage();
        dashboarpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(cardinfo);
        form.paymentSuccessfull();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Покупка картой DECLINED")
    void theCardPaymentMustBeDeclined() {
        var cardinfo = new DataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), generateValidName(), getValidCVC());
        var dashboardpage = new DashboardPage();
        dashboardpage.payCreditCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(cardinfo);
        form.declinedPayment();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Номер карты одни нули")
    public void isZeroCard() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCardNumberZero());
        form.declinedPayment();
    }

    @Test
    @DisplayName("Ввод менее 16 цифр в поле карты")
    public void lessCard16() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCardNumberLess());
        form.invalidCardFormat();
    }

    @Test
    @DisplayName("Ввод в поле карты цифр и спецсимвола")
    public void symbolCard() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCardNumberSymbol());
        form.invalidCardFormat();
    }

    @Test
    @DisplayName("Ввод в поле карты цифр и кириллицы")
    public void cyrillicCard() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCardNumberCyrillic());
        form.invalidCardFormat();
    }

    @Test
    @DisplayName("Ввод в поле карты цифр и латиницы")
    public void latinCard() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCardNumberLatin());
        form.invalidCardFormat();
    }

    @Test
    @DisplayName("Незаполненное поле карты")
    public void emptyCard() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCardNumberEmpty());
        form.invalidCardFormat();
    }

    @Test
    @DisplayName("Поле месяца более 12")
    public void moreThanMonth12() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getMonth13());
        form.invalidCardExpirationDate();
    }

    @Test
    @DisplayName("Нули в поле месяц")
    public void zeroMonth() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getMonthZero());
        form.monthNotValid();
    }

    @Test
    @DisplayName("Ввод в поле месяц цифры и спецсимвола")
    public void simbolMonth() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getMonthSymbol());
        form.monthNotValid();
    }

    @Test
    @DisplayName("Ввод в поле месяц цифры и латиницы")
    public void letterMonth1() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getMonthLatin());
        form.monthNotValid();
    }

    @Test
    @DisplayName("Ввод в поле месяц цифры и кириллицы")
    public void letterMonth2() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getMonthCyrillic());
        form.monthNotValid();

    }

    @Test
    @DisplayName("Одна цифра в поле месяц")
    public void oneDigitMonth() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getMonthOneDigit());
        form.monthNotValid();
    }

    @Test
    @DisplayName("Нули в поле месяц")
    public void twoZerosMonth() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getMonthTwoZeros());
        form.monthNotValid();
    }


    @Test
    @DisplayName("Незаполненное поле месяца")
    public void emptyMonth() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getMonthEmpty());
        form.monthNotValid();
    }

    @Test
    @DisplayName("Год меньше текущего на 1 год")
    public void lessThanCurrentOneYear() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getYearLessThanCurrent());
        form.theСardExpired();
    }

    @Test
    @DisplayName("Год больше текущего на 10 лет")
    public void yearLongerThanTheCurrent() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getYearMoreThanTheCurrentOne());
        form.invalidCardExpirationDate();
    }

    @Test
    @DisplayName("Ввод в поле год цифры и спецсимволов")
    public void symbolYear() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getYearSymbol());
        form.yearNotValid();
    }

    @Test
    @DisplayName("Ввод в поле год цифры и буквы")
    public void letterYear() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getYearLatin());
        form.yearNotValid();
    }

    @Test
    @DisplayName("Поле год одна цифра")
    public void oneDigitYear() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getYearOneDigit());
        form.yearNotValid();
    }

    @Test
    @DisplayName("Поле год не заполнено")
    public void notValidEmptyYear() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getYearEmpty());
        form.yearNotValid();
    }

    @Test
    @DisplayName("Поле владелец на кириллице")
    public void cyrillicInHolder() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getHolderCyrillic());
        form.ownerNotValid();
    }

    @Test
    @DisplayName("Спецсимвол в поле владелец")
    public void symbolInHolder() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getHolderSymbol());
        form.ownerNotValid();
    }

    @Test
    @DisplayName("Цифра в поле владелец")
    public void digitInHolder() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getHolderDigit());
        form.ownerNotValid();
    }

    @Test
    @DisplayName("Одна буква в поле владелец")
    public void oneLetterInHolder() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getHolderOneLetter());
        form.ownerNotValid();
    }

    @Test
    @DisplayName("Более 100 букв в поле владелец")
    public void moreThan100lettersPerHolder() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getHolderMoreThan100());
        form.ownerNotValid();
    }

    @Test
    @DisplayName("Незаполненное поле владелец")
    public void emptyInHolder() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getHolderEmpty());
        form.ownerNotValid();
    }

    @Test
    @DisplayName("Ввод в поле CVC/CVV цифры и спецсимвола")
    public void symbolInCVC() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCVCOneSymbol());
        form.cvcNotValid();
    }

    @Test
    @DisplayName("Ввод в поле CVC/CVV цифры и буквы")
    public void lettersInCVC() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCVCOneLetter());
        form.cvcNotValid();
    }

    @Test
    @DisplayName("Ввод в поле CVC/CVV одной цифры")
    public void oneDigitInCVC() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCVCOneDigit());
        form.cvcNotValid();
    }

    @Test
    @DisplayName("Ввод в поле CVC/CVV двух цифр")
    public void twoDigitInCVC() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCVCTwoDigit());
        form.cvcNotValid();
    }

    @Test
    @DisplayName("CVC/CVV заполнен нулями")
    public void filledWidthZeroInCVC() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCVCZero());
        form.cvcNotValid();
    }

    @Test
    @DisplayName("Незаполненное поле CVC/CVV")
    public void emptyInCVC() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.fillingOutTheForm(DataHelper.getCVCBlank());
        form.ownerNotValid();
    }

    @Test
    @DisplayName("Незаполненная форма")
    void sendAnEmptyPurchaseForm() {
        var dashboardpage = new DashboardPage();
        dashboardpage.payBuyCard();
        var form = new PurchasePage();
        form.emptyForm();
    }
}
