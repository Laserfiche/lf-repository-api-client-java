package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * RepositoryInfo
 */

public class RepositoryInfo {
  @SerializedName("repoId")
  private String repoId = null;

  @SerializedName("repoName")
  private String repoName = null;

  @SerializedName("webclientUrl")
  private String webclientUrl = null;

  public RepositoryInfo repoId(String repoId) {
    this.repoId = repoId;
    return this;
  }

   /**
   * The repository id.
   * @return repoId
  **/
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
   * The repository name.
   * @return repoName
  **/
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
   * The corresponding repository WebClient url.
   * @return webclientUrl
  **/
  @Schema(description = "The corresponding repository WebClient url.")
  public String getWebclientUrl() {
    return webclientUrl;
  }

  public void setWebclientUrl(String webclientUrl) {
    this.webclientUrl = webclientUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RepositoryInfo repositoryInfo = (RepositoryInfo) o;
    return Objects.equals(this.repoId, repositoryInfo.repoId) &&
        Objects.equals(this.repoName, repositoryInfo.repoName) &&
        Objects.equals(this.webclientUrl, repositoryInfo.webclientUrl);
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

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
