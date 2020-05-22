// �ּ� ������ ���� Ŭ����
package Huffman;

import java.util.ArrayList;
import java.util.Collections;


public class MinHeapClass {
	
	
	// �� ���� 
	private ArrayList<NodeClass> heap = new ArrayList<NodeClass>(28);
	
	//�� ������
	public  MinHeapClass() {
		
		heap.add(null); // index1���� �����ϱ� ���� index0 ä���
	}
	
	
	//���� 
	public void insert(NodeClass n) {
		heap.add(n); // �ϴ� �ְ�
		int child = heap.size() -1;  // �ڽ��ε���
		int parent = child/2;		// �θ��ε���
		// �󵵼� �������� heap ����
		while(parent >= 1 && heap.get(child).freq< heap.get(parent).freq) {
			Collections.swap(heap, child, parent);
			child = parent;
			parent = child/2;
		}
	}
	
	
	// ���
	public void printTree() {
		System.out.print("�󵵼��� ������� heap��  ");
		for(NodeClass n : heap)
			if( n != null) {
				System.out.print(n.freq+" ");
			}
		System.out.println(" �Դϴ�.");
	}
	
	
	// �ּ� ��� ��ȯ
	public NodeClass MinNode() {
		
		NodeClass min = heap.get(1);
		int top = heap.size()-1;
		heap.set(1, heap.get(top));
		heap.remove(top);
		int parent = 1;
		int left = parent*2;
		int right = parent*2 + 1;

		// ���� �ڽ��� �ִ� ��쿡�� ������ �����.
		while(left <= heap.size()-1)
		{
			int targetPos;
			
			// ������ �ڽ��� ���� ���
			if(right > heap.size()-1) 			
			{
				// ���� �ڽ��� �� ũ�� for ����
				if(heap.get(left).freq >= heap.get(parent).freq) 
					break;
				targetPos = left;
			}
			// ���� ������ ���� �ִ� ���
			else 
			{
				if(heap.get(left).freq >= heap.get(parent).freq &&
						heap.get(right).freq >= heap.get(parent).freq)
						break; // �� �ڽ� ��尡 �� ũ�ų� ������ while�� ����

				// �� ���� ���� swap
				targetPos = (heap.get(left).freq < heap.get(right).freq) ? left : right;
			}

			Collections.swap(heap, targetPos, parent);

			// top-down ��ȸ
			parent = targetPos;
			left = parent*2;
			right = parent*2 + 1;
		}
		return min;
		
	}
		
}
