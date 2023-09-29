# Migration Guide
The following guide compares the [lf-repository-api-client](https://central.sonatype.com/artifact/com.laserfiche/lf-repository-api-client) package with the [lf-repository-api-client-v2](https://central.sonatype.com/artifact/com.laserfiche/lf-repository-api-client-v2) package at time of initial release.

The `lf-repository-api-client` accesses the v1 Laserfiche Repository APIs and the `lf-repository-api-client-v2` accesses the v2 Laserfiche Repository APIs. Many API function signatures have been updated in the v2 client. See the tables below for the functions in the v1 client that correspond to the functions in the v2 client.

See [here](https://api.laserfiche.com/repository/v2/changelog#2023-10) for more details on the changes between the v1 and v2 Laserfiche Repository APIs.

### Attributes
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [getTrusteeAttributeKeyValuePairs](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/AttributesClient.html#getTrusteeAttributeKeyValuePairs-com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeKeyValuePairs-) | [listAttributes]() |
| [getTrusteeAttributeKeyValuePairsForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/AttributesClient.html#getTrusteeAttributeKeyValuePairsForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeKeyValuePairs-) | [listAttributesForEach]() |
| [getTrusteeAttributeKeyValuePairsNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/AttributesClient.html#getTrusteeAttributeKeyValuePairsNextLink-java.lang.String-int-) | [listAttributesNextLink]() |
| [getTrusteeAttributeValueByKey](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/AttributesClient.html#getTrusteeAttributeValueByKey-com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeValueByKey-) | [getAttribute]() |

### AuditReasons
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [getAuditReasons](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/AuditReasonsClient.html#getAuditReasons-com.laserfiche.repository.api.clients.params.ParametersForGetAuditReasons-) | [listAuditReasons]() |

### Entries
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [assignEntryLinks](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#assignEntryLinks-com.laserfiche.repository.api.clients.params.ParametersForAssignEntryLinks-) | [setLinks]() |
| [assignFieldValues](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#assignFieldValues-com.laserfiche.repository.api.clients.params.ParametersForAssignFieldValues-) | [setFields]() |
| [assignTags](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#assignTags-com.laserfiche.repository.api.clients.params.ParametersForAssignTags-) | [setTags]() |
| [copyEntry](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#copyEntryAsync-com.laserfiche.repository.api.clients.params.ParametersForCopyEntryAsync-) | [startCopyEntry]() |
| [createOrCopyEntry](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#createOrCopyEntry-com.laserfiche.repository.api.clients.params.ParametersForCreateOrCopyEntry-) | Functionality split into [createEntry]() and [copyEntry]() |
| [deleteAssignedTemplate](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#deleteAssignedTemplate-com.laserfiche.repository.api.clients.params.ParametersForDeleteAssignedTemplate-) | [removeTemplate]() |
| [deleteDocument](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#deleteDocument-com.laserfiche.repository.api.clients.params.ParametersForDeleteDocument-) | [deleteElectronicDocument]() |
| [deleteEntryInfo](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#deleteEntryInfo-com.laserfiche.repository.api.clients.params.ParametersForDeleteEntryInfo-) | [startDeleteEntry]() |
| [deletePages](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#deletePages-com.laserfiche.repository.api.clients.params.ParametersForDeletePages-) | [deletePages]() |
| [exportDocument](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#exportDocument-com.laserfiche.repository.api.clients.params.ParametersForExportDocument-) | [exportEntry]() |
| [exportDocumentAsStream](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#exportDocumentAsStream-com.laserfiche.repository.api.clients.params.ParametersForExportDocument-) | [exportEntry]() |
| [exportDocumentWithAuditReason](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#exportDocumentWithAuditReason-com.laserfiche.repository.api.clients.params.ParametersForExportDocumentWithAuditReason-) | [exportEntry]() |
| [exportDocumentWithAuditReasonAsStream](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#exportDocumentWithAuditReasonAsStream-com.laserfiche.repository.api.clients.params.ParametersForExportDocumentWithAuditReason-) | [exportEntry]() |
| [getDocumentContentType](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getDocumentContentType-com.laserfiche.repository.api.clients.params.ParametersForGetDocumentContentType-) | Removed |
| [getDynamicFieldValues](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getDynamicFieldValues-com.laserfiche.repository.api.clients.params.ParametersForGetDynamicFieldValues-) | [listDynamicFieldValues]() |
| [getEntry](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getEntry-com.laserfiche.repository.api.clients.params.ParametersForGetEntry-) | [getEntry]() |
| [getEntryByPath](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getEntryByPath-com.laserfiche.repository.api.clients.params.ParametersForGetEntryByPath-) | [getEntryByPath]() |
| [getEntryListing](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getEntryListing-com.laserfiche.repository.api.clients.params.ParametersForGetEntryListing-) | [listEntries]() |
| [getEntryListingForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getEntryListingForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetEntryListing-) | [listEntriesForEach]() |
| [getEntryListingNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getEntryListingNextLink-java.lang.String-int-) | [listEntriesNextLink]() |
| [getFieldValues](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getFieldValues-com.laserfiche.repository.api.clients.params.ParametersForGetFieldValues-) | [listFields]() |
| [getFieldValuesForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getFieldValuesForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetFieldValues-) | [listFieldsForEach]() |
| [getFieldValuesNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getFieldValuesNextLink-java.lang.String-int-) | [listFieldsNextLink]() |
| [getLinkValuesFromEntry](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getLinkValuesFromEntry-com.laserfiche.repository.api.clients.params.ParametersForGetLinkValuesFromEntry-) | [listLinks]() |
| [getLinkValuesFromEntryForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getLinkValuesFromEntryForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetLinkValuesFromEntry-) | [listLinksForEach]() |
| [getLinkValuesFromEntryNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getLinkValuesFromEntryNextLink-java.lang.String-int-) | [listLinksNextLink]() |
| [getTagsAssignedToEntry](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getTagsAssignedToEntry-com.laserfiche.repository.api.clients.params.ParametersForGetTagsAssignedToEntry-) | [listTags]() |
| [getTagsAssignedToEntryForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getTagsAssignedToEntryForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetTagsAssignedToEntry-) | [listTagsForEach]() |
| [getTagsAssignedToEntryNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#getTagsAssignedToEntryNextLink-java.lang.String-int-) | [listTagsNextLink]() |
| [importDocument](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#importDocument-com.laserfiche.repository.api.clients.params.ParametersForImportDocument-) | [importEntry]() |
| [moveOrRenameEntry](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#moveOrRenameEntry-com.laserfiche.repository.api.clients.params.ParametersForMoveOrRenameEntry-) | [updateEntry]() |
| [writeTemplateValueToEntry](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/EntriesClient.html#writeTemplateValueToEntry-com.laserfiche.repository.api.clients.params.ParametersForWriteTemplateValueToEntry-) | [setTemplate]() |
| -- | [createMultipartUploadUrls]() |
| -- | [startImportUploadedParts]() |
| -- | [startExportEntry]() |

### FieldDefinitions
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [getFieldDefinitionById](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/FieldDefinitionsClient.html#getFieldDefinitionById-com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinitionById-) | [getFieldDefinition]() |
| [getFieldDefinitions](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/FieldDefinitionsClient.html#getFieldDefinitions-com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinitions-) | [listFieldDefinitions]() |
| [getFieldDefinitionsForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/FieldDefinitionsClient.html#getFieldDefinitionsForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinitions-) | [listFieldDefinitionsForEach]() |
| [getFieldDefinitionsNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/FieldDefinitionsClient.html#getFieldDefinitionsNextLink-java.lang.String-int-) | [listFieldDefinitionsNextLink]() |

### LinkDefinitions
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [getLinkDefinitionById](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/LinkDefinitionsClient.html#getLinkDefinitionById-com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinitionById-) | [getLinkDefinition]() |
| [getLinkDefinitions](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/LinkDefinitionsClient.html#getLinkDefinitions-com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinitions-) | [listLinkDefinitions]() |
| [getLinkDefinitionsForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/LinkDefinitionsClient.html#getLinkDefinitionsForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetLinkDefinitions-) | [listLinkDefinitionsForEach]() |
| [getLinkDefinitionsNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/LinkDefinitionsClient.html#getLinkDefinitionsNextLink-java.lang.String-int-) | [listLinkDefinitionsNextLink]() |

### Repositories
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [getRepositoryList](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/RepositoriesClient.html#getRepositoryList--) | [listRepositories]() |
| [getSelfHostedRepositoryList](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/impl/RepositoriesClientImpl.html#getSelfHostedRepositoryList-java.lang.String-) | [listSelfHostedRepositories]() |

### Searches
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [cancelOrCloseSearch](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SearchesClient.html#cancelOrCloseSearch-com.laserfiche.repository.api.clients.params.ParametersForCancelOrCloseSearch-) | [cancelTasks]() |
| [createSearchOperation](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SearchesClient.html#createSearchOperation-com.laserfiche.repository.api.clients.params.ParametersForCreateSearchOperation-) | [startSearchEntry]() |
| [getSearchContextHits](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SearchesClient.html#getSearchContextHits-com.laserfiche.repository.api.clients.params.ParametersForGetSearchContextHits-) | [listSearchContextHits]() |
| [getSearchContextHitsForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SearchesClient.html#getSearchContextHitsForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetSearchContextHits-) | [listSearchContextHitsForEach]() |
| [getSearchContextHitsNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SearchesClient.html#getSearchContextHitsNextLink-java.lang.String-int-) | [listSearchContextHitsNextLink]() |
| [getSearchResults](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SearchesClient.html#getSearchResults-com.laserfiche.repository.api.clients.params.ParametersForGetSearchResults-) | [listSearchResults]() |
| [getSearchResultsForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SearchesClient.html#getSearchResultsForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetSearchResults-) | [listSearchResultsForEach]() |
| [getSearchResultsNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SearchesClient.html#getSearchResultsNextLink-java.lang.String-int-) | [listSearchResultsNextLink]() |
| [getSearchStatus](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SearchesClient.html#getSearchStatus-com.laserfiche.repository.api.clients.params.ParametersForGetSearchStatus-) | [listTasks]() |

### ServerSession
The [IServerSessionClient](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/ServerSessionClient.html) has been removed in `lf-repository-api-client-v2`.

### SimpleSearches
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [createSimpleSearchOperation](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/SimpleSearchesClient.html#createSimpleSearchOperation-com.laserfiche.repository.api.clients.params.ParametersForCreateSimpleSearchOperation-) | [searchEntry]() |

### TagDefinitions
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [getTagDefinitionById](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TagDefinitionsClient.html#getTagDefinitionById-com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinitionById-) | [getTagDefinition]() |
| [getTagDefinitions](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TagDefinitionsClient.html#getTagDefinitions-com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinitions-) | [listTagDefinitions]() |
| [getTagDefinitionsForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TagDefinitionsClient.html#getTagDefinitionsForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetTagDefinitions-) | [listTagDefinitionsForEach]() |
| [getTagDefinitionsNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TagDefinitionsClient.html#getTagDefinitionsNextLink-java.lang.String-int-) | [listTagDefinitionsNextLink]() |

### Tasks
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [cancelOperation](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TasksClient.html#cancelOperation-com.laserfiche.repository.api.clients.params.ParametersForCancelOperation-) | [cancelTasks]() |
| [getOperationStatusAndProgress](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TasksClient.html#getOperationStatusAndProgress-com.laserfiche.repository.api.clients.params.ParametersForGetOperationStatusAndProgress-) | [listTasks]() |

### TemplateDefinitions
| lf-repository-api-client | lf-repository-api-client-v2 |
|----------------------------------|-------------------------------------|
| [getTemplateDefinitionById](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateDefinitionById-com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinitionById-) | [getTemplateDefinition]() |
| [getTemplateDefinitions](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateDefinitions-com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinitions-) | [listTemplateDefinitions]() |
| [getTemplateDefinitionsForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateDefinitionsForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetTemplateDefinitions-) | [listTemplateDefinitionsForEach]() |
| [getTemplateDefinitionsNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateDefinitionsNextLink-java.lang.String-int-) | [listTemplateDefinitionsNextLink]() |
| [getTemplateFieldDefinitions](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateFieldDefinitions-com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitions-) | [listTemplateFieldDefinitionsByTemplateId]() |
| [getTemplateFieldDefinitionsForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateFieldDefinitionsForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitions-) | [listTemplateFieldDefinitionsByTemplateIdForEach]() |
| [getTemplateFieldDefinitionsNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateFieldDefinitionsNextLink-java.lang.String-int-) | [listTemplateFieldDefinitionsByTemplateIdNextLink]() |
| [getTemplateFieldDefinitionsByTemplateName](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateFieldDefinitionsByTemplateName-com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitionsByTemplateName-) | [listTemplateFieldDefinitionsByTemplateName]() |
| [getTemplateFieldDefinitionsByTemplateNameForEach](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateFieldDefinitionsByTemplateNameForEach-java.util.function.Function-java.lang.Integer-com.laserfiche.repository.api.clients.params.ParametersForGetTemplateFieldDefinitionsByTemplateName-) | [listTemplateFieldDefinitionsByTemplateNameForEach]() |
| [getTemplateFieldDefinitionsByTemplateNameNextLink](https://laserfiche.github.io/lf-repository-api-client-java/docs/2.x/com/laserfiche/repository/api/clients/TemplateDefinitionsClient.html#getTemplateFieldDefinitionsByTemplateNameNextLink-java.lang.String-int-) | [listTemplateFieldDefinitionsByTemplateNameNextLink]() |
