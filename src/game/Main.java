package game;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 캐릭터 수 입력받기
		int n = sc.nextInt();
		캐릭터[] 캐릭터들 = new 캐릭터[n];

		// 캐릭터 신상정보 입력받기
		for (int i = 0; i < n; i++) {

			int 번호 = i + 1;

			String 이름 = sc.next();
			int 태어난_해 = Integer.parseInt(sc.next().replace("년", ""));
			String 직업 = sc.next();

			캐릭터 a캐릭터 = null;

			if (직업.equals("의적")) {
				a캐릭터 = new 의적();
			} else if (직업.equals("도적")) {
				a캐릭터 = new 도적();
			} else if (직업.equals("상인")) {
				a캐릭터 = new 상인();
			}

			a캐릭터.이름 = 이름;
			a캐릭터.번호 = 번호;
			a캐릭터.태어난_해 = 태어난_해;

			캐릭터들[i] = a캐릭터;

		}
		
		//캐릭터 능력치 입력받기 
		for (int i = 0; i < n; i++) {
			String 능력치들 = sc.next(); 
			String[] 능력치 = 능력치들.split((",")); 
			int 힘 = Integer.parseInt(능력치[0]);
			int 지능 = Integer.parseInt(능력치[1]);
			int 민첩 = Integer.parseInt(능력치[2]);
			
			캐릭터들[i].힘 = 힘;
			캐릭터들[i].지능 = 지능;
			캐릭터들[i].민첩 = 민첩;
		}

		sc.close();
		
		// 실행구문
		for (int i = 0; i < n; i++) {
			캐릭터들[i].자기소개();
			캐릭터들[i].공격();
		}
	}
}
