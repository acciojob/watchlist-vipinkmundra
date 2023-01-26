package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String,Movie> db1 = new HashMap<>();
    Map<String,Director> db2 = new HashMap<>();
    Map<String,List<String>> db3 = new HashMap<>();
    HashSet<String> hs = new HashSet<>();

    public String addMovie(Movie movie){
        String movieName = movie.getName();
        hs.add(movieName);
        db1.put(movieName,movie);
        return "Movie added Successfully. ";
    }
    public String addDirector(Director director){
        String directorName = director.getName();
        db2.put(directorName,director);
        return "Director Added Successfully. ";
    }
    public String addMovieDirectorPair(String movieName,String directorName){
        if(db1.containsKey(movieName) && db2.containsKey(directorName)){
            if(!db3.containsKey(directorName)){
                List<String> temp = new ArrayList<>();
                temp.add(movieName);
                db3.put(directorName,temp);
            }else{
                List<String> temp = db3.get(directorName);
                temp.add(movieName);
                db3.put(directorName,temp);
            }
            return "Pair added Successfullly.";
        }
        return "Invalid Input.";
    }
    public Movie getMovieByName(String movieName){
        if(!db1.containsKey(movieName)){
            return null;
        }
        return db1.get(movieName);
    }
    public  Director getDirectorByName(String directorName){
        if(!db2.containsKey(directorName)){
            return null;
        }
        return db2.get(directorName);
    }
    public List<String> getMoviesByDirectorName(String directorName){
        List<String> movieList = new ArrayList<>();
        if(!db3.containsKey(directorName)){
            return movieList;
        }
        movieList = db3.get(directorName);
        return movieList;
    }
    public List<String> findAllMovies(){
        return new ArrayList<>(hs);
    }
    public String deleteDirectorByName(String directorName){
        if(!db3.containsKey(directorName)){
            return "Invalid directorName.";
        }
        List<String> movieList = db3.get(directorName);
        for(String movieName : movieList){
            db1.remove(movieName);
            hs.remove(movieName);
        }
        db3.remove(directorName);
        db2.remove(directorName);
        return "Movies deleted Successfully.";
    }
    public String deleteAllDirectors(){
        for(String directorName : db3.keySet()){
            List<String> movieList = db3.get(directorName);
            for(String movieName : movieList){
                hs.remove(movieName);
                db1.remove(movieName);
            }
        }
        db3.clear();;
        return "All Movies Deleted Successfully.";
    }
}
