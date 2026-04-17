package csd214.app.controllers;

import csd214.app.entities.MagazineEntity;
import csd214.app.repositories.MagazineRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/magazines")
public class MagazineController {

    private final MagazineRepository magazineRepository;

    // Constructor Injection
    public MagazineController(MagazineRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    // ==========================================
    // 1. READ (List all magazines)
    // ==========================================
    @GetMapping
    public String listMagazines(Model model) {
        model.addAttribute("magazines", magazineRepository.findAll());
        return "magazine-list";
    }

    // ==========================================
    // 2. CREATE (Show the empty form)
    // ==========================================
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        // We pack a blank, empty MagazineEntity into the model.
        // The Thymeleaf form will bind to this empty object!
        model.addAttribute("magazine", new MagazineEntity());
        return "magazine-form";
    }

    // ==========================================
    // 3. SAVE (Handles BOTH Create and Update!)
    // ==========================================
    @PostMapping("/save")
    public String saveMagazine(@ModelAttribute("magazine") MagazineEntity magazine) {
        // Because MagazineEntity inherits from ProductEntity, we sync the title to the parent's "name" field
        magazine.setName(magazine.getTitle());

        // If the magazine has no ID, JPA creates a new row (INSERT).
        // If the magazine already has an ID, JPA updates the existing row (UPDATE).
        magazineRepository.save(magazine);

        // "redirect:/" tells the browser to immediately make a new GET request to /magazines
        return "redirect:/magazines";
    }

    // ==========================================
    // 4. UPDATE (Show the form with existing data)
    // ==========================================
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        // Find the magazine by its ID, or return null if it doesn't exist
        MagazineEntity magazine = magazineRepository.findById(id).orElse(null);

        if (magazine != null) {
            model.addAttribute("magazine", magazine); // Pack the existing magazine into the model
            return "magazine-form"; // Send them to the form page
        }
        return "redirect:/magazines";
    }

    // ==========================================
    // 5. DELETE
    // ==========================================
    @GetMapping("/delete/{id}")
    public String deleteMagazine(@PathVariable("id") Long id) {
        magazineRepository.deleteById(id);
        return "redirect:/magazines";
    }
}