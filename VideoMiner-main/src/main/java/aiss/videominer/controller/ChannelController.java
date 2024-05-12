package aiss.videominer.controller;

import aiss.videominer.exceptions.ChannelNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


// Uri: http://localhost:42000/api/videominer/channels
@RestController
@RequestMapping("/api/videominer/channels")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    @GetMapping("/{channelId}")
    public Channel readChannel(@PathVariable String channelId)
            throws ChannelNotFoundException{
        Channel res = null;
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        res = channel.get();
        return res;
    }

    @GetMapping
    public List<Channel> readChannels() {
        return channelRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel createChannel(@Valid @RequestBody Channel channel) {
        return channelRepository.save(channel);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{channelId}")
    public void deleteChannel(@PathVariable String channelId)
            throws ChannelNotFoundException {
                if (channelRepository.existsById(channelId)) {
                    channelRepository.deleteById(channelId);
                } else {
            throw new ChannelNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{channelId}")
    public void updateChannel(@PathVariable String channelId,@Valid @RequestBody Channel updatedChannel)
            throws ChannelNotFoundException {

        Optional<Channel> channel = channelRepository.findById(channelId);
        if(channel.isPresent()){
            Channel updatingChannel = channel.get();
            updatingChannel.setName(updatedChannel.getName());
            updatingChannel.setDescription(updatedChannel.getDescription());
            updatingChannel.setCreatedTime(updatedChannel.getCreatedTime());
            updatingChannel.setVideos(updatedChannel.getVideos());
        } else {
            throw new ChannelNotFoundException();
        }
    }


}