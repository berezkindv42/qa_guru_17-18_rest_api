package lesson18;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.hamcrest.CoreMatchers.is;

public class demoWebShopAddToCart {

    @Test
    void addToCartAsNewUserTest() {

        given()
                .contentType("application/x-www-form-urlencoded;")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"));

    }

    @Test
    void addToCartWithCookieTest() {

        Integer cartSize = 0;

        given()
                .contentType("application/x-www-form-urlencoded;")
                .cookie("Nop.customer=0a69af50-b3c3-4b38-9944-dc7f1ca17377;")
                .body("product_attribute_72_5_18=53" +
                        "&product_attribute_72_6_19=54" +
                        "&product_attribute_72_3_20=57" +
                        "&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(" + cartSize + ")"));

//        assertThat(response.extract().path("updatetopcartsectionhtml").toString())
//                .body("updatetopcartsectionhtml", is("(" + cartSize + ")");
    }

    @Test
    void addToCartWithCookieToStringTest() {

        String response =
                given()
                        .contentType("application/x-www-form-urlencoded;")
                        .cookie("Nop.customer=0a69af50-b3c3-4b38-9944-dc7f1ca17377;")
                        .body("product_attribute_72_5_18=53" +
                                "&product_attribute_72_6_19=54" +
                                "&product_attribute_72_3_20=57" +
                                "&addtocart_72.EnteredQuantity=1")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                        .then()
//                        .log().all()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
//                        .body("updatetopcartsectionhtml", is("(37)"))
                        .extract().path("updatetopcartsectionhtml").toString();
        System.out.println(response);

//        assertThat(response.extract().path("updatetopcartsectionhtml").toString())
//                .body("updatetopcartsectionhtml", is("(" + cartSize + ")");
    }
}
