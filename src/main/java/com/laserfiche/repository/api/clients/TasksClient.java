package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.CancelTasksResponse;
import com.laserfiche.repository.api.clients.impl.model.TaskCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForCancelTasks;
import com.laserfiche.repository.api.clients.params.ParametersForListTasks;

/**
 * The Laserfiche Repository Tasks API client.
 */
public interface TasksClient {

    /**
     * - Returns the status of a set of one or more tasks.<br>- Provide a comma-separated list of task IDs to get the task status, progress, and any errors that may have occurred.<br>- Leave the taskIds query parameter empty, to get the list of all the task IDs associated with the current access token.<br>- TaskStatus can be one of the following values: NotStarted, InProgress, Completed, Cancelled, or Failed.<br>- This API employs long polling technique and could return the result immediately (e.g. if the export operation is failed or completed successfully) or after at most 60 seconds.<br>- Required OAuth scope: None
     *
     * @param parameters An object of type {@link ParametersForListTasks} which encapsulates the parameters of {@link #listTasks listTasks} method.
     * @return {@link TaskCollectionResponse} The return value
     */
    TaskCollectionResponse listTasks(ParametersForListTasks parameters);

    /**
     * - Starts the cancellation for a set of one or more tasks.<br>- Provide comma-separated list of task IDs to cancel. Should be used if an operation was created in error, or is no longer necessary.<br>- Check the status of the task to determine if the task has been cancelled successfully.<br>- Leave the taskIds query parameter empty, to cancel the list of all the task IDs associated with the current access token.<br>- Rollbacks must be done manually. For example, if a copy operation is started and is halfway complete when canceled, the client application is responsible for cleaning up the files that were successfully copied before the operation was canceled.<br>- Required OAuth scope: None
     *
     * @param parameters An object of type {@link ParametersForCancelTasks} which encapsulates the parameters of {@link #cancelTasks cancelTasks} method.
     * @return {@link CancelTasksResponse} The return value
     */
    CancelTasksResponse cancelTasks(ParametersForCancelTasks parameters);
}
