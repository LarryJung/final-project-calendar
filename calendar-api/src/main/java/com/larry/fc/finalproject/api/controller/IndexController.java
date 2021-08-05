package com.larry.fc.finalproject.api.controller;

import com.larry.fc.finalproject.core.domain.type.RequestReplyType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.larry.fc.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

@Controller
public class IndexController {

    @GetMapping("/test")
    public String test(Model model) {
        final Map<String, Object> props = new HashMap<>();
        props.put("title", "타이틀입니당");
        props.put("calendar", "sample@gmail.com");
        props.put("period", "언제부터 언제까지");
        props.put("attendees", List.of("test@email.com", "test2@email.com", "test3@email.com"));
        props.put("acceptUrl", "http://localhost:8080");
        props.put("rejectUrl", "http://localhost:8080");
        model.addAllAttributes(props);
        return "engagement-email";
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession,
                        @RequestParam(required = false) String redirect) {
        model.addAttribute("isSignIn", httpSession.getAttribute(LOGIN_SESSION_KEY) != null);
        model.addAttribute("redirect", redirect);
        return "index";
    }

    @GetMapping("/events/engagements/{engagementId}")
    public String updateEngagement(@PathVariable Long engagementId,
                                   @RequestParam RequestReplyType type,
                                   Model model,
                                   HttpSession httpSession) {
        model.addAttribute("isSignIn",
                httpSession.getAttribute(LOGIN_SESSION_KEY) != null);
        model.addAttribute("updateType", type.name());
        model.addAttribute("engagementId", engagementId);
        model.addAttribute("path", "/events/engagements/" + engagementId + "?type=" + type.name());
        return "update-event";
    }

}
