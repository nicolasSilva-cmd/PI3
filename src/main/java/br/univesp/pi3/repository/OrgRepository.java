package br.univesp.pi3.repository;

import br.univesp.pi3.model.entity.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository<OrgEntity,Long> {

    int countAllByNomeContainingAndAgendaContaining(String nome, String agenda);

}

