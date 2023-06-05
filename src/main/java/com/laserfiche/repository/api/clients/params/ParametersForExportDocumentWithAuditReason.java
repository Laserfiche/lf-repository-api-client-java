package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.function.Consumer;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#exportDocumentWithAuditReason(ParametersForExportDocumentWithAuditReason)
 * exportDocumentWithAuditReason}.
 */
public class ParametersForExportDocumentWithAuditReason {

  /** The requested repository ID. */
  private String repoId;

  /** The requested document ID. */
  private Integer entryId;

  private GetEdocWithAuditReasonRequest requestBody;

  /**
   * An optional header used to retrieve partial content of the edoc. Only supports single range
   * with byte unit.
   */
  private String range;

  /**
   * A Consumer&lt;InputStream&gt; object that is provided with the response's inputStream to
   * consume it, if the request has been successful.
   */
  private Consumer<InputStream> inputStreamConsumer;

  /**
   * Sets the value of the repoId parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param repoId The requested repository ID.
   * @return {@link ParametersForExportDocumentWithAuditReason} The return value
   */
  public ParametersForExportDocumentWithAuditReason setRepoId(String repoId) {
    this.repoId = repoId;
    return this;
  }

  /**
   * The requested repository ID.
   *
   * @return {@link String} The return value
   */
  public String getRepoId() {
    return this.repoId;
  }

  /**
   * Sets the value of the entryId parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param entryId The requested document ID.
   * @return {@link ParametersForExportDocumentWithAuditReason} The return value
   */
  public ParametersForExportDocumentWithAuditReason setEntryId(Integer entryId) {
    this.entryId = entryId;
    return this;
  }

  /**
   * The requested document ID.
   *
   * @return {@link Integer} The return value
   */
  public Integer getEntryId() {
    return this.entryId;
  }

  public ParametersForExportDocumentWithAuditReason setRequestBody(
      GetEdocWithAuditReasonRequest requestBody) {
    this.requestBody = requestBody;
    return this;
  }

  public GetEdocWithAuditReasonRequest getRequestBody() {
    return this.requestBody;
  }

  /**
   * Sets the value of the range parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param range An optional header used to retrieve partial content of the edoc. Only supports
   *     single range with byte unit.
   * @return {@link ParametersForExportDocumentWithAuditReason} The return value
   */
  public ParametersForExportDocumentWithAuditReason setRange(String range) {
    this.range = range;
    return this;
  }

  /**
   * An optional header used to retrieve partial content of the edoc. Only supports single<br>
   * range with byte unit.
   *
   * @return {@link String} The return value
   */
  public String getRange() {
    return this.range;
  }

  /**
   * Sets the value of the inputStreamConsumer parameter and returns the current object, to enable
   * chaining further setters.
   *
   * @return {@link ParametersForExportDocumentWithAuditReason} The return value
   */
  public ParametersForExportDocumentWithAuditReason setInputStreamConsumer(
      Consumer<InputStream> inputStreamConsumer) {
    this.inputStreamConsumer = inputStreamConsumer;
    return this;
  }

  /**
   * A Consumer&lt;InputStream&gt; object that is provided with the response's inputStream to
   * consume it, if the request has been successful.
   *
   * @return Consumer&lt;InputStream&gt; The return value
   */
  public Consumer<InputStream> getInputStreamConsumer() {
    return this.inputStreamConsumer;
  }
}
