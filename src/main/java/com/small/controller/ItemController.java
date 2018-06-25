package com.small.controller;


import com.small.domain.Item;
import com.small.service.ItemService;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping(value="/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * Item 도메인의 메인주소로 이동
     * @return Redirect to home url
     */
    @GetMapping
    public String index(){
        return "redirect:" + "/items/listing";
    }

    /**
     * Item Listing Page
     * @param model
     * @return Index page view
     */
    @GetMapping("/listing")
    public String listing(Model model) {
        return "item/listing";
    }


    /**
     * VIP (모델뷰로)
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/viewitem/{id}")
    public ModelAndView viewItem(@PathVariable long id,Map<String, Object> model) {

        Objects.requireNonNull(id, "ItemNo must not be null");

        Optional<Item> item = this.itemService.getItemById(id);
        model.put("item", item.get());

        return new ModelAndView("item/viewitem", model);
    }

}
