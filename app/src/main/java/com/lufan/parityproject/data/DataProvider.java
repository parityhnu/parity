package com.lufan.parityproject.data;

import com.lufan.parityproject.Callback;
import com.lufan.parityproject.Enum.ModifyType;
import com.lufan.parityproject.data.object.AttributeObject;
import com.lufan.parityproject.data.object.CommentReturnObject;
import com.lufan.parityproject.data.object.GoodsListObject;
import com.lufan.parityproject.data.object.ParityObject;
import com.lufan.parityproject.data.object.SearchObject;
import com.lufan.parityproject.data.object.UserObject;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DataProvider {

    private static volatile DataProvider mInstance;

    public static DataProvider getInstance() {
        if (mInstance == null) {
            synchronized (DataProvider.class) {
                if (mInstance == null) {
                    mInstance = new DataProvider();
                }
            }
        }
        return mInstance;
    }

    private Executor mExecutor;
    private ThreadPoolExecutor mThreadPoolExecutor;

    private DataProvider() {
        mExecutor = new Executor();
        mThreadPoolExecutor = getThreadPoolExecutor();
    }

    public void runInExecutor(Runnable command) {
        if (mExecutor == null) {
            return;
        }
        mExecutor.execute(command);
    }

    public void searchGood(final SearchObject searchObject, final Callback<GoodsListObject> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().searchGood(searchObject, callback);
            }
        });
    }

    public void register(final String account, final String password, final String phone, final Callback<UserObject> callback) {
            mExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    DataCenter.getInstance().register(account, password, phone, callback);
                }
            });
    }


    public void login(final String account, final String password, final Callback<UserObject> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().login(account, password, callback);
            }
        });
    }

    public void requestName(final Callback<String> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().requestName(callback);
            }
        });
    }

    public void requestPhone(final Callback<String> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().requestPhone(callback);
            }
        });
    }

    public void modify(final String s1, final String s2, final ModifyType modifyType, final Callback<String> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().modify(s1, s2, modifyType, callback);
            }
        });
    }

    public void forgetPassword(final String account, final String phone, final String passwrod, final Callback<String> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().forgetPassword(account, phone, passwrod, callback);
            }
        });
    }

    public void getComments(final List<String> ids, final String index, final Callback<CommentReturnObject> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().getComments(ids, index, callback);
            }
        });
    }

    public void getAttributes(final List<String> ids, final Callback<List<AttributeObject>> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().getAttributes(ids, callback);
            }
        });
    }

    public void favorite(final String id, final String keyword, final String sort, final boolean cancel, final Callback<String> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().favorite(id, keyword, sort, cancel, callback);
            }
        });
    }

    public void checkFavorite(final String id, final String keyword, final String sort, final Callback<String> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().checkFavorite(id, keyword, sort, callback);
            }
        });
    }

    public void getFavorite(final Callback<List<ParityObject>> callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                DataCenter.getInstance().getFavorites(callback);
            }
        });
    }

    private ThreadPoolExecutor getThreadPoolExecutor() {
        return new ThreadPoolExecutor(1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    private class Executor implements java.util.concurrent.Executor {
        @Override
        public void execute( Runnable command) {
            mThreadPoolExecutor.execute(command);
        }
    }
}
