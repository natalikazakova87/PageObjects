package teat;

import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.DashBoardPage;
import page.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = getVerificationCode();
        DashBoardPage DashBoardPage = verificationPage.validVerify(verificationCode);

    }
    @Test
    @DisplayName("Should Transfer Money From First Card To Second")
    void shouldTransferMoneyFromFirstCardToSecond() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = DashBoardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashBoardPage.getCardBalance(secondCardInfo);
        var amount = generateValidAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var transferPage = dashBoardPage.selectCardToTransfer(secondCardInfo);
        dashBoardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashBoardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashBoardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    @DisplayName("Should Get Error Message If Amount More Balance")
    void shouldGetErrorMessageIfAmountMoreBalance() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashBoardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashBoardPage.getCardBalance(secondCardInfo);
        var amount = generateInvalidAmount(secondCardBalance);
        var transferPage = dashBoardPage.selectCardToTransfer(firstCardInfo);
        transferPage.makeTransfer(String.valueOf(amount), secondCardInfo);
        transferPage.findErrorMessage("На карте недостаточно средств");
        var actualBalanceFirstCard = dashBoardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashBoardPage.getCardBalance(secondCardInfo);
        assertEquals(firstCardBalance, actualBalanceFirstCard);
        assertEquals(secondCardBalance, actualBalanceSecondCard);
    }

}
