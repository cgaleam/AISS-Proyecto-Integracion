package aiss.YouTubeMiner.etl;

import aiss.YouTubeMiner.model.videominer.Channel;
import aiss.YouTubeMiner.model.videominer.Video;
import aiss.YouTubeMiner.model.youtube.channel.ChannelYouTube;
import aiss.YouTubeMiner.model.youtube.videoSnippet.VideoSnippet;

import java.util.LinkedList;
import java.util.List;

public class TransformChannel {

    public static Channel transformChannel(ChannelYouTube channelYouTube){
        Channel channelFinal= new Channel();
        channelFinal.setId(channelYouTube.getId());
        channelFinal.setDescription(channelYouTube.getSnippet().getDescription());
        channelFinal.setName(channelYouTube.getSnippet().getTitle());
        channelFinal.setCreatedTime(channelYouTube.getSnippet().getPublishedAt());
        channelFinal.setVideos(parseoVideos(channelYouTube.getVideos()));
        return channelFinal;
    }

    private static List<Video> parseoVideos(List<VideoSnippet> videos) {
        List<Video> listaVideo = new LinkedList<>();
        for(VideoSnippet v:videos){
            Video videoFinal= TransformVideo.transformVideo(v);
            listaVideo.add(videoFinal);
        }
        return listaVideo;
    }
}
