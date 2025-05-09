package br.univesp.pi3.service;

import br.univesp.pi3.exception.ValidationException;
import br.univesp.pi3.model.dto.OrgDTO;
import br.univesp.pi3.model.dto.UpdateOrgDTO;
import br.univesp.pi3.model.entity.OrgEntity;
import br.univesp.pi3.model.mapper.OrgMapper;
import br.univesp.pi3.repository.OrgRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Objects;

@Service
@ApplicationScope
public class OrgService {

    @Autowired
    OrgRepository repository;

    @Autowired
    OrgMapper mapper;

    public ResponseEntity<List<OrgDTO>> listAll() {
        List<OrgDTO> list = mapper.toDtoList(repository.findAll());
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<OrgDTO> getById(Long id) {
        OrgEntity entity = repository.getReferenceById(id);
        if (entity != null) {
            return ResponseEntity.ok().body(mapper.toDto(entity));
        } else {
            throw new ValidationException("Organização não encontrada");
        }
    }

    public ResponseEntity<String> createOrg(OrgDTO dto) {
        validationSet(dto);
        repository.save(mapper.toEntity(dto));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<OrgDTO> updateOrg(Long id, UpdateOrgDTO dto) {
        OrgEntity entity = repository.getReferenceById(id);
        if (!Objects.isNull(entity)) {
            entity.setNome(dto.getNome());
            entity.setEmail(dto.getEmail());
            entity.setVagasDisponiveis(dto.getVagasDisponiveis());
            entity.setVagasReservadas(dto.getVagasReservadas());
            repository.save(entity);
            return ResponseEntity.ok(mapper.toDto(entity));
        } else {
            throw new ValidationException("Organização não encontrada");
        }
    }

    private void validationSet(OrgDTO dto) {
        if (repository.countAllByNomeContainingAndAgendaContaining(dto.getNome(), dto.getAgenda()) > 0) {
            throw new ValidationException("Deve existir somente uma organização com esse nome e período disponível.");
        } else if (!dto.getEmail().contains("@") && !dto.getEmail().contains(".com")) {
            throw new ValidationException("E-mail inválido. Insira um e-mail válido");
        }
    }
}
