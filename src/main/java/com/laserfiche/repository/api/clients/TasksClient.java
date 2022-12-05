package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.OperationProgress;
import com.laserfiche.repository.api.clients.params.ParametersForCancelOperation;
import com.laserfiche.repository.api.clients.params.ParametersForGetOperationStatusAndProgress;

public interface TasksClient {

    /**
     * - Returns the status of an operation.
     * - Provide an operationToken (returned in other asynchronous routes) to get the operation status, progress, and any errors that may have occurred. When the operation is completed, the Location header can be inspected as a link to the modified resources (if relevant).
     * - OperationStatus can be one of the following values: NotStarted, InProgress, Completed, or Failed.
     *
     * @param parameters An object of type ParametersForGetOperationStatusAndProgress which encapsulates the parameters of getOperationStatusAndProgress method.
     * @return OperationProgress The return value
     */
    OperationProgress getOperationStatusAndProgress(ParametersForGetOperationStatusAndProgress parameters);

    /**
     * - Cancels an operation.
     * - Provide an operationToken to cancel the operation, if possible. Should be used if an operation was created in error, or is no longer necessary.
     * - Rollbacks must be done manually. For example, if a copy operation is started and is halfway complete when canceled, the client application is responsible for cleaning up the files that were successfully copied before the operation was canceled.
     *
     * @param parameters An object of type ParametersForCancelOperation which encapsulates the parameters of cancelOperation method.
     * @return boolean The return value
     */
    boolean cancelOperation(ParametersForCancelOperation parameters);
}
