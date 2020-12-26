package pl.lodz.p.it.bges.inventory.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.inventory.dto.BoardGameDto;
import pl.lodz.p.it.bges.inventory.dto.ElementDto;
import pl.lodz.p.it.bges.inventory.dto.TagDto;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;
import pl.lodz.p.it.bges.inventory.service.BoardGameService;
import pl.lodz.p.it.bges.inventory.service.ElementService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/boardgames")
@RestController
public class BoardGameController {

    private ElementService elementService;
    private BoardGameService boardGameService;

    @Autowired
    public BoardGameController(ElementService elementService, BoardGameService boardGameService) {
        this.elementService = elementService;
        this.boardGameService = boardGameService;
    }

    @PostMapping("/{id}/elements")
    @RolesAllowed(Roles.EMPLOYEE)
    public void postElement(@RequestBody @JsonView(Views.Modify.class) ElementDto elementDto, @PathVariable Long id) throws InventoryException {
        elementService.createElement(id, elementDto);
    }

    @GetMapping
    @PermitAll
    public Page<BoardGameDto>()

    @PostMapping
    @RolesAllowed(Roles.EMPLOYEE)
    public void postBoardGame() {

    }

    @GetMapping("/tags")
    @PermitAll
    @JsonView(Views.List.class)
    public List<TagDto> getTags() {
        return boardGameService.getTags().stream().map(TagDto::new).collect(Collectors.toList());
    }

    @PostMapping("/tags")
    @RolesAllowed(Roles.EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public void postTags(@JsonView(Views.Modify.class) TagDto tagDto) throws InventoryException {
        boardGameService.createTag(tagDto);
    }

    @PatchMapping("/tags/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
}
