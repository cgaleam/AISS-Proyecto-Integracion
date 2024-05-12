package aiss.YouTubeMiner.transformations;

import aiss.YouTubeMiner.model.videoMiner.CaptionVideo;
import aiss.YouTubeMiner.model.videoMiner.CommentVideo;
import aiss.YouTubeMiner.model.youtube.caption.Caption;
import aiss.YouTubeMiner.model.youtube.comment.Comment;

public class CaptionTransform {
    public static CaptionVideo captionTransform(Caption caption) {

        CaptionVideo videoTrans = new CaptionVideo();

        videoTrans.setId(caption.getId());
        videoTrans.setName(caption.getSnippet().getName());
        videoTrans.setLanguage(caption.getSnippet().getLanguage());


        return videoTrans;
    }
}
