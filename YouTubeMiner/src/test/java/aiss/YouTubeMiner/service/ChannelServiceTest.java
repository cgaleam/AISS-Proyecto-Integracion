package aiss.YouTubeMiner.service;

import aiss.YouTubeMiner.model.youtube.channel.Channel;
import aiss.YouTubeMiner.services.ChannelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ChannelServiceTest {
    @Autowired
    ChannelService channelService;
    @Test
    void findChannels(){
        String id = "UC_pT_Iz6XjuM-eMTlXghdfw";
        Channel c = channelService.findChannel(id);
        System.out.println(c);
    }
}

