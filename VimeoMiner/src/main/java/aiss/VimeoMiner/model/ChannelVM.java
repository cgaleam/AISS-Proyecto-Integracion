package aiss.VimeoMiner.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "created_time",
        "videos",

})
@JsonIgnoreProperties(ignoreUnknown = true)

public class ChannelVM {

    public String id;

    @JsonProperty("name")
    private String name;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("created_time")
    private String createdTime;
    @JsonProperty("description")
    private String description;
    @JsonProperty("videos")
    private List<VideoVM> videos;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


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

    @JsonProperty("created_time")
    public String getCreatedTime() {
        return createdTime;
    }

    @JsonProperty("created_time")
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
    @JsonProperty("videos")
    public List<VideoVM> getVideos() {
        return videos;
    }
    @JsonProperty("videos")
    public void setVideos(List<VideoVM> videos) {
        this.videos = videos;
    }


    public String getId() {
        List<String> aux = List.of(this.uri.split("/"));
        return aux.get(aux.size()-1);
    }

    public void setId(String id){ this.id =id;}

//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }


    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", videos=" + videos +
                '}';
    }
}

