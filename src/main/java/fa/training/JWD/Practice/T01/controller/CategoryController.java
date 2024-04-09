package fa.training.JWD.Practice.T01.controller;

import fa.training.JWD.Practice.T01.service.CertService;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {

    private final CertService certService;

    public CategoryController(CertService certService) {
        this.certService = certService;
    }
}
