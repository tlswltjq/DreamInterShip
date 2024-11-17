package dubaichamber.dreamintership.supplierApplication.controller;

import dubaichamber.dreamintership.supplierApplication.model.SupplierForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class SupplierFormController {
    @GetMapping("/supplier/form")
    public String showForm(Model model) {
        model.addAttribute("supplierForm", new SupplierForm());
        return "supplierForm";
    }
    @PostMapping("/supplier/form")
    public String submitForm(@ModelAttribute SupplierForm supplierForm, Model model) {
        // Handle form submission logic here
        log.info(supplierForm.toString());
        model.addAttribute("supplierForm", supplierForm);
        return "formSuccess";
    }
}
