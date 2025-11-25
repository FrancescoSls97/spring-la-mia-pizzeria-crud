package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.models.Pizze;
import org.lessons.java.spring_la_mia_pizzeria_crud.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzeController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Pizze> pizze = repository.findAll();
        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Pizze pizze = repository.findById(id).get();
        model.addAttribute("pizze", pizze);
        return "pizze/show";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Pizze> pizze = repository.findByName(name);
        model.addAttribute("pizze", pizze);
        return "pizze/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizze", new Pizze());
        return "pizze/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizze") Pizze formPizze, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizze/create";
        }
        // salvataggio dato
        repository.save(formPizze);
        return "redirect:/pizze";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizze", repository.findById(id).get());
        return "pizze/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizze") Pizze formPizze, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizze/edit";
        }
        // salvataggio dato
        repository.save(formPizze);
        return "redirect:/pizze";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        repository.deleteById(id);
        return "redirect:/pizze";
    }
}
