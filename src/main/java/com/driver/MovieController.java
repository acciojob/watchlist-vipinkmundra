package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response = movieService.addMovie(movie);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String response = movieService.addDirector(director);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName,@RequestParam("directorName") String directoryName){
        String response = movieService.addMovieDirectorPair(movieName,directoryName);
        if(response.equals("Invalid Input.")){
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.CREATED);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){
        Movie response = movieService.getMovieByName(movieName);
        if(response == null){
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.FOUND);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String directorName){
        Director response = movieService.getDirectorByName(directorName);
        if(response == null){
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.FOUND);
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> response = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity(response,HttpStatus.FOUND);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String> response = movieService.findAllMovies();
        return new ResponseEntity(response,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName){
        String response = movieService.deleteDirectorByName(directorName);
        if(response.equals("Invalid directorName.")){
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String response = movieService.deleteAllDirectors();
        return new ResponseEntity(response,HttpStatus.FOUND);
    }


}
