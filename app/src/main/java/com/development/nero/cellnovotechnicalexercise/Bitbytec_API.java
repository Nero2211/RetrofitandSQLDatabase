package com.development.nero.cellnovotechnicalexercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Bitbytec_API {

    @GET("celltest/product/read.php")
    Call<List<MyPojo>> getProducts();
}
