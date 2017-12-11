package com.iflysse.bbs.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iflysse.bbs.po.User;
import com.iflysse.bbs.service.UserService;

@Controller
@Scope("prototype")
// 每次请求都单独创建对象
public class UserController {
	@Resource(name="userService")
	private UserService userService;

	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, String returnUrl) {
		ModelAndView mv = new ModelAndView("login");
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals("bbs_email")) {
					request.setAttribute("email", c.getValue());
					break;
				}
			}
		}
		mv.addObject("returnUrl", returnUrl);
		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpSession session,
			HttpServletResponse response, String email, String password,
			String returnUrl, String verify, String remmber) {
		ModelAndView mv = new ModelAndView("login");

		if (!verify.equals(session.getAttribute("VerifyCode"))) {
			String error = "验证码错误";
			mv.addObject("error", error);
			mv.addObject("email", email);
			return mv;
		}

		User user = userService.getUser(email, password);

		if (user != null) {
			if (remmber != null) {
				Cookie cookie = new Cookie("bbs_email", user.getEmail());
				cookie.setMaxAge(1 * 60 * 60 * 24 * 7);
				response.addCookie(cookie);
			}
			session.setAttribute("CurrentUser", user);

			if (returnUrl.trim().equals("") || returnUrl == null) {
				mv.setViewName("redirect:/index");
				return mv;
			} else {
				mv.setViewName("redirect:" + returnUrl);
			}

		} else {
			String error = "账号或密码错误";
			mv.addObject("error", error);
			mv.addObject("email", email);
			return mv;
		}

		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(User user, String confirpwd) {
		userService.add(user);
		return "redirect:/login";
	}

	@RequestMapping(value = "/verifycode")
	public void verifycode(HttpServletResponse response, HttpSession session) {
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 在内存中创建图象
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		// 将认证码存入SESSION
		session.setAttribute("VerifyCode", sRand);

		// 图象生效
		g.dispose();
		// 输出图象到页面
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 生成随机颜色
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping(value = "/usercancel")
	public String usercancel(HttpSession session) {
		session.removeAttribute("CurrentUser");
		return "redirect:/index";
	}

	@RequestMapping(value = "/person/pwdedit", method = RequestMethod.GET)
	public String pwdedit() {
		return "person/pwdedit";
	}

	@RequestMapping(value = "/person/pwdedit", method = RequestMethod.POST)
	public ModelAndView pwdedit(HttpSession session, String oldPassword,
			String newpassword, String confirpassword,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		ModelAndView mv = new ModelAndView("person/pwdedit");

		User user = (User) session.getAttribute("CurrentUser");
		if (!user.getPassword().equals(oldPassword)) {
			mv.addObject("oldmsg", "旧密码错误!");
			return mv;
		}
		if (oldPassword.equals(newpassword)) {
			mv.addObject("newmsg", "新密码不能与旧密码一致!");
			mv.addObject("oldpwd", oldPassword);
			return mv;
		}
		if (!newpassword.equals(confirpassword)) {
			mv.addObject("confirmmsg", "两次密码不一致!");
			mv.addObject("oldpwd", oldPassword);
			return mv;
		}

		userService.updatePassword(newpassword, user.getId());
		session.removeAttribute("CurrentUser");
		String script = "<script>parent.window.location.href='"
				+ request.getContextPath() + "/login'</script>";
		PrintWriter out= response.getWriter();
		out.write(script);
		out.close();
		return mv;
	}
	
	@RequestMapping(value="/checkemail")
	public void checkEmail(String email,HttpServletResponse response) throws IOException{
		int count=userService.getEmailCount(email);
		boolean result=count>0?true:false;
		PrintWriter out=response.getWriter();
		out.print(result);
		out.close();
	}
	
	@RequestMapping(value="/pwdcheck")
	public void pwdCheck(HttpSession session,String oldPassword,HttpServletResponse response) throws IOException{
		User user=(User)session.getAttribute("CurrentUser");
		boolean result=user.getPassword().equals(oldPassword);
		PrintWriter out=response.getWriter();
		out.print(result);
		out.close();	
	}

}
