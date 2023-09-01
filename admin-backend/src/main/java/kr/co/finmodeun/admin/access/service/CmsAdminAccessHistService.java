package kr.co.finmodeun.admin.access.service;

import com.project.cmn.http.accesslog.AccessLogDto;
import kr.co.finmodeun.admin.access.dto.CmsAdminAccessHistDto;
import kr.co.finmodeun.admin.access.mapper.CmsAdminAccessHistMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ua_parser.Client;
import ua_parser.Parser;

@Slf4j
@RequiredArgsConstructor
@Service
public class CmsAdminAccessHistService {
    private final CmsAdminAccessHistMapper cmsAdminAccessHistMapper;

    /**
     * 접근 이력 정보를 등록한다.
     *
     * @param param 접근 이력 정보
     */
    public void adminAccessHistCreate(AccessLogDto param) {
        CmsAdminAccessHistDto cmsAdminAccessHistDto = new CmsAdminAccessHistDto();

        BeanUtils.copyProperties(param, cmsAdminAccessHistDto);

        cmsAdminAccessHistDto.setUriAddr(param.getRequestUri());
        cmsAdminAccessHistDto.setCreId(param.getUserId());

        Parser uaParser = new Parser();
        Client client = uaParser.parse(param.getRequestHeader().get("user-agent"));

        cmsAdminAccessHistDto.setOsType(client.os.family);
        cmsAdminAccessHistDto.setBrowserType(client.userAgent.family);
        cmsAdminAccessHistDto.setDeviceType(client.device.family);

        cmsAdminAccessHistMapper.insertCmsAdminAccessHist(cmsAdminAccessHistDto);
    }
}
