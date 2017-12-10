package com.example.gentleman.business.module.recommand;

import com.example.gentleman.business.module.BaseModel;

/**
 * Created by renzhiqiang on 16/8/28.
 */
public class BaseRecommandModel extends BaseModel {

    //保证我们的变量的名字与json中的字段保持一样
    public String ecode;
    public String emsg;
    public RecommandModel data;
}
