package kr.co.finmodeun.admin.menu.service;

import com.project.cmn.util.tree.TreeMaker;
import kr.co.finmodeun.admin.menu.dto.CmsMenuInfoDto;
import kr.co.finmodeun.admin.menu.mapper.CmsMenuInfoMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.cmn.util.tree.TreeMaker.ROOT_MENU_ID;

@RequiredArgsConstructor
@Service
public class CmsMenuInfoService {
    private final CmsMenuInfoMapper cmsMenuInfoMapper;

    /**
     * 메뉴 목록을 Tree 형태로 조회한다.
     *
     * @param param 조회 조건
     * @return 메뉴 목록
     */
    public List<CmsMenuInfoDto> cmsMenuInfoRetrieveList(CmsMenuInfoDto param) {
        List<CmsMenuInfoDto> menuList = cmsMenuInfoMapper.selectCmsMenuInfoList(param);

        TreeMaker<CmsMenuInfoDto> treeMaker = new TreeMaker<>();

        return treeMaker.getList(menuList);
    }

    /**
     * 메뉴 정보를 등록한다.
     *
     * @param param 메뉴 정보
     * @return 등록한 row 수
     */
    public int cmsMenuInfoCreate(CmsMenuInfoDto param) {
        if (StringUtils.isBlank(param.getParentMenuId())) {
            param.setParentMenuId(ROOT_MENU_ID);
        }

        if (param.getMenuLevel() == null) {
            param.setMenuLevel(1);
        }

        if (param.getSortOrder() == null) {
            param.setSortOrder(1);
        }

        return cmsMenuInfoMapper.insertCmsMenuInfo(param);
    }

    /**
     * 메뉴 정보를 수정한다.
     *
     * @param param 메뉴 정보
     * @return 수정한 row 수
     */
    public int cmsMenuInfoModify(CmsMenuInfoDto param) {
        return cmsMenuInfoMapper.updateCmsMenuInfo(param);
    }
}