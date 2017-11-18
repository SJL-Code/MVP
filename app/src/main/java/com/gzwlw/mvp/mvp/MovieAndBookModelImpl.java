package com.gzwlw.mvp.mvp;

import com.gzwlw.mvp.bean.Book;
import com.gzwlw.mvp.bean.Movie;
import com.gzwlw.mvp.bean.MovieAndBook;
import com.gzwlw.mvp.common.interfaces.ApiService;
import com.gzwlw.mvp.common.utils.RetrofitManagerUtil;
import com.gzwlw.mvp.mvp.MovieAndBookContract.CallBack;
import com.gzwlw.mvp.mvp.MovieAndBookContract.MovieAndBookModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class MovieAndBookModelImpl implements MovieAndBookModel {

    private ApiService apiService;

    public MovieAndBookModelImpl() {
        RetrofitManagerUtil instance = RetrofitManagerUtil.getInstance();
        apiService = instance.getApiService();
    }

    @Override
    public void getMovie(int start, int count, final CallBack callBack) {

        Observable<Movie> observable = apiService.getTopMovie(start, count);
        Observer<Movie> observer = new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {
                callBack.onRequestBefore(d);
            }

            @Override
            public void onNext(Movie value) {
                callBack.onRequestMovieSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                callBack.onRequestFailure(e);
            }

            @Override
            public void onComplete() {
                callBack.onRequestComplete();
            }
        };

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getBook(String name, String tag, int start, int count, final CallBack callBack) {

        Observable<Book> observable = apiService.getBook(name, tag, start, count);
        Observer<Book> observer = new Observer<Book>() {
            @Override
            public void onSubscribe(Disposable d) {
                callBack.onRequestBefore(d);
            }

            @Override
            public void onNext(Book value) {
                callBack.onRequestBookSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                callBack.onRequestFailure(e);
            }

            @Override
            public void onComplete() {
                callBack.onRequestComplete();
            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getMovieAndBook(int start1, int count1, String name, String tag, int start2, int count2, final CallBack callBack) {

        Observable<Movie> observable1 = apiService.getTopMovie(start1, count1);
        Observable<Book> observable2 = apiService.getBook(name, tag, start2, count2);

        Observable<MovieAndBook> zip = Observable.zip(observable1, observable2, new BiFunction<Movie, Book, MovieAndBook>() {
            @Override
            public MovieAndBook apply(Movie movie, Book book) throws Exception {
                return new MovieAndBook(movie, book);
            }
        });

        Observer<MovieAndBook> observer = new Observer<MovieAndBook>() {
            @Override
            public void onSubscribe(Disposable d) {
                callBack.onRequestBefore(d);
            }

            @Override
            public void onNext(MovieAndBook value) {
                callBack.onRequestMovieAndBookSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                callBack.onRequestFailure(e);
            }

            @Override
            public void onComplete() {
                callBack.onRequestComplete();
            }
        };

        zip.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
// java中规定,内部类只能访问外部类中的成员变量,不能访问外部类中的方法里面定义的局部变量
// 如果要访问方法中的变量,就要把方法中的变量声明为final（常量）的,因为这样可以使变量全局化,就相当于是在外部定义的而不是在方法里定义的
