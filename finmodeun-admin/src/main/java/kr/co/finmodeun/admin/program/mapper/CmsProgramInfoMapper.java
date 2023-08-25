package kr.co.finmodeun.admin.program.mapper;

import kr.co.finmodeun.admin.program.dto.CmsProgramInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmsProgramInfoMapper {
    /**
     * 프로그램 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 프로그램 목록
     */
    List<CmsProgramInfoDto> selectCmsProgramInfoList(CmsProgramInfoDto param);

    /**
     * 프로그램 정보를 등록한다.
     *
     * @param param 프로그램 정보
     * @return 등록한 row 수
     */
    int insertCmsProgramInfo(CmsProgramInfoDto param);

    /**
     * 프로그램 정보를 수정한다.
     *
     * @param param 프로그램 정보
     * @return 수정한 row 수
     */
    int updateCmsProgramInfo(CmsProgramInfoDto param);
}