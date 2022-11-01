package org.zerock.mreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.service.MovieService;
import org.zerock.mreview.service.ReviewService;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

  private final MovieService movieService;

  private final ReviewService reviewService;

  @GetMapping("/register")
  public void register() {

  }

  /*@PostMapping("/register")
  public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {

    log.info("movieDTO:" + movieDTO);

    Long mno = movieService.register(movieDTO);

    redirectAttributes.addFlashAttribute("msg", mno);

    return "redirect:/movie/list";
  }*/

  @GetMapping("/list")
  public void list(PageRequestDTO pageRequestDTO, Model model){
    model.addAttribute("page", pageRequestDTO);
  }

  @GetMapping({"/read", "/modify"})
  public void read(long mno, Model model){
    log.info("mno:" + mno);
    model.addAttribute("mno", mno);
  }

  @PostMapping("/remove")
  public String remove(long mno) {

    log.info("mno:" + mno);

     movieService.remove(mno);

    return "redirect:/movie/list";
  }


}
