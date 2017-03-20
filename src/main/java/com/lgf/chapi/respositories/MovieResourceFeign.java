package com.lgf.chapi.respositories; /**
 * Created by lucasfavaro on 3/18/17.
 */

import com.lgf.chapi.domain.ResponsePage;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Accept: application/json")
public interface MovieResourceFeign {

    @RequestLine("GET /v2/list_movies.json?query_term={movieName}")
    @Headers("user-agent: bla")
    ResponsePage getMovie(@Param("movieName") String movieName);
}