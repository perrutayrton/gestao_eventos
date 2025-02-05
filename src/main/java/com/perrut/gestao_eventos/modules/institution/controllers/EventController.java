package com.perrut.gestao_eventos.modules.institution.controllers;

import com.perrut.gestao_eventos.modules.institution.dtos.CreateEventDTO;
import com.perrut.gestao_eventos.modules.institution.dtos.EventDTO;
import com.perrut.gestao_eventos.modules.institution.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;

@RestController
@RequestMapping("/event")
@Tag(name = "Eventos", description = "Informaçoes do evento")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    @Operation(summary = "Criar novo evento", description = "Responsavel por criar novos eventos")
    private ResponseEntity<Object> createEvent(@Valid @RequestBody CreateEventDTO createEventDTO) {

        try {

            return ResponseEntity.ok().body(eventService.createEvent(createEventDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @Operation(summary = "Retornar eventos cadastrados", description = "Responsavel por retornar todos os eventos")
    private ResponseEntity<Object> getAll() throws NameNotFoundException {

        try {
            return ResponseEntity.ok().body(this.eventService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/name")
    @Operation(summary = "Retornar eventos com filtro",
            description = "Responsavel por retornar uma lista de eventos de acordo com o filtro")
    private ResponseEntity<Object> getEventsWithFilter(@RequestParam String name) {

        try {
            return ResponseEntity.ok().body(this.eventService.getByEventsFilter(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar evento", description = "Responsavel por atualizar um evento")
    private ResponseEntity<Object> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {

        try {
            return ResponseEntity.ok().body(eventService.updateEvent(id, eventDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar evento", description = "Responsavel por deletar um evento especifico")
    private ResponseEntity<Void> deleteCompanyById(@PathVariable Long id)  {

        eventService.deleteEventById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/final-date")
    @Operation(summary = "Retornar eventos com data final no periodo informado em dias",
            description = "Responsavel por retornar uma lista de eventos com data final no periodo informado em dias")
    public ResponseEntity<Object> getByEventsInPeriod(@RequestParam int days) {

        try {
            return ResponseEntity.ok().body(this.eventService.getEventsWithFinalDateInPeriod(days));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
