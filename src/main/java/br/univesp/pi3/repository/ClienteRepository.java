package br.univesp.pi3.repository;

import br.univesp.pi3.model.entity.ClienteEntity;
import br.univesp.pi3.model.entity.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    ClienteEntity findFirstByStatusAndOrg(String status, OrgEntity org);

    List<ClienteEntity> findAllByStatus(String status);
}

