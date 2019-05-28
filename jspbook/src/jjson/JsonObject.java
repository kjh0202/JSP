package jjson;

import org.json.simple.JSONObject;

public class JsonObject {
	public static void main(String[] args) {
		// JSON DATA 는 가장 아래 단계에서 부터 만들어 나간다.
		System.out.println("-------------------------------------");
		System.out.println("JSON String 생성");
		JSONObject jObj = new JSONObject();
		jObj.put("name", "홍길동");
		jObj.put("age", 40);
		
		System.out.println(jObj.toString());
		
		System.out.println("-------------------------------------");
		System.out.println("JSON String 파싱");
		try {
			int age = (Integer) jObj.get("age");
			String name = (String) jObj.get("name");
			System.out.println("name = " + name);
			System.out.println("age = " + age);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------");
	}
}
