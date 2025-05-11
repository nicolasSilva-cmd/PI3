package br.univesp.pi3.service;

import br.univesp.pi3.exception.ValidationException;
import br.univesp.pi3.model.dto.OrgDTO;
import br.univesp.pi3.model.dto.UpdateOrgDTO;
import br.univesp.pi3.model.entity.OrgEntity;
import br.univesp.pi3.model.mapper.OrgMapper;
import br.univesp.pi3.repository.OrgRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.List;

import static br.univesp.pi3.fixture.OrgFixture.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrgServiceTest {

    @InjectMocks
    OrgService service;

    @Mock
    OrgRepository repository;

    @Mock
    OrgMapper mapper;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldCallListAllAtLeastOnce() {

        List<OrgDTO> dto = List.of(new OrgDTO());

        when(mapper.toDtoList(any())).thenReturn(dto);
        when(repository.findAll()).thenReturn(List.of(new OrgEntity()));

        service.listAll();

        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    void shouldReturnEntityWhenCallingGetById() {

        OrgEntity entity = OrgEntity.builder().build();

        when(repository.getReferenceById(anyLong())).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(OrgDTO.builder().build());

        Assertions.assertNotNull(service.getById(1l));

    }

    @Test
    void shouldThrowExceptionWhenEntityIsNotFound() {

        when(repository.getReferenceById(anyLong())).thenReturn(null);

        assertThrows(ValidationException.class, () -> service.getById(1l));

    }

    @Test
    void shouldSucessfullyCreateAnOrg() {

        OrgDTO dto = getOrgDTO();

        when(mapper.toEntity(dto)).thenReturn(getOrgEntity());

        service.createOrg(dto);

        verify(repository, atLeastOnce()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCreatingOrgWithAlreadyExistentOne() {

        when(repository.countAllByNomeContainingAndAgendaContaining(any(), any())).thenReturn(1);

        assertThrows(ValidationException.class, () -> service.createOrg(getOrgDTO()));

    }

    @Test
    void shouldThrowExceptionWhenCreatingOrgWithInvalidEmail() {

        OrgDTO dto = getOrgDTO();
        dto.setEmail("1234321");

        assertThrows(ValidationException.class, () -> service.createOrg(dto));

    }


    @Test
    void shouldSucessfullyUpdateOrg() {

        UpdateOrgDTO updateOrgDTO = getUpdateOrgDto();

        when(repository.getReferenceById(anyLong())).thenReturn(getOrgEntity());

        service.updateOrg(1l, updateOrgDTO);

        verify(repository, atLeastOnce()).save(any(OrgEntity.class));

    }

    @Test
    void shouldThrowExceptionWhenUpdate() {

        UpdateOrgDTO updateOrgDTO = getUpdateOrgDto();

        when(repository.getReferenceById(anyLong())).thenReturn(null);

        assertThrows(ValidationException.class, () -> service.updateOrg(1l, updateOrgDTO));

    }

    @Test
    void shouldFindNome() {

        when(repository.findByNomeContainingIgnoreCase(anyString())).thenReturn(List.of(getOrgEntity()));
        when(mapper.toDtoList(anyList())).thenReturn(List.of(getOrgDTO()));

        service.findByNome("teste");

        verify(repository, atLeastOnce()).findByNomeContainingIgnoreCase(anyString());
        verify(mapper, atLeastOnce()).toDtoList(anyList());
    }
}