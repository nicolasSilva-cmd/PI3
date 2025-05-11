package br.univesp.pi3.service;

import br.univesp.pi3.exception.ValidationException;
import br.univesp.pi3.model.dto.OrgDTO;
import br.univesp.pi3.model.entity.OrgEntity;
import br.univesp.pi3.model.mapper.ClienteMapper;
import br.univesp.pi3.model.mapper.OrgMapper;
import br.univesp.pi3.repository.ClienteRepository;
import br.univesp.pi3.repository.OrgRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.univesp.pi3.fixture.ClienteFixture.getClienteEntity;
import static br.univesp.pi3.fixture.OrgFixture.getOrgDTO;
import static br.univesp.pi3.fixture.OrgFixture.getOrgEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @InjectMocks
    ReservaService reservaService;

    @Mock
    OrgRepository orgRepository;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    EmailService emailService;

    @Mock
    OrgMapper orgMapper;

    @Mock
    ClienteMapper clienteMapper;

    @Test
    void shouldSucessfullyCreateReserva() {

        when(orgRepository.findById(anyLong())).thenReturn(Optional.of(getOrgEntity()));
        when(orgMapper.toDto(getOrgEntity())).thenReturn(getOrgDTO());
        when(orgMapper.toEntity(getOrgDTO())).thenReturn(getOrgEntity());

        reservaService.createReserva(1l);

        verify(orgRepository, atLeastOnce()).save(any());
        verify(emailService, atLeastOnce()).sendMail(any(), any());
    }

    @Test
    void shouldSucessfullyCreateReservaWithEmptyClientList() {

        OrgDTO dto = getOrgDTO();
        dto.setClienteId(null);

        when(orgRepository.findById(anyLong())).thenReturn(Optional.of(getOrgEntity()));
        when(orgMapper.toDto(getOrgEntity())).thenReturn(dto);
        when(orgMapper.toEntity(dto)).thenReturn(getOrgEntity());

        reservaService.createReserva(1l);

        verify(orgRepository, atLeastOnce()).save(any());
        verify(emailService, atLeastOnce()).sendMail(any(), any());
    }

    @Test
    void shouldThrowExceptionWhenCreatingReserva() {

        when(orgRepository.findById(anyLong())).thenReturn(Optional.of(new OrgEntity()));

        assertThrows(ValidationException.class, () -> reservaService.createReserva(1l));

    }

    @Test
    void shouldSucessfullyDeleteAgendamento() {

        when(orgRepository.getReferenceById(anyLong())).thenReturn(getOrgEntity());
        when(clienteRepository.getReferenceById(anyLong())).thenReturn(getClienteEntity());
        when(clienteRepository.findAllByStatus(any())).thenReturn(List.of(getClienteEntity()));
        when(clienteRepository.findFirstByStatusAndOrg(any(), any())).thenReturn(getClienteEntity());

        reservaService.deleteAgendamento(1l, 1l);

        verify(orgRepository, atLeastOnce()).save(any());
        verify(clienteRepository, atLeastOnce()).delete(any());

    }

    @Test
    void shouldThrowExceptionWhenDeletingAgendamento() {

        when(orgRepository.getReferenceById(anyLong())).thenReturn(new OrgEntity());

        assertThrows(ValidationException.class, () -> reservaService.deleteAgendamento(1l,1l));

    }
}