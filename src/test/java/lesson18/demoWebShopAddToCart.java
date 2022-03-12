package lesson18;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
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
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
//                        .body("updatetopcartsectionhtml", is("(17)"));
        //todo

    }
}
