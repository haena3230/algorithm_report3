//허프만 트리를 구현하기 위한 클래스
package Huffman;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;




public class HuffmanTreeClass{
	// 알파벳과 빈도수를 저장할 해시맵
	public static HashMap<Character, Integer> Freq = new HashMap<Character, Integer>();
	// 생성할 허프만 트리
	public static NodeClass HuffmanTree = null;
	// 읽어온 파일의 문자를 저장할 배열 변수
	static char [] Input = new char[100];
	// 빈도수 저장할 배열 변수
	static int [] freq = new int[27];
	// 알파벳를 저장하여 비교하기 위한 배열 변수
	static char[] arr = new char[27];
	// 빈도수 구하기
	public static void FindFreq() {
		// 알파벳 순서대로 저장
		for (int i = 0; i < arr.length-1; i++) {
			arr[i] = (char)('a'+ i );
		}
		// 마지막 원소는 스페이스
		arr[26] = ' '; 		
		// 알파벳 배열과 비교하여 빈도수 찾기
		for (int i= 0 ; i < Input.length; i++) {
			for( int j=0; j <arr.length ; j++) {
				if(Input[i] == arr[j]) {
					freq[j] +=1;
					Freq.put(arr[j], freq[j] );
				}
			}
		}
		// 빈도수 print
		System.out.println("각 문자의 빈도수는  ");
		for (int i = 0; i< freq.length-1; i++) {
			if(freq[i] != 0 ) {
				System.out.print(arr[i] + "   :   ");
				System.out.println(freq[i]);	
			}
			
		}
		System.out.print("Space : " + freq[26]);
		System.out.println(" 입니다.");
	}
	
	
	// 히프 생성 
	public static void makeHeap() {
		// 히프 생성
		MinHeapClass HT = new MinHeapClass();
		// 최소 히프에 빈도 수 및 알파벳 저장
		for(char key : Freq.keySet())
			HT.insert(new NodeClass(Freq.get(key),key));
		HT.printTree();
	}

	
	// 허프만 트리 생성
	public static void makeHuffmanTree()
	{
		MinHeapClass Tree = new MinHeapClass(); //객체생성

		// 최소 히프에 알파벳과 빈도수 저장
		for(char key : Freq.keySet())
			Tree.insert(new NodeClass(Freq.get(key), key));

		for(int i=0; i<Freq.size()-1; i++)
		{
			// 최소 노드 2개 추출
			NodeClass leftChild = Tree.MinNode();
			NodeClass rightChild = Tree.MinNode();

			// 새로운 부모 노드 생성.( 키 값은 자식 노드 키 값(빈도수)의 합)
			HuffmanTree = new NodeClass(leftChild.freq+rightChild.freq, '.');

			HuffmanTree.leftNode = leftChild;
			HuffmanTree.rightNode = rightChild;

			
			Tree.insert(HuffmanTree);
		}
		
	}
	
	
	// 코드 부여
	public static void HuffmanCode(NodeClass Root, int []trace, int top)
	{
		
		// left를 탐색할 경우 0을 출력하고, right를 탐색할 경우 1을 출력
		// 단말 노드를 만나면 알파벳 출력
		if(Root.leftNode != null)
		{
			trace[top] = 0;
			HuffmanCode(Root.leftNode, trace, top+1);
		}
		if(Root.rightNode != null)
		{
			trace[top] = 1;
			HuffmanCode(Root.rightNode, trace, top+1);
		}

		if(Root.leftNode == null && Root.rightNode == null) // 단말 노드를 만났을 경우
		{
			System.out.print(Root.alphabet + " 의 빈도 수: " + Root.freq +" : ");
			printArr(trace, top);
		}
	}
	
	// 코드 출력
	public static void printArr(int[] Arr ,int top)
	{
		for(int i=0;i<top;i++)
			System.out.print(Arr[i]);
		System.out.println("");
	}

	
	
	// main 메소드
	public static void main(String args[]) {
		
		// Test 파일 읽어와서 Input 배열에 저장
		try {
			System.out.println("읽어올 파일을 선택하세요 (1or2or3입력)  : ");
			Scanner sc= new Scanner(System.in);
			int number = sc.nextInt();
			File file = new File("./txtFile/Test"+ number +".txt");
			FileReader file_reader = new FileReader(file);
			int cur =0;
			int i=0;
			System.out.print("읽어온 파일의 내용은  : " + '"');
			
			// 문자 없을때 까지 반복
			while((cur = file_reader.read()) != -1) {
				System.out.print((char)cur);
				// 배열에 저장
				Input[i] = (char)cur;
				i++;
			}			
			System.out.println('"'+ " 입니다.");
		file_reader.close();
		}// 에러 발생시
		catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		catch(IOException e) {
			e.getStackTrace();
		}
		;
		// 빈도수 도출
		FindFreq();
		
		makeHeap();
		makeHuffmanTree();
		
		int []Arr = new int[Freq.size()-1];
		System.out.println("각 문자에 할당된 코드는 : ");
		HuffmanCode(HuffmanTree, Arr, 0);
		
	}	
}

