package dubaichamber.dreamintership.supplierApplication.controller;

import dubaichamber.dreamintership.supplierApplication.model.SupplierForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupplierFormController {
    @GetMapping("/supplier/form")
    public String showForm(Model model) {
        model.addAttribute("supplierForm", new SupplierForm());
        return "supplierForm";
    }
}
