package pe.edu.i202220098.cl2_jpa_data_narro_frank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.FilmData;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.dto.FilmDetailDto;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.service.MaintenanceLanguage;
import pe.edu.i202220098.cl2_jpa_data_narro_frank.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final MaintenanceLanguage maintenanceLanguage;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService, MaintenanceLanguage maintenanceLanguage) {
        this.maintenanceService = maintenanceService;
        this.maintenanceLanguage = maintenanceLanguage;
    }


    @GetMapping("/start")
    public String start(Model model) {
        model.addAttribute("films", maintenanceService.findAllFilms());
        return "maintenance";
    }


    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("languages", maintenanceLanguage.findAllLanguages());
        return "maintenance-create";
    }


    @PostMapping("/createFilm")
    public String createFilm(@ModelAttribute FilmData filmData, Model model) {
        return handleFilmCreation(filmData, model);
    }


    private String handleFilmCreation(FilmData filmData, Model model) {
        try {
            maintenanceService.createFilm(filmData);
            return "redirect:/maintenance/start";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "maintenance-create";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Ocurrió un error inesperado al crear la película.");
            return "maintenance-create";
        }
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("film", maintenanceService.findDetailById(id));
        return "maintenance-detail";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("film", maintenanceService.findDetailById(id));
        return "maintenance-edit";
    }


    @PostMapping("/edit-confirm")
    public String editConfirm(@ModelAttribute FilmDetailDto film) {
        maintenanceService.updateFilm(film);
        return "redirect:/maintenance/start";
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        String message = deleteFilm(id) ? "Eliminación exitosa" : "No se encontró el valor o hubo un error";
        model.addAttribute("message", message);
        return "redirect:/maintenance/start";
    }


    private boolean deleteFilm(Integer id) {
        return maintenanceService.deleteFilm(id);
    }
}
