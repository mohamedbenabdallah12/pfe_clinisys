package com.csys.access.factory;

import com.csys.access.domain.AccessButtonUser;
import com.csys.access.domain.Button;
import com.csys.access.domain.ButtonPK;
import com.csys.access.dto.AccessButtonDTO;
import com.csys.access.dto.ButtonDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ButtonFactory {

    public static ButtonDTO buttonToButtonDTO(Button button) {
        ButtonDTO buttonDTO = new ButtonDTO();
        buttonDTO.setCode(button.getButtonPK().getCode());
        buttonDTO.setCodeMenu(button.getCodeMenu());
        buttonDTO.setCodeModule(button.getButtonPK().getCodeModule());
        buttonDTO.setDesignation(button.getDesignation());
        buttonDTO.setLogo(button.getLogo());
        buttonDTO.setOrderButton(button.getOrderButton());
        Collection<AccessButtonDTO> accessButtonUserCollectionDtos = new ArrayList<>();
        button.getAccessButtonUserCollection().forEach(x -> {
            AccessButtonDTO accessbuttonuserDto = AccessButtonUserFactory.accessbuttonuserToAccessButtonUserDTO(x);
            accessButtonUserCollectionDtos.add(accessbuttonuserDto);
        });
        if (buttonDTO.getAccessButtonUserCollection() != null) {
            buttonDTO.getAccessButtonUserCollection().clear();
            buttonDTO.getAccessButtonUserCollection().addAll(accessButtonUserCollectionDtos);
        } else {
            buttonDTO.setAccessButtonUserCollection(accessButtonUserCollectionDtos);
        }      
        return buttonDTO;
    }

    public static Collection<ButtonDTO> buttonToButtonDTOs(Collection<Button> buttons) {
        List<ButtonDTO> buttonsDTO = new ArrayList<>();
        buttons.forEach(x -> {
            buttonsDTO.add(buttonToButtonDTO(x));
        });
        return buttonsDTO;
    }

    public static ButtonDTO lazybuttonToButtonDTO(Button button) {
        ButtonDTO buttonDTO = new ButtonDTO();
        buttonDTO.setCode(button.getButtonPK().getCode());
        buttonDTO.setCodeMenu(button.getCodeMenu());
        buttonDTO.setCodeModule(button.getButtonPK().getCodeModule());
        buttonDTO.setDesignation(button.getDesignation());
        buttonDTO.setLogo(button.getLogo());
        buttonDTO.setOrderButton(button.getOrderButton());
        return buttonDTO;
    }

    public static Collection<ButtonDTO> lazybuttonToButtonDTOs(Collection<Button> buttons) {
        List<ButtonDTO> buttonsDTO = new ArrayList<>();
        buttons.forEach(x -> {
            buttonsDTO.add(lazybuttonToButtonDTO(x));
        });
        return buttonsDTO;
    }

    
    
    
    
    public static Button buttonDTOToButton(ButtonDTO buttonDTO) {
    Button button = new Button();
    ButtonPK buttonPK = new ButtonPK();
    buttonPK.setCode(buttonDTO.getCode());
    buttonPK.setCodeModule(buttonDTO.getCodeModule());
    button.setButtonPK(buttonPK);
    button.setCodeMenu(buttonDTO.getCodeMenu());
    button.setDesignation(buttonDTO.getDesignation());
    button.setLogo(buttonDTO.getLogo());
    button.setOrderButton(buttonDTO.getOrderButton());

    // Conversion de la collection de AccessButtonDTO en collection de AccessButtonUser
    Collection<AccessButtonUser> accessButtonUserCollection = new ArrayList<>();
    buttonDTO.getAccessButtonUserCollection().forEach(x -> {
        AccessButtonUser accessButtonUser = AccessButtonUserFactory.accessbuttonuserDTOToAccessButtonUser(x);
        accessButtonUserCollection.add(accessButtonUser);
    });
    button.setAccessButtonUserCollection(accessButtonUserCollection);

    return button;
}
public static Button lazybuttonDTOToButton(ButtonDTO buttonDTO) {
    Button button = new Button();
    ButtonPK buttonPK = new ButtonPK();
    buttonPK.setCode(buttonDTO.getCode());
    buttonPK.setCodeModule(buttonDTO.getCodeModule());
    button.setButtonPK(buttonPK);
    button.setCodeMenu(buttonDTO.getCodeMenu());
    button.setDesignation(buttonDTO.getDesignation());
    button.setLogo(buttonDTO.getLogo());
    button.setOrderButton(buttonDTO.getOrderButton());
    return button;
}

}
