package br.univesp.pi3.service;

import br.univesp.pi3.model.mapper.ClienteMapper;
import br.univesp.pi3.repository.OrgRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.univesp.pi3.fixture.ClienteFixture.getClienteDTO;
import static br.univesp.pi3.fixture.OrgFixture.getOrgEntity;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    ClienteService service;

    @Mock
    OrgRepository repository;

    @Mock
    ClienteMapper mapper;

    @Test
    void shouldGetAnEntity() {

        when(repository.getReferenceById(anyLong())).thenReturn(getOrgEntity());
        when(mapper.mapEntities(anyList())).thenReturn(List.of(getClienteDTO()));

        var cliente = service.getAllById(1l);

        Assertions.assertNotNull(cliente);

    }
}