package kr.co.finmodeun.admin.access.mapper;

import kr.co.finmodeun.admin.access.dto.CmsAdminAccessHistDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsAdminAccessHistMapper {
    /**
     * 접근 이력 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 접근 이력 목록
     */
    List<CmsAdminAccessHistDto> selectCmsAdminAccessHistList(CmsAdminAccessHistDto param);

    /**
     * 접근 이력 정보를 등록한다.
     *
     * @param param 접근 이력 정보
     * @return 등록한 row 수
     */
    int insertCmsAdminAccessHist(CmsAdminAccessHistDto param);
}
