package aiss.VimeoMiner.model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.OneToOne;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uri",
        "text",
        "created_on"
})
public class CommentVM {

    @JsonProperty("uri")
    private String uri;
    @JsonProperty("text")
    private String text;
    @JsonProperty("created_on")
    private String createdOn;
    @OneToOne
    @JsonProperty("user")
    private UserVM VMUser;
    //author

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("created_on")
    public String getCreatedOn() {
        return createdOn;
    }

    @JsonProperty("created_on")
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
    @JsonProperty("user")
    public UserVM getUser() {
        return VMUser;
    }
    @JsonProperty("user")
    public void setUser(UserVM VMUser) {
        this.VMUser = VMUser;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CommentVM.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("uri");
        sb.append('=');
        sb.append(((this.uri == null)?"<null>":this.uri));
        sb.append(',');
        sb.append("text");
        sb.append('=');
        sb.append(((this.text == null)?"<null>":this.text));
        sb.append(',');
        sb.append("createdOn");
        sb.append('=');
        sb.append(((this.createdOn == null)?"<null>":this.createdOn));
        sb.append("author");
        sb.append('=');
        sb.append(((this.VMUser == null)?"<null>":this.VMUser));
        sb.append(',');
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

