package kr.co.finmodeun.admin.menu.mapper;

import kr.co.finmodeun.admin.menu.dto.CmsMenuInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsMenuInfoMapper {
    /**
     * 메뉴 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 메뉴 목록
     */
    List<CmsMenuInfoDto> selectCmsMenuInfoList(CmsMenuInfoDto param);

    /**
     * 메뉴 정보를 등록한다.
     *
     * @param param 메뉴 정보
     * @return 등록한 row 수
     */
    int insertCmsMenuInfo(CmsMenuInfoDto param);

    /**
     * 메뉴 정보를 수정한다.
     *
     * @param param 메뉴 정보
     * @return 수정한 row 수
     */
    int updateCmsMenuInfo(CmsMenuInfoDto param);
}