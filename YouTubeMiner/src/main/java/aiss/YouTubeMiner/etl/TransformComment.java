package aiss.YouTubeMiner.etl;

import aiss.YouTubeMiner.model.videominer.Comment;
import aiss.YouTubeMiner.model.videominer.User;
import aiss.YouTubeMiner.model.youtube.comment.CommentYouTube;

public class TransformComment {
    

    public static Comment transformComment(CommentYouTube commentYouTube){
        Comment commentFinal= new Comment();
        commentFinal.setId(commentYouTube.getCommentSnippet().getTopLevelComment().getId());

        User autorFinal= new User();
        autorFinal.setId(null);
        autorFinal.setName(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName());
        autorFinal.setUser_link(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorChannelUrl());
        autorFinal.setPicture_link(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorProfileImageUrl());
        commentFinal.setAuthor(autorFinal);

        commentFinal.setText(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getTextOriginal());
        commentFinal.setCreatedOn(commentYouTube.getCommentSnippet().getTopLevelComment().getSnippet().getPublishedAt());
        return commentFinal;

    }

}