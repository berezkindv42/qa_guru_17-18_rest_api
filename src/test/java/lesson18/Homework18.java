package lesson18;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Homework18 {

    @Test
    @DisplayName("Login test API + UI")
    void loginTest() {
        String authorizationCookie =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .formParam("Email", "johnsmith@mail.com")
                        .formParam("Password", "js1234")
                        .when()
                        .post("http://demowebshop.tricentis.com/login")
                        .then()
                        .statusCode(302)
                        .extract()
                        .cookie("NOPCOMMERCE.AUTH");
        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", authorizationCookie));
        open("http://demowebshop.tricentis.com/");
        $(".account").shouldHave(text("johnsmith@mail.com"));
    }

    @Test
    @DisplayName(" New user add item to wishlist")
    void addToWishlistNotAuthorisedUserTest() {
        given()
                .contentType("application/x-www-form-urlencoded;")
                .body("addtocart_78.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/78/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is("(1)"));
    }

}
