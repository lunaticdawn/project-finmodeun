package kr.co.finmodeun.admin.program.service;

import kr.co.finmodeun.admin.program.dto.CmsProgramInfoDto;
import kr.co.finmodeun.admin.program.mapper.CmsProgramInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CmsProgramInfoService {
    private final CmsProgramInfoMapper cmsProgramInfoMapper;

    /**
     * 프로그램 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 프로그램 목록 조회
     */
    public List<CmsProgramInfoDto> cmsProgramInfoRetrieveList(CmsProgramInfoDto param) {
        return cmsProgramInfoMapper.selectCmsProgramInfoList(param);
    }

    /**
     * 프로그램 정보를 등록한다.
     *
     * @param param 프로그램 정보
     * @return 등록한 row 수
     */
    public int cmsProgramInfoCreate(CmsProgramInfoDto param) {
        return cmsProgramInfoMapper.insertCmsProgramInfo(param);
    }

    /**
     * 프로그램 정보를 수정한다.
     *
     * @param param 프로그램 정보
     * @return 수정한 row 수
     */
    public int cmsProgramInfoModify(CmsProgramInfoDto param) {
        return cmsProgramInfoMapper.updateCmsProgramInfo(param);
    }
}