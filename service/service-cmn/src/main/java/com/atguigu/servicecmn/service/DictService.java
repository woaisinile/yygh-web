package com.atguigu.servicecmn.service;

import com.atguigu.com.model.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    List<Dict> findChildData(Long id);

    void exportDictDate(HttpServletResponse response);

    void importDictData(MultipartFile file);
}
