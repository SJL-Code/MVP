package com.gzwlw.mvp.mvp;

import com.gzwlw.mvp.base.BaseCallBack;
import com.gzwlw.mvp.base.BasePresenter;
import com.gzwlw.mvp.base.BaseView;
import com.gzwlw.mvp.bean.Book;
import com.gzwlw.mvp.bean.Movie;
import com.gzwlw.mvp.bean.MovieAndBook;

/**
 * 合约模式,将三个Model,View,Presenter接口以及CallBack接口放在一个Contract接口里面方便统一管理
 */
public interface MovieAndBookContract {

    interface MovieAndBookView extends BaseView {

        /**
         * 请求电影数据成功
         *
         * @param data 数据类型
         */
        void onLoadMovieDataSuccess(Movie data);

        /**
         * 请求书本数据成功
         *
         * @param data 数据类型
         */
        void onLoadBookDataSuccess(Book data);

        /**
         * 请求电影和书本数据成功
         *
         * @param data 数据类型
         */
        void onLoadMovieAndBookDataSuccess(MovieAndBook data);
    }

    interface MovieAndBookPresenter extends BasePresenter<MovieAndBookView> {

        /**
         * 获取豆瓣电影数据
         *
         * @param start 开始页
         * @param count 结束页
         */
        void getMovies(int start, int count);


        void getBooks(String name, String tag, int start, int count);

        void getMoviesAndBooks(int start1, int count1, String name, String tag, int start2, int count2);
    }

    interface MovieAndBookModel {

        void getMovie(int start, int count, CallBack callBack);

        void getBook(String name, String tag, int start, int count, CallBack callBack);

        void getMovieAndBook(int start1, int count1, String name, String tag, int start2, int count2, CallBack callBack);
    }

    interface CallBack extends BaseCallBack {

        /**
         * 请求电影数据成功
         *
         * @param data 根据业务返回相应的数据
         */
        void onRequestMovieSuccess(Movie data);

        /**
         * 请求书本数据成功
         *
         * @param data 根据业务返回相应的数据
         */
        void onRequestBookSuccess(Book data);

        /**
         * 请求电影和书本数据成功
         *
         * @param data 根据业务返回相应的数据
         */
        void onRequestMovieAndBookSuccess(MovieAndBook data);
    }
}
