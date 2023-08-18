package kr.co.finmodeun.admin.code.service;

import kr.co.finmodeun.admin.code.dto.CmnCodeInfoDto;
import kr.co.finmodeun.admin.code.mapper.CmnCodeInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CmnCodeInfoService {
    private final CmnCodeInfoMapper cmnCodeInfoMapper;

    /**
     * 코드 목록 조회
     *
     * @param param 조회 조건
     * @return 코드 목록
     */
    public List<CmnCodeInfoDto> codeRetrieveList(CmnCodeInfoDto param) {
        return cmnCodeInfoMapper.selectCmnCodeInfoList(param);
    }

    /**
     * 코드 상세 조회
     *
     * @param param 조회 조건
     * @return 코드 상세
     */
    public CmnCodeInfoDto codeRetrieve(CmnCodeInfoDto param) {
        return cmnCodeInfoMapper.selectCmnCodeInfo(param);
    }

    /**
     * 코드 등록
     *
     * @param param 코드 내용
     * @return 등록한 row 수
     */
    public int codeCreate(CmnCodeInfoDto param) {
        return cmnCodeInfoMapper.insertCmnCodeInfo(param);
    }

    /**
     * 코드 수정
     *
     * @param param 코드 내용
     * @return 수정한 row 수
     */
    public int codeModify(CmnCodeInfoDto param) {
        return cmnCodeInfoMapper.updateCmnCodeInfo(param);
    }
}