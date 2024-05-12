package aiss.YouTubeMiner.transformations;

import aiss.YouTubeMiner.model.videoMiner.CaptionVideo;
import aiss.YouTubeMiner.model.videoMiner.CommentVideo;
import aiss.YouTubeMiner.model.videoMiner.VideoVideo;
import aiss.YouTubeMiner.model.youtube.caption.Caption;
import aiss.YouTubeMiner.model.youtube.comment.Comment;
import aiss.YouTubeMiner.model.youtube.videoSnippet.VideoSnippet;
import aiss.YouTubeMiner.services.VideoService;

import java.util.ArrayList;
import java.util.List;

public class VideoTransform {

    public static VideoVideo videoTransform(VideoSnippet video){
        VideoVideo videoTrans = new VideoVideo();
        videoTrans.setId(video.getId().getVideoId());
        videoTrans.setName(video.getSnippet().getTitle());
        videoTrans.setDescription(video.getSnippet().getDescription());
        videoTrans.setReleaseTime(video.getSnippet().getPublishedAt());
        videoTrans.setCaptions(parseCaption(video.getCaptions()));
        videoTrans.setComments(parseComment(video.getComments()));
        return videoTrans;
    }

    private static List<CommentVideo> parseComment(List<Comment> comments) {
        List<CommentVideo> res = new ArrayList<>();
        for(Comment comment: comments){
            CommentVideo commentTrans = CommentTransform.commentTransform(comment);
            res.add(commentTrans);
        }
        return res;
    }

    private static List<CaptionVideo> parseCaption(List<Caption> captions) {
        List<CaptionVideo> res = new ArrayList<>();
        for(Caption caption: captions){
            CaptionVideo captionTrans = CaptionTransform.captionTransform(caption);
            res.add(captionTrans);
        }
        return res;
    }

}
