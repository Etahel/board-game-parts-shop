package pl.lodz.p.it.bges.inventory.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.inventory.criteria.BoardGameCriteria;
import pl.lodz.p.it.bges.inventory.criteria.ElementCriteria;
import pl.lodz.p.it.bges.inventory.dto.BoardGameDto;
import pl.lodz.p.it.bges.inventory.dto.ElementDto;
import pl.lodz.p.it.bges.inventory.dto.TagDto;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;
import pl.lodz.p.it.bges.inventory.service.BoardGameService;
import pl.lodz.p.it.bges.inventory.service.ElementService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Details.class)
    public ElementDto postElement(@RequestBody @JsonView(Views.Modify.class) @Valid ElementDto elementDto, @PathVariable Long id) throws InventoryException {
        return new ElementDto(elementService.createElement(id, elementDto));
    }

    @GetMapping("/{id}/elements")
    @PermitAll
    @JsonView(Views.List.class)
    public Page<ElementDto> getElements(@PathVariable Long id, Pageable pageable, ElementCriteria elementCriteria) {
        return elementService.getElements(id, pageable, elementCriteria).map(ElementDto::new);
    }

    @GetMapping
    @PermitAll
    @JsonView(Views.List.class)
    public Page<BoardGameDto> getBoardGames(Pageable pageable, BoardGameCriteria boardGameCriteria) {
        return boardGameService.getBoardGames(pageable, boardGameCriteria).map(BoardGameDto::new);
    }

    @PostMapping
    @RolesAllowed(Roles.EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.Details.class)
    public BoardGameDto postBoardGame(@RequestBody @JsonView(Views.Modify.class) @Valid BoardGameDto boardGameDto) throws InventoryException {
        return new BoardGameDto(boardGameService.createBoardGame(boardGameDto));
    }

    @GetMapping("/{id}")
    @PermitAll
    @JsonView(Views.Details.class)
    public BoardGameDto getBoardGame(@PathVariable Long id) throws InventoryException {
        return new BoardGameDto(boardGameService.getBoardGame(id));
    }


    @PutMapping("/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public void putBoardGame(@RequestBody @JsonView(Views.Modify.class) @Valid BoardGameDto boardGameDto, @PathVariable Long id) throws InventoryException {
        boardGameService.putBoardGame(boardGameDto, id);
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
    public void postTag(@RequestBody @JsonView(Views.Modify.class) @Valid TagDto tagDto) throws InventoryException {
        boardGameService.createTag(tagDto);
    }

    @PatchMapping(value = "/tags/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public void patchTag(@RequestBody @JsonView(Views.Modify.class) @Valid TagDto tagDto, @PathVariable Long id) throws InventoryException {
        boardGameService.updateTag(tagDto, id);
    }

    @GetMapping("/tags/{id}")
    @PermitAll
    @JsonView(Views.Details.class)
    public TagDto getTag(@PathVariable Long id) throws InventoryException {
        return new TagDto(boardGameService.getTag(id));
    }

    @DeleteMapping(value = "/tags/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    public void deleteTag(@PathVariable Long id) throws InventoryException {
        boardGameService.deleteTag(id);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    public void deleteBoardGame(@PathVariable Long id) throws InventoryException {
        boardGameService.deleteBoardGame(id);
    }
}
