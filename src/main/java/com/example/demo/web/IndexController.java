package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 디스패처 서블릿 필터에서 com.example.demo이하의 모든 패키지를 컴포넌트 스캔한다. 
// 이 때 찾은 어노테이션은 (@Controller, @RestController) 이다.

// 1. 동작 방식
// http://localhost:8080/ 라고 요청 받을 때
// 마지막에 '/'가 있으면 class파일을 모두 검사해서 
// @Controller @RestController 으로 매핑되어 있는 class파일을 모두 메모리에 띄운다.

// 그리고 찾은 class에서 GET: '/'주소로 매핑된 메소드가 있는지 리플렉션(찾아낸다)한다.
// '/'주소는 다음과 같은 어노테이션들이 가지고 있다.
// @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
// GET으로 요청을 했으면 GetMapping이 걸려있는 함수만 찾아내서
// '/'로 주소가 걸려있는 함수를 invoke해준다.

// 임의의 힙 공간에 메모리가 띄워지고 해당 메모리를 가리키는 힙을 만들어 준다. 이것을 IOC컨네이터라고한다
// Inversion Of Controll = '제어의 역전' 이라고 하는데, 내가 메모리를 관리하는 것이 아니라
// Spring 한테 제어권을 넘긴다는 뜻이다. Spring이 리플렉션으로 'IndexOfController'를 찾아내고
// 'IndexOfController'에서 특정한 메모리를 띄우는 상황이 '제어의 역전'의 상황이다.

// @RestController => return시에 'MessageConvertor'가 동작
// return값을 PrintWriter에 달아서 응답해준다.
// 응답의 값이 일단 String이라면 바로 응답.
// 응답의 값이 javaObject면 json으로 파싱해서 응답해준다.
// application.properties 또는 application.yml  에서 server.servlet.context-path: 를 설정해줘야 한다

// MessageConvertor(부모) - json관련 클래스 extends

// @Controller => return시에 ViewResolver가 동작
// return 값 앞뒤로 먼가를 붙여서 그 파일을 return 해줍니다. (RequestDispacher)
// 결국 파일을 응답해준다.
// 기본 경로는 src/main/ 이다.
// return에 경로를 적어야 되는데, 절대 경로룰 다 적는 다는 것은 매우 귀찮은 일이다.
// 그래서 템플릿 엔진을 사용해서 파일명만 return에 적어주면 찾아진다.
// .jsp파일을 사용하고 싶으면 jsp 템플릿 엔진 tomcat Embed Jasper 라이브러리를 의존성에 추가해줘야 한다.
// tomcat-Embed-Jasper는 /src/main/webapp/을 기본 경로로 설정 되어 있다.
// application.properties 또는 application.yml 에서 preffix(앞경로)와 suffix(뒷경로)를 설정해줘야 한다.

//@RestController
@Controller
public class IndexController {
	
	@GetMapping("/index")
	public String index() {
		System.out.println("index() 호출됨");
		return "hello";
	}
	
	
}
