package fa.training.JWD.Practice.T01.repositories;

import fa.training.JWD.Practice.T01.models.Cert;
import fa.training.JWD.Practice.T01.models.dto.CertList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CertRepository extends JpaRepository<Cert, String> {
    @Query("""
            SELECT new fa.training.JWD.Practice.T01.models.dto.CertList(c.id, c.certName, c.cost, cat.name)\s
            FROM Cert c\s
            LEFT JOIN c.category cat\s
            ON cat.id = c.category.id
            """)
    Page<CertList> findAllCertList(Pageable pageable);

    Optional<Cert> findCertById(String id);

//    delete certificate
    void deleteCertById(String id);

}
