package com.mixotc.abbs.home;
import com.mixotc.abbs.model.HomeModel;
import com.mixotc.abbs.model.HomeModel.ResultCallback;
import com.mixotc.abbs.home.bean.NewsSummaryBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.ArgumentCaptor.*;
import static org.mockito.Mockito.inOrder;

/**
 * @author : Sai
 * e-mail : xiaosai@mixotc.com
 * time   : 2018/07/11
 * class note : Home的Presenter测试类
 */
public class HomePresenterTest {
    private static long TEST_NEWS_ID = 1;
    private static String TEST_NEWS_TITLE = "test";
    private static String TEST_NEWS_READING_NUM = "1";
    private static String TEST_NEWS_COMMENT_NUM = "1";
    private static int TEST_NEWS_IMAGE = 1;

    @Mock
    private HomeModelImpl mHomeModel;
    @Mock
    private HomeContract.View mHomeView;
    @Captor
    private ArgumentCaptor<ResultCallback<NewsSummaryBean>> mLodeNewsCallbackCaptor;

    private NewsSummaryBean mNews =
            new NewsSummaryBean(TEST_NEWS_ID,
                    TEST_NEWS_TITLE,
                    TEST_NEWS_READING_NUM,
                    TEST_NEWS_COMMENT_NUM,
                    TEST_NEWS_IMAGE);
    private List<NewsSummaryBean> mNewsList;

    private HomePresenter mHomePresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mNewsList = Arrays.asList(mNews, mNews, mNews);
    }

    @Test
    public void setThePresenterToView() {
        mHomePresenter = new HomePresenter(mHomeView);
        Mockito.verify(mHomeView).setPresenter(mHomePresenter);
    }

    @Test
    public void loadInfoSuccess() {
        mHomePresenter = new HomePresenter(mHomeView);
        mHomePresenter.setHomeModel(mHomeModel);
        mHomePresenter.loadInfo();

        Mockito.verify(mHomeModel).loadHomeTopNewsData(mLodeNewsCallbackCaptor.capture());
        mLodeNewsCallbackCaptor.getValue().onResult(mNewsList);

        InOrder inOrder = inOrder(mHomeView);
        inOrder.verify(mHomeView).showHomeTopNews(mNewsList);

        ArgumentCaptor<List<NewsSummaryBean>> showNewsArgumentCaptor;
        showNewsArgumentCaptor = forClass(List.class);
        Mockito.verify(mHomeView).showHomeTopNews(showNewsArgumentCaptor.capture());
        assertTrue(showNewsArgumentCaptor.getValue().size() == 3);
    }
}