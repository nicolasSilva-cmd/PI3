package br.univesp.pi3.repository;

import br.univesp.pi3.model.entity.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrgRepository extends JpaRepository<OrgEntity,Long> {

    int countAllByNomeContainingAndAgendaContaining(String nome, String agenda);

    List<OrgEntity> findByNomeContainingIgnoreCase(String nome);
}

