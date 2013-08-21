package be.ordina.droidina.fsm.http;

import android.content.Context;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;
import java.util.Set;

/**
 * Created by ordinamobile on 24/07/13.
 */
public class RestServiceConsumer {
    private static RestServiceConsumer instance;

    private RestTemplate restTemplate;
    private Context context;

    public static RestServiceConsumer getInstance(Context context){
        if(instance==null){
            synchronized (RestServiceConsumer.class){
                if(instance==null){
                    instance=new RestServiceConsumer(context);
                }
            }
        }
        return instance;
    }

    public RestServiceConsumer(Context context){
        this.context = context;
        this.restTemplate = new RestTemplate(true);
    }

    //HTTP DELETE methods RestTemplate
    public void delete(String url, Object... urlVariables) throws RestClientException{}

    public void delete(String url, Map<String, ?> urlVariables) throws RestClientException{}

//    public void delete(URI url) throws RestClientException{}

    //HTTP GET methods RestTemplate
    public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) throws RestClientException{return null;}

    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> urlVariables) throws RestClientException{return null;}

    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException{return null;}

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... urlVariables){return null;}

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> urlVariables){return null;}

    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException{return null;}

    //HTTP HEAD methods RestTemplate
//    public HttpHeaders headForHeaders(String url, Object... urlVariables) throws RestClientException{return null;}
//
//    public HttpHeaders headForHeaders(String url, Map<String, ?> urlVariables) throws RestClientException{return null;}
//
//    public HttpHeaders headForHeaders(URI url) throws RestClientException{return null;}

    //HTTP OPTIONS methods RestTemplate
//    public Set<HttpMethod> optionsForAllow(String url, Object... urlVariables) throws RestClientException{return null;}
//
//    public Set<HttpMethod> optionsForAllow(String url, Map<String, ?> urlVariables) throws RestClientException{return null;}
//
//    public Set<HttpMethod> optionsForAllow(URI url) throws RestClientException{return null;}

    //HTTP POST methods RestTemplate
//    public URI postForLocation(String url, Object request, Object... urlVariables) throws RestClientException{return null;}
//
//    public URI postForLocation(String url, Object request, Map<String, ?> urlVariables){return null;}
//
//    public URI postForLocation(URI url, Object request) throws RestClientException{return null;}

    public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables){
        return restTemplate.postForObject(url, request, responseType, uriVariables);
    }

    public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables){return null;}

    public <T> T postForObject(URI url, Object request, Class<T> responseType) throws RestClientException{return null;}

    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables){return null;}

    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException{return null;}

    public <T> ResponseEntity<T> postForEntity(URI url, Object request, Class<T> responseType) throws RestClientException{return null;}

    //HTTP PUT methods RestTemplate
    public void put(String url, Object request, Object... urlVariables) throws RestClientException{}

    public void put(String url, Object request, Map<String, ?> urlVariables) throws RestClientException{}
}
