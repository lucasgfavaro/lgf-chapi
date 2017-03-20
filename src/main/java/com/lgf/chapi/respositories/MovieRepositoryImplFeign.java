package com.lgf.chapi.respositories;

import com.lgf.chapi.domain.ResponsePage;
import com.lgf.chapi.respositories.MovieResourceFeign;
import feign.Feign;
import feign.gson.GsonDecoder;

/**
 * Created by lucasfavaro on 3/18/17.
 */
public class MovieRepositoryImplFeign implements MovieResourceFeign {

    private static final String URI_MOVIE = "https://yts.ag/api";

    public ResponsePage getMovie(String movieName) {
            MovieResourceFeign movieResourceFeign = Feign.builder()
                .decoder(new GsonDecoder())
                .target(MovieResourceFeign.class, URI_MOVIE);

        return movieResourceFeign.getMovie(movieName);
    }
}
