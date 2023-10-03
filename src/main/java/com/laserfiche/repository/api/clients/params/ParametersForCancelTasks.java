package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.TasksClientImpl#cancelTasks(ParametersForCancelTasks) cancelTasks}.
 */
public class ParametersForCancelTasks {

    /**
     * The requested repository ID
     */
    private String repositoryId;

    /**
     * An array of task IDs. Leave this parameter empty to cancel the list of all the tasks associated with the current access token.
     */
    private String[] taskIds;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID
     * @return {@link ParametersForCancelTasks} The return value
     */
    public ParametersForCancelTasks setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    /**
     * The requested repository ID
     *
     * @return {@link String} The return value
     */
    public String getRepositoryId() {
        return this.repositoryId;
    }

    /**
     * Sets the value of the taskIds parameter and returns the current object, to enable chaining further setters.
     *
     * @param taskIds An array of task IDs. Leave this parameter empty to cancel the list of all the tasks associated with the current access token.
     * @return {@link ParametersForCancelTasks} The return value
     */
    public ParametersForCancelTasks setTaskIds(String... taskIds) {
        this.taskIds = taskIds;
        return this;
    }

    /**
     * An array of task IDs. Leave this parameter empty to cancel the list of all the tasks associated with the current access token.
     *
     * @return {@link String[]} The return value
     */
    public String[] getTaskIds() {
        return this.taskIds;
    }
}
