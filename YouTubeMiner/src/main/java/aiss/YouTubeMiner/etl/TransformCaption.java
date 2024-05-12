package aiss.YouTubeMiner.etl;

import aiss.YouTubeMiner.model.videominer.Caption;
import aiss.YouTubeMiner.model.youtube.caption.CaptionYouTube;

public class TransformCaption {

    public static Caption transformCaption(CaptionYouTube captionYouTube){
        Caption captionFinal= new Caption();
        captionFinal.setId(captionYouTube.getId());
        captionFinal.setName(captionYouTube.getSnippet().getName());
        captionFinal.setLanguage(captionYouTube.getSnippet().getLanguage());
        return captionFinal;

    }
}

