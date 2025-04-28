package br.univesp.pi3.model.dto;

import br.univesp.pi3.model.entity.OrgEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long clienteId;

    private String status;

    private String codAgendamento;

    @JsonIgnore // Ignora a serialização para evitar loop.
    private OrgEntity org;


}
