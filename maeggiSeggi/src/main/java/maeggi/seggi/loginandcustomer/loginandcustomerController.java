package maeggi.seggi.loginandcustomer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/*import com.jade.swp.auth.SNSLogin;
import com.jade.swp.auth.SnsValue;*/
@Controller
public class loginandcustomerController {
	@Autowired
	memberService service;
	@RequestMapping(value= "/loginandcustomer/login.do", method = RequestMethod.GET)
	public String loginPage() {
		return "loginandcustomer/login";
	}
	@RequestMapping(value="/loginandcustomer/login.do", method = RequestMethod.POST)
	public ModelAndView login(memberVO loginUserInfo,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		memberVO loginuser = service.login(loginUserInfo);
		System.out.println(loginUserInfo+"=================================================");
		System.out.println(loginuser+"=================================================");
		String viewName="";
		mav.addObject("loginuser", loginuser);
		if(loginuser!=null) {
			//로그인 성공시
			HttpSession ses = request.getSession();
			//2. 세션에 데이터 공유
			ses.setAttribute("loginuser", loginuser);
			viewName = "fridge";
		}else {
			//로그인 실패시 로그인 페이지 보여준다는 의미
			viewName = "loginandcustomer/login";
		}
		mav.setViewName(viewName);//viewName을 변수처리.
		return mav;
	}
	@RequestMapping("/loginandcustomer/logout.do")
	public String logout(HttpSession session) {
		if(session!=null) {
			session.invalidate();
		}
		return "redirect:/recipe/main.do";
	}
/*	@RequestMapping(value = "/kakaoOauth.do")
	public String getKakaoSignIn(ModelMap model,@RequestParam("code") String code, HttpSession session) throws Exception {
	  JsonNode userInfo = kakaoLogin.getKakaoUserInfo(code);
	  System.out.println(userInfo);
	  String id = userInfo.get("id").toString();
	  String email = userInfo.get("kaccount_email").toString();
	  String image = userInfo.get("properties").get("profile_image").toString();
	  String nickname = userInfo.get("properties").get("nickname").toString();
	  System.out.println(id + email);
	  model.addAttribute("k_userInfo", userInfo);
	  model.addAttribute("id", id);
	  model.addAttribute("email", email);
	  model.addAttribute("nickname", nickname);
	  model.addAttribute("image", image);
	  return "main/main.tiles";
	}*/
	//회원가입 view
	@RequestMapping(value= "/loginandcustomer/join.do", method = RequestMethod.GET)
	public String joinView() {
		return "loginandcustomer/join";
	}
	// 회원가입 Post
	@RequestMapping(value = "/loginandcustomer/join.do", method = RequestMethod.POST)
	public String join(memberVO user){
		System.out.println("넘어온 정보"+user);
		service.insert(user);
		return "redirect:/loginandcustomer/login.do";
	}
	@RequestMapping(value = "/loginandcustomer/idCheck.do", method = RequestMethod.GET ,
			produces="application/text;charset=utf-8")
	public @ResponseBody String idCheck(String member_id) {
		boolean state = service.idCheck(member_id);
	    String result ="";
		if(state) {
			result = "사용 불가능한 아이디";
		}else {
			result = "사용 가능한 아이디";
		}
		return result;
	}
	@RequestMapping("/testLogin")
	public String isComplete(HttpSession session) {
		return "naver/naverlogin";
	}
	@RequestMapping("/callback")
	public String navLogin(HttpServletRequest request) throws Exception{
		return "naver/callback";
	}
	@RequestMapping(value="/testLoginjs", method=RequestMethod.GET)
	public String isCompletejs() {
		return "naver/naverloginjs";
	}
	@RequestMapping(value="/callbackjs", method=RequestMethod.GET)
	public String navLoginjs(HttpSession session) throws Exception{
		return "naver/callbackjs";
	}
	@RequestMapping("/loginandcustomer/admin_askdetail.do")
	public String admin_askdetail() {
		return "loginandcustomer/admin_askdetail";
	}
	@RequestMapping("/loginandcustomer/admin_asklist.do")
	public String admin_asklist() {
		return "loginandcustomer/admin_asklist";
	}
	@RequestMapping("/loginandcustomer/noticedetail.do")
	public String noticedetail() {
		return "loginandcustomer/noticedetail";
	}
	@RequestMapping("/loginandcustomer/noticelist.do")
	public String noticelist() {
		return "loginandcustomer/noticelist";
	}
	@RequestMapping("/loginandcustomer/admin_notice_write.do")
	public String admin_notice_write() {
		return "loginandcustomer/admin_notice_write";
	}
}





