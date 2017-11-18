package com.gzwlw.mvp.mvp;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gzwlw.mvp.R;
import com.gzwlw.mvp.base.BaseActivity;
import com.gzwlw.mvp.bean.Book;
import com.gzwlw.mvp.bean.Movie;
import com.gzwlw.mvp.bean.MovieAndBook;
import com.gzwlw.mvp.common.utils.ExceptionUtil;
import com.gzwlw.mvp.mvp.MovieAndBookContract.MovieAndBookPresenter;
import com.gzwlw.mvp.mvp.MovieAndBookContract.MovieAndBookView;

public class MainActivity extends BaseActivity implements MovieAndBookView, View.OnClickListener {

    private String TAG = "MainActivity";

    private Button mMovieButton;
    private Button mBookButton;
    private Button mMovieAndBookZipButton;
    private ProgressDialog mProgressDialog;

    /**
     * View 持有 Presenter的接口引用
     * <p>
     * PresenterImpl 作用为触发加载数据
     */
    private MovieAndBookPresenter mMovieAndBookPresenter;

    @Override
    protected int setLayouts() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mMovieButton = (Button) findViewById(R.id.movieButton);
        mBookButton = (Button) findViewById(R.id.bookButton);
        mMovieAndBookZipButton = (Button) findViewById(R.id.movieAndBookZipButton);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("加载中...");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mMovieAndBookPresenter = new MovieAndBookPresenterImpl(this);

        mMovieButton.setOnClickListener(this);
        mBookButton.setOnClickListener(this);
        mMovieAndBookZipButton.setOnClickListener(this);
    }

    @Override
    public void netWorkStateNotify(Object object) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.movieButton) {
            mMovieAndBookPresenter.getMovies(0, 2);
        }

        if (id == R.id.bookButton) {
            mMovieAndBookPresenter.getBooks("西游记", null, 0, 1);
        }

        if (id == R.id.movieAndBookZipButton) {
            mMovieAndBookPresenter.getMoviesAndBooks(0, 2, "西游记", null, 0, 1);
        }
    }

    @Override
    public void onShowLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void onHideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onLoadMovieDataSuccess(Movie data) {
        Log.e(TAG, "Movie_Json: " + new Gson().toJson(data));
    }

    @Override
    public void onLoadBookDataSuccess(Book data) {
        Log.e(TAG, "Book_Json: " + new Gson().toJson(data));
    }

    @Override
    public void onLoadMovieAndBookDataSuccess(MovieAndBook data) {
        Log.e(TAG, "Movie-Json: " + new Gson().toJson(data.getMovie()));
        Log.e(TAG, "Book-Json: " + new Gson().toJson(data.getBook()));
    }

    @Override
    public void onLoadDataFailure(Throwable throwable) {
        Log.e(TAG, "错误: " + throwable);
        Toast.makeText(this, ExceptionUtil.handleException(throwable), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mMovieAndBookPresenter) {
            mMovieAndBookPresenter.onDetachView();
            mMovieAndBookPresenter = null;
        }
    }
}
