package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Страница командировок
 */
public class BusinessTripPage extends BasePage {

    @FindBy(xpath = "//h1[normalize-space(text()) = 'Все Командировки']")
    private WebElement businessTripTitle;

    @FindBy(xpath = "//div[*[@title='Создать командировку']]")
    private WebElement createBusinessTripButton;

    @FindBy(xpath = "//h1[normalize-space(text()) = 'Создать командировку']")
    private WebElement createBusinessTripTitle;

    @FindBy(xpath = "//select[contains(@id, 'crm_business_trip_businessUnit')]")
    private WebElement selectBisunessUnit;

    @FindBy(xpath = "//*[text()='Открыть список']")
    private WebElement openListButton;

    @FindBy(xpath = "//div[@class='select2-container select2 input-widget']")
    private WebElement companyDropDown;

    @FindBy(xpath = "//ul[@class='select2-results'][li]")
    private WebElement openedCompanyList;

    @FindBy(xpath = "//ul[@class='select2-results']/li[4]")
    private WebElement elementFromOpenedCompanyList;

    @FindBy(xpath = "//input[@name='crm_business_trip[company]']")
    private WebElement hostOrganization;

    @FindBy(xpath = "//label[text() = 'Заказ билетов']/../input")
    private WebElement orderTicketsCheckbox;

    @FindBy(xpath = "//input[contains(@id, 'crm_business_trip_departureCity')]")
    private WebElement departureCity;

    @FindBy(xpath = "//input[contains(@id, 'crm_business_trip_arrivalCity')]")
    private WebElement arrivalCity;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")
    private WebElement departureDatePlan;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement returnDatePlan;

    @FindBy(xpath = "//div[@id='ui-datepicker-div' and contains(@style, 'display: block;')]")
    private WebElement calendar;

    @FindBy(xpath = "//body[@class='desktop-version lang-ru']")
    private WebElement calendarBody;

    @FindBy(xpath = "//button[contains(text(),'Сохранить и закрыть')]")
    private WebElement saveAndCloseButton;

    @FindBy(xpath = "//span[@class='validation-failed']")
    private WebElement validationErrorField;

    /**
     * Функция проверки видимости страницы BusinessTripPage
     *
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage checkOpenBusinessTripPage() {
        waitUtilElementToBeVisible(businessTripTitle);
        return this;
    }

    /**
     * Функция проверки видимости окна создания командировки
     *
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage checkOpenCreateBusinessTripWindow() {
        waitUtilElementToBeVisible(createBusinessTripTitle);
        return this;
    }

    /**
     * Функция клика на кнопку создания командировки
     *
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage clickCreateBusinessTripButton() {
        waitUtilElementToBeClickable(createBusinessTripButton).click();
        return this;
    }

    /**
     * Функция выбора подразделения
     * 
     * @param subdivisionName - название подразделения
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage selectBusinessUnit(String subdivisionName) {
        Select select = new Select(selectBisunessUnit);
        select.selectByVisibleText(subdivisionName);
        return this;
    }

    /**
     * Функция клика на кнопку открытия списка
     *
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage clickOpenListButton() {
        waitUtilElementToBeClickable(openListButton).click();
        return this;
    }

    /**
     * Функция выбора организации
     *
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage chooseOrganization() {
        waitUtilElementToBeClickable(companyDropDown).click();
        waitUtilElementToBeVisible(openedCompanyList);
        waitUtilElementToBeClickable(elementFromOpenedCompanyList).click();

        String value = hostOrganization.getAttribute("value");

        if (value != null && !value.isEmpty()) {
            System.out.println("Input не пустой");
        } else {
            System.out.println("Input пустой");
        }

        return this;
    }

    /**
     * Функция клика на чекбокс билетов
     *
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage setCheckboxTicketsOrder() {
        waitUtilElementToBeClickable(orderTicketsCheckbox).click();
        Assert.assertTrue("Поле было заполнено некорректно", orderTicketsCheckbox.isSelected());
        return this;
    }

    /**
     * Функция закрытия календаря
     *
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage closeCalendar() {
        waitUtilElementToBeVisible(calendar);
        waitUtilElementToBeClickable(calendarBody).click();
        return this;
    }

    /**
     * Функция заполнения полей при создании командировки
     *
     * @param nameField - название поля
     * @param value     - вводимое значение
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage fillBusinessTripField(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Город выбытия":
                fillInputField(departureCity, value);
                element = departureCity;
                break;
            case "Город прибытия":
                fillInputField(arrivalCity, value);
                element = arrivalCity;
                break;
            case "Планируемая дата выезда":
                fillInputField(departureDatePlan, value);
                closeCalendar();
                element = departureDatePlan;
                break;
            case "Планируемая дата возвращения":
                fillInputField(returnDatePlan, value);
                closeCalendar();
                element = returnDatePlan;
                break;
            default:
                Assert.fail("Поле с наименованием '" + nameField + "' отсутствует на странице ");

        }
        Assert.assertEquals("Поле '" + nameField + "' было заполнено некорректно",
                value, element.getAttribute("value"));
        return this;
    }

    /**
     * Функция клика на кнопку "Сохранить и закрыть"
     *
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage clickSaveAndCloseButton() {
        waitUtilElementToBeClickable(saveAndCloseButton).click();
        return this;
    }

    /**
     * Функция проверки сообщения об ощибке
     *
     * @param errorMessage - сообщение об ошибке
     * @return BusinessTripPage - т.е. остаемся на этой странице
     */
    public BusinessTripPage checkErrorMessageAtValidationPopUp(String errorMessage) {
        Assert.assertEquals("Проверка ошибки у поля не была пройдена",
                errorMessage, validationErrorField.getText());
        return this;
    }

}
