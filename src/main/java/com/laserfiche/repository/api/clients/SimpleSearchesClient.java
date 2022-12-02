package com.laserfiche.repository.api.clients;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.Header;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutionException;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.laserfiche.repository.api.clients.params.*;

public interface SimpleSearchesClient {

    /**
     * - Runs a &quot;simple&quot; search operation on the repository.
     * - Returns a truncated search result listing.
     * - Search result listing may be truncated, depending on number of results. Additionally, searches may time out if they take too long. Use the other search route to run full searches.
     * - Optionally returns field values for the entries in the search result listing. Each field name needs to be specified in the request. Maximum limit of 10 field names.
     * - If field values are requested, only the first value is returned if it is a multi value field.
     * - Null or Empty field values should not be used to determine if a field is assigned to the entry.
     *
     *  @param parameters An object of type ParametersForCreateSimpleSearchOperation which encapsulates the parameters of createSimpleSearchOperation method.
     *  @return ODataValueContextOfIListOfEntry The return value
     */
    ODataValueContextOfIListOfEntry createSimpleSearchOperation(ParametersForCreateSimpleSearchOperation parameters);
}
