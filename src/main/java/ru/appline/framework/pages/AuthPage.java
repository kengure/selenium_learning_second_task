package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница авторизации
 */
public class AuthPage extends BasePage {

    @FindBy(xpath = "//input[@name='_username']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name='_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Войти')]")
    private WebElement loginButton;

    /**
     * Метод заполнения полей аутентификации
     *
     * @param nameField - имя веб элемента, поля ввода
     * @param value     - значение вводимое в поле
     * @return AuthPage - т.е. остаемся на этой странице
     */
    public AuthPage fillAuthField(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Логин":
                fillInputField(loginField, value);
                element = loginField;
                break;
            case "Пароль":
                fillInputField(passwordField, value);
                element = passwordField;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице ");

        }
        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                value, element.getAttribute("value"));
        return this;
    }

    /**
     * кликнуть на кнопки входа
     *
     * @return QuickPanelPage - т.е. переходим на страницу панели быстрого запуска
     */
    public QuickPanelPage clickLoginButton() {
        waitUtilElementToBeClickable(loginButton).click();
        return pageManager.getQuickPanelPage();
    }
}
