package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Represents a Laserfiche repository.
 */
@Schema(description = "Represents a Laserfiche repository.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Repository {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("webClientUrl")
    private String webClientUrl = null;

    public Repository id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the repository id.
     * @return id
     */
    @Schema(description = "The repository id.")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Repository name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the repository name.
     * @return name
     */
    @Schema(description = "The repository name.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Repository webClientUrl(String webClientUrl) {
        this.webClientUrl = webClientUrl;
        return this;
    }

    /**
     * Returns the corresponding repository Web Client url.
     * @return webClientUrl
     */
    @Schema(description = "The corresponding repository Web Client url.")
    public String getWebClientUrl() {
        return webClientUrl;
    }

    public void setWebClientUrl(String webClientUrl) {
        this.webClientUrl = webClientUrl;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Repository repository = (Repository) o;
        return Objects.equals(this.id, repository.id) && Objects.equals(this.name, repository.name) && Objects.equals(this.webClientUrl, repository.webClientUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, webClientUrl);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Repository {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    webClientUrl: ").append(toIndentedString(webClientUrl)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
