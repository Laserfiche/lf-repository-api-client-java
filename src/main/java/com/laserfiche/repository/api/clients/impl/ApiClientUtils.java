package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.impl.model.APIServerException;
import com.laserfiche.repository.api.clients.impl.model.CreateEntryOperations;
import com.laserfiche.repository.api.clients.impl.model.CreateEntryResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Helper class containing utility functions for the RepositoryApiClient.
 */
class ApiClientUtils {
    private ApiClientUtils() {
        throw new IllegalStateException("Utility class with all static methods are not meant to be instantiated.");
    }

    /**
     * Returns a human-readable summary of the {@link CreateEntryResult}.
     *
     * @param createEntryResult The createEntryResult.
     * @return A human-readable summary of the {@link CreateEntryResult}.
     */
    static String getCreateEntryResultSummary(CreateEntryResult createEntryResult) {
        Collection<String> messages = new ArrayList<>();
        if (createEntryResult != null && createEntryResult.getOperations() != null) {
            CreateEntryOperations operations = createEntryResult.getOperations();
            if (operations.getEntryCreate() != null) {
                Integer entryId = operations.getEntryCreate().getEntryId();
                if (entryId != null && entryId > 0) {
                    messages.add(String.format("EntryId=%s.", entryId));
                }
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getEntryCreate()
                        .getExceptions()));
            }
            if (operations.getSetEdoc() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetEdoc()
                        .getExceptions()));
            if (operations.getSetTemplate() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetTemplate()
                        .getExceptions()));
            if (operations.getSetFields() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetFields()
                        .getExceptions()));
            if (operations.getSetTags() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetTags()
                        .getExceptions()));
            if (operations.getSetLinks() != null)
                messages.add(getErrorMessagesFromAPIServerExceptions(operations
                        .getSetLinks()
                        .getExceptions()));
        }
        return messages
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "))
                .trim();
    }

    private static String getErrorMessagesFromAPIServerExceptions(Collection<APIServerException> errors) {
        if (errors == null || errors.isEmpty())
            return null;
        return errors
                .stream()
                .filter(Objects::nonNull)
                .map(APIServerException::getMessage)
                .collect(Collectors.joining(" "))
                .trim();
    }
}
