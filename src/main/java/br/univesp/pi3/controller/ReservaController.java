package br.univesp.pi3.controller;

import br.univesp.pi3.model.dto.OrgDTO;
import br.univesp.pi3.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    ReservaService service;

    @PostMapping("/{id}")
    public OrgDTO createReserva(@PathVariable("id") Long id) { return service.createReserva(id);}

    @DeleteMapping("/{idOrg}/{idCliente}")
    public ResponseEntity<String> deleteReserva(@PathVariable("idOrg") Long idOrg,
                                                @PathVariable("idCliente") Long idCliente) {
        return service.deleteAgendamento(idOrg, idCliente);
    }
}
