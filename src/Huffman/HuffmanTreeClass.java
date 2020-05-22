//������ Ʈ���� �����ϱ� ���� Ŭ����
package Huffman;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;




public class HuffmanTreeClass{
	// ���ĺ��� �󵵼��� ������ �ؽø�
	public static HashMap<Character, Integer> Freq = new HashMap<Character, Integer>();
	// ������ ������ Ʈ��
	public static NodeClass HuffmanTree = null;
	// �о�� ������ ���ڸ� ������ �迭 ����
	static char [] Input = new char[100];
	// �󵵼� ������ �迭 ����
	static int [] freq = new int[27];
	// ���ĺ��� �����Ͽ� ���ϱ� ���� �迭 ����
	static char[] arr = new char[27];
	// �󵵼� ���ϱ�
	public static void FindFreq() {
		// ���ĺ� ������� ����
		for (int i = 0; i < arr.length-1; i++) {
			arr[i] = (char)('a'+ i );
		}
		// ������ ���Ҵ� �����̽�
		arr[26] = ' '; 		
		// ���ĺ� �迭�� ���Ͽ� �󵵼� ã��
		for (int i= 0 ; i < Input.length; i++) {
			for( int j=0; j <arr.length ; j++) {
				if(Input[i] == arr[j]) {
					freq[j] +=1;
					Freq.put(arr[j], freq[j] );
				}
			}
		}
		// �󵵼� print
		System.out.println("�� ������ �󵵼���  ");
		for (int i = 0; i< freq.length-1; i++) {
			if(freq[i] != 0 ) {
				System.out.print(arr[i] + "   :   ");
				System.out.println(freq[i]);	
			}
			
		}
		System.out.print("Space : " + freq[26]);
		System.out.println(" �Դϴ�.");
	}
	
	
	// ���� ���� 
	public static void makeHeap() {
		// ���� ����
		MinHeapClass HT = new MinHeapClass();
		// �ּ� ������ �� �� �� ���ĺ� ����
		for(char key : Freq.keySet())
			HT.insert(new NodeClass(Freq.get(key),key));
		HT.printTree();
	}

	
	// ������ Ʈ�� ����
	public static void makeHuffmanTree()
	{
		MinHeapClass Tree = new MinHeapClass(); //��ü����

		// �ּ� ������ ���ĺ��� �󵵼� ����
		for(char key : Freq.keySet())
			Tree.insert(new NodeClass(Freq.get(key), key));

		for(int i=0; i<Freq.size()-1; i++)
		{
			// �ּ� ��� 2�� ����
			NodeClass leftChild = Tree.MinNode();
			NodeClass rightChild = Tree.MinNode();

			// ���ο� �θ� ��� ����.( Ű ���� �ڽ� ��� Ű ��(�󵵼�)�� ��)
			HuffmanTree = new NodeClass(leftChild.freq+rightChild.freq, '.');

			HuffmanTree.leftNode = leftChild;
			HuffmanTree.rightNode = rightChild;

			
			Tree.insert(HuffmanTree);
		}
		
	}
	
	
	// �ڵ� �ο�
	public static void HuffmanCode(NodeClass Root, int []trace, int top)
	{
		
		// left�� Ž���� ��� 0�� ����ϰ�, right�� Ž���� ��� 1�� ���
		// �ܸ� ��带 ������ ���ĺ� ���
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

		if(Root.leftNode == null && Root.rightNode == null) // �ܸ� ��带 ������ ���
		{
			System.out.print(Root.alphabet + " �� �� ��: " + Root.freq +" : ");
			printArr(trace, top);
		}
	}
	
	// �ڵ� ���
	public static void printArr(int[] Arr ,int top)
	{
		for(int i=0;i<top;i++)
			System.out.print(Arr[i]);
		System.out.println("");
	}

	
	
	// main �޼ҵ�
	public static void main(String args[]) {
		
		// Test ���� �о�ͼ� Input �迭�� ����
		try {
			System.out.println("�о�� ������ �����ϼ��� (1or2or3�Է�)  : ");
			Scanner sc= new Scanner(System.in);
			int number = sc.nextInt();
			File file = new File("./txtFile/Test"+ number +".txt");
			FileReader file_reader = new FileReader(file);
			int cur =0;
			int i=0;
			System.out.print("�о�� ������ ������  : " + '"');
			
			// ���� ������ ���� �ݺ�
			while((cur = file_reader.read()) != -1) {
				System.out.print((char)cur);
				// �迭�� ����
				Input[i] = (char)cur;
				i++;
			}			
			System.out.println('"'+ " �Դϴ�.");
		file_reader.close();
		}// ���� �߻���
		catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		catch(IOException e) {
			e.getStackTrace();
		}
		;
		// �󵵼� ����
		FindFreq();
		
		makeHeap();
		makeHuffmanTree();
		
		int []Arr = new int[Freq.size()-1];
		System.out.println("�� ���ڿ� �Ҵ�� �ڵ�� : ");
		HuffmanCode(HuffmanTree, Arr, 0);
		
	}	
}

