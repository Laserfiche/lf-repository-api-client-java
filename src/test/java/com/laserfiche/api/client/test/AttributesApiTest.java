/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>51c16645afa5983c3eb4a849158d6f1e355d2bb0_.20220512.1</p>
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.api.client.test;

import com.laserfiche.api.client.apiserver.AttributesApi;
import com.laserfiche.api.client.model.Attribute;
import com.laserfiche.api.client.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.api.client.model.ProblemDetails;
import org.junit.Test;
import org.junit.Ignore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for AttributesApi
 */
@Ignore
public class AttributesApiTest {

    private final AttributesApi api = new AttributesApi();

    /**
     * Get the attribute key value pairs associated with the authenticated user.
     *
     * - Returns the attribute key value pairs associated with the authenticated user. Alternatively, return only the attribute key value pairs that are associated with the \&quot;Everyone\&quot; group. - Attribute keys can be used with subsequent calls to get specific attribute values. - Default page size: 100. Allowed OData query options: Select, Count, OrderBy, Skip, Top, SkipToken, Prefer. Optional query parameters: everyone (bool, default false). When true, this route does not return the attributes that are tied to the currently authenticated user, but rather the attributes assigned to the \&quot;Everyone\&quot; group. Note when this is true, the response does not include both the \&quot;Everyone\&quot; groups attribute and the currently authenticated user, but only the \&quot;Everyone\&quot; groups.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getTrusteeAttributeKeyValuePairsTest() throws Exception {
        String repoId = null;
        Boolean everyone = null;
        String prefer = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        ODataValueContextOfListOfAttribute response = api.getTrusteeAttributeKeyValuePairs(repoId, everyone, prefer, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }
    /**
     * Get an attribute object by key associated with the authenticated user.
     *
     * - Returns the attribute associated with the key. Alternatively, return the attribute associated with the key within \&quot;Everyone\&quot; group. - Optional query parameters: everyone (bool, default false). When true, the server only searches for the attribute value with the given key upon the authenticated users attributes. If false, only the authenticated users attributes will be queried.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getTrusteeAttributeValueByKeyTest() throws Exception {
        String repoId = null;
        String attributeKey = null;
        Boolean everyone = null;
        Attribute response = api.getTrusteeAttributeValueByKey(repoId, attributeKey, everyone);

        // TODO: test validations
    }
}
