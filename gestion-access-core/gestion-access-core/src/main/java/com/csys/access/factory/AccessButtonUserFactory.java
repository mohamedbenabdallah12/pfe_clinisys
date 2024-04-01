package com.csys.access.factory;

import com.csys.access.domain.AccessButtonUser;
import com.csys.access.domain.AccessButtonUserPK;
import com.csys.access.dto.AccessButtonDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccessButtonUserFactory {

    public static AccessButtonDTO accessbuttonuserToAccessButtonUserDTO(AccessButtonUser accessbuttonuser) {
        AccessButtonDTO accessbuttonuserDTO = new AccessButtonDTO();
        accessbuttonuserDTO.setCodeButton(accessbuttonuser.getAccessButtonUserPK().getCodeButton());
        accessbuttonuserDTO.setCodeModule(accessbuttonuser.getAccessButtonUserPK().getCodeModule());
        accessbuttonuserDTO.setVisible(accessbuttonuser.getVisible());
        accessbuttonuserDTO.setButton(ButtonFactory.lazybuttonToButtonDTO(accessbuttonuser.getButton()));
        accessbuttonuserDTO.setUserDTO(UserFactory.userToUserDTO(accessbuttonuser.getUser()));
        return accessbuttonuserDTO;
    }

    public static List<AccessButtonDTO> accessbuttonuserToAccessButtonUserDTOs(List<AccessButtonUser> accessbuttonusers) {
        List<AccessButtonDTO> accessbuttonusersDTO = new ArrayList<>();
        accessbuttonusers.forEach(x -> {
            accessbuttonusersDTO.add(accessbuttonuserToAccessButtonUserDTO(x));
        });
        return accessbuttonusersDTO;
    }
    
    
    public static AccessButtonUser accessbuttonuserDTOToAccessButtonUser(AccessButtonDTO accessbuttonuserDTO) {
    AccessButtonUser accessbuttonuser = new AccessButtonUser();
    AccessButtonUserPK accessButtonUserPK = new AccessButtonUserPK();
    accessButtonUserPK.setCodeButton(accessbuttonuserDTO.getCodeButton());
    accessButtonUserPK.setCodeModule(accessbuttonuserDTO.getCodeModule());
    accessbuttonuser.setAccessButtonUserPK(accessButtonUserPK);
    accessbuttonuser.setVisible(accessbuttonuserDTO.getVisible());
    accessbuttonuser.setButton(ButtonFactory.lazybuttonDTOToButton(accessbuttonuserDTO.getButton()));
    accessbuttonuser.setUser(UserFactory.userDTOToUser(accessbuttonuserDTO.getUserDTO()));
    return accessbuttonuser;
}

}
