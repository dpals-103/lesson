package game;

import java.util.Calendar;

public class 캐릭터 {
	int 번호;
	String 이름;
	String 직업;
	int 태어난_해;
	int 나이;
	int 힘;
	int 지능;
	int 민첩;
	
	무기 a무기; 
	
	void 자기소개() {
		System.out.printf("==자기소개시작==\n");
		System.out.printf("번호 : %d\n",번호);
		System.out.printf("이름 : %s\n",이름);
		System.out.printf("직업 : %s\n",직업);
		System.out.printf("나이 : %d\n", get나이());
		System.out.printf("힘 : %d\n",힘);
		System.out.printf("지능 : %d\n",지능);
		System.out.printf("민첩 : %d\n",민첩);
		
	}
	
	void 공격() {
		System.out.println("==공격시작==");
		a무기.작동(직업,이름,힘,민첩); 
		System.out.println("==공격끝==");		
	}
	
	int get나이(){
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year - 태어난_해; 
	}
}

class 의적 extends 캐릭터{
	의적(){
		직업 = "의적";
		a무기 = new 검();
	}
}
class 도적 extends 캐릭터{
	도적(){
		직업 = "도적";
		a무기 = new 도끼();
	}
}
class 상인 extends 캐릭터{
	상인(){
		직업 = "상인";
		a무기 = new 지팡이();
	}
}


