package br.univesp.pi3.fixture;

import br.univesp.pi3.model.Status;
import br.univesp.pi3.model.dto.OrgDTO;
import br.univesp.pi3.model.dto.UpdateOrgDTO;
import br.univesp.pi3.model.entity.ClienteEntity;
import br.univesp.pi3.model.entity.OrgEntity;

import java.util.ArrayList;
import java.util.List;

import static br.univesp.pi3.fixture.ClienteFixture.getClienteEntity;

public class OrgFixture {

    public static OrgDTO getOrgDTO() {
        List<ClienteEntity> entities = new ArrayList<>();

        entities.add(getClienteEntity());

        return OrgDTO.builder()
                .orgId(1l)
                .nome("teste")
                .email("teste@teste.com")
                .agenda("2025-03-24")
                .clientId(entities)
                .vagasDisponiveis(10l)
                .VagasReservadas(0l)
                .build();
    }

    public static OrgEntity getOrgEntity() {
        List<ClienteEntity> entities = new ArrayList<>();

        ClienteEntity clienteEntity = ClienteEntity.builder()
                .clienteId(1l)
                .codAgendamento("agendado")
                .status(Status.AGENDADO.getStatus())
                .org(new OrgEntity())
                .build();

        entities.add(clienteEntity);

        return OrgEntity.builder()
                .orgId(1l)
                .nome("teste")
                .email("teste@teste.com")
                .agenda("2025-03-24")
                .clienteId(entities)
                .vagasDisponiveis(10l)
                .VagasReservadas(0l)
                .build();

    }

    public static UpdateOrgDTO getUpdateOrgDto() {
        return UpdateOrgDTO.builder()
                .nome("zazazaza")
                .email("teste12@teste.com")
                .vagasDisponiveis(10l)
                .VagasReservadas(0l)
                .build();
    }

}
