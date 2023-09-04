package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

public class DashBoardPage {
    private SelenideElement header = $("[data-test-id='dashboard']");
    public DashBoardPage(){
        header.shouldBe(visible);
    }
}
