package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.Attribute;
import com.laserfiche.repository.api.client.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for AttributesApi
 */
public class AttributesApiTest {

    private AttributesApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(AttributesApi.class);
    }


    /**
     * Get the attribute key value pairs associated with the authenticated user.
     *
     * - Returns the attribute key value pairs associated with the authenticated user. Alternatively, return only the attribute key value pairs that are associated with the \&quot;Everyone\&quot; group. - Attribute keys can be used with subsequent calls to get specific attribute values. - Default page size: 100. Allowed OData query options: Select, Count, OrderBy, Skip, Top, SkipToken, Prefer. Optional query parameters: everyone (bool, default false). When true, this route does not return the attributes that are tied to the currently authenticated user, but rather the attributes assigned to the \&quot;Everyone\&quot; group. Note when this is true, the response does not include both the \&quot;Everyone\&quot; groups attribute and the currently authenticated user, but only the \&quot;Everyone\&quot; groups.
     */
    @Test
    public void getTrusteeAttributeKeyValuePairsTest() {
        String repoId = null;
        Boolean everyone = null;
        String prefer = null;
        String $select = null;
        String $orderby = null;
        Integer $top = null;
        Integer $skip = null;
        Boolean $count = null;
        // ODataValueContextOfListOfAttribute response = api.getTrusteeAttributeKeyValuePairs(repoId, everyone, prefer, $select, $orderby, $top, $skip, $count);

        // TODO: test validations
    }

    /**
     * Get an attribute object by key associated with the authenticated user.
     *
     * - Returns the attribute associated with the key. Alternatively, return the attribute associated with the key within \&quot;Everyone\&quot; group. - Optional query parameters: everyone (bool, default false). When true, the server only searches for the attribute value with the given key upon the authenticated users attributes. If false, only the authenticated users attributes will be queried.
     */
    @Test
    public void getTrusteeAttributeValueByKeyTest() {
        String repoId = null;
        String attributeKey = null;
        Boolean everyone = null;
        // Attribute response = api.getTrusteeAttributeValueByKey(repoId, attributeKey, everyone);

        // TODO: test validations
    }
}
