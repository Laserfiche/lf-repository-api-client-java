// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;

/**
 * The Laserfiche Repository Tasks API client.
 */
public interface TasksClient {

    /**
     * - Returns the status of an operation.<br>- Provide an operationToken (returned in other asynchronous routes) to get the operation status, progress, and any errors that may have occurred. When the operation is completed, the Location header can be inspected as a link to the modified resources (if relevant).<br>- OperationStatus can be one of the following values: NotStarted, InProgress, Completed, or Failed.
     *
     * @param parameters An object of type {@link ParametersForGetOperationStatusAndProgress} which encapsulates the parameters of {@link #getOperationStatusAndProgress getOperationStatusAndProgress} method.
     * @return {@link OperationProgress} The return value
     */
    OperationProgress getOperationStatusAndProgress(ParametersForGetOperationStatusAndProgress parameters);

    /**
     * - Cancels an operation.<br>- Provide an operationToken to cancel the operation, if possible. Should be used if an operation was created in error, or is no longer necessary.<br>- Rollbacks must be done manually. For example, if a copy operation is started and is halfway complete when canceled, the client application is responsible for cleaning up the files that were successfully copied before the operation was canceled.
     *
     * @param parameters An object of type {@link ParametersForCancelOperation} which encapsulates the parameters of {@link #cancelOperation cancelOperation} method.
     * @return boolean The return value
     */
    boolean cancelOperation(ParametersForCancelOperation parameters);
}
