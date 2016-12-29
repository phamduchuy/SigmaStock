package admin.sigmastock.sigmastock.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import admin.sigmastock.sigmastock.Entity.RssItem;


/**
 * Created by HuyPD on 8/19/2016.
 */
public class JsoupParser
{
    public static String setDescriptionVNEX(String description) {
//        descrip = description;
        String img = "";
        //parse description for any image or video links
        if (description.contains("<img ")) {
            img = description.substring(description.indexOf("<img "));
            String cleanUp = img.substring(0, img.indexOf(">") + 1);
            img = img.substring(img.indexOf("src=") + 5);
            int indexOf = img.indexOf("'");
            if (indexOf == -1) {
                indexOf = img.indexOf("\"");
            }
            img = img.substring(0, indexOf);

//            descrip = img;
        }
        return img;
    }
    public static List<RssItem> getDataRssItem(String s , String url)
    {
        if (url.compareToIgnoreCase(CommonConst.URL_HOT_VNEX)==0)
        {
            return parserVNEX(s);
        }
        else
        {
            if (url.compareToIgnoreCase(CommonConst.URL_HOT_BONGDA)==0)
            {
                return parserBD(s);
            }
            else
            {
                if (url.compareToIgnoreCase(CommonConst.URL_HOT_ZING)==0)
                {
                    return parserZing(s);
                }
                else
                {
                    if (url.compareToIgnoreCase(CommonConst.URL_HOT_DANTRI)==0)
                    {
                        return parserDantri(s);
                    }
                }
            }
        }
        return null;
    }

