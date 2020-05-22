// 최소 히프를 위한 클래스
package Huffman;

import java.util.ArrayList;
import java.util.Collections;


public class MinHeapClass {
	
	
	// 힙 생성 
	private ArrayList<NodeClass> heap = new ArrayList<NodeClass>(28);
	
	//힙 생성자
	public  MinHeapClass() {
		
		heap.add(null); // index1부터 시작하기 위해 index0 채우기
	}
	
	
	//삽입 
	public void insert(NodeClass n) {
		heap.add(n); // 일단 넣고
		int child = heap.size() -1;  // 자식인덱스
		int parent = child/2;		// 부모인덱스
		// 빈도수 기준으로 heap 구성
		while(parent >= 1 && heap.get(child).freq< heap.get(parent).freq) {
			Collections.swap(heap, child, parent);
			child = parent;
			parent = child/2;
		}
	}
	
	
	// 출력
	public void printTree() {
		System.out.print("빈도수로 만들어진 heap는  ");
		for(NodeClass n : heap)
			if( n != null) {
				System.out.print(n.freq+" ");
			}
		System.out.println(" 입니다.");
	}
	
	
	// 최소 노드 반환
	public NodeClass MinNode() {
		
		NodeClass min = heap.get(1);
		int top = heap.size()-1;
		heap.set(1, heap.get(top));
		heap.remove(top);
		int parent = 1;
		int left = parent*2;
		int right = parent*2 + 1;

		// 왼쪽 자식이 있는 경우에만 균형을 맞춘다.
		while(left <= heap.size()-1)
		{
			int targetPos;
			
			// 오른쪽 자식이 없는 경우
			if(right > heap.size()-1) 			
			{
				// 왼쪽 자식이 더 크면 for 종료
				if(heap.get(left).freq >= heap.get(parent).freq) 
					break;
				targetPos = left;
			}
			// 왼쪽 오른쪽 전부 있는 경우
			else 
			{
				if(heap.get(left).freq >= heap.get(parent).freq &&
						heap.get(right).freq >= heap.get(parent).freq)
						break; // 두 자식 노드가 더 크거나 같으면 while문 종료

				// 더 작은 노드로 swap
				targetPos = (heap.get(left).freq < heap.get(right).freq) ? left : right;
			}

			Collections.swap(heap, targetPos, parent);

			// top-down 순회
			parent = targetPos;
			left = parent*2;
			right = parent*2 + 1;
		}
		return min;
		
	}
		
}
