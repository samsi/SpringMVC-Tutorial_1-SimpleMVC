package me.samuelsimon.springmvc_tutorial.simplemvc;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {
	// Add SQL session member
	@Autowired
	private SqlSession m_SqlSession;

	// RequestMapping value accepts a list, use that if a controller needs multiple request map
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String list(Model p_Model) {
		// Call MyBatis mapper to get data from DB
		List<MyData> t_QueryResult = m_SqlSession.selectList("data-mapper.selectAllPeople");

		// Add retrieved data to our model
		p_Model.addAttribute("personData", t_QueryResult);

		return "list";
	}

	@RequestMapping(value = {"/form", "/edit"}, method = RequestMethod.GET)
	public String form(@RequestParam(value="id", required=false) Integer p_PersonId, Model p_Model) {		
		// Check whether this is an add or edit request 
		MyData t_PersonData;
		if (p_PersonId != null) {
			t_PersonData = m_SqlSession.selectOne("data-mapper.selectOnePerson", p_PersonId);
			p_Model.addAttribute("linkSubmit", "edit");
		}
		else {
			t_PersonData = new MyData();
			p_Model.addAttribute("linkSubmit", "add");
		}

		p_Model.addAttribute("personData", t_PersonData);
		return "form";
	}

	@RequestMapping(value = "/add", method=RequestMethod.POST)
	// Retrieve POST data by adding @ModelAttribute parameter
	public String add(@ModelAttribute("SpringWeb") MyData p_PersonData, Model p_Model) {
		m_SqlSession.insert("data-mapper.insertPerson", p_PersonData);

		return "redirect:/";
	}

	@RequestMapping(value = "/edit", method=RequestMethod.POST)		
	public String edit(@ModelAttribute("SpringWeb") MyData p_PersonData, Model p_Model) {
		m_SqlSession.update("data-mapper.updatePerson", p_PersonData);

		return "redirect:/";
	}

	@RequestMapping(value = "/delete/{personId}")
	public String delete(@PathVariable("personId") Integer p_PersonId) {
		m_SqlSession.delete("data-mapper.deletePerson", p_PersonId);

		return "redirect:/";
	}
}