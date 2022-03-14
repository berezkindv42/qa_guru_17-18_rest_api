package lesson18;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Homework18 {

    @Test
    void addToCartTest() {

        /*

         */

        String addToCartCookie =
                given()
                        .contentType("application/x-www-form-urlencoded;")
                        .body("addtocart_31.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/31/1")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .cookie("ADDTOCART.COOKIE");

        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("ADDTOCART.COOKIE", addToCartCookie));

        open("http://demowebshop.tricentis.com/cart");
        getWebDriver().manage().addCookie(new Cookie("ADDTOCART.COOKIE", addToCartCookie));

        $(".cart").shouldHave(text("14.1-inch Laptop"));
        closeWebDriver();
    }

    @Test
    void hzTest() {
        open("http://demowebshop.tricentis.com/cart");
        $(".cart").shouldHave(text("14.1-inch Laptop"));
        closeWebDriver();
    }


}
