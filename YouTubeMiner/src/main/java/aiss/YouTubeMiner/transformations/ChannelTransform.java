package aiss.YouTubeMiner.transformations;

import aiss.YouTubeMiner.model.videoMiner.ChannelVideo;
import aiss.YouTubeMiner.model.videoMiner.VideoVideo;
import aiss.YouTubeMiner.model.youtube.channel.Channel;
import aiss.YouTubeMiner.model.youtube.videoSnippet.VideoSnippet;

import java.util.ArrayList;
import java.util.List;

public class ChannelTransform {


    public static ChannelVideo channelTransform(Channel channel){
        ChannelVideo channelVideo = new ChannelVideo();

        channelVideo.setId(channel.getId());
        channelVideo.setName(channel.getSnippet().getTitle());
        channelVideo.setDescription(channel.getSnippet().getDescription());
        channelVideo.setCreatedTime(channel.getSnippet().getPublishedAt());
        channelVideo.setVideos(parseVideos(channel.getVideos()));

        return channelVideo;
    }

    private static List<VideoVideo> parseVideos(List<VideoSnippet> videos) {
        List<VideoVideo> res = new ArrayList<>();
        for(VideoSnippet video: videos){
            VideoVideo videoTrans = VideoTransform.videoTransform(video);
            res.add(videoTrans);

        }
    return res;
    }


}
