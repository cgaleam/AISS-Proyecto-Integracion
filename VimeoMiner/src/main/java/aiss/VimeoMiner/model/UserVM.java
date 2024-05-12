package aiss.VimeoMiner.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.OneToOne;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@JsonPropertyOrder({
        "id",
        "name",
        "user_link",
        "picture_link"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVM {

    @JsonProperty("uri")
    private String uri;
    @JsonProperty("name")
    private String name;
    @JsonProperty("user_link")
    private String userLink;
    @JsonProperty("picture_link")
    private String pictureLink;

    private String id;

//    @JsonProperty("comment")
//    private CommentVM VMComment;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public String getId() {
        List<String> aux = List.of(this.uri.split("/"));
        return aux.get(aux.size()-1);
    }

    public void setId(String id){ this.id =id;}

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("user_link")
    public String getUserLink() {
        return userLink;
    }

    @JsonProperty("user_link")
    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    @JsonProperty("picture_link")
    public String getPictureLink() {
        return pictureLink;
    }

    @JsonProperty("picture_link")
    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
//    @JsonProperty("comment")
//    public CommentVM getComment() {
//        return VMComment;
//    }
//    @JsonProperty("comment")
//    public void setComment(CommentVM VMComment) {
//        this.VMComment = VMComment;
//    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserVM.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("uri");
        sb.append('=');
        sb.append(((this.uri == null)?"<null>":this.uri));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("userLink");
        sb.append('=');
        sb.append(((this.userLink == null)?"<null>":this.userLink));
        sb.append(',');
        sb.append("pictureLink");
        sb.append('=');
        sb.append(((this.pictureLink == null)?"<null>":this.pictureLink));
        sb.append(',');
//        sb.append("comment");
//        sb.append('=');
//        sb.append(((this.VMComment == null)?"<null>":this.VMComment));
//        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
