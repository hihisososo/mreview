package org.zerock.mreview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.mreview.dto.PageRequestDTO;

@Controller
@RequestMapping("/movie")
@Log4j2
public class MovieController {

    @GetMapping("/register")
    public void register() {
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("page", pageRequestDTO);
    }

    @GetMapping({"/read"})
    public void read(long mno, Model model) {
        log.info("mno:" + mno);
        model.addAttribute("mno", mno);
    }

}
