package com.gzwlw.mvp.common.interfaces;

import com.gzwlw.mvp.bean.Book;
import com.gzwlw.mvp.bean.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.gzwlw.mvp.common.constants.HttpURLs.BOOK_URL;
import static com.gzwlw.mvp.common.constants.HttpURLs.MOVlE_URL;

public interface ApiService {
    /**
     * 豆瓣电影
     */
    @GET(MOVlE_URL)
    Observable<Movie> getTopMovie(@Query("start") int start,
                                  @Query("count") int count);

    /**
     * 豆瓣图书
     */
    @GET(BOOK_URL)
    Observable<Book> getBook(@Query("q") String name,
                             @Query("tag") String tag,
                             @Query("start") int start,
                             @Query("count") int count);
}
