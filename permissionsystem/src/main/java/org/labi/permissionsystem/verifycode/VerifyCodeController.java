package org.labi.permissionsystem.verifycode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author labi
 * @version 1.0.0
 * @ClassName VerifyCodeController.java
 * @Description 前端获取验证码接口
 * @createTime 2021年01月25日 19:25:00
 */
@RestController
public class VerifyCodeController {

    @GetMapping("/verifyCode")
    public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        HttpSession session = req.getSession();
        session.setAttribute("index_code", text);
        VerifyCode.output(image, resp.getOutputStream());
    }
}
