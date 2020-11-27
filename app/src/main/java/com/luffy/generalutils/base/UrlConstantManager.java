package com.luffy.generalutils.base;

import java.util.ArrayList;
import java.util.List;

public class UrlConstantManager {

    public final static String imageUrl1 = "http://img02.sogoucdn.com/app/a/100520093/ac75323d6b6de243-174fbc37ffe7ed35-26c3d5ac297314b420bce11dcbab09a8.jpg";
    public final static String imageUrl2 = "http://img03.sogoucdn.com/app/a/100520093/c583df4cdb382aff-50aefb79b44e9cc2-54fa4c41ca853d84971c82a2100f3f6b.jpg";
    public final static String imageUrl3 = "http://img02.sogoucdn.com/app/a/100520093/089cb779cbaf57c9-2aab3d69c9a5ee5c-bb826c276302cef8d7827529dd0cf76c.jpg";
    public final static String imageUrl4 = "http://img03.sogoucdn.com/app/a/100520093/df6de2e2256a830e-36618862a7c1988e-2e3f8893a65b0aaa5ad26e552dc535f5.jpg";
    public final static String imageUrl5 = "http://img02.sogoucdn.com/app/a/100520093/e222e3d1e5293cd2-002a269c286867ae-c01ed46aa47358aa50558bde2f21626f.jpg";
    public final static String imageUrl6 = "http://img02.sogoucdn.com/app/a/100520093/ac75323d6b6de243-503c0c74be6ae02f-9a75e09210a0ab8ad2fcfb82ddac0ab4.jpg";
    public final static String imageUrl7 = "http://img02.sogoucdn.com/app/a/100520093/ac75323d6b6de243-cb085b110105c1b4-f736be06f92e79014765affce2dc5441.jpg";
    public final static String imageUrl8 = "http://img02.sogoucdn.com/app/a/100520093/9d0091db716ca3d7-d8cfb50654102235-ea192a865198db09e6f1fbf95b6cccd6.jpg";
    public final static String imageUrl9 = "http://img04.sogoucdn.com/app/a/100520093/ac75323d6b6de243-0d8a45e2aeaeb0b8-9b6fd77b1f5ca716716c38aef3c46061.jpg";
    public final static String imageUrl10 = "http://img02.sogoucdn.com/app/a/100520093/dc72c3c04ef01fdf-22176402958c6b80-f7ac7b64e7adc776e8f731b26dd20b58.jpg";
    public final static String imageUrl11 = "http://f.hiphotos.baidu.com/image/w%3D310/sign=690c5b1b2c2eb938ec6d7cf3e56085fe/6d81800a19d8bc3e8cde5e30818ba61ea9d34571.jpg";
    public final static String imageUrl12 = "http://img0.bdstatic.com/img/image/shouye/ssdp0192.jpg";
    public final static String imageUrl13 = "http://g.hiphotos.baidu.com/image/whcrop%3D190%2C270%3Bq%3D100/sign=f5631aa516ce36d3a251d572558307b6/37d12f2eb9389b50e114062d8635e5dde6116ee2.jpg";
    public final static String imageUrl14 = "http://img0.bdstatic.com/img/image/%E6%84%8F%E5%A4%A7%E5%88%A9%E2%80%94%E2%80%94%E5%8F%AA%E4%B8%80%E7%9C%BC%EF%BC%8C%E4%BE%BF%E6%B0%B8%E8%BF%9C.jpg";
    public final static String imageUrl15 = "http://img0.bdstatic.com/img/image/%E6%96%AF%E9%87%8C%E5%85%B0%E5%8D%A1%E7%AB%96.jpg";
    public final static String imageUrl16 = "http://img0.bdstatic.com/img/image/%E4%B8%89%E4%BA%9A%E6%96%B9.jpg";
    public final static String imageUrl17 = "http://img0.bdstatic.com/img/image/%E5%A4%8F%E5%A8%81%E5%A4%B7%E6%96%B9.jpg";
    public final static String imageUrl18 = "http://img0.bdstatic.com/img/image/%E5%BB%BA%E7%AD%91%E6%96%B9.jpg";
    public final static String imageUrl19 = "http://img0.bdstatic.com/img/image/shouye/jiatingdongjing.jpg";
    public final static String imageUrl20 = "http://a.hiphotos.baidu.com/image/w%3D310/sign=ab91c6a1a964034f0fcdc4079fc27980/b999a9014c086e06fdff373700087bf40ad1cb73.jpg";

    private List<String> urlList;

    private UrlConstantManager() {
        urlList = new ArrayList<>();
        urlList.add(imageUrl1);
        urlList.add(imageUrl2);
        urlList.add(imageUrl3);
        urlList.add(imageUrl4);
        urlList.add(imageUrl5);
        urlList.add(imageUrl6);
        urlList.add(imageUrl7);
        urlList.add(imageUrl8);
        urlList.add(imageUrl9);
        urlList.add(imageUrl10);
        urlList.add(imageUrl11);
        urlList.add(imageUrl12);
        urlList.add(imageUrl13);
        urlList.add(imageUrl14);
        urlList.add(imageUrl15);
        urlList.add(imageUrl16);
        urlList.add(imageUrl17);
        urlList.add(imageUrl18);
        urlList.add(imageUrl19);
        urlList.add(imageUrl20);
    }

    public static UrlConstantManager getInstance() {
        return UrlConstantManagerHolder.instance;
    }

    private static class UrlConstantManagerHolder {
        private static final UrlConstantManager instance = new UrlConstantManager();
    }

    public List<String> getUrlList() {
        return urlList;
    }
}
