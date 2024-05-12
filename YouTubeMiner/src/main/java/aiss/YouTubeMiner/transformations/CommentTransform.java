package aiss.YouTubeMiner.transformations;

import aiss.YouTubeMiner.model.videoMiner.CommentVideo;
import aiss.YouTubeMiner.model.videoMiner.UserVideo;
import aiss.YouTubeMiner.model.youtube.comment.Comment;

public class CommentTransform {
    public static CommentVideo commentTransform(Comment comment) {
        CommentVideo commentTrans = new CommentVideo();

        commentTrans.setId(comment.getCommentSnippet().getTopLevelComment().getId());

        UserVideo usuario = new UserVideo();
        usuario.setId(null);
        usuario.setName(comment.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName());
        usuario.setPicture_link(comment.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorProfileImageUrl());
        usuario.setUser_link(comment.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorChannelUrl());

        commentTrans.setAuthor(usuario);

        commentTrans.setText(comment.getCommentSnippet().getTopLevelComment().getSnippet().getTextOriginal());
        commentTrans.setCreatedOn(comment.getCommentSnippet().getTopLevelComment().getSnippet().getPublishedAt());
        return commentTrans;
    }
}
