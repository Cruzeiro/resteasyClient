package com.cruze.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class RestEasyClientGet {

    public static void main(String[] args) {
        try {
            doGet();
            doPost();
        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private static void doGet() throws Exception {
            ClientRequest request = new ClientRequest(
                    "http://localhost:8080/json/product/get");
            request.accept("application/json");
            ClientResponse<String> response = request.get(String.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

        Gson gson = new Gson();
        Book book = gson.fromJson(response.getEntity(), Book.class);

        System.out.println(book.toString());

    }

    private static void doPost() throws Exception {
            ClientRequest request = new ClientRequest(
                    "http://localhost:8080/json/product/post");
            request.accept("application/json");

        Book book = new Book();
        book.setAuthor("uncle Bob");
        book.setId(23);
        book.setTitle("cleanCode");

            request.body("application/json", new Gson().toJson(book));

            ClientResponse<String> response = request.post(String.class);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(response.getEntity().getBytes())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

    }

}