    public static List<RssItem> parserVNEX (String s )
    {
        List<RssItem> itemList = new ArrayList<RssItem>();
        String kq = "";
        //RssItem item = new RssItem();
        XMLDOMParser parser = new XMLDOMParser();
        Document doc = parser.getDocument(s);
        NodeList nodeList = doc.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            RssItem item = new RssItem();
            Element e = (Element) nodeList.item(i);

            NodeList titleNode = e.getElementsByTagName("title");
            Element titleElement = (Element) titleNode.item(0);
            try {
                item.setTitle(titleElement.getFirstChild().getNodeValue());
            }
            catch (Exception e1)
            {

            }


            NodeList linkNode = e.getElementsByTagName("link");
            Element linkElement = (Element) linkNode.item(0);
            try {
                item.setLink(linkElement.getFirstChild().getNodeValue());
            }
            catch (Exception e1)
            {

            }


            NodeList pubDateNode = e.getElementsByTagName("pubDate");
            Element pubDateElement = (Element) pubDateNode.item(0);

            try {
                item.setDate(pubDateElement.getFirstChild().getNodeValue());
            }
            catch (Exception e1)
            {

            }
            NodeList node = e.getElementsByTagName("description");
            Element desElment = (Element) node.item(0);
            try {
                item.setDate(pubDateElement.getFirstChild().getNodeValue());
                kq = JsoupParser.setDescriptionVNEX(desElment.getFirstChild().getNodeValue());
            }
            catch (Exception e1)
            {

            }

            item.setDescription(kq);
            itemList.add(item);
        }
        return itemList;
    }
    public static List<RssItem> parserDantri(String s )
    {
        List<RssItem> itemList = new ArrayList<RssItem>();
        String kq = "";
        //RssItem item = new RssItem();
        XMLDOMParser parser = new XMLDOMParser();
        Document doc = parser.getDocument(s);
        NodeList nodeList = doc.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            RssItem item = new RssItem();
            Element e = (Element) nodeList.item(i);

            NodeList titleNode = e.getElementsByTagName("title");
            Element titleElement = (Element) titleNode.item(0);
            try {
                item.setTitle(titleElement.getFirstChild().getNodeValue());
            }
            catch (Exception e1)
            {

            }


            NodeList linkNode = e.getElementsByTagName("link");
            Element linkElement = (Element) linkNode.item(0);
            try {
                item.setLink(linkElement.getFirstChild().getNodeValue());
            }
            catch (Exception e1)
            {

            }


            NodeList pubDateNode = e.getElementsByTagName("pubDate");
            Element pubDateElement = (Element) pubDateNode.item(0);

            try {
                item.setDate(pubDateElement.getFirstChild().getNodeValue());
            }
            catch (Exception e1)
            {

            }
            NodeList node = e.getElementsByTagName("description");
            Element desElment = (Element) node.item(0);
            try {
                item.setDate(pubDateElement.getFirstChild().getNodeValue());
                kq = JsoupParser.setDescriptionVNEX(desElment.getFirstChild().getNodeValue());
            }
            catch (Exception e1)
            {

            }

            item.setDescription(kq);
            itemList.add(item);
        }
        return itemList;
    }

    public static List<RssItem> parserBD (String sUrl )
    {
        String s ="";
        s = sUrl.replace("\n", "").replace("\r", "");

        List<RssItem> itemList = new ArrayList<RssItem>();
        String kq = "";
        //RssItem item = new RssItem();
        XMLDOMParser parser = new XMLDOMParser();
        Document doc = parser.getDocument(s);
        NodeList nodeList = doc.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            RssItem item = new RssItem();
            Element e = (Element) nodeList.item(i);

            NodeList titleNode = e.getElementsByTagName("title");
            Element titleElement = (Element) titleNode.item(0);
            item.setTitle(titleElement.getChildNodes().item(1).getNodeValue());

            NodeList linkNode = e.getElementsByTagName("link");
            Element linkElement = (Element) linkNode.item(0);
            item.setLink(linkElement.getFirstChild().getNodeValue());

            NodeList pubDateNode = e.getElementsByTagName("pubDate");
            Element pubDateElement = (Element) pubDateNode.item(0);
            item.setDate(pubDateElement.getFirstChild().getNodeValue());

            NodeList node = e.getElementsByTagName("image");
            Element desElment = (Element) node.item(0);
            item.setDescription(desElment.getFirstChild().getNodeValue());
            itemList.add(item);
        }
        return itemList;
    }
    public static List<RssItem> parserZing (String sUrl )
    {
        List<RssItem> itemList = new ArrayList<RssItem>();
        try {
            String s =sUrl;
            //s = sUrl.replace("\n", "").replace("\r", "");
            //s = s.replaceAll("[^\\x20-\\x7e]", "");
            //s = s.replaceAll("[^\\x20-\\x7e]", "");
             //s = s.replaceAll(">\\s+<", "><").trim();
            XMLDOMParser parser = new XMLDOMParser();
            Document doc = parser.getDocument(s);
            NodeList nodeList = doc.getElementsByTagName("item");
            for (int i = 0; i < nodeList.getLength(); i++) {
                RssItem item = new RssItem();
                Element e = (Element) nodeList.item(i);

                NodeList titleNode = e.getElementsByTagName("title");
                Element titleElement = (Element) titleNode.item(0);
                try
                {
                    item.setTitle(titleElement.getFirstChild().getNodeValue());
                }
                catch (Exception eTitleElement)
                {

                }

                NodeList linkNode = e.getElementsByTagName("link");
                Element linkElement = (Element) linkNode.item(0);
                try {
                    item.setLink(linkElement.getFirstChild().getNodeValue());
                }
                catch (Exception eLink)
                {

                }


                NodeList pubDateNode = e.getElementsByTagName("pubDate");
                Element pubDateElement = (Element) pubDateNode.item(0);
                try
                {
                    item.setDate(pubDateElement.getFirstChild().getNodeValue());
                }
                catch (Exception eLink)
                {

                }

                NodeList node = e.getElementsByTagName("enclosure");
                Element desElment = (Element) node.item(0);

                try
                {
                    item.setDescription(desElment.getAttribute("url"));
                }
                catch (Exception eLink)
                {

                }

                itemList.add(item);
            }
        }
        catch (Exception e)
        {
            System.out.print(e.toString());
        }

        return itemList;
    }
}
