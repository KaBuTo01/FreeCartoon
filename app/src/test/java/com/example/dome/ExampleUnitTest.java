package com.example.dome;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws IOException {
        assertEquals(4, 2 + 2);




//        OkhttpUit.getInstance().get("https://m.dushimh.com/list/", new callback() {
//            @Override
//            public void onSuccess(String response) {
//
//                List<ComicBean> comic_list = new ArrayList<>();
//                Document doc = Jsoup.parse(response);
//                Elements elements1 = doc
//                        .select("#comic-items > li");
//                for (Element data1:
//                        elements1) {
//                    ComicBean comicBean = new ComicBean();
//                    String image = data1.select(" a.ImgA > img").attr("src");
//                    comicBean.setImageUrl(image);
//                    String title = data1.select("a.txtA").text();
//                    comicBean.setComicName(title);
//
//                    String url = data1.select("a.ImgA").attr("href");
//                    comicBean.setUrl(url);
//                    String name = data1.select("span").text();
//                    comicBean.setName(name);
//                    comic_list.add(comicBean);
//                }
//        System.out.println(comic_list);
//
//            }
//
//            @Override
//            public void onFail(IOException e) {
//
//            }
//        });



        while (true){}
    }
}