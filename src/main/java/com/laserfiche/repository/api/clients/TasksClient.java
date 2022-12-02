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

public interface TasksClient {

    /**
     *  - Returns the status of an operation.
     * - Provide an operationToken (returned in other asynchronous routes) to get the operation status, progress, and any errors that may have occurred. When the operation is completed, the Location header can be inspected as a link to the modified resources (if relevant).
     * - OperationStatus can be one of the following values: NotStarted, InProgress, Completed, or Failed.
     *
     *  @param parameters An object of type ParametersForGetOperationStatusAndProgress which encapsulates the parameters of getOperationStatusAndProgress method.
     *  @return OperationProgress The return value
     */
    OperationProgress getOperationStatusAndProgress(ParametersForGetOperationStatusAndProgress parameters);

    /**
     *  - Cancels an operation.
     * - Provide an operationToken to cancel the operation, if possible. Should be used if an operation was created in error, or is no longer necessary.
     * - Rollbacks must be done manually. For example, if a copy operation is started and is halfway complete when canceled, the client application is responsible for cleaning up the files that were successfully copied before the operation was canceled.
     *
     *  @param parameters An object of type ParametersForCancelOperation which encapsulates the parameters of cancelOperation method.
     *  @return boolean The return value
     */
    boolean cancelOperation(ParametersForCancelOperation parameters);
}
