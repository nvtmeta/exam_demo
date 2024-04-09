package fa.training.JWD.Practice.T01.service;

import fa.training.JWD.Practice.T01.models.Cert;
import fa.training.JWD.Practice.T01.models.dto.CertList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CertService {
    public void createCert(Cert cert);

    //
    public void deleteCertById(String id);

    public void updateCert(Cert cert);

    //
    public Optional<Cert> findCertByCertId(String certId);

    public Page<CertList> findAllCert(Pageable pageable);

}
