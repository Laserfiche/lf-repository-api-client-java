package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepositoryInfo {

    @JsonProperty("repoId")
    private String repoId = null;

    @JsonProperty("repoName")
    private String repoName = null;

    @JsonProperty("webclientUrl")
    private String webclientUrl = null;

    public RepositoryInfo repoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
 * Returns the repository id.
 * @return repoId
*/
    @Schema(description = "The repository id.")
    public String getRepoId() {
        return repoId;
    }

    public void setRepoId(String repoId) {
        this.repoId = repoId;
    }

    public RepositoryInfo repoName(String repoName) {
        this.repoName = repoName;
        return this;
    }

    /**
 * Returns the repository name.
 * @return repoName
*/
    @Schema(description = "The repository name.")
    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public RepositoryInfo webclientUrl(String webclientUrl) {
        this.webclientUrl = webclientUrl;
        return this;
    }

    /**
 * Returns the corresponding repository WebClient url.
 * @return webclientUrl
*/
    @Schema(description = "The corresponding repository WebClient url.")
    public String getWebclientUrl() {
        return webclientUrl;
    }

    public void setWebclientUrl(String webclientUrl) {
        this.webclientUrl = webclientUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RepositoryInfo repositoryInfo = (RepositoryInfo) o;
        return Objects.equals(this.repoId, repositoryInfo.repoId) && Objects.equals(this.repoName, repositoryInfo.repoName) && Objects.equals(this.webclientUrl, repositoryInfo.webclientUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repoId, repoName, webclientUrl);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RepositoryInfo {\n");
        sb.append("    repoId: ").append(toIndentedString(repoId)).append("\n");
        sb.append("    repoName: ").append(toIndentedString(repoName)).append("\n");
        sb.append("    webclientUrl: ").append(toIndentedString(webclientUrl)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
