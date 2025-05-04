package br.univesp.pi3.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrgDTO {

    private String nome;

    private String email;

    private Long vagasDisponiveis;

    private Long VagasReservadas;
}
