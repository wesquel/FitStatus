package br.com.weslley_addson.fitstatus.data.user;

import br.com.weslley_addson.fitstatus.data.app.AppRequestAuth;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record UserResponseFitApi(
    @JsonProperty("authUserId") Long authUserId,
    @JsonProperty("appsPermission") Set<AppRequestAuth> appsPermission
) {
}
