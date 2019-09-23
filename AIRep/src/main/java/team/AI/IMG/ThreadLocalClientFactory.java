package team.AI.IMG;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

//ThreadLocalClientFactory.getInstance().getWebClient()调用
public class ThreadLocalClientFactory {
    //单例工厂模式
    private final static ThreadLocalClientFactory instance = new ThreadLocalClientFactory();

    public static ThreadLocalClientFactory getInstance() {
        return instance;
    }

    // 覆写ThreadLocal的initialValue方法
    //线程的本地实例存储器，用于存储WebClient实例
    private ThreadLocal<WebClient> client = new ThreadLocal<WebClient>() {
        @Override
        //该方法ThreadLocal变量第一次get的时候执行,如果该线程已经执行过set方法，initialValue不会执行
        protected synchronized WebClient initialValue() {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            //设置webClient的相关参数
            webClient.getCookieManager().setCookiesEnabled(true);// 开启cookie管理
            webClient.getOptions().setJavaScriptEnabled(true);// 开启js解析
            webClient.getOptions().setCssEnabled(false);
            // 当出现Http error时，程序不抛异常继续执行
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            // 防止js语法错误抛出异常
            webClient.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
            webClient.getOptions().setTimeout(10000);
            // 默认是false, 设置为true的话不让你的浏览行为被记录
            webClient.getOptions().setDoNotTrackEnabled(false);
            // 设置Ajax异步处理控制器即启用Ajax支持
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            return webClient;
        }
    };


    public void setWebClient(WebClient wc) {
        client.set(wc);
    }

    public WebClient getWebClient() {
        return client.get();
    }


}
