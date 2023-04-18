package unit.clients.impl;

import com.laserfiche.repository.api.clients.impl.ApiClientUtils;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetCreateEntryResultSumary {
    @Test
    void getCreateEntryResultSummary_NullCreateEntryResult() {
        String result = ApiClientUtils.getCreateEntryResultSummary(null);

        assertEquals("", result);
    }

    @Test
    void getCreateEntryResultSummary_NullCreateEntryOperations() {
        CreateEntryResult createEntryResult = new CreateEntryResult();
        createEntryResult.setOperations(null);

        String result = ApiClientUtils.getCreateEntryResultSummary(createEntryResult);

        assertEquals("", result);
    }

    @Test
    void getCreateEntryResultSummary_NullExceptionsList() {
        String errorMessage1 = "There was an error.";
        APIServerException apiServerException = new APIServerException();
        apiServerException.setMessage(errorMessage1);
        List<APIServerException> exceptions = new ArrayList<>();
        exceptions.add(apiServerException);

        CreateEntryOperations createEntryOperations = new CreateEntryOperations();
        CreateEntryResult createEntryResult = new CreateEntryResult();
        createEntryResult.setOperations(createEntryOperations);

        SetTemplate setTemplate = new SetTemplate();
        setTemplate.setExceptions(null);
        createEntryOperations.setTemplate(setTemplate);

        SetFields setFields = new SetFields();
        setFields.setExceptions(exceptions);
        createEntryOperations.setFields(setFields);

        String result = ApiClientUtils.getCreateEntryResultSummary(createEntryResult);

        String expectedResult = String.format("%s", errorMessage1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getCreateEntryResultSummary_NullException() {
        String errorMessage1 = "There was an error.";
        APIServerException apiServerException = new APIServerException();
        apiServerException.setMessage(errorMessage1);
        List<APIServerException> exceptions = new ArrayList<>();
        exceptions.add(null);
        exceptions.add(apiServerException);
        exceptions.add(null);

        CreateEntryOperations createEntryOperations = new CreateEntryOperations();
        CreateEntryResult createEntryResult = new CreateEntryResult();
        createEntryResult.setOperations(createEntryOperations);

        SetFields setFields = new SetFields();
        setFields.setExceptions(exceptions);
        createEntryOperations.setFields(setFields);

        String result = ApiClientUtils.getCreateEntryResultSummary(createEntryResult);

        String expectedResult = String.format("%s", errorMessage1);
        assertEquals(expectedResult, result);
    }

    @Test
    void getCreateEntryResultSummary_NoOperationsHaveExceptions() {
        int entryId = 123;
        EntryCreate entryCreate = new EntryCreate();
        entryCreate.setEntryId(entryId);
        CreateEntryOperations createEntryOperations = new CreateEntryOperations();
        createEntryOperations.setEntryCreate(entryCreate);
        CreateEntryResult createEntryResult = new CreateEntryResult();
        createEntryResult.setOperations(createEntryOperations);

        String result = ApiClientUtils.getCreateEntryResultSummary(createEntryResult);

        assertEquals(String.format("EntryId=%s.", entryId), result);
    }

    @Test
    void getCreateEntryResultSummary_OperationHasMultipleExceptions() {
        String errorMessage1 = "There was an error.";
        APIServerException apiServerException1 = new APIServerException();
        apiServerException1.setMessage(errorMessage1);
        String errorMessage2 = "There was another error.";
        APIServerException apiServerException2 = new APIServerException();
        apiServerException2.setMessage(errorMessage2);
        List<APIServerException> exceptions = new ArrayList<>();
        exceptions.add(apiServerException1);
        exceptions.add(apiServerException2);

        CreateEntryOperations createEntryOperations = new CreateEntryOperations();
        CreateEntryResult createEntryResult = new CreateEntryResult();
        createEntryResult.setOperations(createEntryOperations);

        SetFields setFields = new SetFields();
        setFields.setExceptions(exceptions);
        createEntryOperations.setFields(setFields);

        String result = ApiClientUtils.getCreateEntryResultSummary(createEntryResult);

        String expectedResult = String.format("%s %s", errorMessage1, errorMessage2);
        assertEquals(expectedResult, result);
    }

    @Test
    void getCreateEntryResultSummary_AllOperationsHaveExceptions() {
        String errorMessage1 = "There was an error.";
        APIServerException apiServerException1 = new APIServerException();
        apiServerException1.setMessage(errorMessage1);
        List<APIServerException> exceptions1 = new ArrayList<>();
        exceptions1.add(apiServerException1);

        String errorMessage2 = "There was another error.";
        APIServerException apiServerException2 = new APIServerException();
        apiServerException2.setMessage(errorMessage2);
        List<APIServerException> exceptions2 = new ArrayList<>();
        exceptions2.add(apiServerException2);

        CreateEntryOperations createEntryOperations = new CreateEntryOperations();
        CreateEntryResult createEntryResult = new CreateEntryResult();
        createEntryResult.setOperations(createEntryOperations);

        int entryId = 123;
        EntryCreate entryCreate = new EntryCreate();
        entryCreate.setEntryId(entryId);
        entryCreate.setExceptions(exceptions1);
        createEntryOperations.setEntryCreate(entryCreate);

        SetEdoc setEdoc = new SetEdoc();
        setEdoc.setExceptions(exceptions2);
        createEntryOperations.setEdoc(setEdoc);

        SetTemplate setTemplate = new SetTemplate();
        setTemplate.setExceptions(exceptions1);
        createEntryOperations.setTemplate(setTemplate);

        SetFields setFields = new SetFields();
        setFields.setExceptions(exceptions2);
        createEntryOperations.setFields(setFields);

        SetTags setTags = new SetTags();
        setTags.setExceptions(exceptions1);
        createEntryOperations.setTags(setTags);

        SetLinks setLinks = new SetLinks();
        setLinks.setExceptions(exceptions2);
        createEntryOperations.setLinks(setLinks);

        String result = ApiClientUtils.getCreateEntryResultSummary(createEntryResult);

        String expectedResult = String.format("EntryId=%s. %2$s %3$s %2$s %3$s %2$s %3$s", entryId, errorMessage1, errorMessage2);
        assertEquals(expectedResult, result);
    }
}
