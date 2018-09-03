package ch.sbb.esta.jenkinsfiledemo.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Redirect {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String redirect(){
    return "redirect:/index.jsf";
  }

}