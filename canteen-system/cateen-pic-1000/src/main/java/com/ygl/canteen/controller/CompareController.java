package com.ygl.canteen.controller;

import com.ygl.canteen.util.Constants;
import com.ygl.canteen.util.DateUtil;
import com.ygl.canteen.util.FileUtils;
import com.ygl.canteen.util.request.TecentFace;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
public class CompareController {

    @RequestMapping("/comparepic")
    public String dealBase64(String snapData, String urlB) throws Exception {

        File file = uploadfile(snapData);
        String urlA = Constants.PIC_SHOW_PATH+"/"+file.getName();
        System.out.println(urlA);
        String result = TecentFace.CompareFaceByUrl(urlA,urlB);

        if(file.exists()){
            file.delete();
        }
        return result;
    }

    @RequestMapping("/comparepic1")
    public String dealBase(MultipartFile file) throws Exception {

        /*File file = uploadfile(snapData);
        String urlA = Constants.PIC_SHOW_PATH+"/"+file.getName();
        System.out.println(urlA);
        String result = TecentFace.CompareFaceByUrl(urlA,urlB);

        if(file.exists()){
            file.delete();
        }*/
        System.out.println(file.getOriginalFilename());
        return null;
    }

    @RequestMapping("/getfrompic")
    public String getInfo(String base64Data) throws Exception {

        File file = uploadfile(base64Data);
        String url = Constants.PIC_SHOW_PATH+"/"+file.getName();
        System.out.println(url);
        String result = TecentFace.getInfoByUrl(url);
        if(file.exists()){
            file.delete();
        }
        return result;
    }


    private File uploadfile(String snapData){
        String dataPrix = "";
        String data = "";
        if (snapData == null || "".equals(snapData)) {
           // throw new Exception("上传失败，上传图片数据为空");
        } else {
            String[] d = snapData.split("base64,");
            if (d != null && d.length == 2) {
                dataPrix = d[0];
                data = d[1];
            } else {
                //throw new Exception("上传失败，数据不合法");
            }
        }

        String suffix = "";
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {//data:image/jpeg;base64,base64编码的jpeg图片数据
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {//data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {//data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {//data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        } else {}

        String filename = DateUtil.getDateStr()+DateUtil.getTimeStr()+suffix;
        byte[] bs = Base64Utils.decodeFromString(data);
        File file = new File(Constants.PIC_UPLOAD_PATH,filename);
        FileUtils.writeByteArrayToFile(file,bs);

        return file;
    }
}
