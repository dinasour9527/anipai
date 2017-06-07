package com.anipai.system.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.anipai.system.domain.SysRoleAuthority;

public class RoleAuthorityMapperProvider {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insertAll(Map map) {
		List<SysRoleAuthority> list = (List<SysRoleAuthority>) map.get("list");
		StringBuilder sb = new StringBuilder();
        sb.append("insert into sys_role_authority ");
        sb.append("(role_id, authority_id) ");
        sb.append("values ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].roleId}, #'{'list[{0}].authorityId})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
	}
}
