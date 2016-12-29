package admin.sigmastock.sigmastock.Accessor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import admin.sigmastock.sigmastock.Entity.CStock;
import admin.sigmastock.sigmastock.Entity.RssItem;

/**
 * Created by HuyPD on 7/18/2016.
 */
public class MobileCareSortAccessorParseData
{
    protected static List<CStock> parserDataVNI(String res)
    {
        List<CStock> lstArray = new ArrayList();
        try {

            String[] lines = res.split("@");
            if (lines.length < 3)
                return null;
            for (int i = 0; i < lines.length - 1; i++)
            {
                CStock stockMarket = new CStock();
                String[] parts = lines[i + 1].split("#");
                if (parts.length > 6)
                {
                    if (parts[0].compareToIgnoreCase("HOSE")==0||
                            parts[0].compareToIgnoreCase("HNX")==0||
                            parts[0].compareToIgnoreCase("UPCOM")==0)
                    {
                        stockMarket.setCode(parts[0]);
                        stockMarket.setLast(parts[1]);
                        stockMarket.setChange(parts[3]);
                        stockMarket.setChangeRatio(parts[4]);
                        lstArray.add(stockMarket);
                    }
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return lstArray;
    }
    protected static List<CStock> parserDataCOMMO(String res)
    {
        List<CStock> lstArray = new ArrayList();
        try {

            res = res.replace("\n", "");
            res = res.replace("\r", "");
            res = res.replace("\t", "");

            Document doc = Jsoup.parse(res);
            Element elment = doc.getElementById("energy");
            Elements tbody = elment.select("tbody > tr");

            for (Element item : tbody)
            {
                if (item.child(1).getElementsByTag("a").text().compareToIgnoreCase("Brent Oil")==0||
                        item.child(1).getElementsByTag("a").text().compareToIgnoreCase("Crude Oil")==0) {
                    CStock objMarketUpdate = new CStock();
                    objMarketUpdate.setCode(item.child(1).getElementsByTag("a").text());
                    objMarketUpdate.setLast(item.child(3).text());
                    objMarketUpdate.setChange(item.child(7).text());
                    objMarketUpdate.setChangeRatio(item.child(8).text());
                    lstArray.add(objMarketUpdate);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return lstArray;
    }
    protected static List<CStock> parserDataExchangeRate(String res)
    {
        List<CStock> lstArray = new ArrayList();
        CStock cStock = new CStock();
        cStock.setCode("Ngoại tệ");
        cStock.setChange("Mua");
        cStock.setChangeRatio("Bán");
        lstArray.add(cStock);
        try {
            int i = 0;

            Document doc = Jsoup.parse(res);
            Element table = doc.getElementById("tbl_100");
            Elements tbody = table.getElementsByTag("tr");

            int j = 0;
            for (Element item : tbody)
            {
                if (j == 0)
                {
                    j++;
                    continue;
                }
                else
                {
                    CStock objTyGiaNganHang = new CStock();
                    if (item.child(0).text().compareToIgnoreCase("USD")==0||item.child(0).text().compareToIgnoreCase("EUR")==0)
                    {
                        objTyGiaNganHang.setCode(item.child(0).text()+"/VND");
                    }

                    objTyGiaNganHang.setChange(item.child(1).text());
                    objTyGiaNganHang.setChangeRatio( item.child(2).text());
                    i++;
                    if (item.child(0).text().compareToIgnoreCase("USD")==0||item.child(0).text().compareToIgnoreCase("EUR")==0)
                    {
                        lstArray.add(objTyGiaNganHang);
                    }

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lstArray;
    }

    protected static List<RssItem> parserDataCAFEFNEW(String res)
    {
        List<RssItem> itemList = new ArrayList<RssItem>();
        try {
            Document doc = Jsoup.parse(res);
            Elements items = doc.getElementsByTag("item");
            for (Element item : items)
            {
                RssItem rssItem = new RssItem();
                String strReplace = item.getElementsByTag("title").text().replace("<![CDATA[","").replace("]]>","");
                rssItem.setTitle(strReplace);
                rssItem.setDate(item.getElementsByTag("pubDate").text());
                if (item.getElementsByTag("description").text().contains("<img ")) {
                    String img = item.getElementsByTag("description").text().substring(item.getElementsByTag("description").text().indexOf("<img "));
                    String cleanUp = img.substring(0, img.indexOf(">") + 1);
                    Document document = Jsoup.parse(cleanUp);
                    rssItem.setUrlImg(document.getElementsByTag("img").attr("src"));


                }

                itemList.add(rssItem);
            }


        }
        catch (Exception e)
        {
            System.out.print(e.toString());
        }

        return itemList;
    }
}
