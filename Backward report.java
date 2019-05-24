import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n,e,maxLevel,startPoint=1; //정점,간선의 개수
	static int adjMatrix[][],bcost[][],bd[][];
	static int level[];
	static boolean visited[];

	public static void bfs(int startPoint) {
		int temp=0,stage = 1;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(startPoint);
		visited[startPoint] = true; //출발노드는 방문 했으므로 참
		level[startPoint] = stage++;
		
		while(!queue.isEmpty()) {//큐가 빌때까지 반복
			int qSize = queue.size();
			for(int i=1;i<=qSize;i++) {
				temp = queue.poll(); //큐에서 빼줌
				for(int j=1;j<=n;j++) {
					if(adjMatrix[temp][j] >=1 && visited[j] == false) { //간선이 있고 방문하지 않은 노드의 경우만 방문
						queue.offer(j); //큐에 넣어줌
						level[j] = stage; //레벨 배열에 레벨값 넣어준다
						visited[j] = true; //방문했으므로  true로 바꿔줌
					}
				}
			}
			maxLevel = stage++;
		}
		maxLevel--;
	}
	public static void backward() {
		bcost[startPoint][1] = 0;//출발 정점까지의 cost는 0
		int preNode=0;
		//첫번째 preNode는 시작정점

		for(int i=2;i<=maxLevel;i++)
			for(int j=1;j<=n;j++)
				if(level[j] == i) {
					int min = 9999; //최소값을 9999로 저장
					for(int k=1;k<=n;k++)
						if(bcost[i-1][k] + adjMatrix[k][j] < min && adjMatrix[k][j]>0) { //간선의 값이 0보다 크고 이전까지의 cost+간선 값이 min보다 작으면 수행
							min = bcost[i-1][k] + adjMatrix[k][j];
							preNode = k; 
						}
					bcost[i][j] = min; //bcost에 값 삽입
					bd[i][j] = preNode; //이전 노드번호 넣어줌
				}
	}
	public static void main(String args[]) {
		int start,end,val,temp;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); //정점의 개수 입력
		e = sc.nextInt(); //간선의 개수 입력
		int lastPoint = n;//마지막 노드 번호
		
		adjMatrix = new int[n+1][n+1]; //인접행렬 동적할당
		level = new int[n+1];//레벨 저장하는 배열 동적할당
		
		visited = new boolean[n+1];
		
		for(int i=0;i<n+1;i++)//인접행렬 초기화
			for(int j=0;j<n+1;j++) adjMatrix[i][j] = 0;

		for(int i=0;i<e;i++) {//인접행렬에 값 넣어줌
			start = sc.nextInt();
			end = sc.nextInt();
			val = sc.nextInt();
			adjMatrix[start][end] = val;
		}
		bfs(startPoint);
		
		bcost = new int [maxLevel+1][n+1] ;  // 레벨에서의 해당 정점까지 가는데 필요한 cost
        bd = new int [maxLevel+1][n+1] ; //이전 노드 저장
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
