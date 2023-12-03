package mk.ukim.finki.mk.lab.web.controllers;

import mk.ukim.finki.mk.lab.service.DataMigrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DataMigrationService dataMigrationService;

    public AdminController(DataMigrationService dataMigrationService) {
        this.dataMigrationService = dataMigrationService;
    }

    @GetMapping("/migrate-data")
    public String migrateData() {
        dataMigrationService.migrateData();
        return "redirect:/movies";
    }
}
