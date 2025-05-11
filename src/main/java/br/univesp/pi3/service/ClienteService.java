package br.univesp.pi3.service;

import br.univesp.pi3.model.dto.ClienteDTO;
import br.univesp.pi3.model.entity.OrgEntity;
import br.univesp.pi3.model.mapper.ClienteMapper;
import br.univesp.pi3.repository.ClienteRepository;
import br.univesp.pi3.repository.OrgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
@ApplicationScope
public class ClienteService {

    @Autowired
    OrgRepository orgRepository;

    @Autowired
    ClienteMapper mapper;

    public List<ClienteDTO> getAllById(Long id) {
        OrgEntity entity = orgRepository.getReferenceById(id);
        return mapper.mapEntities(entity.getClienteId());
    }
}
