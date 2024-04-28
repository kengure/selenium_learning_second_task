package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Страница панели быстрого доступа
 */
public class QuickPanelPage extends BasePage {

    @FindBy(xpath = "//h1[text() = 'Панель быстрого запуска']")
    private WebElement quickPanelTitle;

    @FindBy(xpath = "//ul[@class='nav nav-multilevel main-menu']/li[@class='dropdown']")
    private List<WebElement> listQuickPanelMenu;

    @FindBy(xpath = "//li[@data-route]//span")
    private List<WebElement> listQuickPanelSubMenu;

    /**
     * Функция проверки видимости title страницы
     *
     * @return QuickPanelPage - т.е. остаемся на этой странице
     */
    public QuickPanelPage checkOpenQuickPanelPage() {
        waitUtilElementToBeVisible(quickPanelTitle);
        return this;
    }

    /**
     * Функция клика на любой пункт меню
     *
     * @param nameQuickPanelMenu - наименование меню
     * @return QuickPanelPage - т.е. остаемся на этой странице
     */
    public QuickPanelPage selectQuickPanelMenu(String nameQuickPanelMenu) {
        for (WebElement menuItem : listQuickPanelMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameQuickPanelMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assert.fail("Меню '" + nameQuickPanelMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    /**
     * Функция клика на любое подменю
     *
     * @param nameSubMenu - наименование подменю
     * @return BusinessTripPage - т.е. переходим на страницу
     *         {@link BusinessTripPage}
     */
    public BusinessTripPage selectSubMenu(String nameSubMenu) {
        for (WebElement menuItem : listQuickPanelSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return pageManager.getBusinessTripPage().checkOpenBusinessTripPage();
            }
        }
        Assert.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getBusinessTripPage();
    }

}
