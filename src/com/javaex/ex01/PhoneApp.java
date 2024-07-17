package com.javaex.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) throws IOException {

		List<Phone> phoneList = new ArrayList<Phone>();

		// 읽기 스트림
		FileInputStream in = new FileInputStream("/Users/jimin/Desktop/javastudy/PhoneDB_원본.txt");
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);



		// 한줄씩 읽기
		while (true) {
			String data = br.readLine();
			// 없으면 스땁
			if (data == null) {
				break;
			}

			// 나누고 배열로 넣기
			String[] str = data.split(",");
			String name = str[0];
			String hp = str[1];
			String company = str[2];

			Phone phone = new Phone(name, hp, company);
			phoneList.add(phone);

		}
		// 시작화면
		Scanner sc = new Scanner(System.in);
		System.out.println("******************************");
		System.out.println("*      전화번호 관리 프로그램      *");
		System.out.println("******************************");
		System.out.println("");

		// 번호 받기 반복문
		while (true) {
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("------------------------------");
			System.out.print(">메뉴번호: ");
			int num = sc.nextInt();
			if (num == 1) {
				// 리스트 출력
				System.out.println("<1.리스트>");
				for (int i = 0; i < phoneList.size(); i++) {
					System.out.print(i + 1);
					phoneList.get(i).list();
					// 시작화면으로 복귀
				}
			} else if (num > 5) {
				System.out.println("[다시 입력해 주세요.]");
			}
			// 등록
			if (num == 2) {
				System.out.println("<2.등록>");
				sc.nextLine();
				System.out.print(">이름: ");
				String name = sc.nextLine();

				System.out.print(">휴대전화: ");
				String hp = sc.nextLine();

				System.out.print(">회사전화: ");
				String company = sc.nextLine();

				Phone phone = new Phone(name, hp, company);
				phoneList.add(phone);

			}
			// 삭제
			if (num == 3) {
				System.out.println("<3.삭제>");
				System.out.print(">번호: ");
				int a = sc.nextInt() - 1;
				phoneList.remove(a);
				System.out.println("[삭제 되었습니다.]");

			}
			// 검색
			if (num == 4) {
				sc.nextLine();
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String str = sc.nextLine();

				for (int i = 0; i < phoneList.size(); i++) {
					Phone a = phoneList.get(i);
					if (a.getName().contains(str) == true) {
						System.out.print(i + 1);
						phoneList.get(i).list();
					}
				}
			}
			
			
			if (num == 5) {
				System.out.println("******************************");
				System.out.println("*          감사합니다.          *");
				System.out.println("******************************");
				break;
			}

		} // while문 끝
		FileOutputStream out = new FileOutputStream("/Users/jimin/Desktop/javastudy/PhoneDB_원본.txt");
		OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		for(int i = 0;i<phoneList.size();i++) {
		// 쓰기 스트림
		bw.write(phoneList.get(i).getName() + "," + phoneList.get(i).getHp() + "," + phoneList.get(i).getCompany());
		bw.newLine();
		bw.flush();
		}


	}

}
