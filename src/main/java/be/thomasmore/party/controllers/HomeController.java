package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        int myCalculatedValue = 34 * 62;
        model.addAttribute("myCalculatedValue", myCalculatedValue);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        int myCalculatedValue = 52 + 44 * 210;
        model.addAttribute("myCalculatedValue", myCalculatedValue);
        return "about";
    }
    @GetMapping("/pay")
    public String pay(Model model){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy E");
        String isWeekend = String.valueOf(today.getDayOfWeek());
        if (String.valueOf(today.getDayOfWeek()).equals("SATURDAY") || String.valueOf(today.getDayOfWeek()).equals("SUNDAY")) {
            isWeekend = "Prettig weekend, je hebt het verdiend";
        }
        else{
            isWeekend = "Voor je het weet is het weekend!";
        }
        model.addAttribute("test",isWeekend);
        LocalDate uitersteDatum = today.plusDays(30);

        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("hh:mm:ss a z");

        String todayTime = formatter2.format(now);
        model.addAttribute("todaytime", todayTime);

        String todayString = formatter.format(today);
        String uitersteDatumString = formatter.format(uitersteDatum);

        model.addAttribute("today", todayString);
        model.addAttribute("uitersteDatum", uitersteDatumString);

        return "pay";
    }
}
