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

public interface ServerSessionClient {

    /**
     *  - Deprecated.
     * - Invalidates the server session.
     * - Acts as a &quot;logout&quot; operation, and invalidates the session associated with the provided access token. This method should be used when the client wants to clean up the current session.
     * - Only available in Laserfiche Cloud.
     *
     *  @param parameters An object of type ParametersForInvalidateServerSession which encapsulates the parameters of invalidateServerSession method.
     *  @return ODataValueOfBoolean The return value
     */
    ODataValueOfBoolean invalidateServerSession(ParametersForInvalidateServerSession parameters);

    /**
     *  - Deprecated. This function is a no-op, always returns 200.
     * - Only available in Laserfiche Cloud.
     *
     *  @param parameters An object of type ParametersForCreateServerSession which encapsulates the parameters of createServerSession method.
     *  @return ODataValueOfBoolean The return value
     */
    ODataValueOfBoolean createServerSession(ParametersForCreateServerSession parameters);

    /**
     *  - Deprecated.
     * - Refreshes the session associated with the access token. This is only necessary if you want to keep the same session alive, otherwise a new session will be automatically created when the session expires.
     * - When a client application wants to keep a session alive that has been idle for an hour, this route can be used to refresh the expiration timer associated with the access token.
     * - Only available in Laserfiche Cloud.
     *
     *  @param parameters An object of type ParametersForRefreshServerSession which encapsulates the parameters of refreshServerSession method.
     *  @return ODataValueOfDateTime The return value
     */
    ODataValueOfDateTime refreshServerSession(ParametersForRefreshServerSession parameters);
}
