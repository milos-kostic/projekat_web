package cubes.main.controller;

import cubes.main.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
 
import java.util.HashMap;
import java.util.Map;
 
@Controller
public class UploadController {
 
    @Autowired
    private FileService fileService;
 
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile multipartFile){
        long fileSize = multipartFile.getSize();
        String fileName = multipartFile.getOriginalFilename();
        ModelAndView modelAndView = new ModelAndView("upload-success");
        if(fileService.saveFile(multipartFile)){
            Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("fileName", fileName);
            modelMap.put("fileSize", fileSize);
            modelAndView.addAllObjects(modelMap);
            return modelAndView;
        }
        return new ModelAndView("upload-failed");
    }
 
}