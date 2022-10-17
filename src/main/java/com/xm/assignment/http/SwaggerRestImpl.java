package com.xm.assignment.http;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import static io.restassured.RestAssured.given;

/**
 * Rest impl class for doing all the api calls needed for the test
 */
@Service
public class SwaggerRestImpl {

    /**
     * BaseURI
     *
     * @return the base URI for the api calls
     */
    public String baseURI() {
        return RestAssured.baseURI = "https://swapi.dev/api/";
    }

    /**
     * Return the url of the movie based on title
     *
     * @param title
     * @return https://swapi.dev/api/films/{id}/
     */
    public String returnFilmUrlOnSpecificTitle(String title) {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseURI() + "/films/")
                .then()
                .extract()
                .response();


        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("results.find {it.title == '" + title + "' }.url");

    }

    /**
     * return the urls of the characters exist in the film episode
     * @param url of the movie
     * @return list of https://swapi.dev/api/people/{id}/
     */
    public List<String> returnListOfCharacters(String url) {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .extract()
                .response();

        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("characters");
    }

    /**
     *  return the starshipsUrls based on the character we are looking for
     * @param charUrls all the characters urls
     * @param charName the name of the character we are looking for
     * @return  list of https://swapi.dev/api/starships/{id}/
     */
    public List<String> returnStarShipUrlsBasedOnCharacterName(List<String> charUrls, String charName) {
        for (String character : charUrls) {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(character)
                    .then()
                    .extract()
                    .response();

            JsonPath jsonPathEvaluator = response.jsonPath();
            String name = jsonPathEvaluator.get("name");
            if (Objects.equals(name, charName)) {
                JsonPath jsonPathEvaluator1 = response.jsonPath();
                return jsonPathEvaluator1.get("starships");
            }
        }
        System.out.println("The name: " + charName + "was not found");
        return Collections.emptyList();
    }

    /**
     * retun the starship class
     * @param starshipsUrl all the urls of the starship a character has
     * @return the starship class of all the starships. Example: "Starfighter"
     */
    public List<String> returnStarShipClass(List<String> starshipsUrl) {
        List<String> starshipClass = new ArrayList<>();
        for (String starship : starshipsUrl) {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(starship)
                    .then()
                    .extract()
                    .response();

            JsonPath jsonPathEvaluator = response.jsonPath();
            String starshipCla = jsonPathEvaluator.get("starship_class");
            starshipClass.add(starshipCla);
        }
        return starshipClass;
    }

    /**
     * return the list of pilots url
     * @param starshipsUrl all the urls of the starship a character has
     * @return the pilots url of each starship https://swapi.dev/api/people/{id}/
     */
    public List<String> returnPilotsUrl(List<String> starshipsUrl) {
        List<String> pilots = new ArrayList<>();
        for (String starship : starshipsUrl) {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(starship)
                    .then()
                    .extract()
                    .response();

            JsonPath jsonPathEvaluator = response.jsonPath();
            pilots = jsonPathEvaluator.get("pilots");
        }
        return pilots;
    }

    /**
     * return the name of all the pilots of a starship
     * @param pilotUrls all the urls of the pilots of a starship
     * @return the names of the pilots. Example: "Luke Skywalker"
     */
    public List<String> returnPilotsNames(List<String> pilotUrls) {
        List<String> pilotsNames = new ArrayList<>();
        for (String pilots : pilotUrls) {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(pilots)
                    .then()
                    .extract()
                    .response();

            JsonPath jsonPathEvaluator = response.jsonPath();
            String starshipCla = jsonPathEvaluator.get("name");
            pilotsNames.add(starshipCla);
        }
        return pilotsNames;
    }



}



