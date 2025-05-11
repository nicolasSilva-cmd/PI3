package br.univesp.pi3.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "org_table")
public class OrgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orgId;

    private String nome;

    private String email;

    private String agenda;

    private Long vagasDisponiveis;

    private Long vagasReservadas;

    @OneToMany(mappedBy = "org")
    @JsonManagedReference
    private List<ClienteEntity> clienteId;

}
