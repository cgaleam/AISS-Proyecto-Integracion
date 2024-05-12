package aiss.VimeoMiner.model;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "release_time",
        "comments",
        "text_tracks"
})

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoVM {


    public String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("uri")
    private String uri;
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

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("release_time")
    public String getReleaseTime() {
        return releaseTime;
    }

    @JsonProperty("release_time")
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public List<CommentVM> getComments() {
        return comments;
    }

    public void setComments(List<CommentVM> comments) {
        this.comments = comments;
    }

    public List<TextTrackVM> getTextTracks() {
        return textTracks;
    }

    public void setTextTracks(List<TextTrackVM> textTracks) {
        this.textTracks = textTracks;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//    public void addTextTracks (List<TextTrackVM> textTrackList){
//        this.textTracks.addAll(textTrackList);
//    }

}
