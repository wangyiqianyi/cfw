package org.cfw.biz.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.cfw.biz.service.LoginService;
import org.cfw.biz.sys.dao.SysAccountMapper;
import org.cfw.biz.sys.dao.SysRoleModuleMapper;
import org.cfw.biz.sys.model.SysAccount;
import org.cfw.biz.sys.model.SysModuledef;
import org.cfw.biz.sys.model.SysRoleModule;
import org.cfw.biz.sys.model.SysRoleModuleExample;
import org.cfw.common.CachedVOUtil;
import org.cfw.common.Constants;
import org.cfw.common.enums.ModuleMaskEnum;
import org.cfw.common.vo.ModulePermitVO;

public class LoginServiceImpl implements LoginService {

    private SysAccountMapper    sysAccountMapper;
    private SysRoleModuleMapper sysRoleModuleMapper;

    public List<ModulePermitVO> selectSysRoleModuleList(short roleid) {
        SysRoleModuleExample example = new SysRoleModuleExample();
        SysRoleModuleExample.Criteria cr = example.createCriteria();
        cr.andRoleidEqualTo(roleid);
        List<SysRoleModule> roleModuleList = sysRoleModuleMapper.selectByExample(example);
        List<ModulePermitVO> moduleList = new LinkedList<ModulePermitVO>();
        for (SysRoleModule roleModule : roleModuleList) {
            ModulePermitVO vo = new ModulePermitVO();
            vo.setModuleid(roleModule.getModuleid());
            vo.setMask(roleModule.getMask());
            moduleList.add(vo);
        }
        return moduleList;
    }

    public List<ModulePermitVO> selectGuestModuleList() {
        List<ModulePermitVO> moduleList = new LinkedList<ModulePermitVO>();
        List<SysModuledef> moduledefList = CachedVOUtil.getModuledefList();
        for (SysModuledef moduledef : moduledefList) {
            if (Constants.GUEST_MASK == moduledef.getMask()) {
                ModulePermitVO vo = new ModulePermitVO();
                vo.setModuleid(moduledef.getModuleid());
                vo.setMask(ModuleMaskEnum.READ.getShortValue());
                moduleList.add(vo);
            }
        }
        return moduleList;
    }

    public SysAccount selectByAccount(String account) {
        return sysAccountMapper.selectByPrimaryKey(account);
    }

    public void setSysAccountMapper(SysAccountMapper sysAccountMapper) {
        this.sysAccountMapper = sysAccountMapper;
    }

    public void setSysRoleModuleMapper(SysRoleModuleMapper sysRoleModuleMapper) {
        this.sysRoleModuleMapper = sysRoleModuleMapper;
    }

}
