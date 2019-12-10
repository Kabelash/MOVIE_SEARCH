package com.kabelash.moviessearch.model;

import java.util.ArrayList;

public class MovieDetails {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;
    ArrayList<Object> Ratings = new ArrayList<Object>();
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String Type;
    private String DVD;
    private String BoxOffice;
    private String Production;
    private String Website;
    private String Response;


    // Getter Methods
    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getActors() {
        return Actors;
    }

    public String getPoster() {
        return Poster;
    }

    public String getProduction() {
        return Production;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getWriter() {
        return Writer;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getType() {
        return Type;
    }

    public String getDVD() {
        return DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getWebsite() {
        return Website;
    }

    public String getResponse() {
        return Response;
    }

    // Setter Methods

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public void setRated(String Rated) {
        this.Rated = Rated;
    }

    public void setReleased(String Released) {
        this.Released = Released;
    }

    public void setRuntime(String Runtime) {
        this.Runtime = Runtime;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public void setDirector(String Director) {
        this.Director = Director;
    }

    public void setWriter(String Writer) {
        this.Writer = Writer;
    }

    public void setActors(String Actors) {
        this.Actors = Actors;
    }

    public void setPlot(String Plot) {
        this.Plot = Plot;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public void setAwards(String Awards) {
        this.Awards = Awards;
    }

    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    public void setMetascore(String Metascore) {
        this.Metascore = Metascore;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setDVD(String DVD) {
        this.DVD = DVD;
    }

    public void setBoxOffice(String BoxOffice) {
        this.BoxOffice = BoxOffice;
    }

    public void setProduction(String Production) {
        this.Production = Production;
    }

    public void setWebsite(String Website) {
        this.Website = Website;
    }

    public void setResponse(String Response) {
        this.Response = Response;
    }
}
