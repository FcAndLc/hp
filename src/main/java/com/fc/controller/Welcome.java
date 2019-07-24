package com.fc.controller;

import com.alibaba.fastjson.JSONObject;
import com.fc.constans.MenuConstans;
import com.fc.exception.AesException;
import com.fc.utils.HttpUtils;
import com.fc.utils.SHA1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

@RequestMapping("welcome")
@Controller
public class Welcome {
    /**微信token**/
    private static final String WINXIN_TOKEN = "123456.";
    /**appID**/
    private static final String APP_ID = "wxdebe9ef3b8438a7e";
    /**appsecret**/
    private static final String APP_SECRET = "de910cf4eaa85af601197e6c33bc1a4f";
    /**菜单请求地址**/
    private static final String MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    /*请求获取token地址*/
    private static final String TOKEN_GET_RUL="https://api.weixin.qq.com/cgi-bin/token";

    @RequestMapping("welcome")
    public ModelAndView welcome() {
        return new ModelAndView("welcome");
    }

    @RequestMapping(value = "/wx/token",method = RequestMethod.GET)
    @ResponseBody
    public String verifyWXToken(HttpServletRequest request) throws AesException {
        String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String signature = SHA1.getSHA1(WINXIN_TOKEN, msgTimestamp, msgNonce);
        if (!signature.equals(msgSignature)) {
            throw new AesException(AesException.ValidateSignatureError);
        }
        return echostr;

    }
    @RequestMapping(value = "/wx/token",method = RequestMethod.POST)
    @ResponseBody
    public void wxPost(HttpServletRequest request) throws Exception {
        // 处理接收消息
        ServletInputStream in = request.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str=null;
        StringBuffer sb=new StringBuffer();
        while ((str=br.readLine())!=null){
            sb.append(str);
        }
        System.out.println(sb.toString());
    }
    @RequestMapping("/wx/menu")
    @ResponseBody
    public String wxMenu(HttpServletRequest request) throws Exception {
        String token = HttpUtils.sendGet(TOKEN_GET_RUL, "grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET);
        String tk= JSONObject.parseObject(token, Map.class).get("access_token").toString();
        return HttpUtils.sendPost( MENU_URL+tk,MenuConstans.WX_MENU);
    }
}
