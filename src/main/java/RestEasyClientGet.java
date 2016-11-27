import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

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

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(response.getEntity().getBytes())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }


    }

    private static void doPost() throws Exception {
            ClientRequest request = new ClientRequest(
                    "http://localhost:8080/json/product/post");
            request.accept("application/json");

            String input = "{\"id\":100,\"author\":\"name\"}";
            request.body("application/json", input);

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