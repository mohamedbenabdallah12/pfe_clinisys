package com.csys.access.factory;

import com.csys.access.domain.AccessMenuUser;
import com.csys.access.domain.Button;
import com.csys.access.domain.Menu;
import com.csys.access.domain.MenuPK;
import com.csys.access.dto.AccessMenuDTO;
import com.csys.access.dto.ButtonDTO;
import com.csys.access.dto.MenuDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MenuFactory {

    public static MenuDTO menuToMenuDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setCode(menu.getMenuPK().getCode());
        menuDTO.setCodeModule(menu.getMenuPK().getCodeModule());
        menuDTO.setDesignation(menu.getDesignation());
        menuDTO.setLogo(menu.getLogo());
        Collection<ButtonDTO> buttonCollectionDtos = new ArrayList<>();
        menu.getButtonCollection().forEach(x -> {
            ButtonDTO buttonDto = ButtonFactory.buttonToButtonDTO(x);
            buttonCollectionDtos.add(buttonDto);
        });
        if (menuDTO.getButtonCollection() != null) {
            menuDTO.getButtonCollection().clear();
            menuDTO.getButtonCollection().addAll(buttonCollectionDtos);
        } else {
            menuDTO.setButtonCollection(buttonCollectionDtos);
        }
        Collection<AccessMenuDTO> accessMenuUserCollectionDtos = new ArrayList<>();
        menu.getAccessMenuUserCollection().forEach(x -> {
            AccessMenuDTO accessmenuuserDto = AccessMenuUserFactory.accessmenuuserToAccessMenuUserDTO(x);
            accessMenuUserCollectionDtos.add(accessmenuuserDto);
        });
        if (menuDTO.getAccessMenuUserCollection() != null) {
            menuDTO.getAccessMenuUserCollection().clear();
            menuDTO.getAccessMenuUserCollection().addAll(accessMenuUserCollectionDtos);
        } else {
            menuDTO.setAccessMenuUserCollection(accessMenuUserCollectionDtos);
        }
//        Collection<MenuDTO> menuCollectionDtos = new ArrayList<>();
//        if (menuDTO.getMenuCollection() != null) {
//            menuDTO.getMenuCollection().clear();
//            menuDTO.getMenuCollection().addAll(menuCollectionDtos);
//        } else {
//            menuDTO.setMenuCollection(menuCollectionDtos);
//        }

         Collection<MenuDTO> menuCollectionDtos = new ArrayList<>();
        menu.getMenuCollection().forEach(subMenu -> {
        MenuDTO subMenuDTO = menuToMenuDTO(subMenu); // Appel récursif pour chaque sous-menu
        menuCollectionDtos.add(subMenuDTO);
    });
    menuDTO.setMenuCollection(menuCollectionDtos);
        return menuDTO;
    }

    public static Collection<MenuDTO> menuToMenuDTOs(Collection<Menu> menus) {
        List<MenuDTO> menusDTO = new ArrayList<>();
        menus.forEach(x -> {
            menusDTO.add(menuToMenuDTO(x));
        });
        return menusDTO;
    }

    public static MenuDTO lazymenuToMenuDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setCode(menu.getMenuPK().getCode());
        menuDTO.setCodeModule(menu.getMenuPK().getCodeModule());
        menuDTO.setDesignation(menu.getDesignation());
        menuDTO.setLogo(menu.getLogo());
        if (menu.getOrder() == null) {
            menuDTO.setOrderMenu(0);
        } else {
            menuDTO.setOrderMenu(menu.getOrder());
        }
        return menuDTO;
    }

    public static Collection<MenuDTO> lazymenuToMenuDTOs(Collection<Menu> menus) {
        List<MenuDTO> menusDTO = new ArrayList<>();
        menus.forEach(x -> {
            menusDTO.add(lazymenuToMenuDTO(x));
        });
        return menusDTO;
    }

    
    
    public static Menu menuDTOToMenu(MenuDTO menuDTO) {
    Menu menu = new Menu();
    MenuPK menuPK = new MenuPK();
    menuPK.setCode(menuDTO.getCode());
    menuPK.setCodeModule(menuDTO.getCodeModule());
    menu.setMenuPK(menuPK);
    menu.setDesignation(menuDTO.getDesignation());
    menu.setLogo(menuDTO.getLogo());
    menu.setOrder(menuDTO.getOrderMenu());
    menu.setMenuName(menuDTO.getMenuName());
    // Conversion de la collection de ButtonDTO en collection de Button
    Collection<Button> buttonCollection = new ArrayList<>();
    menuDTO.getButtonCollection().forEach(x -> {
        Button button = ButtonFactory.buttonDTOToButton(x);
        buttonCollection.add(button);
    });
    menu.setButtonCollection(buttonCollection);

    // Conversion de la collection de AccessMenuDTO en collection de AccessMenuUser
    Collection<AccessMenuUser> accessMenuUserCollection = new ArrayList<>();
    menuDTO.getAccessMenuUserCollection().forEach(x -> {
        AccessMenuUser accessMenuUser = AccessMenuUserFactory.accessmenuuserDTOToAccessMenuUser(x);
        accessMenuUserCollection.add(accessMenuUser);
    });
    menu.setAccessMenuUserCollection(accessMenuUserCollection);

    // Conversion de la collection de MenuDTO en collection de Menu
    Collection<Menu> menuCollection = new ArrayList<>();
    menuDTO.getMenuCollection().forEach(x -> {
        Menu childMenu = menuDTOToMenu(x); // Récursion pour les sous-menus
        menuCollection.add(childMenu);
    });
    menu.setMenuCollection(menuCollection);

    return menu;
}
public static Menu lazymenuDTOToMenu(MenuDTO menuDTO) {
    Menu menu = new Menu();
    MenuPK menuPK = new MenuPK();
    menuPK.setCode(menuDTO.getCode());
    menuPK.setCodeModule(menuDTO.getCodeModule());
    menu.setMenuPK(menuPK);
    menu.setDesignation(menuDTO.getDesignation());
    menu.setLogo(menuDTO.getLogo());
    menu.setOrder(menuDTO.getOrderMenu());
    return menu;
}

}
