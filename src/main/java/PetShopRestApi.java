import com.google.gson.Gson;
import org.junit.Test;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.net.http.*;



public class PetShopRestApi {

    private String key = "special-key";
    PetSerialization pet = createPet();
    PetSerialization putPet = createPet2();
    Gson gson = new Gson();

    // BU CLASS DAKİ TESTLERDE POZİTİF SENARYOLAR AMAÇLANMIŞTIR

    @Test
    public void Pet_POST_Test() throws URISyntaxException, IOException, InterruptedException {


        String jsonRequest = gson.toJson(pet);
        System.out.println(jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://petstore.swagger.io/v2/pet"))
                .header("Authorization", key).header("Content-Type", "application/json").
                        POST(HttpRequest.BodyPublishers.ofString(jsonRequest)).build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(postResponse.body());
        System.out.println(postResponse);

        pet = gson.fromJson(postResponse.body(), PetSerialization.class);

    }

    @Test
    public void Pet_PUT_Test() throws URISyntaxException, IOException, InterruptedException {

        String jsonRequest = gson.toJson(putPet);
        System.out.println(jsonRequest);

        HttpRequest putRequest = HttpRequest.newBuilder()
                .uri(new URI("https://petstore.swagger.io/v2/pet"))
                .header("Authorization", key).header("Content-Type", "application/json").
                        PUT(HttpRequest.BodyPublishers.ofString(jsonRequest)).build();


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> putResponse = httpClient.send(putRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(putResponse.body());
        System.out.println(putResponse);

        pet = gson.fromJson(putResponse.body(), PetSerialization.class);


    }

    @Test
    public void Pet_Get_Test_WithId() throws URISyntaxException, IOException, InterruptedException {


        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://petstore.swagger.io/v2/pet/" + pet.getId()))
                .header("Authorization", key).
                  header("Content-Type", "application/json").
                        build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(getResponse.body());
        System.out.println(getResponse);
        pet = gson.fromJson(getResponse.body(), PetSerialization.class);
        System.out.println(pet.getId());

    }


    @Test
    public void Pet_Delete_Test() throws URISyntaxException, IOException, InterruptedException {


        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(new URI("https://petstore.swagger.io/v2/pet/" + pet.getId()))
                .header("Authorization", key).DELETE().build();
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> deleteResponse = httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(deleteResponse.body());
        System.out.println(deleteResponse);

    }


    private PetSerialization createPet() {

        PetSerialization p = new PetSerialization();
        p.setId(169278);

        Category cat = new Category();
        cat.setId(156);
        cat.setName("HardDogs");
        p.setCategory(cat);

        p.setName("mester");

        List<String> photo = new ArrayList<>();
        photo.add("https://static01.nyt.com/images/2022/08/25/science/25DogDementia-08/25DogDementia-08-mobileMasterAt3x.jpg");
        p.setPhotoUrls(photo);

        List<Tags> tagsList = new ArrayList<>();
        Tags tag = new Tags();
        tag.setId(1456);
        tag.setName("pit887");
        tagsList.add(tag);
        p.setTags(tagsList);

        p.setStatus("available");

        return p;
    }

    private PetSerialization createPet2(){

        PetSerialization putPet = new PetSerialization();
        putPet.setId(169278);

        Category cat = new Category();
        cat.setId(156);
        cat.setName("HardDogs");
        putPet.setCategory(cat);

        putPet.setName("mester");

        List<String> photo = new ArrayList<>();
        photo.add("https://static01.nyt.com/images/2022/08/25/science/25DogDementia-08/25DogDementia-08-mobileMasterAt3x.jpg");
        putPet.setPhotoUrls(photo);

        List<Tags> tagsList = new ArrayList<>();
        Tags tag = new Tags();
        tag.setId(1456);
        tag.setName("pit887");
        tagsList.add(tag);
        putPet.setTags(tagsList);

        putPet.setStatus("sold");

        return putPet;
    }
}


