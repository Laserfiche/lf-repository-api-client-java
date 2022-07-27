package com.laserfiche.repository.api.clients.impl.model;

public class GetDocumentContentTypeResult {
    public GetDocumentContentTypeResult(int contentLength, String contentType) {
        this.contentLength = contentLength;
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public int getContentLength() {
        return contentLength;
    }

    private String contentType;
    private int contentLength;
}
