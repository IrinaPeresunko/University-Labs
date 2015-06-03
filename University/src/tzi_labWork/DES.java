package tzi_labWork;

import java.util.Arrays;

public class DES {

	private static char[] mass_for_generate = new char[10];//4 step - array for 2-bit key
	private static char[] key1 = new char[8];
	private static char[] key2 = new char[8];
	
	public static void main(String[] args) {
		
			
				String key = "0110010000";
				key1 = first_key_generate(key);
				key2 = second_key_generate(key1);
				encrypt_message("10000000");
				
			}
	
	public static char[] first_key_generate(String key){
		char[] key0 = key.toCharArray();
		char[] left_part = new char[5];
		char[] right_part = new char[5];
		
		for(int i = 0; i < 5; i++){
			left_part[i] = key0[i];
			right_part[i] = key0[i+5];
		}
		
		char last_left = left_part[0];//
		char last_right = right_part[0];//    remember value for moving
		
		
		for(int i = 0; i < 4; i++){
			left_part[i] = left_part[i+1];
			right_part[i] = right_part[i+1];
		}
		
		left_part[4] = last_left;
		right_part[4] = last_right;
		
		
		
		for(int i = 0; i < 5; i++){
			mass_for_generate[i] = left_part[i];
			mass_for_generate[i+5] = right_part[i];
		}
		key1[0] = left_part[4];//on the 1st position set 5th bit
		for(int i = 1; i < 4; i++) key1[i] = right_part[i-1];//write 6,7,8 for replacing
		
		for(int i = 0; i < 4; i++) key1[i+4] = left_part[i];//replacing
		return key1;
	}
	
	public static char[] second_key_generate(char[] key){
		
		char[] left_part = new char[5];
		char[] right_part = new char[5];
		for(int i = 0; i < 5; i++){
			left_part[i] = mass_for_generate[i];
			right_part[i] = mass_for_generate[i+5];
		}
		
		char last_left = left_part[1];//
		char last_right = right_part[1];//    remember value for moving(replace on two bit)
		char penult_left = left_part[0];//penult
		char penult_right = right_part[0];//penult
		
		for(int t  = 0; t < 2; t++){
			for(int i = 0; i < 4; i++){
				left_part[i] = left_part[i+1];
				right_part[i] = right_part[i+1];
			}
		}
		
		left_part[3] = penult_left;
		left_part[4] = last_left;
		right_part[3] = penult_right;
		right_part[4] = last_right;
		
		key2[0] = left_part[4];
		
		for(int i = 1; i < 4; i++) key2[i] = right_part[i-1];//write 6,7,8 for replacing
		
		for(int i = 0; i < 4; i++) key2[i+4] = left_part[i];//replacing
		return key2;
	}

