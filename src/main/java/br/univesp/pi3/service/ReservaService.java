package br.univesp.pi3.service;

import br.univesp.pi3.exception.ValidationException;
import br.univesp.pi3.model.Status;
import br.univesp.pi3.model.dto.ClienteDTO;
import br.univesp.pi3.model.dto.OrgDTO;
import br.univesp.pi3.model.entity.ClienteEntity;
import br.univesp.pi3.model.entity.OrgEntity;
import br.univesp.pi3.model.mapper.ClienteMapper;
import br.univesp.pi3.model.mapper.OrgMapper;
import br.univesp.pi3.repository.ClienteRepository;
import br.univesp.pi3.repository.OrgRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ReservaService {

    @Autowired
    OrgRepository orgRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    OrgMapper orgMapper;

    @Autowired
    ClienteMapper clienteMapper;

    @Transactional
    public OrgDTO createReserva(Long id) {
        Optional<OrgEntity> orgEntityOptional = orgRepository.findById(id);
        OrgDTO orgDTO = (orgEntityOptional.isPresent()) ? orgMapper.toDto(orgEntityOptional.get()) : null;

        if (!Objects.isNull(orgDTO)) {
            ClienteDTO clienteDTO = createCliente(orgDTO);

            orgDTO.getClientId().add(clienteMapper.map(clienteDTO));
            if (orgDTO.getVagasDisponiveis() != 0) {
                orgDTO.setVagasDisponiveis(orgDTO.getVagasDisponiveis() - 1);
                orgDTO.setVagasReservadas(orgDTO.getVagasReservadas() + 1);
            }
            orgRepository.save(orgMapper.toEntity(orgDTO));

            emailService.sendMail(orgDTO, clienteDTO);

            return orgDTO;
        }
        throw new ValidationException("Org não encontrada.");
    }

    public ResponseEntity<String> deleteAgendamento(Long orgId, Long clienteId) {
        ClienteEntity clienteEntity = clienteRepository.getReferenceById(clienteId);
        OrgEntity orgEntity = orgRepository.getReferenceById(orgId);
        if (!Objects.isNull(clienteEntity) && !Objects.isNull(orgEntity)) {
            orgEntity.getClienteId().remove(clienteEntity);
            orgEntity.setVagasDisponiveis(orgEntity.getVagasDisponiveis() + 1);
            orgEntity.setVagasReservadas(orgEntity.getVagasReservadas() - 1);

            orgRepository.save(orgEntity);

            if (!clienteRepository.findAllByStatus(Status.AGUARDANDO.getStatus()).isEmpty()) {
                log.info("[DELETE AGENDAMENTO] - Agendando para o primeiro da fila");
                ClienteDTO dto = queue(orgMapper.toDto(orgEntity));
                orgEntity.setVagasDisponiveis(orgEntity.getVagasDisponiveis() - 1);
                orgEntity.setVagasReservadas(orgEntity.getVagasReservadas() + 1);
                orgEntity.getClienteId().add(clienteMapper.map(dto));
                orgRepository.save(orgEntity);
            }

            clienteRepository.delete(clienteEntity);

            return ResponseEntity.noContent().build();
        }
        throw new ValidationException("Cliente ou Org não encontrado.");
    }

    private ClienteDTO createCliente(OrgDTO orgDTO) {
        ClienteDTO dto = new ClienteDTO();
        dto.setCodAgendamento(UUID.randomUUID().toString());
        if (orgDTO.getVagasDisponiveis() > 0) {
            dto.setStatus(Status.AGENDADO.getStatus());
        } else {
            dto.setStatus(Status.AGUARDANDO.getStatus());
        }
        dto.setOrg(orgMapper.toEntity(orgDTO));
        ClienteEntity entity = clienteRepository.save(clienteMapper.map(dto));
        return clienteMapper.mapEntity(entity);
    }

    private ClienteDTO queue(OrgDTO dto) {
        ClienteEntity entity = clienteRepository.findFirstByStatusAndOrg(Status.AGUARDANDO.getStatus(), orgMapper.toEntity(dto));
        if (!Objects.isNull(entity)) {
            entity.setStatus(Status.AGENDADO.getStatus());
            clienteRepository.save(entity);
        }
        return clienteMapper.mapEntity(entity);
    }
}
