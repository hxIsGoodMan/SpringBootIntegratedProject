package com.example.solr.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
 * @Author Hman
 * @Description 测试solr框架的 增删查改 高亮等 操作
 * @Date 2019/3/2 10:07
 * @Version  1.0
 **/
@Controller
@RequestMapping("solr")
public class SolrController {

    @Autowired
    private SolrClient client;

    /**
     *  添加索引至 solr , 正式开发 会让solr 同步mysql 数据库 不会直接添加 (不过也要看领导的)
     * @return  id or error
     */
    @RequestMapping("add")
    @ResponseBody
    public String add() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", uuid);
            doc.setField("level", "测试添加");
            doc.setField("monitoring_point","测试测试");
            doc.setField("target","添加添加");
            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
             * 下面都是一样的
             */
            client.add(doc);
            client.commit();
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 根据id删除索引
     * @param id
     * @return
     */
    @RequestMapping("delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id")String id)  {
        try {
            client.deleteById(id);
            client.commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 删除所有的索引
     * @return 返回成功 或者 错误 flag
     */
    @RequestMapping("deleteAll")
    public String deleteAll(){
        try {

            client.deleteByQuery("collection1","*:*");
            client.commit("collection1");

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 根据id查询索引
     * @return 返回搜索之后的内容
     * @throws Exception
     */
    @RequestMapping("getById/{id}")
    @ResponseBody
    public String getById(@PathVariable("id") String id) throws Exception {
        SolrDocument document = client.getById(id);
        System.out.println(document);
        return document.toString();
    }

    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     *      PS. 高亮需要是搜索的部分值 才会高亮~
     * @return 高亮部分搜索值
     */
    @RequestMapping("search/{key}/{value}")
    @ResponseBody
    public Map search(@PathVariable String key,@PathVariable String value){
        try {
            SolrQuery params = new SolrQuery();

            //查询条件, 这里的 q 对应 下面图片标红的地方
            params.set("q", key+":"+value+"*");

            //过滤条件
        //    params.set("fq", "product_price:[100 TO 100000]");

            //排序
          //  params.addSort("product_price", SolrQuery.ORDER.asc);

            //分页
            params.setStart(0);
            params.setRows(6);

            //默认域
        //    params.set("df", "product_title");

            //只查询指定域
         //   params.set("fl", "id,product_title,product_price");

            //高亮
            //打开开关
            params.setHighlight(true);
            //指定高亮域
            params.addHighlightField(key);
            //设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            //设置后缀
            params.setHighlightSimplePost("</span>");

            QueryResponse queryResponse = client.query(params);

            SolrDocumentList results = queryResponse.getResults();

            long numFound = results.getNumFound();

          //  System.out.println(numFound);

//获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
            Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

            for (SolrDocument result : results) {
                System.out.println(result.get("id"));
                System.out.println(result.get(key));
                //System.out.println(result.get("product_num"));
                //System.out.println(result.get("product_image"));

               Map<String, List<String>> map = highlight.get(result.get("id").toString());
               List<String> list = map.get(key);

                System.out.println(result.get(key));

             //   System.out.println(list);
                System.out.println(JSONObject.toJSONString(highlight));
            }

            return highlight;

           // return highlight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