	public static void encrypt_message(String msg){
		char[] message = msg.toCharArray();
		message_IP(message, 4,0,3,2,5,7,6,1);//replacing for message
		
		char[] left_part = new char[4];
		char[] right_part = new char[4];
		char[] right_clear = new char[4];//array that contain right part without changes
		
		for(int i = 0; i < 4; i++){//split on the two part
			left_part[i] = message[i];
			right_part[i] = message[i+4];
			right_clear[i] = message[i+4];
		}
		
		char[] wide_right = new char[8];//extending array
		
		EP(right_part,wide_right);//extending right part
		
		char[] for_S_block = sum_module_2(wide_right, key1);//array for splitting on S0 and S1
		
		int s0 = S0(for_S_block[2], for_S_block[0], for_S_block[1], for_S_block[3]);// getting value from S0
		int s1 = S1(for_S_block[6], for_S_block[4], for_S_block[5], for_S_block[7]);//getting value from S0
		
		
		char[] for_res = toChar(s0,s1);//array for adding module two with left part of message
		
		for_res = sum_module_2(for_res, left_part);//sum with left part that can't be changed
		
		char [] Fk1 = new char[8];//array that contain result of accomplish the first block
		
		//join the result of block and right part that can't be changed
		for(int i = 0; i < 4; i++){
			Fk1[i] = for_res[i];
			Fk1[i+4] = right_clear[i];
		}
		
		swap(Fk1);
		////////////////////////////////////////////////////////////////////////////////
		message = Fk1;
		
		left_part = new char[4];
		right_part = new char[4];
		right_clear = new char[4];//array that contain rigth part without changes
		
		for(int i = 0; i < 4; i++){//split on two parts
			left_part[i] = message[i];
			right_part[i] = message[i+4];
			right_clear[i] = message[i+4];
		}
		
		wide_right = new char[8];//ðàñøèðåííûé ìàññèâ
		
		EP(right_part,wide_right);//ðàñøèðåíèå ìàññèâà
		
		for_S_block = sum_module_2(wide_right, key2);//ìàññèâ äëÿ ðàçäåëåíèÿ íà S0 è S1
		
		s0 = S0(for_S_block[2], for_S_block[0], for_S_block[1], for_S_block[3]);//ïîëó÷àåì çíà÷åíèå èç S0
		s1 = S1(for_S_block[6], for_S_block[4], for_S_block[5], for_S_block[7]);//ïîëó÷àåì çíà÷åíèå èç S1
		
		
		for_res = toChar(s0,s1);//ìàññèâ äëÿ òîãî ÷òîáû ñëîæèòü ïî ìîäóëþ 2 ñ ëåâîé ÷àñòüþ ñîîáùåíèÿ
		
		for_res = sum_module_2(for_res, left_part);//ñëîæåíèå ñ ëåâîé ÷àñòüþ, êîòîðóþ íå ìåíÿëè
	
		char [] Fk2 = new char[8];//array that contain result of accomplish the second block
		
		//join the result of block and right part that can't be changed
		for(int i = 0; i < 4; i++){
			Fk2[i] = for_res[i];
			Fk2[i+4] = right_clear[i];
		}
		
		char[] result = final_IP(Fk2, 0, 4, 2, 1, 3, 7, 5, 6);
		System.out.println(Arrays.toString(result));
	}
	
	public static char[] sum_module_2(char[] mas1, char[] mas2){
				char[] result = new char[mas1.length];
				for(int i = 0; i < mas1.length; i++){
					if(mas1[i] == '1' && mas2[i] == '1' || mas1[i] == '0' && mas2[i] == '0') result[i] = '0';
					else result[i] = '1';
				}
				//System.out.println(Arrays.toString(result));
				return result;
			}
			
			public static void message_IP(char[] mas, int...params){
				char[] temp = new char[8];
				for(int i = 0; i < 8; i++) temp[i] = mas[i];
				mas[0] = temp[params[0]];
				mas[1] = temp[params[1]];
				mas[2] = temp[params[2]];
				mas[3] = temp[params[3]];
				mas[4] = temp[params[4]];
				mas[5] = temp[params[5]];
				mas[6] = temp[params[6]];
				mas[7] = temp[params[7]];
			}
			
			public static void key_IP(char[] mas, int...params){
				char[] temp = new char[8];
				for(int i = 0; i < 8; i++) temp[i] = mas[i];
				mas[0] = temp[params[0]];
				mas[1] = temp[params[1]];
				mas[2] = temp[params[2]];
				mas[3] = temp[params[3]];
				mas[4] = temp[params[4]];
				mas[5] = temp[params[5]];
				mas[6] = temp[params[6]];
				mas[7] = temp[params[7]];
			}

			public static void  EP(char[] mas1, char[] mas2){//1st array - 4 bit, 2nd - 8 bit for extending
				mas2[0] = mas1[3];
				mas2[1] = mas1[0];
				mas2[2] = mas1[1];
				mas2[3] = mas1[2];
				mas2[4] = mas1[1];
				mas2[5] = mas1[2];
				mas2[6] = mas1[3];
				mas2[7] = mas1[0];
			}

