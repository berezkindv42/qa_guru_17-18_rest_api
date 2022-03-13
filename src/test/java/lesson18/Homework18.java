package lesson18;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Homework18 {

    @Test
    void addToCartTest() {

        /*
curl "http://demowebshop.tricentis.com/addproducttocart/details/31/1" ^
  -H "Connection: keep-alive" ^
  -H "Accept: *" ^
                -H "DNT: 1" ^
                -H "X-Requested-With: XMLHttpRequest" ^
                -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36" ^
                -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" ^
                -H "Origin: http://demowebshop.tricentis.com" ^
                -H "Referer: http://demowebshop.tricentis.com/141-inch-laptop" ^
                -H "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7,de;q=0.6" ^
                -H "Cookie: Nop.customer=0a69af50-b3c3-4b38-9944-dc7f1ca17377; __utmz=78382081.1647077729.1.1.utmcsr=(direct)^|utmccn=(direct)^|utmcmd=(none); ARRAffinity=a1e87db3fa424e3b31370c78def315779c40ca259e77568bef2bb9544f63134e; __utmc=78382081; __utma=78382081.503175620.1647077729.1647171467.1647176957.6; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=31&RecentlyViewedProductIds=72&RecentlyViewedProductIds=2&RecentlyViewedProductIds=16; __atuvc=19^%^7C10^%^2C5^%^7C11; __atuvs=622df1aac033815a002; __utmb=78382081.8.10.1647176957" ^
                --data-raw "addtocart_31.EnteredQuantity=1" ^
                --compressed ^
                --insecure
         */

        Integer cartSize = 0;

        given()
                .contentType("application/x-www-form-urlencoded;")
                .cookie("Nop.customer=0a69af50-b3c3-4b38-9944-dc7f1ca17377;")
                .body("addtocart_31.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/31/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(" + cartSize + ")"));

    }

}
