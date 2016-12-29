package admin.sigmastock.sigmastock.Accessor;

import java.util.List;

import admin.sigmastock.sigmastock.Entity.CStock;
import admin.sigmastock.sigmastock.Entity.RssItem;

/**
 * Created by HuyPD on 7/18/2016.
 */
public interface MobileCareSortAccessor
{

     public List<CStock> getDataVNi() throws Exception;
     public List<CStock> getDataCOM() throws Exception;
     public List<CStock> getDataExchangeRate() throws Exception;
     public List<RssItem> getDataNewCafeF() throws Exception;
     public String testHNX() throws Exception;
     public String testHSX() throws Exception;
     public String sendToken(String token) throws  Exception;
    /*public String checkVerSion(String versionName) throws  Exception;*/
}
