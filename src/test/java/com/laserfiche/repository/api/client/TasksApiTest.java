package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.OperationProgress;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for TasksApi
 */
public class TasksApiTest {

    private TasksApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(TasksApi.class);
    }


    /**
     * 
     *
     * - Cancels an operation. - Provide an operationToken to cancel the operation, if possible. Should be used if an operation was created in error, or is no longer necessary. - Rollbacks must be done manually. For example, if a copy operation is started and is halfway complete when canceled, the client application is responsible for cleaning up the files that were successfully copied before the operation was canceled.
     */
    @Test
    public void cancelOperationTest() {
        String repoId = null;
        String operationToken = null;
        // Void response = api.cancelOperation(repoId, operationToken);

        // TODO: test validations
    }

    /**
     * 
     *
     * - Returns the status of an operation. - Provide an operationToken (returned in other asynchronous routes) to get the operation status, progress, and any errors that may have occurred. When the operation is completed, the Location header can be inspected as a link to the modified resources (if relevant). - OperationStatus can be one of the following values: NotStarted, InProgress, Completed, or Failed.
     */
    @Test
    public void getOperationStatusAndProgressTest() {
        String repoId = null;
        String operationToken = null;
        // OperationProgress response = api.getOperationStatusAndProgress(repoId, operationToken);

        // TODO: test validations
    }
}
