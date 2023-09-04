package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public VerificationPage validLogin(DataHelper.AuthInfo info){
        private SelenideElement loginField = $("[data-test-id=login] input"));
        private SelenideElement passwordField = $("[data-test-id=password] input"));
        private SelenideElement loginButton =$("[data-test-id=action-login] input").click();
        return new VerificationPage();

        public VerificationPage validLogin(DataHelper.AuthInfo info) {
            loginField.setValue(info.getLogin());
            passwordField.setValue(info.getPassword());
            loginButton.click();
            return new VerificationPage();
    }

}
