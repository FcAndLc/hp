package com.fc.weixin.constans;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuConstans {
    enum MenuKey{
        ONE("00001","music"),
        TWO("00002","video"),
        THREE("00003","photo"),;
        MenuKey(String key,String value){
            this.key=key;
            this.value=value;
        }
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
    public static final String WX_MENU;
            static {
                Map<String,List> menu=new HashMap();
                List<Map<String,Object>> menuList=new ArrayList<>();
                //左侧菜单设置
                menuList.add(new HashMap<String,Object>(){{
                    this.put("name","左侧菜单");
                    this.put("type","click");
                    this.put("key","fc001");
//                    List<Map<String,Object>> firstList=new ArrayList<>();
//                    firstList.add(new HashMap<String,Object>(){{
//                        this.put("type","click");
//                        this.put("name","music");
//                        this.put("key","fc001");
//                    }});
//                    firstList.add(new HashMap<String,Object>(){{
//                        this.put("type","click");
//                        this.put("name",MenuKey.TWO.getValue());
//                        this.put("key",MenuKey.TWO.getKey());
//                    }});
//                    firstList.add(new HashMap<String,Object>(){{
//                        this.put("type","click");
//                        this.put("name",MenuKey.THREE.getValue());
//                        this.put("key",MenuKey.THREE.getKey());
//                    }});
//                    this.put("sub_button",firstList);
                }});
//                //中间菜单设置
//                menuList.add(new HashMap<String,Object>(){{
//                    this.put("name","中间菜单");
//                    List<Map<String,Object>> secondList=new ArrayList<>();
//                    secondList.add(new HashMap<String,Object>(){{
//                        this.put("type","view");
//                        this.put("name","小说推荐");
//                        this.put("url","https://www.baidu.com");
//                    }});
//                    this.put("sub_button",secondList);
//                }});
//                //右侧菜单设置
//                menuList.add(new HashMap<String,Object>(){{
//                    this.put("name","右边菜单");
//                    List<Map<String,Object>> thirdList=new ArrayList<>();
//                    thirdList.add(new HashMap<String,Object>(){{
//                        this.put("type","view");
//                        this.put("name","关于fc");
//                        this.put("url","https://www.baidu.com");
//                    }});
//                    this.put("sub_button",thirdList);
//                }});
                menu.put("button",menuList);
                WX_MENU= JSONObject.toJSONString(menu);
            }
    public static void main(String[] args) {
        System.out.println(WX_MENU);
    }
}
