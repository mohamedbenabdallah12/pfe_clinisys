package com.csys.access.web.rest;

import com.csys.access.domain.MenuPK;
import com.csys.access.dto.MenuDTO;
import com.csys.access.service.MenuService;
import com.csys.access.util.RestPreconditions;
import java.lang.String;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Menu.
 */
@RestController
@RequestMapping("/api")
public class MenuResource {
  private static final String ENTITY_NAME = "menu";

  private final MenuService menuService;

  private final Logger log = LoggerFactory.getLogger(MenuService.class);

  public MenuResource(MenuService menuService) {
    this.menuService=menuService;
  }

  @GetMapping("/menus/{id}")
  public ResponseEntity<MenuDTO> getMenu(@PathVariable MenuPK id) {
    log.debug("Request to get Menu: {}",id);
    MenuDTO dto = menuService.findOne(id);
    RestPreconditions.checkFound(dto, "menu.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/menus")
  public Collection<MenuDTO> getAllMenus() {
    log.debug("Request to get all  Menus : {}");
    return menuService.findAll();
  }
 @PostMapping("/menus")
    public ResponseEntity<MenuDTO> addMenu(@RequestBody MenuDTO menuDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
                    throw new MethodArgumentNotValidException(null, bindingResult);
                }

                MenuDTO result = menuService.save(menuDTO);
                return ResponseEntity.created(new URI("/api/menus/" + result.getCode() + "/" + result.getCodeModule())).body(result);
            }
}

