package br.univesp.pi3.model.dto;

import br.univesp.pi3.model.entity.ClienteEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgDTO {

    private Long orgId;

    @NotBlank(message = "campo-obrigatório-não-preenchido")
    private String nome;

    @NotBlank(message = "campo-obrigatório-não-preenchido")
    private String email;

    @NotBlank(message = "campo-obrigatório-não-preenchido")
    private String agenda;

    private Long vagasDisponiveis;

    private Long vagasReservadas;

    private List<ClienteEntity> clienteId;
}
