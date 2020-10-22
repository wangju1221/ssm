package cn.smbms.controller;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import cn.smbms.tools.Constants;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class ProviderController {
    @Resource
    private ProviderService providerService;

    /**
     * 查看
     * @param model
     * @param queryProName
     * @param queryProCode
     * @return
     */
    @RequestMapping("/providerlist.html")
    public String query(Model model, String queryProName ,String queryProCode){
        if(StringUtils.isNullOrEmpty(queryProName)){
            queryProName = "";
        }
        if(StringUtils.isNullOrEmpty(queryProCode)){
            queryProCode = "";
        }
        List<Provider> providerList = null;
        providerList = providerService.getProviderList(queryProName,queryProCode);
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProName", queryProName);
        model.addAttribute("queryProCode", queryProCode);

        return "providerlist";
    }

    /**
     * 跳转添加
     * @param
     * @return
     */
    @RequestMapping("/provideradd.html")
    public String provideradd(){
        return "provideradd";
    }

    /**
     * 处理供应商添加
     * @param provider
     * @param session
     * @return
     */
    @RequestMapping(value = "/provideradd.html",method = RequestMethod.POST)
    public String addProvider(Provider provider, HttpSession session){
        provider.setCreationDate(new Date());
        provider.setCreatedBy(provider.getId());
        if (providerService.add(provider)){
            return "redirect:/providerlist.html";
        }
        return "provideradd";
    }

    /**
     * 跳转修改
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/promodify.html")
    public String update(String id,Model model){
        Provider provider = providerService.getProviderById(id);
        model.addAttribute("provider",provider);
        return "providermodify";
    }

    /**
     * 保存修改
     * @param provider
     * @param session
     * @return
     */
    @RequestMapping(value = "/promodify.html",method = RequestMethod.POST)
    public String saveUpdate(Provider provider,HttpSession session){
        provider.setModifyDate(new Date());
        User pro = (User) session.getAttribute(Constants.USER_SESSION);
        provider.setModifyBy(pro.getId());
        if (providerService.modify(provider)){
            return "redirect:/providerlist.html";
        }
        return "providermodify";
    }

    /**
     * 查看
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/view.html/{id}")
    public String view(@PathVariable String id,Model model){
        Provider provider = providerService.getProviderById(id);
        model.addAttribute("provider",provider);
        return "providerview";
    }
}
