package br.univesp.pi3.controller;

import br.univesp.pi3.model.dto.ClienteDTO;
import br.univesp.pi3.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping
    @RequestMapping("/{id}")
    public List<ClienteDTO> getClientesByOrg(@PathVariable("id") Long id) {
        return service.getAllById(id);
    }
}
