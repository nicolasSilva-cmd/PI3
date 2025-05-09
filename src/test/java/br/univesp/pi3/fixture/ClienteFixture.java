package br.univesp.pi3.fixture;

import br.univesp.pi3.model.Status;
import br.univesp.pi3.model.dto.ClienteDTO;
import br.univesp.pi3.model.entity.ClienteEntity;

import static br.univesp.pi3.fixture.OrgFixture.getOrgEntity;

public class ClienteFixture {

    public static ClienteDTO getClienteDTO(){
           return ClienteDTO.builder()
                   .clienteId(1l)
                   .codAgendamento("agendado")
                   .status(Status.AGENDADO.getStatus())
                   .org(getOrgEntity())
                   .build();
    }

    public static ClienteEntity getClienteEntity() {
        return ClienteEntity.builder()
                .clienteId(1l)
                .codAgendamento("agendado")
                .status(Status.AGENDADO.getStatus())
                .org(getOrgEntity())
                .build();

    }


}
