package br.univesp.pi3.model.mapper;

import br.univesp.pi3.model.dto.OrgDTO;
import br.univesp.pi3.model.entity.OrgEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="Spring")
public interface OrgMapper {

    OrgDTO toDto(OrgEntity entity);

    OrgEntity toEntity(OrgDTO dto);

    List<OrgDTO> toDtoList(List<OrgEntity> entityList);
}
