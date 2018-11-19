package com.ygl.canteen.controller;


import com.ygl.canteen.util.compare.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {

    private String url = "http://58.87.104.133:1000/";
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/emp/upload")
    @ResponseBody
    public String dealBase64(@RequestParam("snapData") String snapData) throws Exception {

        String urlB = "http://58.87.104.133:8080/img/1.jpg";

        MultiValueMap<String,String> postParameters=new LinkedMultiValueMap<>();
        postParameters.add("snapData",snapData);
        postParameters.add("urlB",urlB);

        return restTemplate.postForObject(url+"comparepic",postParameters,String.class);
    }

    @RequestMapping("/emp/upload1")
    @ResponseBody
    public String dealBase(String snapData) throws Exception {

        String urlB = "http://58.87.104.133:8080/img/1.jpg";

        BASE64Decoder decoder=new BASE64Decoder();
//      byte[] rawBytes = utils.File2byte("d://test.png");//将图片转为byte[]
        byte[] rawBytes=decoder.decodeBuffer(snapData);

        String suffix= FileUtils.getSuffix(rawBytes);//获取图片的后缀名，也可以是其他任意文件名
        String fileName="myImage"+suffix;//

        ByteArrayResource fileResource = new ByteArrayResource(rawBytes) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };

        MultiValueMap<String,Object> postParameters=new LinkedMultiValueMap<String,Object>();

        postParameters.add("file",fileResource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity  = new HttpEntity<MultiValueMap<String, Object>>(postParameters, headers);


        return restTemplate.postForObject("http://127.0.0.1:1000/comparepic1",postParameters,String.class);
    }

    @RequestMapping("/upload2")
    @ResponseBody
    public String getInfo(String base64Data){

        //System.out.println(base64Data);
        MultiValueMap<String,String> postParameters=new LinkedMultiValueMap<>();
        postParameters.add("base64Data",base64Data);

        return restTemplate.postForObject(url+"getfrompic",postParameters,String.class);
    }


    @GetMapping("/uploadpic")
    public String uploadpic(){
        return "emp/upload";
    }

    @GetMapping("/upload1")
    public String upload(){
        return "emp/upload1";
    }
}
