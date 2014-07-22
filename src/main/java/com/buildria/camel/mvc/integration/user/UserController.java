package com.buildria.camel.mvc.integration.user;

import com.buildria.camel.mvc.integration.model.User;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/user/")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final String EDIT_INPUT = "userEditInput";

    private static final String EDIT_CONFIRM = "userEditConfirm";

    private static final String EDIT_COMPLETE = "userEditComplete";

    @Autowired
    private ProducerTemplate template;
    
    @ModelAttribute("userForm")
    public UserForm initForm(@RequestParam(required = false, value = "user.id") String id) {
        UserForm userForm = new UserForm();
        User user;
        if (id != null) {
            user = (User) template.sendBodyAndHeader("direct:user_edit", ExchangePattern.InOut,
                    null, "id", id);
        } else {
            user = new User();
        }
        userForm.setUser(user);
        return userForm;
    }

    @RequestMapping(value = "edit/input", method = RequestMethod.GET)
    public ModelAndView input(final UserForm userForm) {
        LOG.info(userForm.toString());
        return new ModelAndView(EDIT_INPUT, new HashMap<String, Object>() {
            {
                put("user", userForm.getUser());
            }
        });
    }

    @RequestMapping(value = "edit/confirm", method = RequestMethod.POST)
    public ModelAndView confirm(@Valid final UserForm userForm, BindingResult result) {
        LOG.info(userForm.toString());
        Map<String, Object> obj = new HashMap<String, Object>() {
            {
                put("user", userForm.getUser());
            }
        };
        if (result.hasErrors()) {
            return new ModelAndView(EDIT_INPUT, obj);
        }

        return new ModelAndView(EDIT_CONFIRM, obj);
    }

    @RequestMapping(value = "edit/complete", method = RequestMethod.POST)
    public ModelAndView complete(@Valid final UserForm userForm, BindingResult result) {
        LOG.info(userForm.toString());

        if (result.hasErrors()) {
            return new ModelAndView(EDIT_INPUT, new HashMap<String, Object>() {
                {
                    put("user", userForm.getUser());
                }
            });
        }

        return new ModelAndView(EDIT_COMPLETE, new HashMap<String, Object>() {
            {
                put("user", userForm.getUser());
            }
        });
    }

    public static class UserForm {

        @Valid
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return "UserForm{" + "user=" + user + '}';
        }

    }
}
