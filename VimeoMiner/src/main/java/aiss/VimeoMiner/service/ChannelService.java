package aiss.VimeoMiner.service;

import aiss.VimeoMiner.model.ChannelVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@ComponentScan
@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "17f1fa3527765a7c2f5c6f3c1317aef0";

    public ChannelVM getChannel(String id) {
        ChannelVM res = null;
        String uri = "https://api.vimeo.com/channels/"+id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + TOKEN);
        HttpEntity<ChannelVM> request = new HttpEntity<>(null, headers);

        ResponseEntity<ChannelVM> response = restTemplate.exchange(uri, HttpMethod.GET, request, ChannelVM.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<ChannelVM> getAllChannels() {
        List<ChannelVM> res = new ArrayList<>();
        String uri = "https://api.vimeo.com/channels";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + TOKEN);
        HttpEntity<ChannelVM> request = new HttpEntity<>(null, headers);

        ResponseEntity<ChannelVM> response = restTemplate.exchange(uri, HttpMethod.GET, request, ChannelVM.class);

        if(response.getBody() != null){
            res.add(response.getBody());
        }
        return res;
    }




}
