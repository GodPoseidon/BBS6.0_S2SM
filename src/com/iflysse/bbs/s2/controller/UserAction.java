package com.iflysse.bbs.s2.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iflysse.bbs.po.User;
import com.iflysse.bbs.service.UserService;
import com.iflysse.bbs.vo.PwdEditView;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements SessionAware,
		ServletResponseAware {

	private static final long serialVersionUID = 1L;
	@Resource(name = "userService")
	private UserService userService;
	private HttpServletResponse response;
	
	private Map<String, Object> session;
	private User user;
	private String verify;
	private String remmber;
	private String error;
	private PwdEditView pwdEdit;
	private String targetUrl;
	private boolean parent=false;
	
	
	public String login() {

		if (!verify.equals(session.get("VerifyCode"))) {
			error = "验证码错误";
			return "login_input";
		}

		User currentUser = userService.getUser(user.getEmail(),
				user.getPassword());

		if (currentUser != null) {
			session.put("CurrentUser", currentUser);
			return "login_success";
			// if (remmber != null) {
			// Cookie cookie = new Cookie("bbs_email", user.getEmail());
			// cookie.setMaxAge(1 * 60 * 60 * 24 * 7);
			// response.addCookie(cookie);
			// }
			// session.setAttribute("CurrentUser", user);
			//
			// if (returnUrl.trim().equals("") || returnUrl == null) {
			// mv.setViewName("redirect:/index");
			// return mv;
			// } else {
			// mv.setViewName("redirect:" + returnUrl);
			// }

		} else {
			error = "账号或密码错误";
			return "login_input";
		}
	}

	public String register() {
		userService.add(user);
		return "register_success";
	}

	
//	public void checkEmail() throws IOException{
//		int count=userService.getEmailCount(user.getEmail());
//		boolean result=count>0?true:false;
//		PrintWriter out=response.getWriter();
//		out.print(result);
//		out.close();
//	}
	
	public String pwdEdit() throws IOException{
		User user = (User) session.get("CurrentUser");
		if (!user.getPassword().equals(pwdEdit.getOldPwd())) {
			pwdEdit.setOldMsg("旧密码错误!");
			return "pwdedit_input";
		}
		if (pwdEdit.getOldPwd().equals(pwdEdit.getNewPwd())) {
			pwdEdit.setNewMsg("新密码不能与旧密码一致!");
			return "pwdedit_input";
		}
		if (!pwdEdit.getNewPwd().equals(pwdEdit.getConfirmPwd())) {
			pwdEdit.setConfirmMsg("两次密码不一致!");
			return "pwdedit_input";
		}

		userService.updatePassword(pwdEdit.getNewPwd(), user.getId());
		session.remove("CurrentUser");
	
		this.parent=true;
		this.targetUrl="view_login";
		return "pwdedit_success";
	}
	
	public String cancel(){
		session.remove("CurrentUser");
		return "cancel_success";
	}
	
	public void verify() {
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
		session.put("VerifyCode", sRand);

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getRemmber() {
		return remmber;
	}

	public void setRemmber(String remmber) {
		this.remmber = remmber;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}


	public PwdEditView getPwdEdit() {
		return pwdEdit;
	}

	public void setPwdEdit(PwdEditView pwdEdit) {
		this.pwdEdit = pwdEdit;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

}
