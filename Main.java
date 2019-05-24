import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n,e,maxLevel,startPoint=1; //����,������ ����
	static int adjMatrix[][],bcost[][],bd[][];
	static int level[];
	static boolean visited[];

	public static void bfs(int startPoint) {
		int temp=0,stage = 1;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(startPoint);
		visited[startPoint] = true; //��߳��� �湮 �����Ƿ� ��
		level[startPoint] = stage++;
		
		while(!queue.isEmpty()) {//ť�� �������� �ݺ�
			int qSize = queue.size();
			for(int i=1;i<=qSize;i++) {
				temp = queue.poll(); //ť���� ����
				for(int j=1;j<=n;j++) {
					if(adjMatrix[temp][j] >=1 && visited[j] == false) { //������ �ְ� �湮���� ���� ����� ��츸 �湮
						queue.offer(j); //ť�� �־���
						level[j] = stage; //���� �迭�� ������ �־��ش�
						visited[j] = true; //�湮�����Ƿ�  true�� �ٲ���
					}
				}
			}
			maxLevel = stage++;
		}
		maxLevel--;
	}
	public static void backward() {
		bcost[startPoint][1] = 0;//��� ���������� cost�� 0
		int preNode=0;
		//ù��° preNode�� ��������

		for(int i=2;i<=maxLevel;i++)
			for(int j=1;j<=n;j++)
				if(level[j] == i) {
					int min = 9999; //�ּҰ��� 9999�� ����
					for(int k=1;k<=n;k++)
						if(bcost[i-1][k] + adjMatrix[k][j] < min && adjMatrix[k][j]>0) { //������ ���� 0���� ũ�� ���������� cost+���� ���� min���� ������ ����
							min = bcost[i-1][k] + adjMatrix[k][j];
							preNode = k; 
						}
					bcost[i][j] = min; //bcost�� �� ����
					bd[i][j] = preNode; //���� ����ȣ �־���
				}
	}
	public static void main(String args[]) {
		int start,end,val,temp;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); //������ ���� �Է�
		e = sc.nextInt(); //������ ���� �Է�
		int lastPoint = n;//������ ��� ��ȣ
		
		adjMatrix = new int[n+1][n+1]; //������� �����Ҵ�
		level = new int[n+1];//���� �����ϴ� �迭 �����Ҵ�
		
		visited = new boolean[n+1];
		
		for(int i=0;i<n+1;i++)//������� �ʱ�ȭ
			for(int j=0;j<n+1;j++) adjMatrix[i][j] = 0;

		for(int i=0;i<e;i++) {//������Ŀ� �� �־���
			start = sc.nextInt();
			end = sc.nextInt();
			val = sc.nextInt();
			adjMatrix[start][end] = val;
		}
		bfs(startPoint);
		
		bcost = new int [maxLevel+1][n+1] ;  // ���������� �ش� �������� ���µ� �ʿ��� cost
        bd = new int [maxLevel+1][n+1] ; //���� ��� ����
		backward();
		
		System.out.println("==================AdjMatrix==========================");
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++)
				System.out.printf("%3d ",adjMatrix[i][j]);
			System.out.println("");
		}
		System.out.println("==================BCOST==========================");
		for(int i=1;i<=maxLevel;i++)
			for(int j=1;j<=n;j++) 
				if(bcost[i][j] > 0) System.out.println("bcost("+i+","+j+") = "+ bcost[i][j]);	
		
		System.out.println("==================B**D==========================");
		for(int i=1;i<=maxLevel;i++)
			for(int j=1;j<=n;j++)
				if(bd[i][j] > 0) System.out.println("bd("+i+","+j+") = "+ bd[i][j]);
		
		System.out.println("==================PATH==========================");
		System.out.print(lastPoint);
		temp = lastPoint;
		for(int i=maxLevel;i>1;i--) {
			System.out.print("<="+bd[i][temp]);
			temp = bd[i][temp];
		}
	}
}
