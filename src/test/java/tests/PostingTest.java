package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.PostData;
import models.UserData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class PostingTest extends TestBase {

    @Parameterized.Parameters(name = "test data")
    public static Iterable<Object[]> data() {
        String millis = String.valueOf(System.currentTimeMillis());

        UserData testUserData = new UserData()
                .setLogin("selenium")
                .setPassword("super_password");

        PostData testPostData = new PostData()
                .setTitle(String.format("title_%s", millis))
                .setSlug(String.format("slug_%s", millis))
                .setMarkdown(String.format("markdown_%s", millis))
                .setText(String.format("text_%s", millis));

        return Arrays.asList(new Object[][] { { testUserData, testPostData } });
    }

    private final UserData user;
    private final PostData post;

    public PostingTest(UserData user, PostData post) {
        this.user = user;
        this.post = post;
    }

    @Test
    @DisplayName("Добавление и удаление записи")
    @Description("Реализация проверки возможностей добавления и удаления записи на тестовом стенде.")
    public void posting() {

        /*
            1) Заходит на страницу - http://igorakintev.ru/admin/
         */
        app.openURL("http://igorakintev.ru/admin/");

        /*
            2) Заполняет имя пользователя - selenium
            3) Заполняет пароль - super_password
            4) Нажимает кнопку Войти
        */
        app.fillAuthFormAndLogin(user);

        /*
            5) Проверяет что на новой станице присутствует заголовок - “Панель управления”
        */
        app.checkPageTitle("Панель управления");

        /*
            6) Нажимает кнопку “+ Добавить” в разделе Blog/Entries
            7) Проверяет что на новой станице присутствует заголовок - “Добавить entry”
            8) Заполняет поле title - случайной строкой (пример: Title43565463456)
            9) Заполняет поле Slug - случайной строкой (пример: Slug43565463456)
            10) Заполняет поле Text markdown - случайной строкой (пример: Slug43565463456)
            11) Заполняет поле Text - случайной строкой (пример: Slug43565463456)
            12) Нажимает кнопку Сохранить
        */
        app.addNewPost(post);

        /*
            13) Переходит на страницу: http://igorakintev.ru/blog/
        */
        app.openURL("http://igorakintev.ru/blog/");

        /*
            14) Удостоверяется что ранее созданная запись отображается на сайте.
        */
        app.findPost(post);

        /*
            15) Удаляет созданную запись (в админ панели).
        */
        app.openURL("http://igorakintev.ru/admin/");
        app.removePost(post);
    }

}