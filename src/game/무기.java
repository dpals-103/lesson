package game;

public class 무기 {
	
	String 무기이름; 
	int 공격력;

	void 작동(String 직업, String 이름, int 힘, int 민첩) {
		System.out.printf("직업이 %s인 %s(이)가 %s(으)로 공격합니다\n", 직업, 이름, 무기이름);
		System.out.printf("-무기공격력 : %d\n", 공격력);
		System.out.printf("-데미지 : %d\n", 힘*민첩*공격력);
		
		
	}
}


class 검 extends 무기 {
	검(){
		무기이름 = "검";
		공격력 = 10;
	}
}

class 도끼 extends 무기 {
	도끼(){
		무기이름 = "도끼";
		공격력 = 15;
	}
}

class 지팡이 extends 무기 {
	지팡이(){
		무기이름 = "지팡이"; 
		공격력 = 2;
	}
}