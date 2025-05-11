package br.univesp.pi3.model.mapper;

import br.univesp.pi3.model.dto.ClienteDTO;
import br.univesp.pi3.model.entity.ClienteEntity;
import org.mapstruct.Mapper;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteEntity map(ClienteDTO dto);

    ClienteDTO mapEntity(ClienteEntity entity);

    List<ClienteDTO> mapEntities(List<ClienteEntity> entity);
}
