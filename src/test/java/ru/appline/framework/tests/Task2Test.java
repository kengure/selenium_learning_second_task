package ru.appline.framework.tests;

import org.junit.Test;
import ru.appline.framework.basetestsclass.BaseTests;

public class Task2Test extends BaseTests {

    @Test
    public void secondTask() {
        app.getAuthPage()
                .fillAuthField("Логин", "Taraskina Valeriya")
                .fillAuthField("Пароль", "testing")
                .clickLoginButton()
                .checkOpenQuickPanelPage()
                .selectQuickPanelMenu("Расходы")
                .selectSubMenu("Командировки")
                .clickCreateBusinessTripButton()
                .checkOpenCreateBusinessTripWindow()
                .selectBusinessUnit("Отдел внутренней разработки")
                .clickOpenListButton()
                .chooseOrganization()
                .setCheckboxTicketsOrder()
                .fillBusinessTripField("Город выбытия", "Россия, Санкт-Петербург")
                .fillBusinessTripField("Город прибытия", "Россия, ИБС")
                .fillBusinessTripField("Планируемая дата выезда", "01.01.2024")
                .fillBusinessTripField("Планируемая дата возвращения", "10.01.2024")
                .clickSaveAndCloseButton()
                .checkErrorMessageAtValidationPopUp("Список командируемых сотрудников не может быть пустым");
    }

}