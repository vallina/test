package com.hulu;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//搜索2个关键词，验证搜索结果页面是否包含期望的关键词
public class DataProviderDemo {

    @DataProvider(name = "searchData")
    public static Object[][] data() {
        //return new Object[][]{{"老九门", "演员", "赵丽颖"}, {"X站警天启", "导演", "布莱恩·辛格"}, {"诛仙青云志", "编剧", "张戬"}};

        //从Excel中读取数据
        return ReadXLS.getExcelData();

    }

    @Test(dataProvider = "searchData")
    public void testSearch(String searchdata1, String searchdata2, String searchResult) {

        System.out.println(searchdata1);
        System.out.println(searchdata2);
        System.out.println(searchResult);
        System.out.println("----------------");
    }

    @BeforeMethod
    public void beforeMethod() {
    }

    @AfterMethod
    public void afterMethod() {
    }
}
