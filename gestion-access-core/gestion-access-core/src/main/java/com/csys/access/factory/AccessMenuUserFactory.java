package com.csys.access.factory;

import com.csys.access.domain.AccessMenuUser;
import com.csys.access.domain.AccessMenuUserPK;
import com.csys.access.dto.AccessMenuDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccessMenuUserFactory {

    public static AccessMenuDTO accessmenuuserToAccessMenuUserDTO(AccessMenuUser accessmenuuser) {
        AccessMenuDTO accessmenuuserDTO = new AccessMenuDTO();
        accessmenuuserDTO.setCodeMenu(accessmenuuser.getAccessMenuUserPK().getCodeMenu());
        accessmenuuserDTO.setCodeModule(accessmenuuser.getAccessMenuUserPK().getCodeModule());
        accessmenuuserDTO.setVisible(accessmenuuser.getVisible());
        accessmenuuserDTO.setMenu(MenuFactory.lazymenuToMenuDTO(accessmenuuser.getMenu()));
        accessmenuuserDTO.setUser(UserFactory.userToUserDTO(accessmenuuser.getUser()));
        return accessmenuuserDTO;
    }

    public static Collection<AccessMenuDTO> accessmenuuserToAccessMenuUserDTOs(Collection<AccessMenuUser> accessmenuusers) {
        List<AccessMenuDTO> accessmenuusersDTO = new ArrayList<>();
        accessmenuusers.forEach(x -> {
            accessmenuusersDTO.add(accessmenuuserToAccessMenuUserDTO(x));
        });
        return accessmenuusersDTO;
    }
    public static AccessMenuUser accessmenuuserDTOToAccessMenuUser(AccessMenuDTO accessmenuuserDTO) {
    AccessMenuUser accessmenuuser = new AccessMenuUser();
    AccessMenuUserPK accessMenuUserPK = new AccessMenuUserPK();
    accessMenuUserPK.setCodeMenu(accessmenuuserDTO.getCodeMenu());
    accessMenuUserPK.setCodeModule(accessmenuuserDTO.getCodeModule());
    accessmenuuser.setAccessMenuUserPK(accessMenuUserPK);
    accessmenuuser.setVisible(accessmenuuserDTO.getVisible());
    accessmenuuser.setMenu(MenuFactory.lazymenuDTOToMenu(accessmenuuserDTO.getMenu()));
    accessmenuuser.setUser(UserFactory.userDTOToUser(accessmenuuserDTO.getUser()));
    return accessmenuuser;
}

}