			public static int S0(char...params){
				if(		(params[0] == '0' && params[1] == '0' && params[2] == '0' && params[3] == '0') ||
						(params[0] == '0' && params[1] == '1' && params[2] == '1' && params[3] == '0') ||
						(params[0] == '1' && params[1] == '0' && params[2] == '1' && params[3] == '0') ||
						(params[0] == '1' && params[1] == '1' && params[2] == '0' && params[3] == '1')) {return 1;}
				
				if(		(params[0] == '0' && params[1] == '0' && params[2] == '0' && params[3] == '1') ||
						(params[0] == '0' && params[1] == '1' && params[2] == '1' && params[3] == '1') ||
						(params[0] == '1' && params[1] == '0' && params[2] == '0' && params[3] == '0') ||
						(params[0] == '1' && params[1] == '1' && params[2] == '1' && params[3] == '1')) {return 0;}
				
				if(		(params[0] == '0' && params[1] == '0' && params[2] == '1' && params[3] == '1') ||
						(params[0] == '0' && params[1] == '1' && params[2] == '0' && params[3] == '1') ||
						(params[0] == '1' && params[1] == '0' && params[2] == '0' && params[3] == '1') ||
						(params[0] == '1' && params[1] == '1' && params[2] == '1' && params[3] == '0')) {return 2;}
				else return 3;
			}
			
			public static int S1(char...params){
				if(		(params[0] == '0' && params[1] == '0' && params[2] == '0' && params[3] == '0') ||
						(params[0] == '0' && params[1] == '1' && params[2] == '1' && params[3] == '0') ||
						(params[0] == '1' && params[1] == '0' && params[2] == '1' && params[3] == '0') ||
						(params[0] == '1' && params[1] == '1' && params[2] == '0' && params[3] == '1')) {return 1;}
				
				if(		(params[0] == '0' && params[1] == '0' && params[2] == '0' && params[3] == '1') ||
						(params[0] == '0' && params[1] == '1' && params[2] == '0' && params[3] == '1') ||
						(params[0] == '1' && params[1] == '0' && params[2] == '0' && params[3] == '1') ||
						(params[0] == '1' && params[1] == '1' && params[2] == '1' && params[3] == '0')) {return 0;}
				
				if(		(params[0] == '0' && params[1] == '0' && params[2] == '1' && params[3] == '0') ||
						(params[0] == '0' && params[1] == '1' && params[2] == '0' && params[3] == '0') ||
						(params[0] == '1' && params[1] == '0' && params[2] == '1' && params[3] == '1') ||
						(params[0] == '1' && params[1] == '1' && params[2] == '0' && params[3] == '0')) {return 2;}
				else return 3;
			}

			public static char[] toChar(int a, int b){//method that convert numbers in the binary view
				char[] result = new char[4];
				if(a == 0){
					result[0] = '0';
					result[1] = '0';
				}
				if(a == 1){
					result[0] = '0';
					result[1] = '1';
				}
				if(b == 0){
					result[2] = '0';
					result[3] = '0';
				}
				if(b == 1){
					result[2] = '0';
					result[3] = '1';
				}
				if(a > 1){
					String t1 = Integer.toBinaryString(a);//t1 - contain binary view for number S0
					char[] t2 = t1.toCharArray();//t2 array that split string for separate bits
					result[0] = t2[0];
					result[1] = t2[1];
				}
				if(b > 1){
					String t3 = Integer.toBinaryString(a);//t3 - contain binary view for number S1
					char[] t4 = t3.toCharArray();//t4 array that split string for separate bits
					result[2] = t4[0];
					result[3] = t4[1];
				}
				return result;
			}

			public static void P4(char[] mas, int...params){//method for transposition for 4 bits getting from S0 and S1
				char[] temp = new char[8];
				for(int i = 0; i < 8; i++) temp[i] = mas[i];
				mas[0] = temp[params[0]];
				mas[1] = temp[params[1]];
				mas[2] = temp[params[2]];
				mas[3] = temp[params[3]];
			}

			public static void swap(char[] mas){//method for transposition left and right parts
				char[] temp = new char[mas.length/2];
				for(int i = 0; i < 4; i++) temp[i] = mas[i+4];//remember right part
				for(int i = 0; i < 4; i++){
					mas[i+4] = mas[i];
					mas[i] = temp[i];
				}
			}

			public static char[] final_IP(char[] mas, int...params){
				char[] temp = new char[8];
				char[] temp2 = new char[8];
				for(int i = 0; i < 8; i++) temp[i] = mas[i];
				temp2[0] = temp[params[0]];
				temp2[1] = temp[params[1]];
				temp2[2] = temp[params[2]];
				temp2[3] = temp[params[3]];
				temp2[4] = temp[params[4]];
				temp2[5] = temp[params[5]];
				temp2[6] = temp[params[6]];
				temp2[7] = temp[params[7]];
				return temp2;
			}
			
}
