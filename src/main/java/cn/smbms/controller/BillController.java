package cn.smbms.controller;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.bill.BillServiceImpl;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import cn.smbms.tools.Constants;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class BillController {
    @Resource
    private ProviderService providerService;
    @Resource
    private  BillService billService;
    @RequestMapping("/billlist.html")
    public String query(Bill bill, String queryProductName, String queryProviderId, String queryIsPayment, HttpServletRequest request){
        List<Provider> providerList = null;

        providerList = providerService.getProviderList("","");
        request.setAttribute("providerList", providerList);

        request.getParameter("queryProductName");
        request.getParameter("queryProviderId");
        request.getParameter("queryIsPayment");
        if(StringUtils.isNullOrEmpty(queryProductName)){
            queryProductName = "";
        }

        List<Bill> billList = null;
        if(StringUtils.isNullOrEmpty(queryIsPayment)){
            bill.setIsPayment(0);
        }else{
            bill.setIsPayment(Integer.parseInt(queryIsPayment));
        }

        if(StringUtils.isNullOrEmpty(queryProviderId)){
            bill.setProviderId(0);
        }else{
            bill.setProviderId(Integer.parseInt(queryProviderId));
        }
        bill.setProductName(queryProductName);
        billList = billService.getBillList(bill);
        request.setAttribute("billList", billList);
        request.setAttribute("queryProductName", queryProductName);
        request.setAttribute("queryProviderId", queryProviderId);
        request.setAttribute("queryIsPayment", queryIsPayment);
       return "billlist";
    }

    /**
     * 跳转添加
     * @param bill
     * @return
     */
    @RequestMapping("billadd.html")
    public String addBill(@ModelAttribute Bill bill){
        return "billadd";
    }
    @RequestMapping(value = "/billadd.html",method = RequestMethod.POST)
    public String billAdd(Bill bill, HttpSession session){
        bill.setCreationDate(new Date());
        bill.setCreatedBy(bill.getId());
        if (billService.add(bill)){
            return "redirect:/billlist.html";
        }
        return "billadd";
    }

    /**
     * 获取异步数据
     * @return
     */
    @RequestMapping("/providerList")
    @ResponseBody
    public List<Provider> providerList(){
        return providerService.getProviderList(null,null);
    }

    /**
     * 查看
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/billview.html")
    public String view(String id,Model model){
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill",bill);
        return "billview";

    }

    /**
     * 跳转修改
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/billmodify.html")
    public String updateBill(String id,Model model){
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill",bill);
        return "billmodify";
    }
    @RequestMapping(value = "/billmodify.html",method = RequestMethod.POST)
    public String updateBillSave(Bill bill,HttpSession session){
        bill.setModifyDate(new Date());
        User pro = (User) session.getAttribute(Constants.USER_SESSION);
        bill.setModifyBy(pro.getId());
        if (billService.modify(bill)){
            return "redirect:/billlist.html";
        }
        return "billmodify";
    }
}
