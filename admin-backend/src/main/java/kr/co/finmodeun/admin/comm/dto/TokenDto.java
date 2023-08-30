package kr.co.finmodeun.admin.comm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.cmn.http.validate.groups.Retrieve;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class TokenDto {
    /**
     * Access Token
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * Refresh Token
     */
    @NotBlank(groups = { Retrieve.class })
    @JsonProperty("refresh_token")
    private String refreshToken;
}
