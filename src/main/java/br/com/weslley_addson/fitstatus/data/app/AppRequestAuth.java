package br.com.weslley_addson.fitstatus.data.app;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AppRequestAuth(
        @JsonProperty("id") Long appId,
        @JsonProperty("appName") String appName) {
}
