package site.imcu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.imcu.mapper.WeiboVoMapper;
import site.imcu.po.WeiboVo;
import site.imcu.service.WeiboService;

import java.util.List;

@Service
public class WeiboServicesImpl implements WeiboService {
    @Autowired
    WeiboVoMapper weiboVoMapper;
    @Override
    public int postWeibo(WeiboVo weiboVo) {
        return weiboVoMapper.postWeibo(weiboVo);
    }

    @Override
    public List<Integer> queryFollowId(int id) {
        return weiboVoMapper.queryFollowIds(id);
    }

    @Override
    public List<WeiboVo> queryTimeLine(List<Integer> idList,int pageNum,int pageSize) {
        return weiboVoMapper.queryTimeLine(idList,pageNum,pageSize);
    }

    @Override
    public List<WeiboVo> beforeTimeLine(List<Integer> idList, int pageNum, int pageSize, int before) {
        return weiboVoMapper.beforeTimeLine(idList,pageNum,pageSize,before);
    }

    @Override
    public List<WeiboVo> afterTimeLine(List<Integer> idList, int pageNum, int pageSize, int after) {
        return weiboVoMapper.afterTimeLine(idList,pageNum,pageSize,after);
    }

    @Override
    public List<WeiboVo> queryAll() {
        return weiboVoMapper.queryAll();
    }

    @Override
    public WeiboVo queryById(int weiboId) {
        return weiboVoMapper.queryById(weiboId);
    }
}
