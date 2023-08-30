package kr.co.finmodeun.admin.code.mapper;

import kr.co.finmodeun.admin.code.dto.CmnCodeInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmnCodeInfoMapper {
    /**
     * 코드 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 코드 목록
     */
    List<CmnCodeInfoDto> selectCmnCodeInfoList(CmnCodeInfoDto param);

    /**
     * 코드 상세를 조회한다.
     *
     * @param param 조회 조건
     * @return 코드 상세
     */
    CmnCodeInfoDto selectCmnCodeInfo(CmnCodeInfoDto param);

    /**
     * 코드를 등록한다.
     *
     * @param param 코드 내용
     * @return 등록한 row 수
     */
    int insertCmnCodeInfo(CmnCodeInfoDto param);

    /**
     * 코드를 수정한다.
     *
     * @param param 코드 내용
     * @return 수정한 row 수
     */
    int updateCmnCodeInfo(CmnCodeInfoDto param);
}