package Huffman;

public class NodeClass {
	// ��带 ����
	public int freq; // �󵵼�
	public char alphabet; // ���ĺ� ���� �ϳ�
	public NodeClass leftNode; //���� �ڽĳ��
	public NodeClass rightNode; // ������ �ڽĳ��

	public NodeClass(int freq, char alphabet)
	{
		this.freq = freq;
		this.alphabet = alphabet;
		leftNode = rightNode = null;
	}	
}
