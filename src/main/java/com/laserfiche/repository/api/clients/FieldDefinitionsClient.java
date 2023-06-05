package com.laserfiche.repository.api.clients;

import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.function.Function;

/** The Laserfiche Repository FieldDefinitions API client. */
public interface FieldDefinitionsClient {

  /**
   * - Returns a single field definition associated with the specified ID. <br>
   * - Useful when a route provides a minimal amount of details and more information about the
   * specific field definition is needed.<br>
   * - Allowed OData query options: Select
   *
   * @param parameters An object of type {@link ParametersForGetFieldDefinitionById} which
   *     encapsulates the parameters of {@link #getFieldDefinitionById getFieldDefinitionById}
   *     method.
   * @return {@link WFieldInfo} The return value
   */
  WFieldInfo getFieldDefinitionById(ParametersForGetFieldDefinitionById parameters);

  /**
   * - Returns a paged listing of field definitions available in the specified repository.<br>
   * - Useful when trying to find a list of all field definitions available, rather than only those
   * assigned to a specific entry/template.<br>
   * - Default page size: 100. Allowed OData query options: Select | Count | OrderBy | Skip | Top |
   * SkipToken | Prefer.
   *
   * @param parameters An object of type {@link ParametersForGetFieldDefinitions} which encapsulates
   *     the parameters of {@link #getFieldDefinitions getFieldDefinitions} method.
   * @return {@link ODataValueContextOfIListOfWFieldInfo} The return value
   */
  ODataValueContextOfIListOfWFieldInfo getFieldDefinitions(
      ParametersForGetFieldDefinitions parameters);

  /**
   * Returns the next subset of the requested collection, using a nextlink url.
   *
   * @param nextLink A url that allows retrieving the next subset of the requested collection.
   * @param maxPageSize Optionally specify the maximum number of items to retrieve.
   * @return {@link ODataValueContextOfIListOfWFieldInfo} The return value
   */
  ODataValueContextOfIListOfWFieldInfo getFieldDefinitionsNextLink(
      String nextLink, int maxPageSize);

  /**
   * Provides the functionality to iteratively (i.e. through paging) call {@link
   * #getFieldDefinitions getFieldDefinitions}, and apply a function on the response of each
   * iteration.
   *
   * @param callback A delegate that will be called each time new data is retrieved. Returns false
   *     to stop receiving more data; returns true to be called again if there's more data.
   * @param maxPageSize Optionally specify the maximum number of items to retrieve.
   * @param parameters An object of type {@link ParametersForGetFieldDefinitions} which encapsulates
   *     the parameters of {@link #getFieldDefinitions getFieldDefinitions} method.
   */
  void getFieldDefinitionsForEach(
      Function<ODataValueContextOfIListOfWFieldInfo, Boolean> callback,
      Integer maxPageSize,
      ParametersForGetFieldDefinitions parameters);
}
