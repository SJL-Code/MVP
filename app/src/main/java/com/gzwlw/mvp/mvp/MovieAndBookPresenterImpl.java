package com.gzwlw.mvp.mvp;

import com.gzwlw.mvp.base.BasePresenterImpl;
import com.gzwlw.mvp.bean.Book;
import com.gzwlw.mvp.bean.Movie;
import com.gzwlw.mvp.bean.MovieAndBook;
import com.gzwlw.mvp.mvp.MovieAndBookContract.CallBack;
import com.gzwlw.mvp.mvp.MovieAndBookContract.MovieAndBookModel;
import com.gzwlw.mvp.mvp.MovieAndBookContract.MovieAndBookPresenter;
import com.gzwlw.mvp.mvp.MovieAndBookContract.MovieAndBookView;

public class MovieAndBookPresenterImpl extends BasePresenterImpl<MovieAndBookView> implements MovieAndBookPresenter, CallBack {

    /**
     * PresenterImpl 持有 View的接口引用
     */
    private MovieAndBookView mMovieAndBookView;

    /**
     * PresenterImpl 持有 Model的接口引用
     */
    private MovieAndBookModel mMovieAndBookModel;

    public MovieAndBookPresenterImpl(MovieAndBookView mMovieAndBookView) {
        super(mMovieAndBookView);

        this.mMovieAndBookView = getView(); // 调用父类的方法获取View的对象
        mMovieAndBookModel = new MovieAndBookModelImpl();
    }

    @Override
    public void getMovies(int start, int count) {
        mMovieAndBookModel.getMovie(start, count, this);
    }

    @Override
    public void getBooks(String name, String tag, int start, int count) {
        mMovieAndBookModel.getBook(name, tag, start, count, this);
    }

    @Override
    public void getMoviesAndBooks(int start1, int count1, String name, String tag, int start2, int count2) {
        mMovieAndBookModel.getMovieAndBook(start1, count1, name, tag, start2, count2, this);
    }

    @Override
    public void onRequestMovieSuccess(Movie data) {
        if (null != mMovieAndBookView) {
            mMovieAndBookView.onLoadMovieDataSuccess(data);
        }
    }

    @Override
    public void onRequestBookSuccess(Book data) {
        if (null != mMovieAndBookView) {
            mMovieAndBookView.onLoadBookDataSuccess(data);
        }
    }

    @Override
    public void onRequestMovieAndBookSuccess(MovieAndBook data) {
        if (null != mMovieAndBookView) {
            mMovieAndBookView.onLoadMovieAndBookDataSuccess(data);
        }
    }
}
