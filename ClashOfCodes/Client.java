package webClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Client {
    Gson gs;
    private String link;
    private CloseableHttpClient client;
    public String winner = "0";
    public boolean isStarted = false;


    public Client(String link) {
        this.link = link;
    }

    public Byte[][] getGameField() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(link+"/");
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    //Result
                    String result = EntityUtils.toString(entity);
                    gs = new Gson();
                    Type listType = new TypeToken<Byte[][]>(){}.getType();
                    Byte[][] gameField = gs.fromJson(result, listType);
                    return gameField;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void postColumn(byte column){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {

            HttpPost request = new HttpPost(link+"/" + column);
            gs = new Gson();
            String body = gs.toJson(column);
            request.setEntity(new StringEntity(body));

            CloseableHttpResponse response = httpClient.execute(request);
            String result = EntityUtils.toString(response.getEntity());
            if(result.equals("1") || result.equals("2")){
                this.winner = result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGame(){
        this.winner = "0";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {

            HttpPut request = new HttpPut(link+"/");
            CloseableHttpResponse response = httpClient.execute(request);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
