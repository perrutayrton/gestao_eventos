package com.perrut.gestao_eventos.modules.institution.controllers;

import com.perrut.gestao_eventos.modules.institution.dtos.CreateInstitutionDTO;
import com.perrut.gestao_eventos.modules.institution.dtos.InstitutionDTO;
import com.perrut.gestao_eventos.modules.institution.entities.InstitutionEntity;
import com.perrut.gestao_eventos.modules.institution.services.InstitutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;

@RestController
@RequestMapping("/institution")
@Tag(name = "Instituição", description = "Informações da instituição")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @PostMapping("/create")
    @Operation(summary = "Criar nova instituição", description = "Responsavel por criar novas instituições")
    private ResponseEntity<Object> createInstitution(@Valid @RequestBody CreateInstitutionDTO createInstitutionDTO) {

        try {

            return ResponseEntity.ok().body(this.institutionService.createInstitution(createInstitutionDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @Operation(summary = "Retornar instituições cadastradas", description = "Responsavel por retornar todas as instituições cadastradas")
    private ResponseEntity<Object> getAll() throws NameNotFoundException {

        try {
            return ResponseEntity.ok().body(this.institutionService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/name")
    @Operation(summary = "Retornar instituição especifica", description = "Responsavel por retornar uma instituição especifica")
    private ResponseEntity<Object> getInstitution(@RequestParam String name) throws NameNotFoundException {

        try {
            return ResponseEntity.ok().body(this.institutionService.getByName(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar instituição", description = "Responsavel por atualizar uma instituição")
    private ResponseEntity<Object> updateInstitution(@PathVariable Long id, @RequestBody InstitutionDTO institutionDTO) {

        try {
            return ResponseEntity.ok().body(institutionService.updateInstitution(id, institutionDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar instituição", description = "Responsavel por deletar uma instituição especifica")
    private ResponseEntity<Object> deleteInstitutionById(@PathVariable Long id)  {

        try {
            return ResponseEntity.ok().body(institutionService.deleteInstitutionById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
