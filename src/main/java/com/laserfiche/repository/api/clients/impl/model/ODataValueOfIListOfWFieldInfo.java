package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import com.laserfiche.api.client.model.WFieldInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ODataValueOfIListOfWFieldInfo {

    @JsonProperty("value")
    private List<WFieldInfo> value = null;

    public ODataValueOfIListOfWFieldInfo value(List<WFieldInfo> value) {
        this.value = value;
        return this;
    }

    public ODataValueOfIListOfWFieldInfo addValueItem(WFieldInfo valueItem) {
        if (this.value == null) {
            this.value = new ArrayList<WFieldInfo>();
        }
        this.value.add(valueItem);
        return this;
    }

    /**
 * Returns value
 * @return value
 
*/
    @Schema(description = "")
    public List<WFieldInfo> getValue() {
        return value;
    }

    public void setValue(List<WFieldInfo> value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ODataValueOfIListOfWFieldInfo odataValueOfIListOfWFieldInfo = (ODataValueOfIListOfWFieldInfo) o;
        return Objects.equals(this.value, odataValueOfIListOfWFieldInfo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ODataValueOfIListOfWFieldInfo {\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
