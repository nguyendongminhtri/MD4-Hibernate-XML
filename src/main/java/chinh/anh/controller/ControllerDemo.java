package chinh.anh.controller;

import chinh.anh.model.Customer;
import chinh.anh.service.CustomerService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControllerDemo {
    @Autowired
    CustomerService1 customerService1;
    @GetMapping("/customers")
    public ModelAndView listCustomers(){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customerService1.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public String showFormCreate(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customerForm",customer);
        System.out.println("name"+customer.getName());
        return "/customer/add";
    }

    @PostMapping("/create/customer")
    public String createCustomer(@ModelAttribute("customerForm") Customer customer, Model model) {
//        List<Customer> customerList = customerService1.findAll();
//        int id = customerList.size() + 1;
//        String name = customer.getName();
//        String address = customer.getAddress();


            customerService1.persist(customer);
            return "redirect:/customers";

//        String error = "Please fill the field in the form!";
//        model.addAttribute("status",error);
//        return "/customer/add";
    }
}
