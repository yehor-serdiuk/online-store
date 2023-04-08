package ua.volcaniccupcake.onlinestore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.volcaniccupcake.onlinestore.service.SneakersService;
import ua.volcaniccupcake.onlinestore.model.Sneakers;

import java.util.Optional;

@RestController
@RequestMapping("/sneakers/")
@RequiredArgsConstructor
@Tag(name = "Sneakers Controller", description = "This REST controller provides services to manage sneakers in the Online Store Application")
public class SneakersController {

    private final SneakersService sneakersService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all sneakers available in the application")
    public Iterable<Sneakers> getAllSneakers() {
        return sneakersService.getSneakers();
    }

    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides sneakers details for the supplied sneakers id")
    public Optional<Sneakers> getSneakersById(@PathVariable("id") long courseId) {
        return sneakersService.getSneakersById(courseId);
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates a new course in the application")
    public Sneakers createSneakers(@RequestBody Sneakers sneakers) {
        return sneakersService.createSneakers(sneakers);
    }

    @PutMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Updates a sneakers in the application for a supplied sneakers id")
    public void updateCourse(@PathVariable("id") long sneakersId, @RequestBody Sneakers sneakers) {
        sneakersService.updateSneakers(sneakersId, sneakers);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a sneakers in the application for a supplied sneakers id")
    void deleteSneakersById(@PathVariable("id") long sneakersId) {
        sneakersService.deleteSneakersById(sneakersId);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes all sneakers in the application")
    void deleteSneakers() {
        sneakersService.deleteSneakers();
    }
}
