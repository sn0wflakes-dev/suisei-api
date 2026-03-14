package com.sn0w.suisei.api.presentation.rest.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebRes<T> {
    private Meta meta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Error error;

    private String path;

    @Data
    @Builder
    @JsonPropertyOrder({"requestId", "timestamp"})
    public static class Meta {
        private String requestId;
        private String timestamp;
    }

    @Data
    @Builder
    @JsonPropertyOrder({"errorCode", "message", "detail"})
    public static class Error{
        private String errorCode;
        private String message;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Object detail;
    }
}
