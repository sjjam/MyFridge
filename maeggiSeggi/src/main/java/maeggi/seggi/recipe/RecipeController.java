package maeggi.seggi.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecipeController {
	
	@Autowired
	RecipeService service;
	@RequestMapping("/recipe/main.do")
	public String recipe() {
		return "main";
	}

	@RequestMapping("/recipe/levelRecipe.do")
	public String level() {
		return "level";
	}
	
	@RequestMapping("/recipe/detailRecipe.do")
	public ModelAndView detail(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		System.out.println("====================================================================================");
		String recipe_id = req.getParameter("id");
		System.out.println(recipe_id);
		ArrayList<HashMap<String, String>> detail = (ArrayList<HashMap<String, String>>)service.detail(recipe_id);
		mav.addObject("detail",detail);
		mav.setViewName("detail");
		return mav;
	}
	@RequestMapping("/recipe/addPlanner.do")
	public String add() {
		return "add";
	}
	//검색어로 찾기
	@RequestMapping(value="/recipe/ajax_SearchName.do", method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public @ResponseBody ArrayList<RecipeVO> readbyName(String name){
		ArrayList<RecipeVO> recipeSearch =(ArrayList<RecipeVO>)service.readbyName(name);
		return recipeSearch;
	}
	//카테고리로 찾기 
		@RequestMapping(value="/recipe/ajax_searchRecipe.do",method=RequestMethod.GET,produces="application/json;charset=utf-8")	
		public @ResponseBody ArrayList<RecipeVO> categoryList(String category) {
			ArrayList<RecipeVO> recipeList = (ArrayList<RecipeVO>)service.recipeList(category);
			System.out.println("ajax 통신"+recipeList.size());
			return recipeList;
		}
	//전체 list 출력
	@RequestMapping("/recipe/searchRecipe.do")
	public ModelAndView recipeList() {
		ModelAndView mav = new ModelAndView();
		List<RecipeVO> list = service.listall();
		mav.addObject("list",list);
		mav.setViewName("search");
		return mav;
	}
/*	삭제 
	public String delete(RecipeVO recipe) {
		return "redirect:/recipe/list.do";
	}*/
	// 레시피을 작성하기 위한 뷰를 response할 메소드
	@RequestMapping(value="/recipe/recipe_write.do",method=RequestMethod.GET)
	public String write() {
		return "recipe_write";
	}
	//입력한 레시피 실제 db에 저장하는 메소드
	@RequestMapping(value="/recipe/recipe_write.do",method=RequestMethod.POST)
	public String insert(RecipeVO recipe) {
		System.out.println("***"+recipe);
		service.insert(recipe);
		return "redirect:/recipe/searchRecipe.do";
	}
	
}
