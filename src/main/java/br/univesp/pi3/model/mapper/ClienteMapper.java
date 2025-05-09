package br.univesp.pi3.model.mapper;

import br.univesp.pi3.model.dto.ClienteDTO;
import br.univesp.pi3.model.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteEntity map(ClienteDTO dto);

    ClienteDTO mapEntity(ClienteEntity entity);
}
