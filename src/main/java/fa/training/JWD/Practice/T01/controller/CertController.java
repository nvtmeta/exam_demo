package fa.training.JWD.Practice.T01.controller;

import fa.training.JWD.Practice.T01.models.Category;
import fa.training.JWD.Practice.T01.models.Cert;
import fa.training.JWD.Practice.T01.models.dto.CertForm;
import fa.training.JWD.Practice.T01.models.dto.CertList;
import fa.training.JWD.Practice.T01.models.dto.SelectOptionDto;
import fa.training.JWD.Practice.T01.service.CategoryService;
import fa.training.JWD.Practice.T01.service.CertService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CertController {

    private final CertService certService;
    private final CategoryService categoryService;

    public CertController(CertService certService, CategoryService categoryService) {
        this.certService = certService;
        this.categoryService = categoryService;
    }

    @GetMapping("/certs")
    public String getCertList(@PageableDefault(page = 0, size = 5) Pageable pageable,
                              Model model) {
        Page<CertList> certList = certService.findAllCert(pageable);
        model.addAttribute("certList", certList);
        CertForm certFormData = new CertForm();
        model.addAttribute("certFormData", certFormData);
        prepareMasterData(model);
        return "cert";
    }

    @PostMapping("/certs")
    public String addCert(@ModelAttribute("certFormData") @Valid CertForm certFormData,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "cert";
        }

//        validate unique certId
        Optional<Cert> certFind = certService.findCertByCertId(certFormData.getId());
        if (certFind.isPresent()) {
            bindingResult.rejectValue("id", "error.cert.id.exists");
            return "cert";
        }

        Cert cert = new Cert();
        BeanUtils.copyProperties(certFormData, cert);

        Category category = null;
        if (certFormData.getCategoryId() != null) {
            Optional<Category> categoryOptional = categoryService.getCategoryById(certFormData.getCategoryId());
            if (categoryOptional.isPresent()) {
                category = categoryOptional.get();
            }
        }
        cert.setCategory(category);

        certService.createCert(cert);

        return "redirect:/certs?success=add";
    }

    @GetMapping("/certs/update/{id}")
    public String getCertList(@PageableDefault(page = 0, size = 3) Pageable pageable,
                              @PathVariable("id") String id,
                              Model model) {
        Page<CertList> certList = certService.findAllCert(pageable);
        model.addAttribute("certList", certList);

        CertForm certFormData = new CertForm();
        Optional<Cert> cert = certService.findCertByCertId(id);
        cert.ifPresent(value -> BeanUtils.copyProperties(value, certFormData));

        model.addAttribute("certFormData", certFormData);
        model.addAttribute("id", id);
        prepareMasterData(model);
        return "cert";
    }

    @PostMapping("/certs/update/{id}")
    public String updateCert(@ModelAttribute("certFormData") @Valid CertForm certFormData, BindingResult bindingResult) {
        System.out.println("certFormData: " + certFormData);
        if (bindingResult.hasErrors()) {
            return "cert";
        }
        Cert cert = new Cert();
        Category category = null;
        if (certFormData.getCategoryId() != null) {
            Optional<Category> categoryOptional = categoryService.getCategoryById(certFormData.getCategoryId());
            if (categoryOptional.isPresent()) {
                System.out.println("categoryOptional: " + categoryOptional.get());
                category = categoryOptional.get();
            }
        } else {
            Optional<Cert> certOptional = certService.findCertByCertId(certFormData.getId());
            if (certOptional.isPresent()) {
                System.out.println("certOptional: " + certOptional.get());
                category = certOptional.get().getCategory();
            }
        }

        BeanUtils.copyProperties(certFormData, cert);
        cert.setCategory(category);
        certService.updateCert(cert);
        return "redirect:/certs?success=update";
    }

    @GetMapping("/certs/delete/{id}")
    public String deleteCert(@PathVariable("id") String id) {
        certService.deleteCertById(id);
        return "redirect:/certs?success=delete";
    }

    public void prepareMasterData(Model model) {
        List<SelectOptionDto> selectOptionDtoList = categoryService.findAllCategory()
                .stream()
                .map(category -> new SelectOptionDto(category.getId().toString(), category.getName(), category.getCerts().size()))
                .toList();

        model.addAttribute("categoryData", selectOptionDtoList);

    }
}
