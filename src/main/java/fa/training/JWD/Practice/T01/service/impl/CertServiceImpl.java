package fa.training.JWD.Practice.T01.service.impl;

import fa.training.JWD.Practice.T01.models.Cert;
import fa.training.JWD.Practice.T01.models.dto.CertList;
import fa.training.JWD.Practice.T01.repositories.CertRepository;
import fa.training.JWD.Practice.T01.service.CertService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CertServiceImpl implements CertService {
    private final CertRepository certRepository;

    public CertServiceImpl(CertRepository certRepository) {
        this.certRepository = certRepository;
    }

    @Override
    public void createCert(Cert cert) {
        certRepository.save(cert);
    }

    @Override
    public void deleteCertById(String id) {
        certRepository.deleteById(id);
    }

    @Override
    public void updateCert(Cert cert) {
        Optional<Cert> optionalCert = certRepository.findById(cert.getId());
        if (optionalCert.isPresent()) {
            optionalCert.get().setCertName(cert.getCertName());
            optionalCert.get().setCost(cert.getCost());
            optionalCert.get().setCategory(cert.getCategory());
            certRepository.save(optionalCert.get());
        }
    }

    @Override
    public Optional<Cert> findCertByCertId(String certId) {
        return certRepository.findCertById(certId);
    }

    public Page<CertList> findAllCert(Pageable pageable) {
        return certRepository.findAllCertList(pageable);
    }

}
