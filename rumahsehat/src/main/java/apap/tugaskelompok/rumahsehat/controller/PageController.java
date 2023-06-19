package apap.tugaskelompok.rumahsehat.controller;

import apap.tugaskelompok.rumahsehat.model.AdminModel;
import apap.tugaskelompok.rumahsehat.model.Role;
import apap.tugaskelompok.rumahsehat.model.UserModel;
import apap.tugaskelompok.rumahsehat.service.AdminService;
import apap.tugaskelompok.rumahsehat.service.UserService;
import apap.tugaskelompok.rumahsehat.setting.Setting;
import apap.tugaskelompok.rumahsehat.setting.xml.Attributes;
import apap.tugaskelompok.rumahsehat.setting.xml.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class PageController {

    @Autowired
    private ServerProperties serverProperties;

    private WebClient webClient = WebClient.builder().build();

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        UserModel user = userService.getUserByUsername(request.getRemoteUser());
        model.addAttribute("user", user);
        log.info("access home");
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/validate-ticket")
    public ModelAndView loginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request
    ) {
        log.info("access validate ticket");
        ServiceResponse serviceResponse = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET,
                        ticket,
                        Setting.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponse.class).block();

        assert serviceResponse != null;
        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();

        AdminModel user = adminService.getAdminByUsername(username);

        List<String> admins = new ArrayList<>();
        admins.add("puti.adiva");
        admins.add("salsabila.zahra01");
        admins.add("rheina.elvaretta");
        admins.add("nugraha.aktwovhiday");
        admins.add("muhammad.sendi");

        if (user == null) {
            user = new AdminModel();
            user.setEmail(username + "@ui.ac.id");
            user.setNama(attributes.getNama());
            user.setPassword("rumahsehat");
            user.setUsername(username);
            user.setIsSso(true);
            user.setRole(Role.ADMIN);
            adminService.addAdmin(user);
        }

        boolean isAdmin = false;
        for (int i=0; i< admins.size(); i++) {
            if (user.getUsername().equals(admins.get(i))) {
                isAdmin = true;
            }
        }

        if (!isAdmin) {
            return new ModelAndView("redirect:/logout-sso");
        } else {
            // Autentikasi
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, "rumahsehat");

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

            return new ModelAndView("redirect:/");
        }
    }

    @GetMapping("/login-sso")
    public ModelAndView loginSSO() {
        log.info("Login via SSO Universitas Indonesia");
        return new ModelAndView("redirect:" + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

    @GetMapping("/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        UserModel user = userService.getUserByUsername(principal.getName());
        if (!user.getIsSso()) {
            return new ModelAndView("redirect:/logout");
        }
        log.info("Log Out via SSO Universitas Indonesia");
        return new ModelAndView("redirect:" + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }
}
