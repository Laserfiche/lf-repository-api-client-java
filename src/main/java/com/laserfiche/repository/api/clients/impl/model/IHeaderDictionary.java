package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IHeaderDictionary {

  @JsonProperty("Item")
  private List<Object> item = null;

  @JsonProperty("ContentLength")
  private Long contentLength = null;

  @JsonProperty("Accept")
  private List<Object> accept = null;

  @JsonProperty("AcceptCharset")
  private List<Object> acceptCharset = null;

  @JsonProperty("AcceptEncoding")
  private List<Object> acceptEncoding = null;

  @JsonProperty("AcceptLanguage")
  private List<Object> acceptLanguage = null;

  @JsonProperty("AcceptRanges")
  private List<Object> acceptRanges = null;

  @JsonProperty("AccessControlAllowCredentials")
  private List<Object> accessControlAllowCredentials = null;

  @JsonProperty("AccessControlAllowHeaders")
  private List<Object> accessControlAllowHeaders = null;

  @JsonProperty("AccessControlAllowMethods")
  private List<Object> accessControlAllowMethods = null;

  @JsonProperty("AccessControlAllowOrigin")
  private List<Object> accessControlAllowOrigin = null;

  @JsonProperty("AccessControlExposeHeaders")
  private List<Object> accessControlExposeHeaders = null;

  @JsonProperty("AccessControlMaxAge")
  private List<Object> accessControlMaxAge = null;

  @JsonProperty("AccessControlRequestHeaders")
  private List<Object> accessControlRequestHeaders = null;

  @JsonProperty("AccessControlRequestMethod")
  private List<Object> accessControlRequestMethod = null;

  @JsonProperty("Age")
  private List<Object> age = null;

  @JsonProperty("Allow")
  private List<Object> allow = null;

  @JsonProperty("AltSvc")
  private List<Object> altSvc = null;

  @JsonProperty("Authorization")
  private List<Object> authorization = null;

  @JsonProperty("Baggage")
  private List<Object> baggage = null;

  @JsonProperty("CacheControl")
  private List<Object> cacheControl = null;

  @JsonProperty("Connection")
  private List<Object> connection = null;

  @JsonProperty("ContentDisposition")
  private List<Object> contentDisposition = null;

  @JsonProperty("ContentEncoding")
  private List<Object> contentEncoding = null;

  @JsonProperty("ContentLanguage")
  private List<Object> contentLanguage = null;

  @JsonProperty("ContentLocation")
  private List<Object> contentLocation = null;

  @JsonProperty("ContentMD5")
  private List<Object> contentMD5 = null;

  @JsonProperty("ContentRange")
  private List<Object> contentRange = null;

  @JsonProperty("ContentSecurityPolicy")
  private List<Object> contentSecurityPolicy = null;

  @JsonProperty("ContentSecurityPolicyReportOnly")
  private List<Object> contentSecurityPolicyReportOnly = null;

  @JsonProperty("ContentType")
  private List<Object> contentType = null;

  @JsonProperty("CorrelationContext")
  private List<Object> correlationContext = null;

  @JsonProperty("Cookie")
  private List<Object> cookie = null;

  @JsonProperty("Date")
  private List<Object> date = null;

  @JsonProperty("ETag")
  private List<Object> etag = null;

  @JsonProperty("Expires")
  private List<Object> expires = null;

  @JsonProperty("Expect")
  private List<Object> expect = null;

  @JsonProperty("From")
  private List<Object> from = null;

  @JsonProperty("GrpcAcceptEncoding")
  private List<Object> grpcAcceptEncoding = null;

  @JsonProperty("GrpcEncoding")
  private List<Object> grpcEncoding = null;

  @JsonProperty("GrpcMessage")
  private List<Object> grpcMessage = null;

  @JsonProperty("GrpcStatus")
  private List<Object> grpcStatus = null;

  @JsonProperty("GrpcTimeout")
  private List<Object> grpcTimeout = null;

  @JsonProperty("Host")
  private List<Object> host = null;

  @JsonProperty("KeepAlive")
  private List<Object> keepAlive = null;

  @JsonProperty("IfMatch")
  private List<Object> ifMatch = null;

  @JsonProperty("IfModifiedSince")
  private List<Object> ifModifiedSince = null;

  @JsonProperty("IfNoneMatch")
  private List<Object> ifNoneMatch = null;

  @JsonProperty("IfRange")
  private List<Object> ifRange = null;

  @JsonProperty("IfUnmodifiedSince")
  private List<Object> ifUnmodifiedSince = null;

  @JsonProperty("LastModified")
  private List<Object> lastModified = null;

  @JsonProperty("Link")
  private List<Object> link = null;

  @JsonProperty("Location")
  private List<Object> location = null;

  @JsonProperty("MaxForwards")
  private List<Object> maxForwards = null;

  @JsonProperty("Origin")
  private List<Object> origin = null;

  @JsonProperty("Pragma")
  private List<Object> pragma = null;

  @JsonProperty("ProxyAuthenticate")
  private List<Object> proxyAuthenticate = null;

  @JsonProperty("ProxyAuthorization")
  private List<Object> proxyAuthorization = null;

  @JsonProperty("ProxyConnection")
  private List<Object> proxyConnection = null;

  @JsonProperty("Range")
  private List<Object> range = null;

  @JsonProperty("Referer")
  private List<Object> referer = null;

  @JsonProperty("RetryAfter")
  private List<Object> retryAfter = null;

  @JsonProperty("RequestId")
  private List<Object> requestId = null;

  @JsonProperty("SecWebSocketAccept")
  private List<Object> secWebSocketAccept = null;

  @JsonProperty("SecWebSocketKey")
  private List<Object> secWebSocketKey = null;

  @JsonProperty("SecWebSocketProtocol")
  private List<Object> secWebSocketProtocol = null;

  @JsonProperty("SecWebSocketVersion")
  private List<Object> secWebSocketVersion = null;

  @JsonProperty("SecWebSocketExtensions")
  private List<Object> secWebSocketExtensions = null;

  @JsonProperty("Server")
  private List<Object> server = null;

  @JsonProperty("SetCookie")
  private List<Object> setCookie = null;

  @JsonProperty("StrictTransportSecurity")
  private List<Object> strictTransportSecurity = null;

  @JsonProperty("TE")
  private List<Object> TE = null;

  @JsonProperty("Trailer")
  private List<Object> trailer = null;

  @JsonProperty("TransferEncoding")
  private List<Object> transferEncoding = null;

  @JsonProperty("Translate")
  private List<Object> translate = null;

  @JsonProperty("TraceParent")
  private List<Object> traceParent = null;

  @JsonProperty("TraceState")
  private List<Object> traceState = null;

  @JsonProperty("Upgrade")
  private List<Object> upgrade = null;

  @JsonProperty("UpgradeInsecureRequests")
  private List<Object> upgradeInsecureRequests = null;

  @JsonProperty("UserAgent")
  private List<Object> userAgent = null;

  @JsonProperty("Vary")
  private List<Object> vary = null;

  @JsonProperty("Via")
  private List<Object> via = null;

  @JsonProperty("Warning")
  private List<Object> warning = null;

  @JsonProperty("WebSocketSubProtocols")
  private List<Object> webSocketSubProtocols = null;

  @JsonProperty("WWWAuthenticate")
  private List<Object> wwWAuthenticate = null;

  @JsonProperty("XContentTypeOptions")
  private List<Object> xcontentTypeOptions = null;

  @JsonProperty("XFrameOptions")
  private List<Object> xframeOptions = null;

  @JsonProperty("XPoweredBy")
  private List<Object> xpoweredBy = null;

  @JsonProperty("XRequestedWith")
  private List<Object> xrequestedWith = null;

  @JsonProperty("XUACompatible")
  private List<Object> xuACompatible = null;

  @JsonProperty("XXSSProtection")
  private List<Object> xxSSProtection = null;

  public IHeaderDictionary item(List<Object> item) {
    this.item = item;
    return this;
  }

  public IHeaderDictionary addItemItem(Object itemItem) {
    if (this.item == null) {
      this.item = new ArrayList<Object>();
    }
    this.item.add(itemItem);
    return this;
  }

  /**
   * Returns et item
   *
   * @return item
   */
  @Schema(description = "")
  public List<Object> getItem() {
    return item;
  }

  public void setItem(List<Object> item) {
    this.item = item;
  }

  public IHeaderDictionary contentLength(Long contentLength) {
    this.contentLength = contentLength;
    return this;
  }

  /**
   * Returns et contentLength
   *
   * @return contentLength
   */
  @Schema(description = "")
  public Long getContentLength() {
    return contentLength;
  }

  public void setContentLength(Long contentLength) {
    this.contentLength = contentLength;
  }

  public IHeaderDictionary accept(List<Object> accept) {
    this.accept = accept;
    return this;
  }

  public IHeaderDictionary addAcceptItem(Object acceptItem) {
    if (this.accept == null) {
      this.accept = new ArrayList<Object>();
    }
    this.accept.add(acceptItem);
    return this;
  }

  /**
   * Returns et accept
   *
   * @return accept
   */
  @Schema(description = "")
  public List<Object> getAccept() {
    return accept;
  }

  public void setAccept(List<Object> accept) {
    this.accept = accept;
  }

  public IHeaderDictionary acceptCharset(List<Object> acceptCharset) {
    this.acceptCharset = acceptCharset;
    return this;
  }

  public IHeaderDictionary addAcceptCharsetItem(Object acceptCharsetItem) {
    if (this.acceptCharset == null) {
      this.acceptCharset = new ArrayList<Object>();
    }
    this.acceptCharset.add(acceptCharsetItem);
    return this;
  }

  /**
   * Returns et acceptCharset
   *
   * @return acceptCharset
   */
  @Schema(description = "")
  public List<Object> getAcceptCharset() {
    return acceptCharset;
  }

  public void setAcceptCharset(List<Object> acceptCharset) {
    this.acceptCharset = acceptCharset;
  }

  public IHeaderDictionary acceptEncoding(List<Object> acceptEncoding) {
    this.acceptEncoding = acceptEncoding;
    return this;
  }

  public IHeaderDictionary addAcceptEncodingItem(Object acceptEncodingItem) {
    if (this.acceptEncoding == null) {
      this.acceptEncoding = new ArrayList<Object>();
    }
    this.acceptEncoding.add(acceptEncodingItem);
    return this;
  }

  /**
   * Returns et acceptEncoding
   *
   * @return acceptEncoding
   */
  @Schema(description = "")
  public List<Object> getAcceptEncoding() {
    return acceptEncoding;
  }

  public void setAcceptEncoding(List<Object> acceptEncoding) {
    this.acceptEncoding = acceptEncoding;
  }

  public IHeaderDictionary acceptLanguage(List<Object> acceptLanguage) {
    this.acceptLanguage = acceptLanguage;
    return this;
  }

  public IHeaderDictionary addAcceptLanguageItem(Object acceptLanguageItem) {
    if (this.acceptLanguage == null) {
      this.acceptLanguage = new ArrayList<Object>();
    }
    this.acceptLanguage.add(acceptLanguageItem);
    return this;
  }

  /**
   * Returns et acceptLanguage
   *
   * @return acceptLanguage
   */
  @Schema(description = "")
  public List<Object> getAcceptLanguage() {
    return acceptLanguage;
  }

  public void setAcceptLanguage(List<Object> acceptLanguage) {
    this.acceptLanguage = acceptLanguage;
  }

  public IHeaderDictionary acceptRanges(List<Object> acceptRanges) {
    this.acceptRanges = acceptRanges;
    return this;
  }

  public IHeaderDictionary addAcceptRangesItem(Object acceptRangesItem) {
    if (this.acceptRanges == null) {
      this.acceptRanges = new ArrayList<Object>();
    }
    this.acceptRanges.add(acceptRangesItem);
    return this;
  }

  /**
   * Returns et acceptRanges
   *
   * @return acceptRanges
   */
  @Schema(description = "")
  public List<Object> getAcceptRanges() {
    return acceptRanges;
  }

  public void setAcceptRanges(List<Object> acceptRanges) {
    this.acceptRanges = acceptRanges;
  }

  public IHeaderDictionary accessControlAllowCredentials(
      List<Object> accessControlAllowCredentials) {
    this.accessControlAllowCredentials = accessControlAllowCredentials;
    return this;
  }

  public IHeaderDictionary addAccessControlAllowCredentialsItem(
      Object accessControlAllowCredentialsItem) {
    if (this.accessControlAllowCredentials == null) {
      this.accessControlAllowCredentials = new ArrayList<Object>();
    }
    this.accessControlAllowCredentials.add(accessControlAllowCredentialsItem);
    return this;
  }

  /**
   * Returns et accessControlAllowCredentials
   *
   * @return accessControlAllowCredentials
   */
  @Schema(description = "")
  public List<Object> getAccessControlAllowCredentials() {
    return accessControlAllowCredentials;
  }

  public void setAccessControlAllowCredentials(List<Object> accessControlAllowCredentials) {
    this.accessControlAllowCredentials = accessControlAllowCredentials;
  }

  public IHeaderDictionary accessControlAllowHeaders(List<Object> accessControlAllowHeaders) {
    this.accessControlAllowHeaders = accessControlAllowHeaders;
    return this;
  }

  public IHeaderDictionary addAccessControlAllowHeadersItem(Object accessControlAllowHeadersItem) {
    if (this.accessControlAllowHeaders == null) {
      this.accessControlAllowHeaders = new ArrayList<Object>();
    }
    this.accessControlAllowHeaders.add(accessControlAllowHeadersItem);
    return this;
  }

  /**
   * Returns et accessControlAllowHeaders
   *
   * @return accessControlAllowHeaders
   */
  @Schema(description = "")
  public List<Object> getAccessControlAllowHeaders() {
    return accessControlAllowHeaders;
  }

  public void setAccessControlAllowHeaders(List<Object> accessControlAllowHeaders) {
    this.accessControlAllowHeaders = accessControlAllowHeaders;
  }

  public IHeaderDictionary accessControlAllowMethods(List<Object> accessControlAllowMethods) {
    this.accessControlAllowMethods = accessControlAllowMethods;
    return this;
  }

  public IHeaderDictionary addAccessControlAllowMethodsItem(Object accessControlAllowMethodsItem) {
    if (this.accessControlAllowMethods == null) {
      this.accessControlAllowMethods = new ArrayList<Object>();
    }
    this.accessControlAllowMethods.add(accessControlAllowMethodsItem);
    return this;
  }

  /**
   * Returns et accessControlAllowMethods
   *
   * @return accessControlAllowMethods
   */
  @Schema(description = "")
  public List<Object> getAccessControlAllowMethods() {
    return accessControlAllowMethods;
  }

  public void setAccessControlAllowMethods(List<Object> accessControlAllowMethods) {
    this.accessControlAllowMethods = accessControlAllowMethods;
  }

  public IHeaderDictionary accessControlAllowOrigin(List<Object> accessControlAllowOrigin) {
    this.accessControlAllowOrigin = accessControlAllowOrigin;
    return this;
  }

  public IHeaderDictionary addAccessControlAllowOriginItem(Object accessControlAllowOriginItem) {
    if (this.accessControlAllowOrigin == null) {
      this.accessControlAllowOrigin = new ArrayList<Object>();
    }
    this.accessControlAllowOrigin.add(accessControlAllowOriginItem);
    return this;
  }

  /**
   * Returns et accessControlAllowOrigin
   *
   * @return accessControlAllowOrigin
   */
  @Schema(description = "")
  public List<Object> getAccessControlAllowOrigin() {
    return accessControlAllowOrigin;
  }

  public void setAccessControlAllowOrigin(List<Object> accessControlAllowOrigin) {
    this.accessControlAllowOrigin = accessControlAllowOrigin;
  }

  public IHeaderDictionary accessControlExposeHeaders(List<Object> accessControlExposeHeaders) {
    this.accessControlExposeHeaders = accessControlExposeHeaders;
    return this;
  }

  public IHeaderDictionary addAccessControlExposeHeadersItem(
      Object accessControlExposeHeadersItem) {
    if (this.accessControlExposeHeaders == null) {
      this.accessControlExposeHeaders = new ArrayList<Object>();
    }
    this.accessControlExposeHeaders.add(accessControlExposeHeadersItem);
    return this;
  }

  /**
   * Returns et accessControlExposeHeaders
   *
   * @return accessControlExposeHeaders
   */
  @Schema(description = "")
  public List<Object> getAccessControlExposeHeaders() {
    return accessControlExposeHeaders;
  }

  public void setAccessControlExposeHeaders(List<Object> accessControlExposeHeaders) {
    this.accessControlExposeHeaders = accessControlExposeHeaders;
  }

  public IHeaderDictionary accessControlMaxAge(List<Object> accessControlMaxAge) {
    this.accessControlMaxAge = accessControlMaxAge;
    return this;
  }

  public IHeaderDictionary addAccessControlMaxAgeItem(Object accessControlMaxAgeItem) {
    if (this.accessControlMaxAge == null) {
      this.accessControlMaxAge = new ArrayList<Object>();
    }
    this.accessControlMaxAge.add(accessControlMaxAgeItem);
    return this;
  }

  /**
   * Returns et accessControlMaxAge
   *
   * @return accessControlMaxAge
   */
  @Schema(description = "")
  public List<Object> getAccessControlMaxAge() {
    return accessControlMaxAge;
  }

  public void setAccessControlMaxAge(List<Object> accessControlMaxAge) {
    this.accessControlMaxAge = accessControlMaxAge;
  }

  public IHeaderDictionary accessControlRequestHeaders(List<Object> accessControlRequestHeaders) {
    this.accessControlRequestHeaders = accessControlRequestHeaders;
    return this;
  }

  public IHeaderDictionary addAccessControlRequestHeadersItem(
      Object accessControlRequestHeadersItem) {
    if (this.accessControlRequestHeaders == null) {
      this.accessControlRequestHeaders = new ArrayList<Object>();
    }
    this.accessControlRequestHeaders.add(accessControlRequestHeadersItem);
    return this;
  }

  /**
   * Returns et accessControlRequestHeaders
   *
   * @return accessControlRequestHeaders
   */
  @Schema(description = "")
  public List<Object> getAccessControlRequestHeaders() {
    return accessControlRequestHeaders;
  }

  public void setAccessControlRequestHeaders(List<Object> accessControlRequestHeaders) {
    this.accessControlRequestHeaders = accessControlRequestHeaders;
  }

  public IHeaderDictionary accessControlRequestMethod(List<Object> accessControlRequestMethod) {
    this.accessControlRequestMethod = accessControlRequestMethod;
    return this;
  }

  public IHeaderDictionary addAccessControlRequestMethodItem(
      Object accessControlRequestMethodItem) {
    if (this.accessControlRequestMethod == null) {
      this.accessControlRequestMethod = new ArrayList<Object>();
    }
    this.accessControlRequestMethod.add(accessControlRequestMethodItem);
    return this;
  }

  /**
   * Returns et accessControlRequestMethod
   *
   * @return accessControlRequestMethod
   */
  @Schema(description = "")
  public List<Object> getAccessControlRequestMethod() {
    return accessControlRequestMethod;
  }

  public void setAccessControlRequestMethod(List<Object> accessControlRequestMethod) {
    this.accessControlRequestMethod = accessControlRequestMethod;
  }

  public IHeaderDictionary age(List<Object> age) {
    this.age = age;
    return this;
  }

  public IHeaderDictionary addAgeItem(Object ageItem) {
    if (this.age == null) {
      this.age = new ArrayList<Object>();
    }
    this.age.add(ageItem);
    return this;
  }

  /**
   * Returns et age
   *
   * @return age
   */
  @Schema(description = "")
  public List<Object> getAge() {
    return age;
  }

  public void setAge(List<Object> age) {
    this.age = age;
  }

  public IHeaderDictionary allow(List<Object> allow) {
    this.allow = allow;
    return this;
  }

  public IHeaderDictionary addAllowItem(Object allowItem) {
    if (this.allow == null) {
      this.allow = new ArrayList<Object>();
    }
    this.allow.add(allowItem);
    return this;
  }

  /**
   * Returns et allow
   *
   * @return allow
   */
  @Schema(description = "")
  public List<Object> getAllow() {
    return allow;
  }

  public void setAllow(List<Object> allow) {
    this.allow = allow;
  }

  public IHeaderDictionary altSvc(List<Object> altSvc) {
    this.altSvc = altSvc;
    return this;
  }

  public IHeaderDictionary addAltSvcItem(Object altSvcItem) {
    if (this.altSvc == null) {
      this.altSvc = new ArrayList<Object>();
    }
    this.altSvc.add(altSvcItem);
    return this;
  }

  /**
   * Returns et altSvc
   *
   * @return altSvc
   */
  @Schema(description = "")
  public List<Object> getAltSvc() {
    return altSvc;
  }

  public void setAltSvc(List<Object> altSvc) {
    this.altSvc = altSvc;
  }

  public IHeaderDictionary authorization(List<Object> authorization) {
    this.authorization = authorization;
    return this;
  }

  public IHeaderDictionary addAuthorizationItem(Object authorizationItem) {
    if (this.authorization == null) {
      this.authorization = new ArrayList<Object>();
    }
    this.authorization.add(authorizationItem);
    return this;
  }

  /**
   * Returns et authorization
   *
   * @return authorization
   */
  @Schema(description = "")
  public List<Object> getAuthorization() {
    return authorization;
  }

  public void setAuthorization(List<Object> authorization) {
    this.authorization = authorization;
  }

  public IHeaderDictionary baggage(List<Object> baggage) {
    this.baggage = baggage;
    return this;
  }

  public IHeaderDictionary addBaggageItem(Object baggageItem) {
    if (this.baggage == null) {
      this.baggage = new ArrayList<Object>();
    }
    this.baggage.add(baggageItem);
    return this;
  }

  /**
   * Returns et baggage
   *
   * @return baggage
   */
  @Schema(description = "")
  public List<Object> getBaggage() {
    return baggage;
  }

  public void setBaggage(List<Object> baggage) {
    this.baggage = baggage;
  }

  public IHeaderDictionary cacheControl(List<Object> cacheControl) {
    this.cacheControl = cacheControl;
    return this;
  }

  public IHeaderDictionary addCacheControlItem(Object cacheControlItem) {
    if (this.cacheControl == null) {
      this.cacheControl = new ArrayList<Object>();
    }
    this.cacheControl.add(cacheControlItem);
    return this;
  }

  /**
   * Returns et cacheControl
   *
   * @return cacheControl
   */
  @Schema(description = "")
  public List<Object> getCacheControl() {
    return cacheControl;
  }

  public void setCacheControl(List<Object> cacheControl) {
    this.cacheControl = cacheControl;
  }

  public IHeaderDictionary connection(List<Object> connection) {
    this.connection = connection;
    return this;
  }

  public IHeaderDictionary addConnectionItem(Object connectionItem) {
    if (this.connection == null) {
      this.connection = new ArrayList<Object>();
    }
    this.connection.add(connectionItem);
    return this;
  }

  /**
   * Returns et connection
   *
   * @return connection
   */
  @Schema(description = "")
  public List<Object> getConnection() {
    return connection;
  }

  public void setConnection(List<Object> connection) {
    this.connection = connection;
  }

  public IHeaderDictionary contentDisposition(List<Object> contentDisposition) {
    this.contentDisposition = contentDisposition;
    return this;
  }

  public IHeaderDictionary addContentDispositionItem(Object contentDispositionItem) {
    if (this.contentDisposition == null) {
      this.contentDisposition = new ArrayList<Object>();
    }
    this.contentDisposition.add(contentDispositionItem);
    return this;
  }

  /**
   * Returns et contentDisposition
   *
   * @return contentDisposition
   */
  @Schema(description = "")
  public List<Object> getContentDisposition() {
    return contentDisposition;
  }

  public void setContentDisposition(List<Object> contentDisposition) {
    this.contentDisposition = contentDisposition;
  }

  public IHeaderDictionary contentEncoding(List<Object> contentEncoding) {
    this.contentEncoding = contentEncoding;
    return this;
  }

  public IHeaderDictionary addContentEncodingItem(Object contentEncodingItem) {
    if (this.contentEncoding == null) {
      this.contentEncoding = new ArrayList<Object>();
    }
    this.contentEncoding.add(contentEncodingItem);
    return this;
  }

  /**
   * Returns et contentEncoding
   *
   * @return contentEncoding
   */
  @Schema(description = "")
  public List<Object> getContentEncoding() {
    return contentEncoding;
  }

  public void setContentEncoding(List<Object> contentEncoding) {
    this.contentEncoding = contentEncoding;
  }

  public IHeaderDictionary contentLanguage(List<Object> contentLanguage) {
    this.contentLanguage = contentLanguage;
    return this;
  }

  public IHeaderDictionary addContentLanguageItem(Object contentLanguageItem) {
    if (this.contentLanguage == null) {
      this.contentLanguage = new ArrayList<Object>();
    }
    this.contentLanguage.add(contentLanguageItem);
    return this;
  }

  /**
   * Returns et contentLanguage
   *
   * @return contentLanguage
   */
  @Schema(description = "")
  public List<Object> getContentLanguage() {
    return contentLanguage;
  }

  public void setContentLanguage(List<Object> contentLanguage) {
    this.contentLanguage = contentLanguage;
  }

  public IHeaderDictionary contentLocation(List<Object> contentLocation) {
    this.contentLocation = contentLocation;
    return this;
  }

  public IHeaderDictionary addContentLocationItem(Object contentLocationItem) {
    if (this.contentLocation == null) {
      this.contentLocation = new ArrayList<Object>();
    }
    this.contentLocation.add(contentLocationItem);
    return this;
  }

  /**
   * Returns et contentLocation
   *
   * @return contentLocation
   */
  @Schema(description = "")
  public List<Object> getContentLocation() {
    return contentLocation;
  }

  public void setContentLocation(List<Object> contentLocation) {
    this.contentLocation = contentLocation;
  }

  public IHeaderDictionary contentMD5(List<Object> contentMD5) {
    this.contentMD5 = contentMD5;
    return this;
  }

  public IHeaderDictionary addContentMD5Item(Object contentMD5Item) {
    if (this.contentMD5 == null) {
      this.contentMD5 = new ArrayList<Object>();
    }
    this.contentMD5.add(contentMD5Item);
    return this;
  }

  /**
   * Returns et contentMD5
   *
   * @return contentMD5
   */
  @Schema(description = "")
  public List<Object> getContentMD5() {
    return contentMD5;
  }

  public void setContentMD5(List<Object> contentMD5) {
    this.contentMD5 = contentMD5;
  }

  public IHeaderDictionary contentRange(List<Object> contentRange) {
    this.contentRange = contentRange;
    return this;
  }

  public IHeaderDictionary addContentRangeItem(Object contentRangeItem) {
    if (this.contentRange == null) {
      this.contentRange = new ArrayList<Object>();
    }
    this.contentRange.add(contentRangeItem);
    return this;
  }

  /**
   * Returns et contentRange
   *
   * @return contentRange
   */
  @Schema(description = "")
  public List<Object> getContentRange() {
    return contentRange;
  }

  public void setContentRange(List<Object> contentRange) {
    this.contentRange = contentRange;
  }

  public IHeaderDictionary contentSecurityPolicy(List<Object> contentSecurityPolicy) {
    this.contentSecurityPolicy = contentSecurityPolicy;
    return this;
  }

  public IHeaderDictionary addContentSecurityPolicyItem(Object contentSecurityPolicyItem) {
    if (this.contentSecurityPolicy == null) {
      this.contentSecurityPolicy = new ArrayList<Object>();
    }
    this.contentSecurityPolicy.add(contentSecurityPolicyItem);
    return this;
  }

  /**
   * Returns et contentSecurityPolicy
   *
   * @return contentSecurityPolicy
   */
  @Schema(description = "")
  public List<Object> getContentSecurityPolicy() {
    return contentSecurityPolicy;
  }

  public void setContentSecurityPolicy(List<Object> contentSecurityPolicy) {
    this.contentSecurityPolicy = contentSecurityPolicy;
  }

  public IHeaderDictionary contentSecurityPolicyReportOnly(
      List<Object> contentSecurityPolicyReportOnly) {
    this.contentSecurityPolicyReportOnly = contentSecurityPolicyReportOnly;
    return this;
  }

  public IHeaderDictionary addContentSecurityPolicyReportOnlyItem(
      Object contentSecurityPolicyReportOnlyItem) {
    if (this.contentSecurityPolicyReportOnly == null) {
      this.contentSecurityPolicyReportOnly = new ArrayList<Object>();
    }
    this.contentSecurityPolicyReportOnly.add(contentSecurityPolicyReportOnlyItem);
    return this;
  }

  /**
   * Returns et contentSecurityPolicyReportOnly
   *
   * @return contentSecurityPolicyReportOnly
   */
  @Schema(description = "")
  public List<Object> getContentSecurityPolicyReportOnly() {
    return contentSecurityPolicyReportOnly;
  }

  public void setContentSecurityPolicyReportOnly(List<Object> contentSecurityPolicyReportOnly) {
    this.contentSecurityPolicyReportOnly = contentSecurityPolicyReportOnly;
  }

  public IHeaderDictionary contentType(List<Object> contentType) {
    this.contentType = contentType;
    return this;
  }

  public IHeaderDictionary addContentTypeItem(Object contentTypeItem) {
    if (this.contentType == null) {
      this.contentType = new ArrayList<Object>();
    }
    this.contentType.add(contentTypeItem);
    return this;
  }

  /**
   * Returns et contentType
   *
   * @return contentType
   */
  @Schema(description = "")
  public List<Object> getContentType() {
    return contentType;
  }

  public void setContentType(List<Object> contentType) {
    this.contentType = contentType;
  }

  public IHeaderDictionary correlationContext(List<Object> correlationContext) {
    this.correlationContext = correlationContext;
    return this;
  }

  public IHeaderDictionary addCorrelationContextItem(Object correlationContextItem) {
    if (this.correlationContext == null) {
      this.correlationContext = new ArrayList<Object>();
    }
    this.correlationContext.add(correlationContextItem);
    return this;
  }

  /**
   * Returns et correlationContext
   *
   * @return correlationContext
   */
  @Schema(description = "")
  public List<Object> getCorrelationContext() {
    return correlationContext;
  }

  public void setCorrelationContext(List<Object> correlationContext) {
    this.correlationContext = correlationContext;
  }

  public IHeaderDictionary cookie(List<Object> cookie) {
    this.cookie = cookie;
    return this;
  }

  public IHeaderDictionary addCookieItem(Object cookieItem) {
    if (this.cookie == null) {
      this.cookie = new ArrayList<Object>();
    }
    this.cookie.add(cookieItem);
    return this;
  }

  /**
   * Returns et cookie
   *
   * @return cookie
   */
  @Schema(description = "")
  public List<Object> getCookie() {
    return cookie;
  }

  public void setCookie(List<Object> cookie) {
    this.cookie = cookie;
  }

  public IHeaderDictionary date(List<Object> date) {
    this.date = date;
    return this;
  }

  public IHeaderDictionary addDateItem(Object dateItem) {
    if (this.date == null) {
      this.date = new ArrayList<Object>();
    }
    this.date.add(dateItem);
    return this;
  }

  /**
   * Returns et date
   *
   * @return date
   */
  @Schema(description = "")
  public List<Object> getDate() {
    return date;
  }

  public void setDate(List<Object> date) {
    this.date = date;
  }

  public IHeaderDictionary etag(List<Object> etag) {
    this.etag = etag;
    return this;
  }

  public IHeaderDictionary addEtagItem(Object etagItem) {
    if (this.etag == null) {
      this.etag = new ArrayList<Object>();
    }
    this.etag.add(etagItem);
    return this;
  }

  /**
   * Returns et etag
   *
   * @return etag
   */
  @Schema(description = "")
  public List<Object> getEtag() {
    return etag;
  }

  public void setEtag(List<Object> etag) {
    this.etag = etag;
  }

  public IHeaderDictionary expires(List<Object> expires) {
    this.expires = expires;
    return this;
  }

  public IHeaderDictionary addExpiresItem(Object expiresItem) {
    if (this.expires == null) {
      this.expires = new ArrayList<Object>();
    }
    this.expires.add(expiresItem);
    return this;
  }

  /**
   * Returns et expires
   *
   * @return expires
   */
  @Schema(description = "")
  public List<Object> getExpires() {
    return expires;
  }

  public void setExpires(List<Object> expires) {
    this.expires = expires;
  }

  public IHeaderDictionary expect(List<Object> expect) {
    this.expect = expect;
    return this;
  }

  public IHeaderDictionary addExpectItem(Object expectItem) {
    if (this.expect == null) {
      this.expect = new ArrayList<Object>();
    }
    this.expect.add(expectItem);
    return this;
  }

  /**
   * Returns et expect
   *
   * @return expect
   */
  @Schema(description = "")
  public List<Object> getExpect() {
    return expect;
  }

  public void setExpect(List<Object> expect) {
    this.expect = expect;
  }

  public IHeaderDictionary from(List<Object> from) {
    this.from = from;
    return this;
  }

  public IHeaderDictionary addFromItem(Object fromItem) {
    if (this.from == null) {
      this.from = new ArrayList<Object>();
    }
    this.from.add(fromItem);
    return this;
  }

  /**
   * Returns et from
   *
   * @return from
   */
  @Schema(description = "")
  public List<Object> getFrom() {
    return from;
  }

  public void setFrom(List<Object> from) {
    this.from = from;
  }

  public IHeaderDictionary grpcAcceptEncoding(List<Object> grpcAcceptEncoding) {
    this.grpcAcceptEncoding = grpcAcceptEncoding;
    return this;
  }

  public IHeaderDictionary addGrpcAcceptEncodingItem(Object grpcAcceptEncodingItem) {
    if (this.grpcAcceptEncoding == null) {
      this.grpcAcceptEncoding = new ArrayList<Object>();
    }
    this.grpcAcceptEncoding.add(grpcAcceptEncodingItem);
    return this;
  }

  /**
   * Returns et grpcAcceptEncoding
   *
   * @return grpcAcceptEncoding
   */
  @Schema(description = "")
  public List<Object> getGrpcAcceptEncoding() {
    return grpcAcceptEncoding;
  }

  public void setGrpcAcceptEncoding(List<Object> grpcAcceptEncoding) {
    this.grpcAcceptEncoding = grpcAcceptEncoding;
  }

  public IHeaderDictionary grpcEncoding(List<Object> grpcEncoding) {
    this.grpcEncoding = grpcEncoding;
    return this;
  }

  public IHeaderDictionary addGrpcEncodingItem(Object grpcEncodingItem) {
    if (this.grpcEncoding == null) {
      this.grpcEncoding = new ArrayList<Object>();
    }
    this.grpcEncoding.add(grpcEncodingItem);
    return this;
  }

  /**
   * Returns et grpcEncoding
   *
   * @return grpcEncoding
   */
  @Schema(description = "")
  public List<Object> getGrpcEncoding() {
    return grpcEncoding;
  }

  public void setGrpcEncoding(List<Object> grpcEncoding) {
    this.grpcEncoding = grpcEncoding;
  }

  public IHeaderDictionary grpcMessage(List<Object> grpcMessage) {
    this.grpcMessage = grpcMessage;
    return this;
  }

  public IHeaderDictionary addGrpcMessageItem(Object grpcMessageItem) {
    if (this.grpcMessage == null) {
      this.grpcMessage = new ArrayList<Object>();
    }
    this.grpcMessage.add(grpcMessageItem);
    return this;
  }

  /**
   * Returns et grpcMessage
   *
   * @return grpcMessage
   */
  @Schema(description = "")
  public List<Object> getGrpcMessage() {
    return grpcMessage;
  }

  public void setGrpcMessage(List<Object> grpcMessage) {
    this.grpcMessage = grpcMessage;
  }

  public IHeaderDictionary grpcStatus(List<Object> grpcStatus) {
    this.grpcStatus = grpcStatus;
    return this;
  }

  public IHeaderDictionary addGrpcStatusItem(Object grpcStatusItem) {
    if (this.grpcStatus == null) {
      this.grpcStatus = new ArrayList<Object>();
    }
    this.grpcStatus.add(grpcStatusItem);
    return this;
  }

  /**
   * Returns et grpcStatus
   *
   * @return grpcStatus
   */
  @Schema(description = "")
  public List<Object> getGrpcStatus() {
    return grpcStatus;
  }

  public void setGrpcStatus(List<Object> grpcStatus) {
    this.grpcStatus = grpcStatus;
  }

  public IHeaderDictionary grpcTimeout(List<Object> grpcTimeout) {
    this.grpcTimeout = grpcTimeout;
    return this;
  }

  public IHeaderDictionary addGrpcTimeoutItem(Object grpcTimeoutItem) {
    if (this.grpcTimeout == null) {
      this.grpcTimeout = new ArrayList<Object>();
    }
    this.grpcTimeout.add(grpcTimeoutItem);
    return this;
  }

  /**
   * Returns et grpcTimeout
   *
   * @return grpcTimeout
   */
  @Schema(description = "")
  public List<Object> getGrpcTimeout() {
    return grpcTimeout;
  }

  public void setGrpcTimeout(List<Object> grpcTimeout) {
    this.grpcTimeout = grpcTimeout;
  }

  public IHeaderDictionary host(List<Object> host) {
    this.host = host;
    return this;
  }

  public IHeaderDictionary addHostItem(Object hostItem) {
    if (this.host == null) {
      this.host = new ArrayList<Object>();
    }
    this.host.add(hostItem);
    return this;
  }

  /**
   * Returns et host
   *
   * @return host
   */
  @Schema(description = "")
  public List<Object> getHost() {
    return host;
  }

  public void setHost(List<Object> host) {
    this.host = host;
  }

  public IHeaderDictionary keepAlive(List<Object> keepAlive) {
    this.keepAlive = keepAlive;
    return this;
  }

  public IHeaderDictionary addKeepAliveItem(Object keepAliveItem) {
    if (this.keepAlive == null) {
      this.keepAlive = new ArrayList<Object>();
    }
    this.keepAlive.add(keepAliveItem);
    return this;
  }

  /**
   * Returns et keepAlive
   *
   * @return keepAlive
   */
  @Schema(description = "")
  public List<Object> getKeepAlive() {
    return keepAlive;
  }

  public void setKeepAlive(List<Object> keepAlive) {
    this.keepAlive = keepAlive;
  }

  public IHeaderDictionary ifMatch(List<Object> ifMatch) {
    this.ifMatch = ifMatch;
    return this;
  }

  public IHeaderDictionary addIfMatchItem(Object ifMatchItem) {
    if (this.ifMatch == null) {
      this.ifMatch = new ArrayList<Object>();
    }
    this.ifMatch.add(ifMatchItem);
    return this;
  }

  /**
   * Returns et ifMatch
   *
   * @return ifMatch
   */
  @Schema(description = "")
  public List<Object> getIfMatch() {
    return ifMatch;
  }

  public void setIfMatch(List<Object> ifMatch) {
    this.ifMatch = ifMatch;
  }

  public IHeaderDictionary ifModifiedSince(List<Object> ifModifiedSince) {
    this.ifModifiedSince = ifModifiedSince;
    return this;
  }

  public IHeaderDictionary addIfModifiedSinceItem(Object ifModifiedSinceItem) {
    if (this.ifModifiedSince == null) {
      this.ifModifiedSince = new ArrayList<Object>();
    }
    this.ifModifiedSince.add(ifModifiedSinceItem);
    return this;
  }

  /**
   * Returns et ifModifiedSince
   *
   * @return ifModifiedSince
   */
  @Schema(description = "")
  public List<Object> getIfModifiedSince() {
    return ifModifiedSince;
  }

  public void setIfModifiedSince(List<Object> ifModifiedSince) {
    this.ifModifiedSince = ifModifiedSince;
  }

  public IHeaderDictionary ifNoneMatch(List<Object> ifNoneMatch) {
    this.ifNoneMatch = ifNoneMatch;
    return this;
  }

  public IHeaderDictionary addIfNoneMatchItem(Object ifNoneMatchItem) {
    if (this.ifNoneMatch == null) {
      this.ifNoneMatch = new ArrayList<Object>();
    }
    this.ifNoneMatch.add(ifNoneMatchItem);
    return this;
  }

  /**
   * Returns et ifNoneMatch
   *
   * @return ifNoneMatch
   */
  @Schema(description = "")
  public List<Object> getIfNoneMatch() {
    return ifNoneMatch;
  }

  public void setIfNoneMatch(List<Object> ifNoneMatch) {
    this.ifNoneMatch = ifNoneMatch;
  }

  public IHeaderDictionary ifRange(List<Object> ifRange) {
    this.ifRange = ifRange;
    return this;
  }

  public IHeaderDictionary addIfRangeItem(Object ifRangeItem) {
    if (this.ifRange == null) {
      this.ifRange = new ArrayList<Object>();
    }
    this.ifRange.add(ifRangeItem);
    return this;
  }

  /**
   * Returns et ifRange
   *
   * @return ifRange
   */
  @Schema(description = "")
  public List<Object> getIfRange() {
    return ifRange;
  }

  public void setIfRange(List<Object> ifRange) {
    this.ifRange = ifRange;
  }

  public IHeaderDictionary ifUnmodifiedSince(List<Object> ifUnmodifiedSince) {
    this.ifUnmodifiedSince = ifUnmodifiedSince;
    return this;
  }

  public IHeaderDictionary addIfUnmodifiedSinceItem(Object ifUnmodifiedSinceItem) {
    if (this.ifUnmodifiedSince == null) {
      this.ifUnmodifiedSince = new ArrayList<Object>();
    }
    this.ifUnmodifiedSince.add(ifUnmodifiedSinceItem);
    return this;
  }

  /**
   * Returns et ifUnmodifiedSince
   *
   * @return ifUnmodifiedSince
   */
  @Schema(description = "")
  public List<Object> getIfUnmodifiedSince() {
    return ifUnmodifiedSince;
  }

  public void setIfUnmodifiedSince(List<Object> ifUnmodifiedSince) {
    this.ifUnmodifiedSince = ifUnmodifiedSince;
  }

  public IHeaderDictionary lastModified(List<Object> lastModified) {
    this.lastModified = lastModified;
    return this;
  }

  public IHeaderDictionary addLastModifiedItem(Object lastModifiedItem) {
    if (this.lastModified == null) {
      this.lastModified = new ArrayList<Object>();
    }
    this.lastModified.add(lastModifiedItem);
    return this;
  }

  /**
   * Returns et lastModified
   *
   * @return lastModified
   */
  @Schema(description = "")
  public List<Object> getLastModified() {
    return lastModified;
  }

  public void setLastModified(List<Object> lastModified) {
    this.lastModified = lastModified;
  }

  public IHeaderDictionary link(List<Object> link) {
    this.link = link;
    return this;
  }

  public IHeaderDictionary addLinkItem(Object linkItem) {
    if (this.link == null) {
      this.link = new ArrayList<Object>();
    }
    this.link.add(linkItem);
    return this;
  }

  /**
   * Returns et link
   *
   * @return link
   */
  @Schema(description = "")
  public List<Object> getLink() {
    return link;
  }

  public void setLink(List<Object> link) {
    this.link = link;
  }

  public IHeaderDictionary location(List<Object> location) {
    this.location = location;
    return this;
  }

  public IHeaderDictionary addLocationItem(Object locationItem) {
    if (this.location == null) {
      this.location = new ArrayList<Object>();
    }
    this.location.add(locationItem);
    return this;
  }

  /**
   * Returns et location
   *
   * @return location
   */
  @Schema(description = "")
  public List<Object> getLocation() {
    return location;
  }

  public void setLocation(List<Object> location) {
    this.location = location;
  }

  public IHeaderDictionary maxForwards(List<Object> maxForwards) {
    this.maxForwards = maxForwards;
    return this;
  }

  public IHeaderDictionary addMaxForwardsItem(Object maxForwardsItem) {
    if (this.maxForwards == null) {
      this.maxForwards = new ArrayList<Object>();
    }
    this.maxForwards.add(maxForwardsItem);
    return this;
  }

  /**
   * Returns et maxForwards
   *
   * @return maxForwards
   */
  @Schema(description = "")
  public List<Object> getMaxForwards() {
    return maxForwards;
  }

  public void setMaxForwards(List<Object> maxForwards) {
    this.maxForwards = maxForwards;
  }

  public IHeaderDictionary origin(List<Object> origin) {
    this.origin = origin;
    return this;
  }

  public IHeaderDictionary addOriginItem(Object originItem) {
    if (this.origin == null) {
      this.origin = new ArrayList<Object>();
    }
    this.origin.add(originItem);
    return this;
  }

  /**
   * Returns et origin
   *
   * @return origin
   */
  @Schema(description = "")
  public List<Object> getOrigin() {
    return origin;
  }

  public void setOrigin(List<Object> origin) {
    this.origin = origin;
  }

  public IHeaderDictionary pragma(List<Object> pragma) {
    this.pragma = pragma;
    return this;
  }

  public IHeaderDictionary addPragmaItem(Object pragmaItem) {
    if (this.pragma == null) {
      this.pragma = new ArrayList<Object>();
    }
    this.pragma.add(pragmaItem);
    return this;
  }

  /**
   * Returns et pragma
   *
   * @return pragma
   */
  @Schema(description = "")
  public List<Object> getPragma() {
    return pragma;
  }

  public void setPragma(List<Object> pragma) {
    this.pragma = pragma;
  }

  public IHeaderDictionary proxyAuthenticate(List<Object> proxyAuthenticate) {
    this.proxyAuthenticate = proxyAuthenticate;
    return this;
  }

  public IHeaderDictionary addProxyAuthenticateItem(Object proxyAuthenticateItem) {
    if (this.proxyAuthenticate == null) {
      this.proxyAuthenticate = new ArrayList<Object>();
    }
    this.proxyAuthenticate.add(proxyAuthenticateItem);
    return this;
  }

  /**
   * Returns et proxyAuthenticate
   *
   * @return proxyAuthenticate
   */
  @Schema(description = "")
  public List<Object> getProxyAuthenticate() {
    return proxyAuthenticate;
  }

  public void setProxyAuthenticate(List<Object> proxyAuthenticate) {
    this.proxyAuthenticate = proxyAuthenticate;
  }

  public IHeaderDictionary proxyAuthorization(List<Object> proxyAuthorization) {
    this.proxyAuthorization = proxyAuthorization;
    return this;
  }

  public IHeaderDictionary addProxyAuthorizationItem(Object proxyAuthorizationItem) {
    if (this.proxyAuthorization == null) {
      this.proxyAuthorization = new ArrayList<Object>();
    }
    this.proxyAuthorization.add(proxyAuthorizationItem);
    return this;
  }

  /**
   * Returns et proxyAuthorization
   *
   * @return proxyAuthorization
   */
  @Schema(description = "")
  public List<Object> getProxyAuthorization() {
    return proxyAuthorization;
  }

  public void setProxyAuthorization(List<Object> proxyAuthorization) {
    this.proxyAuthorization = proxyAuthorization;
  }

  public IHeaderDictionary proxyConnection(List<Object> proxyConnection) {
    this.proxyConnection = proxyConnection;
    return this;
  }

  public IHeaderDictionary addProxyConnectionItem(Object proxyConnectionItem) {
    if (this.proxyConnection == null) {
      this.proxyConnection = new ArrayList<Object>();
    }
    this.proxyConnection.add(proxyConnectionItem);
    return this;
  }

  /**
   * Returns et proxyConnection
   *
   * @return proxyConnection
   */
  @Schema(description = "")
  public List<Object> getProxyConnection() {
    return proxyConnection;
  }

  public void setProxyConnection(List<Object> proxyConnection) {
    this.proxyConnection = proxyConnection;
  }

  public IHeaderDictionary range(List<Object> range) {
    this.range = range;
    return this;
  }

  public IHeaderDictionary addRangeItem(Object rangeItem) {
    if (this.range == null) {
      this.range = new ArrayList<Object>();
    }
    this.range.add(rangeItem);
    return this;
  }

  /**
   * Returns et range
   *
   * @return range
   */
  @Schema(description = "")
  public List<Object> getRange() {
    return range;
  }

  public void setRange(List<Object> range) {
    this.range = range;
  }

  public IHeaderDictionary referer(List<Object> referer) {
    this.referer = referer;
    return this;
  }

  public IHeaderDictionary addRefererItem(Object refererItem) {
    if (this.referer == null) {
      this.referer = new ArrayList<Object>();
    }
    this.referer.add(refererItem);
    return this;
  }

  /**
   * Returns et referer
   *
   * @return referer
   */
  @Schema(description = "")
  public List<Object> getReferer() {
    return referer;
  }

  public void setReferer(List<Object> referer) {
    this.referer = referer;
  }

  public IHeaderDictionary retryAfter(List<Object> retryAfter) {
    this.retryAfter = retryAfter;
    return this;
  }

  public IHeaderDictionary addRetryAfterItem(Object retryAfterItem) {
    if (this.retryAfter == null) {
      this.retryAfter = new ArrayList<Object>();
    }
    this.retryAfter.add(retryAfterItem);
    return this;
  }

  /**
   * Returns et retryAfter
   *
   * @return retryAfter
   */
  @Schema(description = "")
  public List<Object> getRetryAfter() {
    return retryAfter;
  }

  public void setRetryAfter(List<Object> retryAfter) {
    this.retryAfter = retryAfter;
  }

  public IHeaderDictionary requestId(List<Object> requestId) {
    this.requestId = requestId;
    return this;
  }

  public IHeaderDictionary addRequestIdItem(Object requestIdItem) {
    if (this.requestId == null) {
      this.requestId = new ArrayList<Object>();
    }
    this.requestId.add(requestIdItem);
    return this;
  }

  /**
   * Returns et requestId
   *
   * @return requestId
   */
  @Schema(description = "")
  public List<Object> getRequestId() {
    return requestId;
  }

  public void setRequestId(List<Object> requestId) {
    this.requestId = requestId;
  }

  public IHeaderDictionary secWebSocketAccept(List<Object> secWebSocketAccept) {
    this.secWebSocketAccept = secWebSocketAccept;
    return this;
  }

  public IHeaderDictionary addSecWebSocketAcceptItem(Object secWebSocketAcceptItem) {
    if (this.secWebSocketAccept == null) {
      this.secWebSocketAccept = new ArrayList<Object>();
    }
    this.secWebSocketAccept.add(secWebSocketAcceptItem);
    return this;
  }

  /**
   * Returns et secWebSocketAccept
   *
   * @return secWebSocketAccept
   */
  @Schema(description = "")
  public List<Object> getSecWebSocketAccept() {
    return secWebSocketAccept;
  }

  public void setSecWebSocketAccept(List<Object> secWebSocketAccept) {
    this.secWebSocketAccept = secWebSocketAccept;
  }

  public IHeaderDictionary secWebSocketKey(List<Object> secWebSocketKey) {
    this.secWebSocketKey = secWebSocketKey;
    return this;
  }

  public IHeaderDictionary addSecWebSocketKeyItem(Object secWebSocketKeyItem) {
    if (this.secWebSocketKey == null) {
      this.secWebSocketKey = new ArrayList<Object>();
    }
    this.secWebSocketKey.add(secWebSocketKeyItem);
    return this;
  }

  /**
   * Returns et secWebSocketKey
   *
   * @return secWebSocketKey
   */
  @Schema(description = "")
  public List<Object> getSecWebSocketKey() {
    return secWebSocketKey;
  }

  public void setSecWebSocketKey(List<Object> secWebSocketKey) {
    this.secWebSocketKey = secWebSocketKey;
  }

  public IHeaderDictionary secWebSocketProtocol(List<Object> secWebSocketProtocol) {
    this.secWebSocketProtocol = secWebSocketProtocol;
    return this;
  }

  public IHeaderDictionary addSecWebSocketProtocolItem(Object secWebSocketProtocolItem) {
    if (this.secWebSocketProtocol == null) {
      this.secWebSocketProtocol = new ArrayList<Object>();
    }
    this.secWebSocketProtocol.add(secWebSocketProtocolItem);
    return this;
  }

  /**
   * Returns et secWebSocketProtocol
   *
   * @return secWebSocketProtocol
   */
  @Schema(description = "")
  public List<Object> getSecWebSocketProtocol() {
    return secWebSocketProtocol;
  }

  public void setSecWebSocketProtocol(List<Object> secWebSocketProtocol) {
    this.secWebSocketProtocol = secWebSocketProtocol;
  }

  public IHeaderDictionary secWebSocketVersion(List<Object> secWebSocketVersion) {
    this.secWebSocketVersion = secWebSocketVersion;
    return this;
  }

  public IHeaderDictionary addSecWebSocketVersionItem(Object secWebSocketVersionItem) {
    if (this.secWebSocketVersion == null) {
      this.secWebSocketVersion = new ArrayList<Object>();
    }
    this.secWebSocketVersion.add(secWebSocketVersionItem);
    return this;
  }

  /**
   * Returns et secWebSocketVersion
   *
   * @return secWebSocketVersion
   */
  @Schema(description = "")
  public List<Object> getSecWebSocketVersion() {
    return secWebSocketVersion;
  }

  public void setSecWebSocketVersion(List<Object> secWebSocketVersion) {
    this.secWebSocketVersion = secWebSocketVersion;
  }

  public IHeaderDictionary secWebSocketExtensions(List<Object> secWebSocketExtensions) {
    this.secWebSocketExtensions = secWebSocketExtensions;
    return this;
  }

  public IHeaderDictionary addSecWebSocketExtensionsItem(Object secWebSocketExtensionsItem) {
    if (this.secWebSocketExtensions == null) {
      this.secWebSocketExtensions = new ArrayList<Object>();
    }
    this.secWebSocketExtensions.add(secWebSocketExtensionsItem);
    return this;
  }

  /**
   * Returns et secWebSocketExtensions
   *
   * @return secWebSocketExtensions
   */
  @Schema(description = "")
  public List<Object> getSecWebSocketExtensions() {
    return secWebSocketExtensions;
  }

  public void setSecWebSocketExtensions(List<Object> secWebSocketExtensions) {
    this.secWebSocketExtensions = secWebSocketExtensions;
  }

  public IHeaderDictionary server(List<Object> server) {
    this.server = server;
    return this;
  }

  public IHeaderDictionary addServerItem(Object serverItem) {
    if (this.server == null) {
      this.server = new ArrayList<Object>();
    }
    this.server.add(serverItem);
    return this;
  }

  /**
   * Returns et server
   *
   * @return server
   */
  @Schema(description = "")
  public List<Object> getServer() {
    return server;
  }

  public void setServer(List<Object> server) {
    this.server = server;
  }

  public IHeaderDictionary setCookie(List<Object> setCookie) {
    this.setCookie = setCookie;
    return this;
  }

  public IHeaderDictionary addSetCookieItem(Object setCookieItem) {
    if (this.setCookie == null) {
      this.setCookie = new ArrayList<Object>();
    }
    this.setCookie.add(setCookieItem);
    return this;
  }

  /**
   * Returns et setCookie
   *
   * @return setCookie
   */
  @Schema(description = "")
  public List<Object> getSetCookie() {
    return setCookie;
  }

  public void setSetCookie(List<Object> setCookie) {
    this.setCookie = setCookie;
  }

  public IHeaderDictionary strictTransportSecurity(List<Object> strictTransportSecurity) {
    this.strictTransportSecurity = strictTransportSecurity;
    return this;
  }

  public IHeaderDictionary addStrictTransportSecurityItem(Object strictTransportSecurityItem) {
    if (this.strictTransportSecurity == null) {
      this.strictTransportSecurity = new ArrayList<Object>();
    }
    this.strictTransportSecurity.add(strictTransportSecurityItem);
    return this;
  }

  /**
   * Returns et strictTransportSecurity
   *
   * @return strictTransportSecurity
   */
  @Schema(description = "")
  public List<Object> getStrictTransportSecurity() {
    return strictTransportSecurity;
  }

  public void setStrictTransportSecurity(List<Object> strictTransportSecurity) {
    this.strictTransportSecurity = strictTransportSecurity;
  }

  public IHeaderDictionary TE(List<Object> TE) {
    this.TE = TE;
    return this;
  }

  public IHeaderDictionary addTEItem(Object TEItem) {
    if (this.TE == null) {
      this.TE = new ArrayList<Object>();
    }
    this.TE.add(TEItem);
    return this;
  }

  /**
   * Returns et TE
   *
   * @return TE
   */
  @Schema(description = "")
  public List<Object> getTE() {
    return TE;
  }

  public void setTE(List<Object> TE) {
    this.TE = TE;
  }

  public IHeaderDictionary trailer(List<Object> trailer) {
    this.trailer = trailer;
    return this;
  }

  public IHeaderDictionary addTrailerItem(Object trailerItem) {
    if (this.trailer == null) {
      this.trailer = new ArrayList<Object>();
    }
    this.trailer.add(trailerItem);
    return this;
  }

  /**
   * Returns et trailer
   *
   * @return trailer
   */
  @Schema(description = "")
  public List<Object> getTrailer() {
    return trailer;
  }

  public void setTrailer(List<Object> trailer) {
    this.trailer = trailer;
  }

  public IHeaderDictionary transferEncoding(List<Object> transferEncoding) {
    this.transferEncoding = transferEncoding;
    return this;
  }

  public IHeaderDictionary addTransferEncodingItem(Object transferEncodingItem) {
    if (this.transferEncoding == null) {
      this.transferEncoding = new ArrayList<Object>();
    }
    this.transferEncoding.add(transferEncodingItem);
    return this;
  }

  /**
   * Returns et transferEncoding
   *
   * @return transferEncoding
   */
  @Schema(description = "")
  public List<Object> getTransferEncoding() {
    return transferEncoding;
  }

  public void setTransferEncoding(List<Object> transferEncoding) {
    this.transferEncoding = transferEncoding;
  }

  public IHeaderDictionary translate(List<Object> translate) {
    this.translate = translate;
    return this;
  }

  public IHeaderDictionary addTranslateItem(Object translateItem) {
    if (this.translate == null) {
      this.translate = new ArrayList<Object>();
    }
    this.translate.add(translateItem);
    return this;
  }

  /**
   * Returns et translate
   *
   * @return translate
   */
  @Schema(description = "")
  public List<Object> getTranslate() {
    return translate;
  }

  public void setTranslate(List<Object> translate) {
    this.translate = translate;
  }

  public IHeaderDictionary traceParent(List<Object> traceParent) {
    this.traceParent = traceParent;
    return this;
  }

  public IHeaderDictionary addTraceParentItem(Object traceParentItem) {
    if (this.traceParent == null) {
      this.traceParent = new ArrayList<Object>();
    }
    this.traceParent.add(traceParentItem);
    return this;
  }

  /**
   * Returns et traceParent
   *
   * @return traceParent
   */
  @Schema(description = "")
  public List<Object> getTraceParent() {
    return traceParent;
  }

  public void setTraceParent(List<Object> traceParent) {
    this.traceParent = traceParent;
  }

  public IHeaderDictionary traceState(List<Object> traceState) {
    this.traceState = traceState;
    return this;
  }

  public IHeaderDictionary addTraceStateItem(Object traceStateItem) {
    if (this.traceState == null) {
      this.traceState = new ArrayList<Object>();
    }
    this.traceState.add(traceStateItem);
    return this;
  }

  /**
   * Returns et traceState
   *
   * @return traceState
   */
  @Schema(description = "")
  public List<Object> getTraceState() {
    return traceState;
  }

  public void setTraceState(List<Object> traceState) {
    this.traceState = traceState;
  }

  public IHeaderDictionary upgrade(List<Object> upgrade) {
    this.upgrade = upgrade;
    return this;
  }

  public IHeaderDictionary addUpgradeItem(Object upgradeItem) {
    if (this.upgrade == null) {
      this.upgrade = new ArrayList<Object>();
    }
    this.upgrade.add(upgradeItem);
    return this;
  }

  /**
   * Returns et upgrade
   *
   * @return upgrade
   */
  @Schema(description = "")
  public List<Object> getUpgrade() {
    return upgrade;
  }

  public void setUpgrade(List<Object> upgrade) {
    this.upgrade = upgrade;
  }

  public IHeaderDictionary upgradeInsecureRequests(List<Object> upgradeInsecureRequests) {
    this.upgradeInsecureRequests = upgradeInsecureRequests;
    return this;
  }

  public IHeaderDictionary addUpgradeInsecureRequestsItem(Object upgradeInsecureRequestsItem) {
    if (this.upgradeInsecureRequests == null) {
      this.upgradeInsecureRequests = new ArrayList<Object>();
    }
    this.upgradeInsecureRequests.add(upgradeInsecureRequestsItem);
    return this;
  }

  /**
   * Returns et upgradeInsecureRequests
   *
   * @return upgradeInsecureRequests
   */
  @Schema(description = "")
  public List<Object> getUpgradeInsecureRequests() {
    return upgradeInsecureRequests;
  }

  public void setUpgradeInsecureRequests(List<Object> upgradeInsecureRequests) {
    this.upgradeInsecureRequests = upgradeInsecureRequests;
  }

  public IHeaderDictionary userAgent(List<Object> userAgent) {
    this.userAgent = userAgent;
    return this;
  }

  public IHeaderDictionary addUserAgentItem(Object userAgentItem) {
    if (this.userAgent == null) {
      this.userAgent = new ArrayList<Object>();
    }
    this.userAgent.add(userAgentItem);
    return this;
  }

  /**
   * Returns et userAgent
   *
   * @return userAgent
   */
  @Schema(description = "")
  public List<Object> getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(List<Object> userAgent) {
    this.userAgent = userAgent;
  }

  public IHeaderDictionary vary(List<Object> vary) {
    this.vary = vary;
    return this;
  }

  public IHeaderDictionary addVaryItem(Object varyItem) {
    if (this.vary == null) {
      this.vary = new ArrayList<Object>();
    }
    this.vary.add(varyItem);
    return this;
  }

  /**
   * Returns et vary
   *
   * @return vary
   */
  @Schema(description = "")
  public List<Object> getVary() {
    return vary;
  }

  public void setVary(List<Object> vary) {
    this.vary = vary;
  }

  public IHeaderDictionary via(List<Object> via) {
    this.via = via;
    return this;
  }

  public IHeaderDictionary addViaItem(Object viaItem) {
    if (this.via == null) {
      this.via = new ArrayList<Object>();
    }
    this.via.add(viaItem);
    return this;
  }

  /**
   * Returns et via
   *
   * @return via
   */
  @Schema(description = "")
  public List<Object> getVia() {
    return via;
  }

  public void setVia(List<Object> via) {
    this.via = via;
  }

  public IHeaderDictionary warning(List<Object> warning) {
    this.warning = warning;
    return this;
  }

  public IHeaderDictionary addWarningItem(Object warningItem) {
    if (this.warning == null) {
      this.warning = new ArrayList<Object>();
    }
    this.warning.add(warningItem);
    return this;
  }

  /**
   * Returns et warning
   *
   * @return warning
   */
  @Schema(description = "")
  public List<Object> getWarning() {
    return warning;
  }

  public void setWarning(List<Object> warning) {
    this.warning = warning;
  }

  public IHeaderDictionary webSocketSubProtocols(List<Object> webSocketSubProtocols) {
    this.webSocketSubProtocols = webSocketSubProtocols;
    return this;
  }

  public IHeaderDictionary addWebSocketSubProtocolsItem(Object webSocketSubProtocolsItem) {
    if (this.webSocketSubProtocols == null) {
      this.webSocketSubProtocols = new ArrayList<Object>();
    }
    this.webSocketSubProtocols.add(webSocketSubProtocolsItem);
    return this;
  }

  /**
   * Returns et webSocketSubProtocols
   *
   * @return webSocketSubProtocols
   */
  @Schema(description = "")
  public List<Object> getWebSocketSubProtocols() {
    return webSocketSubProtocols;
  }

  public void setWebSocketSubProtocols(List<Object> webSocketSubProtocols) {
    this.webSocketSubProtocols = webSocketSubProtocols;
  }

  public IHeaderDictionary wwWAuthenticate(List<Object> wwWAuthenticate) {
    this.wwWAuthenticate = wwWAuthenticate;
    return this;
  }

  public IHeaderDictionary addWwWAuthenticateItem(Object wwWAuthenticateItem) {
    if (this.wwWAuthenticate == null) {
      this.wwWAuthenticate = new ArrayList<Object>();
    }
    this.wwWAuthenticate.add(wwWAuthenticateItem);
    return this;
  }

  /**
   * Returns et wwWAuthenticate
   *
   * @return wwWAuthenticate
   */
  @Schema(description = "")
  public List<Object> getWwWAuthenticate() {
    return wwWAuthenticate;
  }

  public void setWwWAuthenticate(List<Object> wwWAuthenticate) {
    this.wwWAuthenticate = wwWAuthenticate;
  }

  public IHeaderDictionary xcontentTypeOptions(List<Object> xcontentTypeOptions) {
    this.xcontentTypeOptions = xcontentTypeOptions;
    return this;
  }

  public IHeaderDictionary addXcontentTypeOptionsItem(Object xcontentTypeOptionsItem) {
    if (this.xcontentTypeOptions == null) {
      this.xcontentTypeOptions = new ArrayList<Object>();
    }
    this.xcontentTypeOptions.add(xcontentTypeOptionsItem);
    return this;
  }

  /**
   * Returns et xcontentTypeOptions
   *
   * @return xcontentTypeOptions
   */
  @Schema(description = "")
  public List<Object> getXcontentTypeOptions() {
    return xcontentTypeOptions;
  }

  public void setXcontentTypeOptions(List<Object> xcontentTypeOptions) {
    this.xcontentTypeOptions = xcontentTypeOptions;
  }

  public IHeaderDictionary xframeOptions(List<Object> xframeOptions) {
    this.xframeOptions = xframeOptions;
    return this;
  }

  public IHeaderDictionary addXframeOptionsItem(Object xframeOptionsItem) {
    if (this.xframeOptions == null) {
      this.xframeOptions = new ArrayList<Object>();
    }
    this.xframeOptions.add(xframeOptionsItem);
    return this;
  }

  /**
   * Returns et xframeOptions
   *
   * @return xframeOptions
   */
  @Schema(description = "")
  public List<Object> getXframeOptions() {
    return xframeOptions;
  }

  public void setXframeOptions(List<Object> xframeOptions) {
    this.xframeOptions = xframeOptions;
  }

  public IHeaderDictionary xpoweredBy(List<Object> xpoweredBy) {
    this.xpoweredBy = xpoweredBy;
    return this;
  }

  public IHeaderDictionary addXpoweredByItem(Object xpoweredByItem) {
    if (this.xpoweredBy == null) {
      this.xpoweredBy = new ArrayList<Object>();
    }
    this.xpoweredBy.add(xpoweredByItem);
    return this;
  }

  /**
   * Returns et xpoweredBy
   *
   * @return xpoweredBy
   */
  @Schema(description = "")
  public List<Object> getXpoweredBy() {
    return xpoweredBy;
  }

  public void setXpoweredBy(List<Object> xpoweredBy) {
    this.xpoweredBy = xpoweredBy;
  }

  public IHeaderDictionary xrequestedWith(List<Object> xrequestedWith) {
    this.xrequestedWith = xrequestedWith;
    return this;
  }

  public IHeaderDictionary addXrequestedWithItem(Object xrequestedWithItem) {
    if (this.xrequestedWith == null) {
      this.xrequestedWith = new ArrayList<Object>();
    }
    this.xrequestedWith.add(xrequestedWithItem);
    return this;
  }

  /**
   * Returns et xrequestedWith
   *
   * @return xrequestedWith
   */
  @Schema(description = "")
  public List<Object> getXrequestedWith() {
    return xrequestedWith;
  }

  public void setXrequestedWith(List<Object> xrequestedWith) {
    this.xrequestedWith = xrequestedWith;
  }

  public IHeaderDictionary xuACompatible(List<Object> xuACompatible) {
    this.xuACompatible = xuACompatible;
    return this;
  }

  public IHeaderDictionary addXuACompatibleItem(Object xuACompatibleItem) {
    if (this.xuACompatible == null) {
      this.xuACompatible = new ArrayList<Object>();
    }
    this.xuACompatible.add(xuACompatibleItem);
    return this;
  }

  /**
   * Returns et xuACompatible
   *
   * @return xuACompatible
   */
  @Schema(description = "")
  public List<Object> getXuACompatible() {
    return xuACompatible;
  }

  public void setXuACompatible(List<Object> xuACompatible) {
    this.xuACompatible = xuACompatible;
  }

  public IHeaderDictionary xxSSProtection(List<Object> xxSSProtection) {
    this.xxSSProtection = xxSSProtection;
    return this;
  }

  public IHeaderDictionary addXxSSProtectionItem(Object xxSSProtectionItem) {
    if (this.xxSSProtection == null) {
      this.xxSSProtection = new ArrayList<Object>();
    }
    this.xxSSProtection.add(xxSSProtectionItem);
    return this;
  }

  /**
   * Returns et xxSSProtection
   *
   * @return xxSSProtection
   */
  @Schema(description = "")
  public List<Object> getXxSSProtection() {
    return xxSSProtection;
  }

  public void setXxSSProtection(List<Object> xxSSProtection) {
    this.xxSSProtection = xxSSProtection;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IHeaderDictionary iheaderDictionary = (IHeaderDictionary) o;
    return Objects.equals(this.item, iheaderDictionary.item)
        && Objects.equals(this.contentLength, iheaderDictionary.contentLength)
        && Objects.equals(this.accept, iheaderDictionary.accept)
        && Objects.equals(this.acceptCharset, iheaderDictionary.acceptCharset)
        && Objects.equals(this.acceptEncoding, iheaderDictionary.acceptEncoding)
        && Objects.equals(this.acceptLanguage, iheaderDictionary.acceptLanguage)
        && Objects.equals(this.acceptRanges, iheaderDictionary.acceptRanges)
        && Objects.equals(
            this.accessControlAllowCredentials, iheaderDictionary.accessControlAllowCredentials)
        && Objects.equals(
            this.accessControlAllowHeaders, iheaderDictionary.accessControlAllowHeaders)
        && Objects.equals(
            this.accessControlAllowMethods, iheaderDictionary.accessControlAllowMethods)
        && Objects.equals(this.accessControlAllowOrigin, iheaderDictionary.accessControlAllowOrigin)
        && Objects.equals(
            this.accessControlExposeHeaders, iheaderDictionary.accessControlExposeHeaders)
        && Objects.equals(this.accessControlMaxAge, iheaderDictionary.accessControlMaxAge)
        && Objects.equals(
            this.accessControlRequestHeaders, iheaderDictionary.accessControlRequestHeaders)
        && Objects.equals(
            this.accessControlRequestMethod, iheaderDictionary.accessControlRequestMethod)
        && Objects.equals(this.age, iheaderDictionary.age)
        && Objects.equals(this.allow, iheaderDictionary.allow)
        && Objects.equals(this.altSvc, iheaderDictionary.altSvc)
        && Objects.equals(this.authorization, iheaderDictionary.authorization)
        && Objects.equals(this.baggage, iheaderDictionary.baggage)
        && Objects.equals(this.cacheControl, iheaderDictionary.cacheControl)
        && Objects.equals(this.connection, iheaderDictionary.connection)
        && Objects.equals(this.contentDisposition, iheaderDictionary.contentDisposition)
        && Objects.equals(this.contentEncoding, iheaderDictionary.contentEncoding)
        && Objects.equals(this.contentLanguage, iheaderDictionary.contentLanguage)
        && Objects.equals(this.contentLocation, iheaderDictionary.contentLocation)
        && Objects.equals(this.contentMD5, iheaderDictionary.contentMD5)
        && Objects.equals(this.contentRange, iheaderDictionary.contentRange)
        && Objects.equals(this.contentSecurityPolicy, iheaderDictionary.contentSecurityPolicy)
        && Objects.equals(
            this.contentSecurityPolicyReportOnly, iheaderDictionary.contentSecurityPolicyReportOnly)
        && Objects.equals(this.contentType, iheaderDictionary.contentType)
        && Objects.equals(this.correlationContext, iheaderDictionary.correlationContext)
        && Objects.equals(this.cookie, iheaderDictionary.cookie)
        && Objects.equals(this.date, iheaderDictionary.date)
        && Objects.equals(this.etag, iheaderDictionary.etag)
        && Objects.equals(this.expires, iheaderDictionary.expires)
        && Objects.equals(this.expect, iheaderDictionary.expect)
        && Objects.equals(this.from, iheaderDictionary.from)
        && Objects.equals(this.grpcAcceptEncoding, iheaderDictionary.grpcAcceptEncoding)
        && Objects.equals(this.grpcEncoding, iheaderDictionary.grpcEncoding)
        && Objects.equals(this.grpcMessage, iheaderDictionary.grpcMessage)
        && Objects.equals(this.grpcStatus, iheaderDictionary.grpcStatus)
        && Objects.equals(this.grpcTimeout, iheaderDictionary.grpcTimeout)
        && Objects.equals(this.host, iheaderDictionary.host)
        && Objects.equals(this.keepAlive, iheaderDictionary.keepAlive)
        && Objects.equals(this.ifMatch, iheaderDictionary.ifMatch)
        && Objects.equals(this.ifModifiedSince, iheaderDictionary.ifModifiedSince)
        && Objects.equals(this.ifNoneMatch, iheaderDictionary.ifNoneMatch)
        && Objects.equals(this.ifRange, iheaderDictionary.ifRange)
        && Objects.equals(this.ifUnmodifiedSince, iheaderDictionary.ifUnmodifiedSince)
        && Objects.equals(this.lastModified, iheaderDictionary.lastModified)
        && Objects.equals(this.link, iheaderDictionary.link)
        && Objects.equals(this.location, iheaderDictionary.location)
        && Objects.equals(this.maxForwards, iheaderDictionary.maxForwards)
        && Objects.equals(this.origin, iheaderDictionary.origin)
        && Objects.equals(this.pragma, iheaderDictionary.pragma)
        && Objects.equals(this.proxyAuthenticate, iheaderDictionary.proxyAuthenticate)
        && Objects.equals(this.proxyAuthorization, iheaderDictionary.proxyAuthorization)
        && Objects.equals(this.proxyConnection, iheaderDictionary.proxyConnection)
        && Objects.equals(this.range, iheaderDictionary.range)
        && Objects.equals(this.referer, iheaderDictionary.referer)
        && Objects.equals(this.retryAfter, iheaderDictionary.retryAfter)
        && Objects.equals(this.requestId, iheaderDictionary.requestId)
        && Objects.equals(this.secWebSocketAccept, iheaderDictionary.secWebSocketAccept)
        && Objects.equals(this.secWebSocketKey, iheaderDictionary.secWebSocketKey)
        && Objects.equals(this.secWebSocketProtocol, iheaderDictionary.secWebSocketProtocol)
        && Objects.equals(this.secWebSocketVersion, iheaderDictionary.secWebSocketVersion)
        && Objects.equals(this.secWebSocketExtensions, iheaderDictionary.secWebSocketExtensions)
        && Objects.equals(this.server, iheaderDictionary.server)
        && Objects.equals(this.setCookie, iheaderDictionary.setCookie)
        && Objects.equals(this.strictTransportSecurity, iheaderDictionary.strictTransportSecurity)
        && Objects.equals(this.TE, iheaderDictionary.TE)
        && Objects.equals(this.trailer, iheaderDictionary.trailer)
        && Objects.equals(this.transferEncoding, iheaderDictionary.transferEncoding)
        && Objects.equals(this.translate, iheaderDictionary.translate)
        && Objects.equals(this.traceParent, iheaderDictionary.traceParent)
        && Objects.equals(this.traceState, iheaderDictionary.traceState)
        && Objects.equals(this.upgrade, iheaderDictionary.upgrade)
        && Objects.equals(this.upgradeInsecureRequests, iheaderDictionary.upgradeInsecureRequests)
        && Objects.equals(this.userAgent, iheaderDictionary.userAgent)
        && Objects.equals(this.vary, iheaderDictionary.vary)
        && Objects.equals(this.via, iheaderDictionary.via)
        && Objects.equals(this.warning, iheaderDictionary.warning)
        && Objects.equals(this.webSocketSubProtocols, iheaderDictionary.webSocketSubProtocols)
        && Objects.equals(this.wwWAuthenticate, iheaderDictionary.wwWAuthenticate)
        && Objects.equals(this.xcontentTypeOptions, iheaderDictionary.xcontentTypeOptions)
        && Objects.equals(this.xframeOptions, iheaderDictionary.xframeOptions)
        && Objects.equals(this.xpoweredBy, iheaderDictionary.xpoweredBy)
        && Objects.equals(this.xrequestedWith, iheaderDictionary.xrequestedWith)
        && Objects.equals(this.xuACompatible, iheaderDictionary.xuACompatible)
        && Objects.equals(this.xxSSProtection, iheaderDictionary.xxSSProtection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        item,
        contentLength,
        accept,
        acceptCharset,
        acceptEncoding,
        acceptLanguage,
        acceptRanges,
        accessControlAllowCredentials,
        accessControlAllowHeaders,
        accessControlAllowMethods,
        accessControlAllowOrigin,
        accessControlExposeHeaders,
        accessControlMaxAge,
        accessControlRequestHeaders,
        accessControlRequestMethod,
        age,
        allow,
        altSvc,
        authorization,
        baggage,
        cacheControl,
        connection,
        contentDisposition,
        contentEncoding,
        contentLanguage,
        contentLocation,
        contentMD5,
        contentRange,
        contentSecurityPolicy,
        contentSecurityPolicyReportOnly,
        contentType,
        correlationContext,
        cookie,
        date,
        etag,
        expires,
        expect,
        from,
        grpcAcceptEncoding,
        grpcEncoding,
        grpcMessage,
        grpcStatus,
        grpcTimeout,
        host,
        keepAlive,
        ifMatch,
        ifModifiedSince,
        ifNoneMatch,
        ifRange,
        ifUnmodifiedSince,
        lastModified,
        link,
        location,
        maxForwards,
        origin,
        pragma,
        proxyAuthenticate,
        proxyAuthorization,
        proxyConnection,
        range,
        referer,
        retryAfter,
        requestId,
        secWebSocketAccept,
        secWebSocketKey,
        secWebSocketProtocol,
        secWebSocketVersion,
        secWebSocketExtensions,
        server,
        setCookie,
        strictTransportSecurity,
        TE,
        trailer,
        transferEncoding,
        translate,
        traceParent,
        traceState,
        upgrade,
        upgradeInsecureRequests,
        userAgent,
        vary,
        via,
        warning,
        webSocketSubProtocols,
        wwWAuthenticate,
        xcontentTypeOptions,
        xframeOptions,
        xpoweredBy,
        xrequestedWith,
        xuACompatible,
        xxSSProtection);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IHeaderDictionary {\n");
    sb.append("    item: ").append(toIndentedString(item)).append("\n");
    sb.append("    contentLength: ").append(toIndentedString(contentLength)).append("\n");
    sb.append("    accept: ").append(toIndentedString(accept)).append("\n");
    sb.append("    acceptCharset: ").append(toIndentedString(acceptCharset)).append("\n");
    sb.append("    acceptEncoding: ").append(toIndentedString(acceptEncoding)).append("\n");
    sb.append("    acceptLanguage: ").append(toIndentedString(acceptLanguage)).append("\n");
    sb.append("    acceptRanges: ").append(toIndentedString(acceptRanges)).append("\n");
    sb.append("    accessControlAllowCredentials: ")
        .append(toIndentedString(accessControlAllowCredentials))
        .append("\n");
    sb.append("    accessControlAllowHeaders: ")
        .append(toIndentedString(accessControlAllowHeaders))
        .append("\n");
    sb.append("    accessControlAllowMethods: ")
        .append(toIndentedString(accessControlAllowMethods))
        .append("\n");
    sb.append("    accessControlAllowOrigin: ")
        .append(toIndentedString(accessControlAllowOrigin))
        .append("\n");
    sb.append("    accessControlExposeHeaders: ")
        .append(toIndentedString(accessControlExposeHeaders))
        .append("\n");
    sb.append("    accessControlMaxAge: ")
        .append(toIndentedString(accessControlMaxAge))
        .append("\n");
    sb.append("    accessControlRequestHeaders: ")
        .append(toIndentedString(accessControlRequestHeaders))
        .append("\n");
    sb.append("    accessControlRequestMethod: ")
        .append(toIndentedString(accessControlRequestMethod))
        .append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    allow: ").append(toIndentedString(allow)).append("\n");
    sb.append("    altSvc: ").append(toIndentedString(altSvc)).append("\n");
    sb.append("    authorization: ").append(toIndentedString(authorization)).append("\n");
    sb.append("    baggage: ").append(toIndentedString(baggage)).append("\n");
    sb.append("    cacheControl: ").append(toIndentedString(cacheControl)).append("\n");
    sb.append("    connection: ").append(toIndentedString(connection)).append("\n");
    sb.append("    contentDisposition: ").append(toIndentedString(contentDisposition)).append("\n");
    sb.append("    contentEncoding: ").append(toIndentedString(contentEncoding)).append("\n");
    sb.append("    contentLanguage: ").append(toIndentedString(contentLanguage)).append("\n");
    sb.append("    contentLocation: ").append(toIndentedString(contentLocation)).append("\n");
    sb.append("    contentMD5: ").append(toIndentedString(contentMD5)).append("\n");
    sb.append("    contentRange: ").append(toIndentedString(contentRange)).append("\n");
    sb.append("    contentSecurityPolicy: ")
        .append(toIndentedString(contentSecurityPolicy))
        .append("\n");
    sb.append("    contentSecurityPolicyReportOnly: ")
        .append(toIndentedString(contentSecurityPolicyReportOnly))
        .append("\n");
    sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
    sb.append("    correlationContext: ").append(toIndentedString(correlationContext)).append("\n");
    sb.append("    cookie: ").append(toIndentedString(cookie)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    etag: ").append(toIndentedString(etag)).append("\n");
    sb.append("    expires: ").append(toIndentedString(expires)).append("\n");
    sb.append("    expect: ").append(toIndentedString(expect)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    grpcAcceptEncoding: ").append(toIndentedString(grpcAcceptEncoding)).append("\n");
    sb.append("    grpcEncoding: ").append(toIndentedString(grpcEncoding)).append("\n");
    sb.append("    grpcMessage: ").append(toIndentedString(grpcMessage)).append("\n");
    sb.append("    grpcStatus: ").append(toIndentedString(grpcStatus)).append("\n");
    sb.append("    grpcTimeout: ").append(toIndentedString(grpcTimeout)).append("\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    keepAlive: ").append(toIndentedString(keepAlive)).append("\n");
    sb.append("    ifMatch: ").append(toIndentedString(ifMatch)).append("\n");
    sb.append("    ifModifiedSince: ").append(toIndentedString(ifModifiedSince)).append("\n");
    sb.append("    ifNoneMatch: ").append(toIndentedString(ifNoneMatch)).append("\n");
    sb.append("    ifRange: ").append(toIndentedString(ifRange)).append("\n");
    sb.append("    ifUnmodifiedSince: ").append(toIndentedString(ifUnmodifiedSince)).append("\n");
    sb.append("    lastModified: ").append(toIndentedString(lastModified)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    maxForwards: ").append(toIndentedString(maxForwards)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
    sb.append("    pragma: ").append(toIndentedString(pragma)).append("\n");
    sb.append("    proxyAuthenticate: ").append(toIndentedString(proxyAuthenticate)).append("\n");
    sb.append("    proxyAuthorization: ").append(toIndentedString(proxyAuthorization)).append("\n");
    sb.append("    proxyConnection: ").append(toIndentedString(proxyConnection)).append("\n");
    sb.append("    range: ").append(toIndentedString(range)).append("\n");
    sb.append("    referer: ").append(toIndentedString(referer)).append("\n");
    sb.append("    retryAfter: ").append(toIndentedString(retryAfter)).append("\n");
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    secWebSocketAccept: ").append(toIndentedString(secWebSocketAccept)).append("\n");
    sb.append("    secWebSocketKey: ").append(toIndentedString(secWebSocketKey)).append("\n");
    sb.append("    secWebSocketProtocol: ")
        .append(toIndentedString(secWebSocketProtocol))
        .append("\n");
    sb.append("    secWebSocketVersion: ")
        .append(toIndentedString(secWebSocketVersion))
        .append("\n");
    sb.append("    secWebSocketExtensions: ")
        .append(toIndentedString(secWebSocketExtensions))
        .append("\n");
    sb.append("    server: ").append(toIndentedString(server)).append("\n");
    sb.append("    setCookie: ").append(toIndentedString(setCookie)).append("\n");
    sb.append("    strictTransportSecurity: ")
        .append(toIndentedString(strictTransportSecurity))
        .append("\n");
    sb.append("    TE: ").append(toIndentedString(TE)).append("\n");
    sb.append("    trailer: ").append(toIndentedString(trailer)).append("\n");
    sb.append("    transferEncoding: ").append(toIndentedString(transferEncoding)).append("\n");
    sb.append("    translate: ").append(toIndentedString(translate)).append("\n");
    sb.append("    traceParent: ").append(toIndentedString(traceParent)).append("\n");
    sb.append("    traceState: ").append(toIndentedString(traceState)).append("\n");
    sb.append("    upgrade: ").append(toIndentedString(upgrade)).append("\n");
    sb.append("    upgradeInsecureRequests: ")
        .append(toIndentedString(upgradeInsecureRequests))
        .append("\n");
    sb.append("    userAgent: ").append(toIndentedString(userAgent)).append("\n");
    sb.append("    vary: ").append(toIndentedString(vary)).append("\n");
    sb.append("    via: ").append(toIndentedString(via)).append("\n");
    sb.append("    warning: ").append(toIndentedString(warning)).append("\n");
    sb.append("    webSocketSubProtocols: ")
        .append(toIndentedString(webSocketSubProtocols))
        .append("\n");
    sb.append("    wwWAuthenticate: ").append(toIndentedString(wwWAuthenticate)).append("\n");
    sb.append("    xcontentTypeOptions: ")
        .append(toIndentedString(xcontentTypeOptions))
        .append("\n");
    sb.append("    xframeOptions: ").append(toIndentedString(xframeOptions)).append("\n");
    sb.append("    xpoweredBy: ").append(toIndentedString(xpoweredBy)).append("\n");
    sb.append("    xrequestedWith: ").append(toIndentedString(xrequestedWith)).append("\n");
    sb.append("    xuACompatible: ").append(toIndentedString(xuACompatible)).append("\n");
    sb.append("    xxSSProtection: ").append(toIndentedString(xxSSProtection)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
