package jjson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonMIx {
	public static void main(String[] args) {
		// JSON DATA 는 가장 아래 단계에서 부터 만들어 나간다.
		System.out.println("-------------------------------------");
		System.out.println("JSON String 생성");
		JSONObject jObj = new JSONObject();
		
		JSONArray jArray = new JSONArray();
		JSONObject school = new JSONObject();
		school.put("subject", "math");
		jArray.add(school);
		jObj.put("name", "홍길동");
		jObj.put("age", 40);
		
		school = new JSONObject();
		school.put("subject", "국어");
		jArray.add(school);
		jObj.put("school", jArray);
		
		System.out.println(jObj.toString());
		
		System.out.println("-------------------------------------");
		System.out.println("JSON String 파싱");
		//key값이 중요하지 순서는 상관 없음
		try {
			//JsonObject
			int age = (Integer) jObj.get("age");
			String name = (String) jObj.get("name");
			System.out.println("name = " + name);
			System.out.println("age = " + age);
			
			JSONArray returnSchool = (JSONArray) jObj.get("school");
			System.out.println("Size = " + returnSchool.size());
			for (int i=0; i<returnSchool.size(); i++) {
				JSONObject returnSubject = (JSONObject) returnSchool.get(i);
				System.out.println("subject : " + returnSubject.get("subject"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------");
	}

}
