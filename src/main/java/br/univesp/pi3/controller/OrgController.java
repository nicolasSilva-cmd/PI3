package br.univesp.pi3.controller;

import br.univesp.pi3.model.dto.OrgDTO;
import br.univesp.pi3.model.dto.UpdateOrgDTO;
import br.univesp.pi3.service.OrgService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org")
public class OrgController {

    @Autowired
    private OrgService service;

    @GetMapping
    public ResponseEntity<List<OrgDTO>> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrgDTO> getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<String> createOrg(@RequestBody @Valid OrgDTO dto) {
        return service.createOrg(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrgDTO> updateOrg(@PathVariable("id") Long id,
                                            @RequestBody UpdateOrgDTO updateDTO) {
        return service.updateOrg(id, updateDTO);
    }
}
