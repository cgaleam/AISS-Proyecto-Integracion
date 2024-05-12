package aiss.VimeoMiner.model;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "release_time"
})

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoVM {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("release_time")
    private String releaseTime;
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonProperty("comments")
    private List<CommentVM> comments;
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonProperty("text_track")
    private List<TextTrackVM> textTracks;

    @JsonProperty("comments")
    public List<CommentVM> getComments() {
        return comments;
    }
    @JsonProperty("comments")
    public void setComments(List<CommentVM> comments) {
        this.comments = comments;
    }
    @JsonProperty("captions")
    public List<TextTrackVM> getCaptions() {
        return textTracks;
    }
    @JsonProperty("texttracks")
    public void setCaptions(List<TextTrackVM> captions) {
        this.textTracks = captions;
    }


    public VideoVM(){
        this.textTracks = new ArrayList<>();
        this.comments = new ArrayList<>();
    }@JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("uri")
    public void setId(String id) {
        this.id = id.split("/")[2];
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", captions=" + textTracks + '\'' +
                ", comments=" + comments + '\'' +
                '}';
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("releaseTime")
    public String getReleaseTime() {
        return releaseTime;
    }

    @JsonProperty("release_time")
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

}
