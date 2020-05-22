package Huffman;

public class NodeClass {
	// 노드를 생성
	public int freq; // 빈도수
	public char alphabet; // 알파벳 문자 하나
	public NodeClass leftNode; //왼쪽 자식노드
	public NodeClass rightNode; // 오른쪽 자식노드

	public NodeClass(int freq, char alphabet)
	{
		this.freq = freq;
		this.alphabet = alphabet;
		leftNode = rightNode = null;
	}	
}
