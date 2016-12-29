package admin.sigmastock.sigmastock.Util;

/**
 * Created by Admin on 26/6/2016.
 */
public class CommonConst
{
    ///--- Http ---////
    public final static String URL_GATEWAY_2015="http://gateway.fpts.com.vn/fpts/";
    public final static String URL_VN_MARKET_INDEX = URL_GATEWAY_2015 + "?s=vn_indices";
    public final static String URL_CAFE_F_HOME= "http://cafef.vn/home.rss";
    public final static String URL_TEST_HNX= "http://hnx.vn/web/guest/tin-niem-yet?p_p_id=ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_cacheability=cacheLevelPage&p_p_col_id=column-3&p_p_col_count=1&_ThongTinCongBo_WAR_ThongTinCongBoportlet_INSTANCE_aO8s_anchor=newsAction";
    public final static String URL_TEST_HSX= "https://www.hsx.vn/Modules/CMS/Web/ArticleInCategory/dca0933e-a578-4eaf-8b29-beb4575052c5";
    public final static String URL_COMMO= "http://www.investing.com/commodities/";
    public final static String URL_EXCHANGE_RATE= "http://www.bidv.com.vn/";
    public static final String URL_HOT_VNEX = "http://vnexpress.net/rss/tin-moi-nhat.rss";
    public static final String URL_HOT_DANTRI= "http://dantri.com.vn/trangchu.rss";
    public static final String URL_HOT_ZING= "http://news.zing.vn/rss/trang-chu.rss";
    public static final String URL_HOT_BONGDA = "http://www.bongda.com.vn/feed.rss";



    public static final  String URL_WS ="http://sigmastock.net/sigmawebservice/api/";
    public static final  String METHOD_PUSH_TOKEN = URL_WS + "pushtoken";
    //public static final String WS_METHOD_GETCOUNT_TICKET_DETAILS_FILTER = "ticketSumary/getCountTicketAllFilters";
    public static final int GET = 0;
    public static final int POST = 1;

    ///--- Mess ---///
    public static final String CONECTION_ERROR = "Không có kết nối Internet . Vui lòng kiểm tra lại kết nối 3G hoặc Wifi";
    public static final String DIS_SESSTION = "Hết Session . Vui lòng đăng nhập lại !";
    public static final String SERVER_ERROR = "Server đang bảo trì. Vui lòng quay lại sau!";

    ///--- Name File SharedPreferences ---///
    public static final String FILE_INFOR_FRAGMENT = "fileinforfragment";

    public static final int  EN = 0;
    public static final int VI = 1;
}
