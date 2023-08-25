package kr.co.finmodeun.admin.admin;

import kr.co.finmodeun.admin.admin.dto.CmsAdminInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class CmsAdminInfoControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("어드민 등록 시 유효성 체크")
    @Test
    void createValidationTest() {
        CmsAdminInfoDto dto = CmsAdminInfoDto.builder().build();

        dto.setCreId("SYSTEM");

        String url = "http://localhost:" + port + "/admin/admin/create";

        ResponseEntity<Map> response = restTemplate.postForEntity(url, dto, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // 어드민 아이디
        dto.setAdminId("admin");

        response = restTemplate.postForEntity(url, dto, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // 어드민 비밀번호
        dto.setAdminPwd("imsi00!!");

        response = restTemplate.postForEntity(url, dto, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // 어드민 비밀번호 확인
        dto.setAdminPwdConfirm("imsi00!!");

        response = restTemplate.postForEntity(url, dto, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // 어드민 이름
        dto.setAdminName("관리자");

        response = restTemplate.postForEntity(url, dto, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // 어드민 유형
        dto.setAdminType("A");

        response = restTemplate.postForEntity(url, dto, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // 어드민 휴대폰 번호
        dto.setHpNo("01010001000");

        response = restTemplate.postForEntity(url, dto, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}