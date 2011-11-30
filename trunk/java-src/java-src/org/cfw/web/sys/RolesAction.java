package org.cfw.web.sys;

import java.util.List;

import org.cfw.biz.sys.model.SysRole;
import org.cfw.biz.sys.service.RoleService;
import org.cfw.common.vo.MenuVO;
import org.cfw.web.common.BaseAction;

@SuppressWarnings("serial")
public class RolesAction extends BaseAction {

    private RoleService   roleService;
    private List<SysRole> roleList;
    private List<MenuVO>  menuList;

    public String init() {
        return SUCCESS;
    }

    public String query() {
        roleList = roleService.query();
        return SUCCESS;
    }

    public String menu() {
        menuList = roleService.menu();
        return SUCCESS;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public List<MenuVO> getMenuList() {
        return menuList;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

}