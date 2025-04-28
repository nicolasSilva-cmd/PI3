package br.univesp.pi3.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente_table")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clienteId;

    private String status;

    @Column(name = "cod_agendamento")
    private String codAgendamento;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "orgId")
    private OrgEntity org;
}
