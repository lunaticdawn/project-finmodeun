package kr.co.finmodeun.admin.admin.service;

import com.github.pagehelper.PageHelper;
import com.project.cmn.http.exception.ServiceException;
import com.project.cmn.util.cipher.Sha;
import kr.co.finmodeun.admin.admin.dto.CmsAdminInfoDto;
import kr.co.finmodeun.admin.admin.mapper.CmsAdminInfoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CmsAdminInfoService {
    private final CmsAdminInfoMapper cmsAdminInfoMapper;

    /**
     * 어드민 목록을 조회한다.
     *
     * @param param 조회 조건
     * @return 어드민 목록
     */
    public List<CmsAdminInfoDto> adminInfoRetrieveList(CmsAdminInfoDto param) {
        if (param.getPageNum() != null && param.getPageNum() > 0) {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
        }

        return cmsAdminInfoMapper.selectCmsAdminInfoList(param);
    }

    /**
     * 어드민 상세를 조회한다.
     *
     * @param param 조회 조건
     * @return 어드민 상세
     */
    public CmsAdminInfoDto adminInfoRetrieve(CmsAdminInfoDto param) {
        return cmsAdminInfoMapper.selectCmsAdminInfo(param.getAdminId());
    }

    /**
     * 어드민 정보를 등록한다.
     *
     * @param param 어드민 정보
     * @return 등록한 row 수
     * @throws NoSuchAlgorithmException 어드민 비밀번호를 암호화 시 오류
     */
    public int adminInfoCreate(CmsAdminInfoDto param) throws NoSuchAlgorithmException {
        param.setAdminPwd(Sha.encrypt512(param.getAdminPwd()));

        return cmsAdminInfoMapper.insertCmsAdminInfo(param);
    }

    /**
     * 어드민 정보를 수정한다.
     *
     * @param param 어드민 정보
     * @return 수정한 row 수
     */
    public int adminInfoModify(CmsAdminInfoDto param) {
        return cmsAdminInfoMapper.updateCmsAdminInfo(param);
    }

    /**
     * 어드민 비밀번호를 수정한다.
     *
     * @param param 어드민 정보
     * @return 수정한 row 수
     * @throws NoSuchAlgorithmException 어드민 비밀번호를 암호화 시 오류
     * @throws ServiceException 비밀번호가 틀린 경우
     */
    public int adminPwdModify(CmsAdminInfoDto param) throws NoSuchAlgorithmException, ServiceException {
        CmsAdminInfoDto adminInfo = cmsAdminInfoMapper.selectCmsAdminInfo(param.getAdminId());
        String encOldAdminPwd = Sha.encrypt512(param.getOldAdminPwd());

        if (!StringUtils.equals(adminInfo.getAdminPwd(), encOldAdminPwd)) {
            throw new ServiceException("비밀번호가 틀립니다.");
        }

        param.setAdminPwd(Sha.encrypt512(param.getAdminPwd()));

        return cmsAdminInfoMapper.updateAdminPwd(param);
    }

    /**
     * 로그인을 처리한다.
     *
     * @param param 어드민 정보
     * @throws NoSuchAlgorithmException 어드민 비밀번호를 암호화 시 오류
     * @throws ServiceException 아이디 또는 비밀번호가 틀린 경우
     */
    public void login(CmsAdminInfoDto param) throws NoSuchAlgorithmException, ServiceException {
        CmsAdminInfoDto adminInfo = cmsAdminInfoMapper.selectCmsAdminInfo(param.getAdminId());

        if (adminInfo == null) {
            throw new ServiceException("아이디가 틀립니다.");
        }

        if (adminInfo.getLoginFailuerCnt() >= 5) {
            throw new ServiceException("비밀번호가 5회이상 틀렸습니다.");
        }

        String encAdminPwd = Sha.encrypt512(param.getAdminPwd());

        if (!StringUtils.equals(adminInfo.getAdminPwd(), encAdminPwd)) {
            cmsAdminInfoMapper.updateLoginFailuerCnt(param.getAdminId());

            throw new ServiceException("비밀번호가 틀립니다.");
        }

        cmsAdminInfoMapper.updateLoginDt(param.getAdminId());
    }
}