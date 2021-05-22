package com.fabiankevin.feign;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import feign.*;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .create();

        UserClient userClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder(gson))
                .decoder(new GsonDecoder(gson))
                .logLevel(Logger.Level.FULL)
                .requestInterceptor(template -> {

                    template.header("Accept", "*/*");
                    template.header("Cache-Control", "no-cache");
                    template.header("Content-Type", "application/json");
                })
                .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true))
                .target(UserClient.class, "http://localhost:8080/users");

        try {
           User user = new User();
           user.setName("Virgo");
           user.setBirthDate("1992-06-15");

           User createdUser = userClient.create(user);
           userClient.createv2(user);
           user = userClient.getUser(createdUser.getId());
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



interface UserClient {
    @RequestLine("GET /{id}")
    User getUser(@Param("id") Long id);

    @RequestLine("POST /")
    User create(User user);

    @RequestLine("POST /v2")
    void createv2(User user);

    @RequestLine("GET /greet")
    String greet();
}
