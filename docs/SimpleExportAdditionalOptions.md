# SimpleExportAdditionalOptions

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**jpegCompression** | **Integer** | The JPEG compression quality when part&#x3D;Pages. The value must be between 0 and 100 (inclusive). |  [optional]
**exportImageIncludeAnnotations** | **Boolean** | Determines whether or not the annotations need to be included when part&#x3D;Pages. If true, annotations are included, otherwise they are not. |  [optional]
**exportImageConvertPdfAnnotations** | **Boolean** | Determines whether or not the annotations need to be converted when part&#x3D;Pages. If true, annotations are converted, otherwise they are not. |  [optional]
**exportImageMultipageTiff** | **Boolean** | Determines whether exporting to TIFF should result in a single multi-page TIFF file, or in multiple single-page TIFF files (in a single Zip file), when part&#x3D;Pages. If true, a single multi-page file is generated, otherwise, multiple single-page files are generated. |  [optional]
**exportImagePagePrefix** | **String** | The page prefix of the individual files, when exporting to multi-file format (e.g.zip) when part&#x3D;Pages. The value must have a length of atmost 10 characters.  Further, as the value is used in the file name, it cannot include characters that are invalid from the point of view of the file system. These invalid characters are: colon (:), slash, backslash, double-quotation, asterisk, question mark, Angle brackets(i.e. less than, greater than), and vertical bar (|) An empty string or all-whitespace string is allowed. |  [optional]
**exportImageIncludeRedacts** | **Boolean** | Determines whether or not the redactions need to be included. If true, redactions are included, otherwise they are not. This is applicable when part&#x3D;Pages or part&#x3D;Text. |  [optional]
**exportTextRedactChar** | **String** | The redaction character, i.e. the character that replaces the original character in a redacted text.  This is applicable when part&#x3D;Pages or part&#x3D;Text. The value must be a string of length 1. Empty or all-whitespace string is not allowed. |  [optional]
**watermark** | **OneOfSimpleExportAdditionalOptionsWatermark** | The watermark element added to the pages when Part&#x3D;Pages. |  [optional]
