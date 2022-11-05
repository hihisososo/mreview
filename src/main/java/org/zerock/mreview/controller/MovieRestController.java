package org.zerock.mreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.dto.PageResultDTO;
import org.zerock.mreview.service.MovieService;

@RestController
@RequestMapping("/movies")
@Log4j2
@RequiredArgsConstructor
public class MovieRestController {

    private final MovieService movieService;

    @GetMapping("")
    public ResponseEntity<PageResultDTO> list(PageRequestDTO requestDTO) {
        return new ResponseEntity<PageResultDTO>(movieService.getList(requestDTO), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> register(MovieDTO movieDTO) {
        Long mno = movieService.register(movieDTO);
        return new ResponseEntity<Long>(mno, HttpStatus.OK);
    }

    @GetMapping("/{mno}")
    public ResponseEntity<MovieDTO> get(@PathVariable long mno) {
        return new ResponseEntity<MovieDTO>(movieService.getMovie(mno), HttpStatus.OK);

    }

    @DeleteMapping("/{mno}")
    public ResponseEntity<Long> remove(@PathVariable long mno) {
        movieService.remove(mno);
        return new ResponseEntity(mno, HttpStatus.OK);
    }

}
