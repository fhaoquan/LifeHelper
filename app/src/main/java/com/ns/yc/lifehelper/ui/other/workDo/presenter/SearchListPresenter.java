package com.ns.yc.lifehelper.ui.other.workDo.presenter;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.ns.yc.lifehelper.api.Constant;
import com.ns.yc.lifehelper.ui.other.toDo.bean.TaskDetailEntity;
import com.ns.yc.lifehelper.ui.other.workDo.contract.SearchListContract;
import com.ns.yc.lifehelper.ui.other.workDo.data.DataDao;
import com.ns.yc.lifehelper.ui.other.workDo.ui.activity.WorkNewActivity;

import java.util.List;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/9/14
 * 描    述：干货集中营
 * 修订历史：
 * ================================================
 */
public class SearchListPresenter implements SearchListContract.Presenter {

    private SearchListContract.View mView;
    private CompositeSubscription mSubscriptions;
    private Context mContext;

    @Inject
    DataDao mDataDao;

    @Inject
    public SearchListPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void bindView(SearchListContract.View homeView) {
        this.mView = homeView;
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void onItemClick(int position, TaskDetailEntity entity) {
        Intent intent = new Intent(mContext, WorkNewActivity.class);
        intent.putExtra(Constant.INTENT_EXTRA_EDIT_TASK_DETAIL_ENTITY, entity.cloneObj());
        intent.putExtra(Constant.INTENT_EXTRA_MODE_OF_NEW_ACT, Constant.MODE_OF_NEW_ACT.MODE_EDIT);
        mView.finishActivity();
        mView.startActivityAndForResult(intent, Constant.EDIT_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void subscribe() {
        handleIntent(mView.intent());
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
        mContext = null;
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (TextUtils.isEmpty(query)) return;
            doSearch(query);
        }
    }

    private void doSearch(String query) {
        List<TaskDetailEntity> list = mDataDao.search(query);
        int size = list.size();
        mView.hideNoResults();
        if (size == 0) {
            mView.updateToolbarTitle("查找完成");
            mView.showNoResults();
            return;
        }
        mView.updateToolbarTitle("找到 " + size + " 条记录");
        mView.updateList(list);
    }



}
