package src.main.java.api;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import static src.main.java.config.URL.BASE_URL;

public class Client {
        protected RequestSpecification getSpec() {
            return new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .setBaseUri(BASE_URL)
                    .build();
        }
}
