package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dao.ProductDao;
import org.example.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/product/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductDao productDao;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("products",productDao.list());
        return "productList";
    }

    @PostMapping("add")
    public String add(@ModelAttribute ProductDto productDto){
        productDao.add(productDto);
        return "productList";
    }

    @PostMapping("update")
    public String update(@ModelAttribute ProductDto productDto){
        productDao.add(productDto);
        return "productList";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        productDao.delete(id);
        return "productList";
    }

}
