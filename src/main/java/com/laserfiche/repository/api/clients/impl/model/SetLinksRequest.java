// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Request body for assigning links to an entry.
 */
@Schema(description = "Request body for assigning links to an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetLinksRequest {

    @JsonProperty("links")
    private List<LinkToUpdate> links = null;

    public SetLinksRequest links(List<LinkToUpdate> links) {
        this.links = links;
        return this;
    }

    public SetLinksRequest addLinksItem(LinkToUpdate linksItem) {
        if (this.links == null) {
            this.links = new ArrayList<LinkToUpdate>();
        }
        this.links.add(linksItem);
        return this;
    }

    /**
     * Returns the links that will be assigned to the entry.
     *
     * @return links
     */
    @Schema(description = "The links that will be assigned to the entry.")
    public List<LinkToUpdate> getLinks() {
        return links;
    }

    public void setLinks(List<LinkToUpdate> links) {
        this.links = links;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetLinksRequest setLinksRequest = (SetLinksRequest) o;
        return Objects.equals(this.links, setLinksRequest.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(links);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SetLinksRequest {\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
