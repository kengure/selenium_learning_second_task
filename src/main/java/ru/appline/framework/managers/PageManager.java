package ru.appline.framework.managers;

import ru.appline.framework.pages.AuthPage;
import ru.appline.framework.pages.QuickPanelPage;
import ru.appline.framework.pages.BusinessTripPage;

/**
 * @author Arkadiy_Alaverdyan
 *         Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Страничка аутентификации
     */
    private AuthPage authPage;

    /**
     * Страничка панели быстрого доступа
     */
    private QuickPanelPage quickPanelPage;

    /**
     * Страничка командировок
     */
    private BusinessTripPage businessTripPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link AuthPage}
     *
     * @return AuthPage
     */
    public AuthPage getAuthPage() {
        if (authPage == null) {
            authPage = new AuthPage();
        }
        return authPage;
    }

    public QuickPanelPage getQuickPanelPage() {
        if (quickPanelPage == null) {
            quickPanelPage = new QuickPanelPage();
        }
        return quickPanelPage;
    }

    public BusinessTripPage getBusinessTripPage() {
        if (businessTripPage == null) {
            businessTripPage = new BusinessTripPage();
        }
        return businessTripPage;
    }
}
