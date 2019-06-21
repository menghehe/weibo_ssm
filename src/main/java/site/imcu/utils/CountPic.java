package site.imcu.utils;

import site.imcu.po.WeiboVo;

public class CountPic {
    public static int getNumber(WeiboVo weiboVo){
        if (weiboVo.getPic1()==null)
            return 0;
        if (weiboVo.getPic2()==null)
            return 1;
        if (weiboVo.getPic3()==null)
            return 2;
        if (weiboVo.getPic4()==null)
            return 3;
        if (weiboVo.getPic5()==null)
            return 4;
        if (weiboVo.getPic6()==null)
            return 5;
        if (weiboVo.getPic7()==null)
            return 6;
        if (weiboVo.getPic8()==null)
            return 7;
        if (weiboVo.getPic9()==null)
            return 8;
        if (weiboVo.getPic9()!=null)
            return 9;
        return 0;
    }
}
