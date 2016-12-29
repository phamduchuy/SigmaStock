package admin.sigmastock.sigmastock.Accessor;

import java.util.List;

import admin.sigmastock.sigmastock.Entity.CStock;
import admin.sigmastock.sigmastock.Entity.RssItem;
import admin.sigmastock.sigmastock.Util.CommonConst;
import admin.sigmastock.sigmastock.Util.RestClient;

/**
 * Created by HuyPD on 7/18/2016.
 */
public class MobileCareSortAccessorImpl extends MobileCareSortAccessorParseData implements MobileCareSortAccessor
{
    public String strContent ="";
    public RestClient client;

    @Override
    public List<CStock> getDataVNi() throws Exception
    {
        try
        {
            client = new RestClient(CommonConst.URL_VN_MARKET_INDEX);
           // client.AddHeader("clientId",CommonConst.CLIENT_ID_MOBILE);

            client.Execute(CommonConst.GET);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MobileCareSortAccessorParseData.parserDataVNI(client.getResponse());
    }

    @Override
    public List<CStock> getDataCOM() throws Exception
    {
        try {
            client = new RestClient(CommonConst.URL_COMMO);
            client.AddHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
            client.Execute(CommonConst.GET);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return MobileCareSortAccessorParseData.parserDataCOMMO(client.getResponse());
    }

    @Override
    public List<CStock> getDataExchangeRate() throws Exception {
        try {
            client = new RestClient(CommonConst.URL_EXCHANGE_RATE);
            //client.AddHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
            client.Execute(CommonConst.GET);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return MobileCareSortAccessorParseData.parserDataExchangeRate(client.getResponse());

    }

    @Override
    public List<RssItem> getDataNewCafeF() throws Exception {
        try {
            client = new RestClient(CommonConst.URL_CAFE_F_HOME);
            client.AddHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
            client.AddHeader("Host","cafef.vn");
            client.Execute(CommonConst.GET);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return MobileCareSortAccessorParseData.parserDataCAFEFNEW(client.getResponse());

    }

    @Override
    public String testHNX() throws Exception
    {
        try
        {
            client = new RestClient(CommonConst.URL_TEST_HNX);
            /*client.AddHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
            client.AddHeader("Host","cafef.vn");*/
            client.AddParam("sEcho","8");
            client.AddParam("iColumns","9");
            client.AddParam("sColumns","");
            client.AddParam("iDisplayStart","0");
            client.AddParam("iDisplayLength","100");
            client.AddParam("mDataProp_0","0");
            client.AddParam("mDataProp_1","1");
            client.AddParam("mDataProp_2","2");
            client.AddParam("mDataProp_3","3");
            client.AddParam("mDataProp_4","4");
            client.AddParam("mDataProp_5","5");
            client.AddParam("mDataProp_6","6");
            client.AddParam("mDataProp_7","7");
            client.AddParam("mDataProp_8","8");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_s_page","ny");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_c_code","null");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_news_stock_code","");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_news_memeber_code","");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_news_bond_code","");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_type_lists","");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_news_ops_s_date","31/08/2016");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_news_ops_e_date","31/08/2016");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_content_search","");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_rp_ryear","");
            client.AddParam("_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_rp_type","");
            client.Execute(CommonConst.POST);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return client.getResponse();
    }

    @Override
    public String testHSX() throws Exception {

        try {
            client = new RestClient(CommonConst.URL_TEST_HSX);
           /* client.AddHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36");
            client.AddHeader("Host","cafef.vn");*/
            client.AddParam("exclude","00000000-0000-0000-0000-000000000000");
            client.AddParam("lim","True");
            client.AddParam("pageFieldName1","FromDate");
            client.AddParam("pageFieldValue1","03.10.2016");
            client.AddParam("pageFieldOperator1","eq");
            client.AddParam("pageFieldName2","ToDate");
            client.AddParam("pageFieldValue2","03.10.2016");
            client.AddParam("pageFieldOperator2","eq");
            client.AddParam("pageFieldName3","TokenCode");
            client.AddParam("pageFieldValue3","");
            client.AddParam("pageFieldOperator3","eq");
            client.AddParam("pageFieldName4","CategoryId");
            client.AddParam("pageFieldValue4","dca0933e-a578-4eaf-8b29-beb4575052c5");
            client.AddParam("pageFieldOperator4","eq");
            client.AddParam("pageCriteriaLength","4");
            client.AddParam("_search","false");
            client.AddParam("nd","1475487837222");
            client.AddParam("rows","30");
            client.AddParam("page","1");
            client.AddParam("sidx","id");
            client.AddParam("sord","desc");
            client.Execute(CommonConst.GET);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return client.getResponse();
    }

    @Override
    public String sendToken(String token) throws Exception {
        try
        {
            client = new RestClient(CommonConst.METHOD_PUSH_TOKEN);
            client.AddParam("token_push",token);
            client.Execute(CommonConst.POST);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return client.getResponse();
    }
  /*  @Override
    public UserInfo checkDomain(String strDomain) throws Exception {

        try
        {
            client = new RestClient(CommonConst.WS_URL_CHECKDOMAIN +CommonConst.WS_METHOD_CHECKDOMAIN);
            client.AddHeader("clientId",CommonConst.CLIENT_ID_MOBILE);
            client.AddParam("domain", strDomain);
            client.Execute(CommonConst.GET);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MobileCareSortAccessorParseData.parserUserInfo(client.getResponse());

    }*/

}
